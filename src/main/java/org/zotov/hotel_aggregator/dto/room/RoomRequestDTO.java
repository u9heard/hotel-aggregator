package org.zotov.hotel_aggregator.dto.room;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class RoomRequestDTO {

    @NotNull(message = "Hotel id can't be empty")
    private Long hotelId;

    @NotNull(message = "Room number can't be empty")
    private Integer roomNumber;

    @NotNull(message = "Price can't be empty")
    private BigDecimal price;

    public RoomRequestDTO() {
    }

    public RoomRequestDTO(Long hotelId, Integer roomNumber, BigDecimal price) {
        this.hotelId = hotelId;
        this.roomNumber = roomNumber;
        this.price = price;
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

    @Override
    public String toString() {
        return "RoomRequestDTO{" +
                "hotelId=" + hotelId +
                ", roomNumber=" + roomNumber +
                ", price=" + price +
                '}';
    }
}
