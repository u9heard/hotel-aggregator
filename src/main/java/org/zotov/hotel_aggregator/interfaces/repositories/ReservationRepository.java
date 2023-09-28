package org.zotov.hotel_aggregator.interfaces.repositories;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.zotov.hotel_aggregator.models.Reservation;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    List<Reservation> readByUserId(Long userId);

    @Modifying
    @Query("DELETE FROM reservations WHERE id = :id AND user_id = :user_id")
    void deleteByIdAndUserId(@Param("id") Long id,@Param("user_id") Long userId);

    @Query("SELECT CASE WHEN COUNT(id) > 0 THEN true ELSE false END " +
           "FROM reservations WHERE room_id = :id AND " +
           "date_start <= :end_date AND date_end >= :start_date")
    boolean findByIdAndDateRange(@Param("id") Long id, @Param("start_date") LocalDate dateStart, @Param("end_date") LocalDate dateEnd);

}
