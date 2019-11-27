package com.fredrik.roombooking.service;

import com.fredrik.roombooking.dao.BookingRepository;
import com.fredrik.roombooking.model.Booking;
import com.fredrik.roombooking.model.Room;
import com.fredrik.roombooking.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    // TODO Legg til felles abstract
    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public void addBooking(User user, Room room, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        if (bookingRepository.findByRoom(room) == null) {
            bookingRepository.save(new Booking(user, room, startDateTime, endDateTime));
        }
    }

    @Override
    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }

    @Override
    public boolean isBooked(Room room) {
        if (bookingRepository.findByRoom(room) != null) {
            return true;
        }
        return false;
    }

    @Override
    public Booking getBooking(Long id) {
        return bookingRepository.getOne(id);
    }

    @Override
    public void deleteBooking(Long id) {
        bookingRepository.delete(getBooking(id));
    }

    @Override
    public List<Booking> getByUser(User user) {
        return bookingRepository.findByUser(user);
    }

}
