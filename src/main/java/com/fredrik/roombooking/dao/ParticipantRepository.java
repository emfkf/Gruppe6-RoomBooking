package com.fredrik.roombooking.dao;

import com.fredrik.roombooking.model.Booking;
import com.fredrik.roombooking.model.Participant;
import com.fredrik.roombooking.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    List<Participant> findByBooking(Booking booking);

}
