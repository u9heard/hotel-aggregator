package org.zotov.hotel_aggregator.interfaces.repositories;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.zotov.hotel_aggregator.models.ArchivedHotel;
import org.zotov.hotel_aggregator.models.ArchivedRoom;
import org.zotov.hotel_aggregator.models.Room;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {
    List<Room> findByHotelId(Long hotelId);

    @Query("SELECT r.* " +
    "FROM rooms r " +
    "LEFT JOIN reservations res ON r.id = res.room_id " +
    "AND (res.date_start <= :end_date AND res.date_end >= :start_date) " +
    "WHERE res.id IS NULL AND r.hotel_id = :hotel_id " +
    "ORDER BY r.room_number")
    List<Room> findFreeRoomsByDate(@Param("start_date") LocalDate date_start,
                                          @Param("end_date") LocalDate date_end,
                                          @Param("hotel_id") Long hotelId);

    List<Room> findRoomsByHotelId(Long hotelId);

    Optional<Room> findRoomByHotelIdAndRoomNumber(Long hotelIdm, Integer roomNumber);
}
