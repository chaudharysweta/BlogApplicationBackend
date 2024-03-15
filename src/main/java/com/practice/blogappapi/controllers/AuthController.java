package com.practice.blogappapi.controllers;

import com.practice.blogappapi.exceptions.ApiException;
import com.practice.blogappapi.payloads.JwtAuthRequest;
import com.practice.blogappapi.payloads.JwtAuthResponse;
import com.practice.blogappapi.payloads.UserDto;
import com.practice.blogappapi.security.JwtTokenHelper;
import com.practice.blogappapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> createToken(@RequestBody JwtAuthRequest request) {

            this.authenticate(request.getUsername(), request.getPassword());

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());

            String token = this.jwtTokenHelper.generateToken(userDetails);

            JwtAuthResponse response = new JwtAuthResponse();
            response.setToken(token);

            return new ResponseEntity<>(response, HttpStatus.OK);

    }

    private void authenticate(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        try {
            this.authenticationManager.authenticate(authenticationToken);
        }catch (BadCredentialsException e){
            System.out.println("Invalid Details");
            throw new ApiException("Invalid username and password");
        }
    }
    //Register New User API

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){

        UserDto registeredUser = this.userService.registerNewUser(userDto);

        return new ResponseEntity<UserDto>(registeredUser,HttpStatus.CREATED);
    }
}
