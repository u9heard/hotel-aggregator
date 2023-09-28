package org.zotov.hotel_aggregator.dto.hotel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.zotov.hotel_aggregator.models.Hotel;

public class HotelRequestDTO {
    private Long id;

    @NotBlank(message = "Name can't be empty")
    private String name;

    @NotBlank(message = "City can't be empty")
    private String city;

    public HotelRequestDTO() {

    }

    public HotelRequestDTO(Long id, String name, String city) {
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
