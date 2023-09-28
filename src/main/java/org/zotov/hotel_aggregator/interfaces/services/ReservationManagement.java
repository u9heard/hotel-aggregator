package org.zotov.hotel_aggregator.interfaces.services;

import org.zotov.hotel_aggregator.dto.reservation.ReservationRequestDTO;
import org.zotov.hotel_aggregator.dto.reservation.ReservationResponseDTO;
import org.zotov.hotel_aggregator.models.Reservation;

import java.util.List;

public interface ReservationManagement extends BaseService<ReservationResponseDTO, ReservationRequestDTO> {
    void deleteReservationForUser(Long ReservationId, Long userId);
    List<ReservationResponseDTO> findReservationsByUserId(Long userId);
}
