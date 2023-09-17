package org.zotov.hotel_aggregator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zotov.hotel_aggregator.annotations.RoleRequired;
import org.zotov.hotel_aggregator.dto.HotelDTO;
import org.zotov.hotel_aggregator.models.Hotel;
import org.zotov.hotel_aggregator.repositories.HotelRepository;
import org.zotov.hotel_aggregator.services.HotelService;

import java.time.LocalDate;
import java.util.List;

@Controller
@RoleRequired("Admin")
@RequestMapping("/hotels")
public class HotelController {

    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping()
    public String homePage(Model model){
        return "home";
    }

    @GetMapping("/hotel_list")
    public String hotelList(@RequestParam("date_start")LocalDate dateStart, @RequestParam("date_end") LocalDate dateEnd, @RequestParam("city") String city, Model model){
        List<HotelDTO> hotelList = this.hotelService.findHotelsWithFreeRooms(city, dateStart, dateEnd);
        model.addAttribute("hotels", hotelList);
        return "hotel_list";
    }


}
