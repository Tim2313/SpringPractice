package com.example.horses.service;

import com.example.horses.domain.entity.User;
import com.example.horses.domain.specification.UserSpecification;
import com.example.horses.excepition.EntityNotFoundException;
import com.example.horses.repositorie.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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

    public User findByEmail(String email) {
        Specification<User> emailSpecification = UserSpecification.byEmail(email);
        return userRepository.findOne(emailSpecification).orElseThrow(() -> {
            String message = "Entity %s with email '%s' has not been found.".formatted(User.ENTITY_NAME, email);
            return new EntityNotFoundException(message);
        });
    }
}
