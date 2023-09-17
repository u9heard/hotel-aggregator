package org.zotov.hotel_aggregator.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.zotov.hotel_aggregator.models.Room;

import java.util.List;

public class HotelDTO {
    private Long id;
    private String name;
    private String city;
    private List<Room> roomList;

    public HotelDTO(Long id, String name, String city, List<Room> roomList) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.roomList = roomList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }
}
