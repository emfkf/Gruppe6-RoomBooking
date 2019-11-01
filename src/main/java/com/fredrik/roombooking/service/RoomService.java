package com.fredrik.roombooking.service;

import com.fredrik.roombooking.dto.RoomDto;
import com.fredrik.roombooking.model.Room;

import java.util.List;

public interface RoomService {

    void addRoom(RoomDto room);
    List<Room> getAll();
    Room getRoom(Long id);
    void deleteRoom(Long id);
    void updateRoom(Long id, RoomDto room);

}
