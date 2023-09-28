package org.zotov.hotel_aggregator.interfaces.services;

import org.zotov.hotel_aggregator.dto.room.RoomRequestDTO;
import org.zotov.hotel_aggregator.dto.room.RoomResponseDTO;

import java.util.List;

public interface RoomManagement extends BaseService<RoomResponseDTO, RoomRequestDTO> {
    List<RoomResponseDTO> addRooms(List<RoomRequestDTO> roomList);
    List<RoomResponseDTO> getRoomsForHotel(Long hotelId);

}
