package org.zotov.hotel_aggregator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zotov.hotel_aggregator.dto.room.RoomRequestDTO;
import org.zotov.hotel_aggregator.dto.room.RoomResponseDTO;
import org.zotov.hotel_aggregator.exceptions.service.ModelConflictException;
import org.zotov.hotel_aggregator.exceptions.service.ModelNotFoundException;
import org.zotov.hotel_aggregator.interfaces.services.ModelMapperService;
import org.zotov.hotel_aggregator.interfaces.services.RoomManagement;
import org.zotov.hotel_aggregator.interfaces.repositories.RoomRepository;
import org.zotov.hotel_aggregator.models.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class RoomService extends CrudService<RoomResponseDTO, RoomRequestDTO, Room, RoomRepository> implements RoomManagement {

    @Autowired
    public RoomService(RoomRepository repository, ModelMapperService<RoomRequestDTO, RoomResponseDTO, Room> modelMapper) {
        super(repository, modelMapper, "Room");
    }

    @Override
    @Transactional
    public RoomResponseDTO save(RoomRequestDTO model) {
        if(!checkRoomExists(model)){
            throw new ModelConflictException("Room already exists");
        }
        return super.save(model);
    }

    @Transactional
    public List<RoomResponseDTO> addRooms(List<RoomRequestDTO> roomList){
        List<Room> roomListToSave = new ArrayList<>();

        roomList.forEach(roomRequestDTO -> {
            if (checkRoomExists(roomRequestDTO)) {
                throw new ModelConflictException(String.format("Room already exists. Hotel id = %d, room number = %d", roomRequestDTO.getHotelId(), roomRequestDTO.getRoomNumber()));
            }
            roomListToSave.add(this.modelMapper.RequestDTOtoModel(roomRequestDTO));
        });

        Iterable<Room> savedRooms = this.repository.saveAll(roomListToSave);
        List<RoomResponseDTO> roomResponseDTOS = new ArrayList<>();
        savedRooms.forEach(room -> roomResponseDTOS.add(this.modelMapper.modelToResponseDTO(room)));

        return roomResponseDTOS;
    }

    public List<RoomResponseDTO> getRoomsForHotel(Long hotelId){
        return this.repository.findRoomsByHotelId(hotelId).stream().map(this.modelMapper::modelToResponseDTO).toList();
    }

    @Override
    @Transactional
    public void update(Long id, RoomRequestDTO model) {
        if(checkRoomOnUpdate(id, model)){
            Room roomToUpdate = this.modelMapper.RequestDTOtoModel(model);
            roomToUpdate.setId(id);
            this.repository.save(roomToUpdate);
        }
        else {
            throw new ModelConflictException("Only price can be updated");
        }
    }

    private boolean checkRoomExists(RoomRequestDTO room) {
        return this.repository.findRoomByHotelIdAndRoomNumber(room.getHotelId(), room.getRoomNumber()).isPresent();
    }

    private boolean checkRoomOnUpdate(Long id, RoomRequestDTO room) {
        Room checkRoom = this.repository.findById(id).orElseThrow(() -> new ModelNotFoundException("Room with this id not found"));
        return Objects.equals(room.getHotelId(), checkRoom.getHotelId()) && Objects.equals(room.getRoomNumber(), checkRoom.getRoomNumber());
    }
}
