package com.fredrik.roombooking.controller;

import com.fredrik.roombooking.dto.BookingDto;
import com.fredrik.roombooking.dto.RoomDto;
import com.fredrik.roombooking.model.Room;
import com.fredrik.roombooking.model.User;
import com.fredrik.roombooking.service.BookingService;
import com.fredrik.roombooking.service.RoomService;
import com.fredrik.roombooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/room")
public class RoomController {

    @Autowired
    private RoomService roomService;
    @Autowired
    private UserService userService;
    @Autowired
    private BookingService bookingService;

    @GetMapping("/all")
    public String viewAllRooms(Model model) {
        model.addAttribute("rooms", roomService.getAll());
        model.addAttribute("room", new RoomDto());
        return "room_all";
    }

    @GetMapping("/add")
    public String showRoomForm(Model model) {
        model.addAttribute("room", new RoomDto());
        return "room_add";
    }

    @PostMapping("/add")
    public String checkRoomInfo(@Valid RoomDto room, BindingResult bindingResult) {
        if (!bindingResult.hasErrors() && room != null) {
            roomService.addRoom(room);
        }
        return "redirect:/room/all";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String handleDeleteRoom(@RequestParam(name = "roomId") String roomId) {
        if (!bookingService.isBooked(roomService.getRoom(Long.parseLong(roomId)))) {
            roomService.deleteRoom(Long.parseLong(roomId));
        }
        return "redirect:/room/all";
    }

    @GetMapping("/bookings")
    public String viewAllBookings(Model model) {
        model.addAttribute("bookings", bookingService.getAll());
        return "bookings";
    }

    @GetMapping("/book")
    public String showBookingForm(Model model) {
        model.addAttribute("booking", new BookingDto());
        model.addAttribute("users", userService.getAll());
        model.addAttribute("rooms", roomService.getAll());
        return "room_book";
    }

    @PostMapping("/book")
    public String checkBookingInfo(@Valid BookingDto booking, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "room_book";
        }
        Room room = roomService.getRoom(booking.getRoomId());
        User user = userService.getUser(booking.getUserId());
        bookingService.addBooking(user, room);
        return "redirect:/room/bookings";
    }

    @RequestMapping(value = "/delete_booking", method = RequestMethod.GET)
    public String handleDeleteBooking(@RequestParam(name = "bookingId") String bookingId) {
        bookingService.deleteBooking(Long.parseLong(bookingId));
        return "redirect:/room/bookings";
    }

}
