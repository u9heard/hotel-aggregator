package org.zotov.hotel_aggregator.services;

import org.springframework.stereotype.Service;
import org.zotov.hotel_aggregator.dto.hotel.HotelWithRoomsResponseDTO;
import org.zotov.hotel_aggregator.dto.hotel.HotelRequestDTO;
import org.zotov.hotel_aggregator.dto.hotel.HotelResponseDTO;
import org.zotov.hotel_aggregator.dto.reservation.ReservationSearchDTO;
import org.zotov.hotel_aggregator.exceptions.service.ModelConflictException;
import org.zotov.hotel_aggregator.exceptions.service.ModelNotFoundException;
import org.zotov.hotel_aggregator.interfaces.services.HotelManagement;
import org.zotov.hotel_aggregator.interfaces.services.ModelMapperService;
import org.zotov.hotel_aggregator.models.Hotel;
import org.zotov.hotel_aggregator.models.Room;
import org.zotov.hotel_aggregator.interfaces.repositories.HotelRepository;
import org.zotov.hotel_aggregator.interfaces.repositories.ReservationRepository;
import org.zotov.hotel_aggregator.interfaces.repositories.RoomRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class HotelService extends CrudService<HotelResponseDTO, HotelRequestDTO, Hotel, HotelRepository> implements HotelManagement {

    RoomRepository roomRepository;
    ReservationRepository reservationRepository;

    public HotelService(HotelRepository repository, ModelMapperService<HotelRequestDTO, HotelResponseDTO, Hotel> modelMapper, RoomRepository roomRepository, ReservationRepository reservationRepository) {
        super(repository, modelMapper, "Hotel");
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public HotelResponseDTO save(HotelRequestDTO hotel) {
        if (checkIfExists(hotel.getName(), hotel.getCity())) {
            throw new ModelConflictException("Hotel already exists");
        }

        return super.save(hotel);
    }

    public void update(Long id, HotelRequestDTO hotel){
        checkIfExists(hotel.getName(), hotel.getCity());

        this.repository.save(new Hotel(id, hotel.getName(), hotel.getCity()));
    }

    public List<HotelWithRoomsResponseDTO> findHotelsWithFreeRooms(ReservationSearchDTO reservationSearchDTO) {
        List<Hotel> hotelList = this.repository.findHotelByCity(reservationSearchDTO.getCity());
        if (hotelList.isEmpty()) {
            throw new ModelNotFoundException("There is no hotels in " + reservationSearchDTO.getCity());
        }

        List<HotelWithRoomsResponseDTO> extendedHotels = hotelList.stream()
                .map(hotel -> new HotelWithRoomsResponseDTO(hotel.getId(), hotel.getName(), hotel.getCity(), null)).toList();

        addFreeRoomsForHotels(extendedHotels, reservationSearchDTO.getDateStart(), reservationSearchDTO.getDateEnd());
        return extendedHotels;
    }

    public List<HotelResponseDTO> findAll() {
        List<HotelResponseDTO> responseDTOS = new ArrayList<>();
        this.repository.findAll().forEach(hotel -> {
            responseDTOS.add(this.modelMapper.modelToResponseDTO(hotel));
        });

        return responseDTOS;
    }

    private void addFreeRoomsForHotels(List<HotelWithRoomsResponseDTO> hotels, LocalDate dateStart, LocalDate dateEnd) {
        hotels.forEach(hotelWithRoomsResponseDTO -> {
            List<Room> roomList = this.roomRepository.findFreeRoomsByDate(dateStart, dateEnd, hotelWithRoomsResponseDTO.getId());
            hotelWithRoomsResponseDTO.setRoomList(roomList);
        });
    }

    private boolean checkIfExists(String name, String city) {
        return this.repository.getHotelByCityAndName(city, name).isPresent();
    }
}
