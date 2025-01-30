package com.example.horses.services;

import com.example.horses.models.User;
import com.example.horses.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public User addUsers(User user) {
        return userRepository.save(user);
    }
}
