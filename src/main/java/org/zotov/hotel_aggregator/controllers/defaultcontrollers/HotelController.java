package org.zotov.hotel_aggregator.controllers.defaultcontrollers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zotov.hotel_aggregator.annotations.RoleRequired;
import org.zotov.hotel_aggregator.dto.hotel.HotelWithRoomsResponseDTO;
import org.zotov.hotel_aggregator.dto.reservation.ReservationSearchDTO;
import org.zotov.hotel_aggregator.interfaces.services.HotelManagement;

import java.util.List;

@Controller
@RequestMapping("/")
public class HotelController {

    private final HotelManagement hotelService;

    @Autowired
    public HotelController(HotelManagement hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping()
    @RoleRequired("User")
    public String homePage(Model model){
        return "client/home";
    }

    @GetMapping(value = "/hotel_list")
    @RoleRequired("User")
    public String hotelList(@ModelAttribute("reservationDTO") @Valid ReservationSearchDTO reservationSearchDTO,
                            Model model,
                            BindingResult bindingResult){

        List<HotelWithRoomsResponseDTO> hotelList = this.hotelService.findHotelsWithFreeRooms(reservationSearchDTO);
        model.addAttribute("hotels", hotelList);
        return "client/hotel_list";
    }

    @GetMapping("/admin_hotel_list")
    @RoleRequired("Admin")
    public String hotelListPage(Model model) {
        model.addAttribute("hotels", this.hotelService.findAll());
        return "admin/fragments/hotel_manage";
    }
}
