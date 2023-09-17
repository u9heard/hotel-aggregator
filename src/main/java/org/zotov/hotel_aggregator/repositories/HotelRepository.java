package org.zotov.hotel_aggregator.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.zotov.hotel_aggregator.models.Hotel;

import java.util.List;

@Repository
public interface HotelRepository extends CrudRepository<Hotel, Long> {
    List<Hotel> findHotelByCity(String city);
}
