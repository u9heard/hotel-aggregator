package org.zotov.hotel_aggregator.services.mappers;

import org.springframework.stereotype.Service;
import org.zotov.hotel_aggregator.dto.hotel.HotelRequestDTO;
import org.zotov.hotel_aggregator.dto.hotel.HotelResponseDTO;
import org.zotov.hotel_aggregator.interfaces.services.ModelMapperService;
import org.zotov.hotel_aggregator.models.Hotel;

@Service
public class HotelMapperService implements ModelMapperService<HotelRequestDTO, HotelResponseDTO, Hotel> {
    @Override
    public Hotel RequestDTOtoModel(HotelRequestDTO hotelRequestDTO) {
        return new Hotel(null, hotelRequestDTO.getName(), hotelRequestDTO.getCity());
    }

    @Override
    public HotelResponseDTO modelToResponseDTO(Hotel hotel) {
        return new HotelResponseDTO(hotel.getId(), hotel.getName(), hotel.getCity());
    }
}
