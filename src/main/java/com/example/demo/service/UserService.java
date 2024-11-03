package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
//@RequiredArgsConstructor
public class UserService {
    
	@Autowired
    private  UserRepository userRepository; // Correctly initialized via constructor injection

	

    public boolean authenticate(String email, String password) {
        User user = userRepository.findByUserEmail(email);
        return user != null && user.getUserPassword().equals(password);
    }
    // Method to save a new user
    public User createUser(User user) {
        return userRepository.save(user);
    }
    
    public void save(User user) {
        // Handle password hashing here if applicable
        userRepository.save(user);
    }


    // Method to retrieve all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Method to find a user by ID
    public User getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found")); // Consider creating a custom exception
    }
////
//     Uncomment and implement the update method if needed
    public User updateUser(Integer id, User userDetails) {
        User user = getUserById(id); // Get user or throw exception if not found
        user.setUserName(userDetails.getUserName());
        user.setUserEmail(userDetails.getUserEmail());
        user.setUserAddress(userDetails.getUserAddress());
        user.setUserMobile(userDetails.getUserMobile());
        return userRepository.save(user);
    }
//
    // Method to delete a user by ID
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
