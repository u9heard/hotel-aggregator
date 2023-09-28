package org.zotov.hotel_aggregator.services.mappers;

import org.springframework.stereotype.Service;
import org.zotov.hotel_aggregator.dto.room.RoomRequestDTO;
import org.zotov.hotel_aggregator.dto.room.RoomResponseDTO;
import org.zotov.hotel_aggregator.interfaces.services.ModelMapperService;
import org.zotov.hotel_aggregator.models.Room;

@Service
public class RoomMapperService implements ModelMapperService<RoomRequestDTO, RoomResponseDTO, Room> {
    @Override
    public Room RequestDTOtoModel(RoomRequestDTO roomRequestDTO) {
        Room mappedRoom = new Room();
        mappedRoom.setHotelId(roomRequestDTO.getHotelId());
        mappedRoom.setRoomNumber(roomRequestDTO.getRoomNumber());
        mappedRoom.setPrice(roomRequestDTO.getPrice());
        return mappedRoom;
    }

    @Override
    public RoomResponseDTO modelToResponseDTO(Room room) {
        return new RoomResponseDTO(room.getId(), room.getHotelId(), room.getRoomNumber(), room.getPrice());
    }
}
