package com.example.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.model.User;

@Service
public class UserService {

	 private Map<Long, User> userMap = new HashMap<>();
	    private Long nextId = 1L;

	    // Get all users
	    public List<User> getAllUsers() {
	        return new ArrayList<>(userMap.values());
	    }

	    // Get user by ID
	    public User getUserById(Long id) {
	        return userMap.get(id);
	    }

	    // Create user
	    public void createUser(User user) {
	        user.setId(nextId++);
	        userMap.put(user.getId(), user);
	    }

	    // Update user
	    public void updateUser(User user) {
	        userMap.put(user.getId(), user);
	    }

	    // Delete user
	    public void deleteUser(Long id) {
	        userMap.remove(id);
	    }
}
