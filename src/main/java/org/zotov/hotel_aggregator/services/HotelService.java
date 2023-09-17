package org.zotov.hotel_aggregator.services;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zotov.hotel_aggregator.dto.HotelDTO;
import org.zotov.hotel_aggregator.exceptions.service.ModelNotFoundException;
import org.zotov.hotel_aggregator.interfaces.DTOConvertable;
import org.zotov.hotel_aggregator.models.Hotel;
import org.zotov.hotel_aggregator.models.Room;
import org.zotov.hotel_aggregator.repositories.HotelRepository;
import org.zotov.hotel_aggregator.repositories.ReservationRepository;
import org.zotov.hotel_aggregator.repositories.RoomRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelService { //TODO Сделать абстрактные сервисы

    HotelRepository hotelRepository;
    RoomRepository roomRepository;
    ReservationRepository reservationRepository;

    @Autowired
    public HotelService(HotelRepository hotelRepository, RoomRepository roomRepository, ReservationRepository reservationRepository) {
        this.hotelRepository = hotelRepository;
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
    }

    public Hotel findById(Long id){
        return this.hotelRepository.findById(id).orElseThrow(() -> new ModelNotFoundException("Hotel not found, id = " + id));
    }

    public List<HotelDTO> findHotelsWithFreeRooms(String city, LocalDate date_start, LocalDate date_end){
        List<Hotel> hotelList = this.hotelRepository.findHotelByCity(city);
        return hotelList.stream().map(hotel -> {
            List<Room> roomList = this.roomRepository.findFreeRoomsByDate(date_start, date_end, hotel.getId());
            return new HotelDTO(hotel.getId(), hotel.getName(), hotel.getCity(), roomList);
        }).toList();
    }

    public List<Hotel> findAll(){
        return IterableUtils.toList(this.hotelRepository.findAll());
    }

//    public List<HotelDTO> toDTO(List<Hotel> hotelList){
//        return hotelList.stream().map(hotel -> {
//            List<Room> roomList = this.roomRepository.findFreeRoomsByDate(hotel.hotel.getId());
//            return new HotelDTO(hotel.getId(), hotel.getName(), hotel.getCity(), roomList);
//        }).toList();
//    }
}

//@Table("hotels")
//public class Hotel {
//    @Id
//    private Long id;
//
//    @MappedCollection(idColumn = "hotel_id")
//    private Set<Room> rooms;
//
//    // Другие поля и методы
//}
