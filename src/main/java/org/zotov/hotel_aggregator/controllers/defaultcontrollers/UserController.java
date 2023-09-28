package org.zotov.hotel_aggregator.controllers.defaultcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.zotov.hotel_aggregator.annotations.RoleRequired;
import org.zotov.hotel_aggregator.interfaces.services.UserManagement;


@Controller
@RequestMapping("/users")
public class UserController {
    private final UserManagement userService;

    @Autowired
    public UserController(UserManagement userService) {
        this.userService = userService;
    }

    @GetMapping()
    @RoleRequired("Admin")
    public String getUsersPage(Model model){
        model.addAttribute("users", this.userService.getAll());
        return "admin/fragments/user_manage";
    }
}
