package com.fredrik.roombooking.service;

import com.fredrik.roombooking.dao.RoomRepository;
import com.fredrik.roombooking.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public void addRoom(Room room) {
        String building = room.getBuilding();
        String floor = room.getFloor();
        String number = room.getNumber();

        List<Room> roomList = roomRepository.findByBuildingAndFloorAndNumber(building, floor, number);

        if (roomList.isEmpty()) {
            roomRepository.save(new Room(building, floor, number, room.getCapacity()));
        }
    }

    public List<Room> getAll() {
        return roomRepository.findAll();
    }

    public Room getRoom(Long id) {
        return roomRepository.getOne(id);
    }

    public void deleteRoom(Long id) {
        roomRepository.delete(getRoom(id));
    }

    public void updateRoom(Long id, Room room) {
        String building = room.getBuilding();
        String floor = room.getFloor();
        String number = room.getNumber();
        List<Room> roomList = roomRepository.findByBuildingAndFloorAndNumber(building, floor, number);
        if (roomList.isEmpty()) {
            Room toUpdate = roomRepository.getOne(id);
            toUpdate.setBuilding(building);
            toUpdate.setFloor(floor);
            toUpdate.setNumber(number);
            toUpdate.setCapacity(room.getCapacity());
            roomRepository.save(toUpdate);
        }

    }

    // TODO Make roomExists boolean function
    

}
