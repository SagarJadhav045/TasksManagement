package com.tasksmanagement.controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tasksmanagement.config.JwtHelper;
import com.tasksmanagement.model.JwtResponse;
import com.tasksmanagement.model.LoginRequest;
import com.tasksmanagement.model.RegisterRequest;
import com.tasksmanagement.model.User;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
    private UserDetailsService userDetailsService;
	
	@Autowired
    private JwtHelper jwtHelper;
	
	@PostMapping("/register")
    public RegisterRequest registerUser(@RequestBody RegisterRequest registerRequest) {		        
        return registerRequest;    
    }
	
	@PostMapping("/login")
    public ResponseEntity<JwtResponse> loginUser(@RequestBody LoginRequest loginRequest) {
		
		this.doAuthenticate(loginRequest.getUsername(), loginRequest.getPassword());
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        String token = this.jwtHelper.generateToken(userDetails);
		
        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
     
    }
	
	private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
        	authenticationManager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }
	
	@ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

}
