package com.fredrik.roombooking.dao;

import com.fredrik.roombooking.model.Booking;
import com.fredrik.roombooking.model.Room;
import com.fredrik.roombooking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    Booking findByRoom(Room room);
    List<Booking> findByUser(User user);

}
