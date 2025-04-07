package com.example.horses.service;

import com.example.horses.api.dto.User;
import com.example.horses.repositorie.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public User addUsers(User user) {
        return userRepository.save(user);
    }
}
