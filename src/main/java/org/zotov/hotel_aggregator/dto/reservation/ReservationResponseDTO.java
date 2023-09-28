package org.zotov.hotel_aggregator.dto.reservation;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ReservationResponseDTO {
    private Long id;
    private String hotelName;
    private String city;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private Integer roomNumber;
    private BigDecimal totalPrice;
    private String description;

    public ReservationResponseDTO() {
    }

    public ReservationResponseDTO(Long id, String hotelName, String city, LocalDate dateStart, LocalDate dateEnd, Integer roomNumber, BigDecimal totalPrice) {
        this.id = id;
        this.hotelName = hotelName;
        this.city = city;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.roomNumber = roomNumber;
        this.totalPrice = totalPrice;
    }

    public ReservationResponseDTO(Long id, String hotelName, String city, LocalDate dateStart, LocalDate dateEnd, Integer roomNumber, BigDecimal totalPrice, String description) {
        this.id = id;
        this.hotelName = hotelName;
        this.city = city;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.roomNumber = roomNumber;
        this.totalPrice = totalPrice;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
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

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
