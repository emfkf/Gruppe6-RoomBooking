package com.fredrik.roombooking.controller;

import com.fredrik.roombooking.dto.BookingDto;
import com.fredrik.roombooking.dto.RoomRangeDto;
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
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping(path = "/room")
public class RoomController {

    @Autowired
    private RoomService roomService;
    @Autowired
    private UserService userService;
    @Autowired
    private BookingService bookingService;

    // GET request to /room/add
    // Returns the view file room_add.html with a room object for user input.
    @GetMapping("/add")
    public ModelAndView showRoomForm() {
        return new ModelAndView("room_add", "room", new Room());
    }

    // POST request to /room/add
    // Verifies the user input of the room object
    // If the fields of the room object are valid, and there are no errors, the saving of the room is handled by
    // the roomService object
    // The method then redirects to a list of all rooms, which matches the description of the
    @PostMapping("/add")
    public String checkRoomForm(@Valid Room room, BindingResult bindingResult) {
        if (bindingResult.hasErrors() && room == null) {
            return "redirect:/room/all?error";
        }
        roomService.addRoom(room);
        return "redirect:/room/all";
    }

    // GET request to /room/add
    // Returns the view file room_all.html with a model that has
    // - a List with all the rooms from roomService
    // - a Room object used for form user input
    // - a RoomRangeDto which is used to add a range of rooms
    @GetMapping("/all")
    public String viewAllRooms(Model model) {
        model.addAttribute("rooms", roomService.getAll());
        model.addAttribute("room", new Room());
        model.addAttribute("roomRange", new RoomRangeDto());
        return "room_all";
    }

    // GET request to /room/delete?roomId=x
    // Takes room id as a parameter of the url path, specifying which room to be deleted
    // If the room is not booked by a user, it is deleted by the roomService
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String handleDeleteRoom(@RequestParam(name = "roomId") String roomId) {
        if (!bookingService.isBooked(roomService.getRoom(Long.parseLong(roomId)))) {
            roomService.deleteRoom(Long.parseLong(roomId));
        } else {
            return "redirect:/room/all?error";
        }
        return "redirect:/room/all";
    }

    // GET request to /room/update?roomId=x
    // Takes room id as a parameter of the url path, specifying which room to be updated
    // If there are no errors in the method, the updateRoom method of roomService is called
    // The used is then redirected to /room/all
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String handleUpdateRoom(@RequestParam(name = "roomId") String roomId, @Valid Room room, BindingResult bindingResult) {
        if (!bindingResult.hasErrors() && room != null) {
            roomService.updateRoom(Long.parseLong(roomId), room);
        } else {
            return "redirect:/room/all?error";
        }
        return "redirect:/room/all";
    }

    /* NED TIL HER */

    @PostMapping("/add_range")
    public String addRoomRange(@Valid RoomRangeDto roomRange, BindingResult bindingResult) {
        if (!bindingResult.hasErrors() && roomRange != null) {
            for (int i = roomRange.getStartNumber(); i <= roomRange.getEndNumber(); i++) {
                Room room = new Room();
                room.setBuilding(roomRange.getBuilding());
                room.setFloor(roomRange.getFloor());
                room.setNumber(String.valueOf(i));
                room.setCapacity(roomRange.getCapacity());

                roomService.addRoom(room);
            }
        } else {
            return "redirect:/room/all?error";
        }
        return "redirect:/room/all";
    }

    @GetMapping("/book")
    public String showBookingForm(Model model, Principal principal) {
        model.addAttribute("booking", new BookingDto());
        model.addAttribute("users", userService.getUserByEmail(principal.getName()));
        model.addAttribute("rooms", roomService.getAll());
        return "room_book";
    }

    @PostMapping("/book")
    public String checkBookingForm(@Valid BookingDto booking, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/room/book?error";
        }
        Room room = roomService.getRoom(booking.getRoomId());
        User user = userService.getUser(booking.getUserId());
        bookingService.addBooking(
                user,
                room,
                booking.getStartDateTime(),
                booking.getEndDateTime()
        );
        return "redirect:/room/booked";
    }

    @GetMapping("/booked")
    public String viewAllBookings(Model model) {
        model.addAttribute("bookings", bookingService.getAll());
        return "bookings";
    }

    @RequestMapping(value = "/delete_booking", method = RequestMethod.GET)
    public String handleDeleteBooking(@RequestParam(name = "bookingId") String bookingId) {
        bookingService.deleteBooking(Long.parseLong(bookingId));
        return "redirect:/room/booked";
    }

    @RequestMapping(value = "/my_bookings", method = RequestMethod.GET)
    public String viewMyBookings(@RequestParam(name = "userId") String userId, Model model) {
        User user = userService.getUser(Long.parseLong(userId));
        model.addAttribute("bookings", bookingService.getByUser(user));
        return "my_bookings";
    }

}
