package org.zotov.hotel_aggregator.controllers.defaultcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.zotov.hotel_aggregator.annotations.RoleRequired;
import org.zotov.hotel_aggregator.dto.reservation.ReservationResponseDTO;
import org.zotov.hotel_aggregator.interfaces.services.ReservationManagement;

import java.util.List;

@Controller
public class ReservationController {

    private final ReservationManagement reservationService;

    @Autowired
    public ReservationController(ReservationManagement reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/reservations")
    @RoleRequired("User")
    public String getReservations(@RequestAttribute("user_id") Long user_id, Model model){
        List<ReservationResponseDTO> reservationSearchDTOS = this.reservationService.findReservationsByUserId(user_id);
        model.addAttribute("reservations", reservationSearchDTOS);
        return "client/reservations";
    }
}
