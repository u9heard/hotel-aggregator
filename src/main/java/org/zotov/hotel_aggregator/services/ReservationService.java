package org.zotov.hotel_aggregator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zotov.hotel_aggregator.dto.reservation.ReservationRequestDTO;
import org.zotov.hotel_aggregator.dto.reservation.ReservationResponseDTO;
import org.zotov.hotel_aggregator.exceptions.service.ModelConflictException;
import org.zotov.hotel_aggregator.exceptions.service.ModelNotFoundException;
import org.zotov.hotel_aggregator.interfaces.services.ModelMapperService;
import org.zotov.hotel_aggregator.interfaces.services.ReservationManagement;
import org.zotov.hotel_aggregator.models.Reservation;
import org.zotov.hotel_aggregator.interfaces.repositories.ReservationRepository;
import org.zotov.hotel_aggregator.interfaces.repositories.RoomRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ReservationService extends CrudService<ReservationResponseDTO, ReservationRequestDTO, Reservation, ReservationRepository> implements ReservationManagement {
    private final RoomRepository roomRepository;

    @Autowired
    public ReservationService(ReservationRepository repository, ModelMapperService<ReservationRequestDTO, ReservationResponseDTO, Reservation> modelMapper, RoomRepository roomRepository) {
        super(repository, modelMapper, "Reservation");
        this.roomRepository = roomRepository;
    }

    @Override
    @Transactional
    public ReservationResponseDTO save(ReservationRequestDTO reservation){
        if(checkIfRoomRented(reservation.getRoomId(), reservation.getDateStart(), reservation.getDateEnd())){
            throw new ModelConflictException("Room is already booked");
        }

        Reservation reservationToSave = this.modelMapper.RequestDTOtoModel(reservation);

        BigDecimal price = roomRepository.findById(reservation.getRoomId()).orElseThrow(() -> new ModelNotFoundException("Room not found")).getPrice();
        BigDecimal totalPrice = price.multiply(BigDecimal.valueOf(ChronoUnit.DAYS.between(reservation.getDateStart(), reservation.getDateEnd())));
        reservationToSave.setTotalPrice(totalPrice);

        return this.modelMapper.modelToResponseDTO(this.repository.save(reservationToSave));
    }

    @Override
    @Transactional
    public void update(Long id, ReservationRequestDTO reservation) {
        if(checkIfRoomRented(reservation.getRoomId(), reservation.getDateStart(), reservation.getDateEnd())){
            throw new ModelConflictException("Room is already booked");
        }

        Reservation reservationToUpdate = this.modelMapper.RequestDTOtoModel(reservation);
        reservationToUpdate.setUserId(id);
        this.repository.save(reservationToUpdate);
    }

    public void deleteReservationForUser(Long id, Long userId) {
        this.repository.deleteByIdAndUserId(id, userId);
    }

    @Transactional(readOnly = true)
    public List<ReservationResponseDTO> findReservationsByUserId(Long userId){
        return this.repository.readByUserId(userId).stream().map(this.modelMapper::modelToResponseDTO).toList();
    }

    private boolean checkIfRoomRented(Long roomId, LocalDate dateStart, LocalDate dateEnd){
        return this.repository.findByIdAndDateRange(roomId, dateStart, dateEnd);
    }
}
