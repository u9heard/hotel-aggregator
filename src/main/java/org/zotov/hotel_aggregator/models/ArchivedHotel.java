package org.zotov.hotel_aggregator.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table("archived_hotels")
public class ArchivedHotel {
    @Id
    private Long id;

    @Column("hotel_id")
    private Long hotelId;

    @Column("name")
    private String name;

    @Column("city")
    private String city;

    @Column("archived_at")
    private LocalDate archivedAt;

    public ArchivedHotel() {
    }

    public ArchivedHotel(Long id, Long hotelId, String name, String city, LocalDate archivedAt) {
        this.id = id;
        this.hotelId = hotelId;
        this.name = name;
        this.city = city;
        this.archivedAt = archivedAt;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getArchivedAt() {
        return archivedAt;
    }

    public void setArchivedAt(LocalDate archivedAt) {
        this.archivedAt = archivedAt;
    }
}
