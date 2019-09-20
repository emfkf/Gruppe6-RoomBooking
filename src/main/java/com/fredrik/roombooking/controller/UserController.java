package com.fredrik.roombooking.controller;

import com.fredrik.roombooking.dto.UserDto;
import com.fredrik.roombooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "user_registration";
    }

    @PostMapping("/register")
    public ModelAndView registerUser(@Valid UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("registration", "user", userDto);
        } else if (userDto == null) {
            return new ModelAndView("registration", "user", new UserDto());
        }
        userService.registerNewUser(userDto);
        return new ModelAndView("user_all", "users", userService.getAllUsers());
    }

    @GetMapping("/all")
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user_all";
    }

}
