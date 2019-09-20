package com.fredrik.roombooking.dao;

import com.fredrik.roombooking.model.Booking;
import com.fredrik.roombooking.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    Booking findByRoom(Room room);

}
