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

    // GET requests to /register
    // The function returns a ModelAndView with the view and model necessary for a registration form
    // The view is the user_register.html file, the model is the UserDto()
    @GetMapping("/register")
    public ModelAndView showRegistrationForm(Model model) {
        return new ModelAndView("user_register", "user", new UserDto());
    }

    // POST to /register
    // The method takes a UserDto (user Data Transfer Object) and BindingResult as parameters
    // and checks whether there are any errors before either returning the registration form again or registering
    // the user, returning the view and model necessary to show all users.
    @PostMapping("/register")
    public String registerUser(@Valid UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors() || userDto == null) {
            return "redirect:/user/register?error";
        }
        userService.registerNewUser(userDto);
        return "redirect:/login?registrationSuccess";
    }

    @GetMapping("/all")
    public ModelAndView showAllUsers() {
        return new ModelAndView("user_all", "users", userService.getAll());
    }

}