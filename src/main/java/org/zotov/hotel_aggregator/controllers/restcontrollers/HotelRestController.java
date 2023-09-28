package org.zotov.hotel_aggregator.controllers.restcontrollers;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zotov.hotel_aggregator.annotations.RoleRequired;
import org.zotov.hotel_aggregator.dto.hotel.HotelRequestDTO;
import org.zotov.hotel_aggregator.dto.hotel.HotelResponseDTO;
import org.zotov.hotel_aggregator.interfaces.services.HotelManagement;
import org.zotov.hotel_aggregator.services.HotelService;

@RestController
@RequestMapping("/api/hotels")
public class HotelRestController {
    private final HotelManagement hotelService;

    @Autowired
    public HotelRestController(HotelManagement hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @RoleRequired("Admin")
    public ResponseEntity<HotelResponseDTO> addHotel(@RequestBody @Valid HotelRequestDTO hotel){
        return new ResponseEntity<>(this.hotelService.save(hotel), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @RoleRequired("Admin")
    public ResponseEntity<String> deleteHotel(@PathVariable Long id){
        this.hotelService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
