package org.zotov.hotel_aggregator.controllers.restcontrollers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.zotov.hotel_aggregator.annotations.RoleRequired;
import org.zotov.hotel_aggregator.dto.room.RoomRequestDTO;
import org.zotov.hotel_aggregator.dto.room.RoomResponseDTO;
import org.zotov.hotel_aggregator.interfaces.services.RoomManagement;
import org.zotov.hotel_aggregator.services.RoomService;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api/rooms")
public class RoomRestController {
    private final RoomManagement roomService;

    @Autowired
    public RoomRestController(RoomManagement roomService) {
        this.roomService = roomService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @RoleRequired("Admin")
    public ResponseEntity<List<RoomResponseDTO>> addRooms(@RequestBody @NotEmpty(message = "{empty.list.room}") List<@Valid RoomRequestDTO> roomList){
        return ResponseEntity.ok(this.roomService.addRooms(roomList));
    }

    @DeleteMapping("/{id}")
    @RoleRequired("Admin")
    public ResponseEntity<String> deleteRoom(@PathVariable Long id){
        this.roomService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
