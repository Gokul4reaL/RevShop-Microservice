package com.userservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userservice.model.User;
import com.userservice.repository.UserRepository;



@Service
public class UserService {
	
	@Autowired
    private UserRepository userRepository;

    // Fetch user by userId from the database
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    // Update user details
    public User updateUserDetails(User updatedUser) {
        return userRepository.save(updatedUser);  // Save the updated user information
    }
    
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }

}