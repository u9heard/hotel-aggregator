package org.zotov.hotel_aggregator.controllers.restcontrollers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zotov.hotel_aggregator.annotations.RoleRequired;
import org.zotov.hotel_aggregator.dto.user.UserRequestDTO;
import org.zotov.hotel_aggregator.dto.user.UserResponseDTO;
import org.zotov.hotel_aggregator.interfaces.services.UserManagement;


@RestController
@RequestMapping("/api/users")
public class UserRestController {
    private final UserManagement userService;

    @Autowired
    public UserRestController(UserManagement userService) {
        this.userService = userService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @RoleRequired("Admin")
    public ResponseEntity<UserResponseDTO> addUser(@RequestBody @Valid UserRequestDTO userDTO){
        return ResponseEntity.ok(this.userService.save(userDTO));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @RoleRequired("Admin")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        this.userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
