package com.fredrik.roombooking.service;

import com.fredrik.roombooking.model.Booking;
import com.fredrik.roombooking.model.Room;
import com.fredrik.roombooking.model.User;

import java.util.List;

public interface BookingService {

    void addBooking(User user, Room room);
    List<Booking> getAll();
    boolean isBooked(Room room);
    Booking getBooking(Long id);
    void deleteBooking(Long id);

}