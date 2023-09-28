package org.zotov.hotel_aggregator.dto.reservation;

import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.zotov.hotel_aggregator.annotations.DateValid;

import java.time.LocalDate;

@DateValid(dateStartMethod = "getDateStart", dateEndMethod = "getDateEnd")
public class ReservationRequestDTO {


    private Long userId;

    @NotNull(message = "Room id can't be empty")
    private Long roomId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Start date can't be empty")
    private LocalDate dateStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "End date can't be empty")
    private LocalDate dateEnd;

    public ReservationRequestDTO() {
    }

    public ReservationRequestDTO(Long userId, Long roomId, LocalDate dateStart, LocalDate dateEnd) {
        this.userId = userId;
        this.roomId = roomId;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
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
