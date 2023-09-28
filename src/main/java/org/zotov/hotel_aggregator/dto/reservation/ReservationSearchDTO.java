package org.zotov.hotel_aggregator.dto.reservation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.zotov.hotel_aggregator.annotations.DateValid;

import java.time.LocalDate;

@Validated
@DateValid(dateStartMethod = "getDateStart", dateEndMethod = "getDateEnd")
public class ReservationSearchDTO {
    private String hotel_name;

    @NotBlank(message = "City can't be empty")
    private String city;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Start date can't be empty")
    private LocalDate dateStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "End date can't be empty")
    private LocalDate dateEnd;

    public ReservationSearchDTO() {
    }

    public ReservationSearchDTO(String hotel_name, String city, LocalDate dateStart, LocalDate dateEnd) {
        this.hotel_name = hotel_name;
        this.city = city;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    @Override
    public String toString() {
        return "ReservationDTO{" +
                ", hotel_name='" + hotel_name + '\'' +
                ", city='" + city + '\'' +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                '}';
    }
}
