package com.fredrik.roombooking.controller;

import com.fredrik.roombooking.dto.RoomDto;
import com.fredrik.roombooking.service.RoomService;
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

    @GetMapping("/all")
    public String viewAllRooms(Model model) {
        model.addAttribute("rooms", roomService.getAll());
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
        roomService.deleteRoom(Long.parseLong(roomId));
        return "redirect:/room/all";
    }

}
