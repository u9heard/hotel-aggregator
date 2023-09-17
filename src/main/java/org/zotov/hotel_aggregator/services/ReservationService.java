package org.zotov.hotel_aggregator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zotov.hotel_aggregator.dto.ReservationDTO;
import org.zotov.hotel_aggregator.exceptions.service.ModelNotFoundException;
import org.zotov.hotel_aggregator.models.Hotel;
import org.zotov.hotel_aggregator.models.Reservation;
import org.zotov.hotel_aggregator.models.Room;
import org.zotov.hotel_aggregator.repositories.HotelRepository;
import org.zotov.hotel_aggregator.repositories.ReservationRepository;
import org.zotov.hotel_aggregator.repositories.RoomRepository;

import java.util.List;

@Service
public class ReservationService {
    private ReservationRepository reservationRepository;
    private HotelRepository hotelRepository;
    private RoomRepository roomRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, HotelRepository hotelRepository, RoomRepository roomRepository) {
        this.reservationRepository = reservationRepository;
        this.hotelRepository = hotelRepository;
        this.roomRepository = roomRepository;
    }

    public Reservation addReserve(Reservation reservation){
        return this.reservationRepository.save(reservation);
    }

    public List<ReservationDTO> findReservationsByUserId(Long userId){
        return this.reservationRepository.readByUserId(userId).stream().map(reservation -> {
            Room room = this.roomRepository.findById(reservation.getRoomId()).orElseThrow(() -> new ModelNotFoundException("Room not found"));
            Hotel hotel = this.hotelRepository.findById(room.getHotelId()).orElseThrow(() -> new ModelNotFoundException("Hotel not found"));
            return new ReservationDTO(reservation.getId(), hotel, room, reservation.getDateStart(), reservation.getDateEnd());
        }).toList();
    }
}
