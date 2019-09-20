package com.fredrik.roombooking.controller;

import com.fredrik.roombooking.dto.RoomDto;
import com.fredrik.roombooking.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/room")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping("/all")
    public String viewAllRooms(Model model) {
        model.addAttribute("rooms", roomService.viewAll());
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

}