package org.zotov.hotel_aggregator.interfaces.services;

import org.zotov.hotel_aggregator.dto.hotel.HotelWithRoomsResponseDTO;
import org.zotov.hotel_aggregator.dto.hotel.HotelRequestDTO;
import org.zotov.hotel_aggregator.dto.hotel.HotelResponseDTO;
import org.zotov.hotel_aggregator.dto.reservation.ReservationSearchDTO;

import java.util.List;

public interface HotelManagement extends BaseService<HotelResponseDTO, HotelRequestDTO>{
    List<HotelWithRoomsResponseDTO> findHotelsWithFreeRooms(ReservationSearchDTO reservationSearchDTO);

    List<HotelResponseDTO> findAll();
}
