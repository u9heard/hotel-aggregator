package org.zotov.hotel_aggregator.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Table("archived_rooms")
public class ArchivedRoom {
    @Id
    private Long id;

    @Column("room_id")
    private Long roomId;

    @Column("hotel_id")
    private Long hotelId;

    @Column("room_number")
    private Integer roomNumber;

    @Column("price")
    private BigDecimal price;

    @Column("archived_at")
    private LocalDate archivedAt;

    public ArchivedRoom() {
    }

    public ArchivedRoom(Long id, Long roomId, Long hotelId, Integer roomNumber, BigDecimal price, LocalDate archivedAt) {
        this.id = id;
        this.roomId = roomId;
        this.hotelId = hotelId;
        this.roomNumber = roomNumber;
        this.price = price;
        this.archivedAt = archivedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
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

    public LocalDate getArchivedAt() {
        return archivedAt;
    }

    public void setArchivedAt(LocalDate archivedAt) {
        this.archivedAt = archivedAt;
    }
}
