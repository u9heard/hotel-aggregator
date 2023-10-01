package org.zotov.hotel_aggregator.interfaces.repositories;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.zotov.hotel_aggregator.models.ArchivedRoom;

import java.util.Optional;

@Repository
public interface RoomArchiveRepository extends CrudRepository<ArchivedRoom, Long> {
    @Query("SELECT * FROM archived_rooms WHERE room_id = :id")
    Optional<ArchivedRoom> getRoomByIdFromArchive(@Param("id") Long id);
}
