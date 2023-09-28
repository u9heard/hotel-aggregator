package org.zotov.hotel_aggregator.controllers.restcontrollers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zotov.hotel_aggregator.annotations.RoleRequired;
import org.zotov.hotel_aggregator.dto.reservation.ReservationRequestDTO;
import org.zotov.hotel_aggregator.dto.reservation.ReservationResponseDTO;
import org.zotov.hotel_aggregator.interfaces.services.ReservationManagement;
import org.zotov.hotel_aggregator.models.Reservation;
import org.zotov.hotel_aggregator.services.ReservationService;

@RestController
@RequestMapping("/api/reservations")
public class ReservationRestController {
    private final ReservationManagement reservationService;

    @Autowired
    public ReservationRestController(ReservationManagement reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @RoleRequired("User")
    public ResponseEntity<ReservationResponseDTO> addReservation(@RequestBody @Valid ReservationRequestDTO reservation, @RequestAttribute("user_id") Long userId){
        reservation.setUserId(userId);
        return new ResponseEntity<>(this.reservationService.save(reservation), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    @RoleRequired("User")
    public ResponseEntity<String> deleteReservation(@PathVariable @NotNull Long id, @RequestAttribute("user_id") Long userId){
        this.reservationService.deleteReservationForUser(id, userId);
        return ResponseEntity.noContent().build();
    }
}
