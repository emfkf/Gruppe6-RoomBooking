package com.fredrik.roombooking.dao;

import com.fredrik.roombooking.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByBuildingAndFloorAndNumber(String building, String floor, String number);

}