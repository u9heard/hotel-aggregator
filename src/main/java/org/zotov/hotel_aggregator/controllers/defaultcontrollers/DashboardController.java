package org.zotov.hotel_aggregator.controllers.defaultcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zotov.hotel_aggregator.annotations.RoleRequired;
import org.zotov.hotel_aggregator.interfaces.services.HotelManagement;
import org.zotov.hotel_aggregator.interfaces.services.ReservationManagement;
import org.zotov.hotel_aggregator.interfaces.services.UserManagement;
import org.zotov.hotel_aggregator.services.HotelService;
import org.zotov.hotel_aggregator.services.ReservationService;


@Controller
@RequestMapping("/admin")
public class DashboardController {
    private final UserManagement userService;
    private final HotelManagement hotelService;
    private final ReservationManagement reservationService;

    @Autowired
    public DashboardController(UserManagement userService, HotelManagement hotelService, ReservationManagement reservationService) {
        this.userService = userService;
        this.hotelService = hotelService;
        this.reservationService = reservationService;
    }

    @GetMapping("/dashboard")
    @RoleRequired("Admin")
    public String dashboard(Model model){
        Long userCount = this.userService.count();
        Long hotelCount = this.hotelService.count();
        Long reservationsCount = this.reservationService.count();
        model.addAttribute("user_count", userCount);
        model.addAttribute("hotel_count", hotelCount);
        model.addAttribute("reservations_count", reservationsCount);
        return "admin/dashboard";
    }
}
