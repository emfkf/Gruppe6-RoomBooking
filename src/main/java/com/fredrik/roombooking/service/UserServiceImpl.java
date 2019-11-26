package com.fredrik.roombooking.service;

import com.fredrik.roombooking.dao.RoleRepository;
import com.fredrik.roombooking.dao.UserRepository;
import com.fredrik.roombooking.dto.UserDto;
import com.fredrik.roombooking.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void registerNewUser(UserDto userDto) {
        if (!emailExists(userDto.getEmail())) {
            User user = new User(
                    userDto.getFirstName(),
                    userDto.getLastName(),
                    userDto.getEmail(),
                    passwordEncoder.encode(userDto.getPassword())
            );
            user.setRoles(Arrays.asList(roleRepository.findByName("USER")));
            userRepository.save(user);
        }
    }

    @Override
    public boolean emailExists(String email) {
        if (userRepository.findByEmail(email) != null) {
            return true;
        }
        return false;
    }

    @Override
    public User getUser(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    // FREDRIK
    @Override
    public List<User> getUserByEmail(String email) {
        if (email.equals("admin")) {
            return getAll();
        }
        List<User> users = new ArrayList<>();
        users.add(userRepository.findByEmail(email));
        return users;
    }

}