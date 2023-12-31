package org.zotov.hotel_aggregator.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

@Table("reservations")
public class Reservation {
    @Id
    private Long id;

    @Column("user_id")
    private Long userId;

    @Column("room_id")
    private Long roomId;

    @Column("date_start")
    private LocalDate dateStart;

    @Column("date_end")
    private LocalDate dateEnd;

    @Column("total_price")
    private BigDecimal totalPrice;

    public Reservation() {
    }

    public Reservation(Long id, Long userId, Long roomId, LocalDate dateStart, LocalDate dateEnd, BigDecimal totalPrice) {
        this.id = id;
        this.userId = userId;
        this.roomId = roomId;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", userId=" + userId +
                ", roomId=" + roomId +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
