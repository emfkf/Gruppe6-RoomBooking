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
    public ModelAndView showRegistrationForm(Model model) {
        return new ModelAndView("user_register", "user", new UserDto());
    }

    @PostMapping("/register")
    public ModelAndView registerUser(@Valid UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("registration", "user", userDto);
        } else if (userDto == null) {
            return new ModelAndView("registration", "user", new UserDto());
        }
        userService.registerNewUser(userDto);
        return new ModelAndView("user_all", "users", userService.getAll());
    }

    @GetMapping("/all")
    public ModelAndView showAllUsers() {
        return new ModelAndView("user_all", "users", userService.getAll());
    }

}