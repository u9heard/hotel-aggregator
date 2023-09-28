package org.zotov.hotel_aggregator.dto.hotel;

import org.zotov.hotel_aggregator.models.Room;

import java.util.List;

public class HotelResponseDTO {
    private Long id;
    private String name;
    private String city;

    public HotelResponseDTO() {
    }

    public HotelResponseDTO(Long id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
