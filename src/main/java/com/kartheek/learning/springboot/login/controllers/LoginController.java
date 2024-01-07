package com.kartheek.learning.springboot.login.controllers;


import com.kartheek.learning.springboot.login.model.LoginReq;
import com.kartheek.learning.springboot.login.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class LoginController {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginReq loginReq){
        //If user present then only give token
       Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getUsername(),loginReq.getPassword()));
       if(authentication.isAuthenticated()){
           String token = jwtService.generateToken(loginReq.getUsername());
           return new ResponseEntity<>(token, HttpStatus.OK);
       }else{
           throw new UsernameNotFoundException("Invalid user details");
       }
    }
}
