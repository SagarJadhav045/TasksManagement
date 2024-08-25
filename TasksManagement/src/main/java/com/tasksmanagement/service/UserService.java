package com.tasksmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tasksmanagement.model.RegisterRequest;
import com.tasksmanagement.model.User;
import com.tasksmanagement.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
    

    public User registerUser(RegisterRequest registerRequest) {
    	User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        
        String role = registerRequest.getRole();
        if (role == null || role.isEmpty()) {
            role = "USER"; 
        }
        user.setRole(role);
        return userRepository.save(user);
    }   
    
    

}
