package com.fredrik.roombooking.service;

import com.fredrik.roombooking.dao.RoomRepository;
import com.fredrik.roombooking.dto.RoomDto;
import com.fredrik.roombooking.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public void addRoom(RoomDto room) {
        String building = room.getBuilding();
        String floor = room.getFloor();
        String number = room.getNumber();

        List<Room> roomList = roomRepository.findByBuildingAndFloorAndNumber(building, floor, number);

        if (roomList.isEmpty()) {
            roomRepository.save(new Room(building, floor, number, room.getCapacity()));
        }
    }

    public List<Room> viewAll() {
        return roomRepository.findAll();
    }

}
