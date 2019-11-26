package com.fredrik.roombooking.service;

import com.fredrik.roombooking.dto.UserDto;
import com.fredrik.roombooking.model.User;

import java.util.List;

public interface UserService {

    void registerNewUser(UserDto userDto);
    boolean emailExists(String email);
    User getUser(Long id);
    List<User> getAll();
    List<User> getUserByEmail(String email);

}
