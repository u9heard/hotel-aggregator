package org.zotov.hotel_aggregator.dto;

import org.zotov.hotel_aggregator.models.Hotel;
import org.zotov.hotel_aggregator.models.Room;

import java.time.LocalDate;

public class ReservationDTO {
    private Long id;
    private Hotel hotel;
    private Room room;
    private LocalDate dateStart;
    private LocalDate dateEnd;

    public ReservationDTO() {
    }

    public ReservationDTO(Long id, Hotel hotel, Room room, LocalDate dateStart, LocalDate dateEnd) {
        this.id = id;
        this.hotel = hotel;
        this.room = room;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }
}
