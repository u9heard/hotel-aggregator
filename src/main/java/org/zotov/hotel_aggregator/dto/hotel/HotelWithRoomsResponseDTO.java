package org.zotov.hotel_aggregator.dto.hotel;

import org.zotov.hotel_aggregator.models.Room;

import java.util.List;

public class HotelWithRoomsResponseDTO extends HotelResponseDTO {
    private List<Room> roomList;

    public HotelWithRoomsResponseDTO() {

    }

    public HotelWithRoomsResponseDTO(Long id, String name, String city, List<Room> roomList) {
        super(id, name, city);
        this.roomList = roomList;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }
}
