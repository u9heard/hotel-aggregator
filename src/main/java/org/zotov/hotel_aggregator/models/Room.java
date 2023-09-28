package org.zotov.hotel_aggregator.models;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Table("rooms")
public class Room {
    @Id
    private Long id;

    @Column("hotel_id")
    @NotNull
    private Long hotelId;

    @Column("room_number")
    @NotNull
    private Integer roomNumber;

    @Column("price")
    @NotNull
    private BigDecimal price;

    public Room() {
    }

    public Room(Long id, Long hotelId, Integer roomNumber, BigDecimal price) {
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
