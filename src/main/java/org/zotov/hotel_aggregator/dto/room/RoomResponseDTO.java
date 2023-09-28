package org.zotov.hotel_aggregator.dto.room;

import org.zotov.hotel_aggregator.models.Room;

import java.math.BigDecimal;

public class RoomResponseDTO {

    private Long id;

    private Long hotelId;

    private Integer roomNumber;

    private BigDecimal price;

    public RoomResponseDTO() {
    }

    public RoomResponseDTO(Long id, Long hotelId, Integer roomNumber, BigDecimal price) {
        this.id = id;
        this.hotelId = hotelId;
        this.roomNumber = roomNumber;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
