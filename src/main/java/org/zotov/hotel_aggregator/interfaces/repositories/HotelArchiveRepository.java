package org.zotov.hotel_aggregator.interfaces.repositories;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.zotov.hotel_aggregator.models.ArchivedHotel;

import java.util.Optional;

@Repository
public interface HotelArchiveRepository extends CrudRepository<ArchivedHotel, Long> {
    @Query("SELECT * FROM archived_hotels WHERE hotel_id = :id")
    Optional<ArchivedHotel> getHotelByIdFromArchive(@Param("id") Long id);
}