package org.zotov.hotel_aggregator.controllers.defaultcontrollers;


import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zotov.hotel_aggregator.annotations.RoleRequired;
import org.zotov.hotel_aggregator.interfaces.services.RoomManagement;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    private final RoomManagement roomService;

    @Autowired
    public RoomController(RoomManagement roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/{hotel_id}")
    @RoleRequired("Admin")
    public String getRoomsForHotelPage(@PathVariable @NotNull Long hotel_id, Model model){
        model.addAttribute("rooms", this.roomService.getRoomsForHotel(hotel_id));
        return "admin/fragments/room_list";
    }
}
