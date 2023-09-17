package org.zotov.hotel_aggregator.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.zotov.hotel_aggregator.annotations.RoleRequired;
import org.zotov.hotel_aggregator.dto.ReservationDTO;
import org.zotov.hotel_aggregator.models.Hotel;
import org.zotov.hotel_aggregator.models.Reservation;
import org.zotov.hotel_aggregator.services.ReservationService;

import java.time.LocalDate;
import java.util.List;

@Controller
@RoleRequired("Admin")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/reservations")
    public String getReservations(@RequestAttribute("user_id") Long user_id, Model model){
        List<ReservationDTO> reservationDTOS = this.reservationService.findReservationsByUserId(user_id);
        model.addAttribute("reservations", reservationDTOS);
        return "reservations";
    }

    @PostMapping("/reserve")
    public ResponseEntity<String> postReservation(@RequestAttribute("user_id") Long userId, @RequestParam("date_start") LocalDate dateStart, @RequestParam("date_end") LocalDate dateEnd, @RequestParam("room_id") Long roomId){
        System.out.println(dateStart);
        System.out.println(dateEnd);
        System.out.println(roomId);
        System.out.println(userId);

        Reservation newReservation = new Reservation(null, userId, roomId, dateStart, dateEnd);

        reservationService.addReserve(newReservation);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
