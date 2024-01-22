package com.kartheek.learning.springboot.registration.controllers;


import com.kartheek.learning.springboot.registration.entity.RegisterUser;
import com.kartheek.learning.springboot.registration.model.RegisterReq;
import com.kartheek.learning.springboot.registration.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/register")
public class RegistrationController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RegisterService registerService;

    @ResponseBody
    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody RegisterReq registerReq){
        // creating user object
        RegisterUser user = new RegisterUser();
        user.setFullName(registerReq.getFullName());
        user.setUserName(registerReq.getUsername());
        user.setEmail(registerReq.getEmail());
        user.setPassword(passwordEncoder.encode(registerReq.getPassword()));
        return registerService.saveUser(user);
    }

    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token")String confirmationToken) {
        return registerService.confirmEmail(confirmationToken);
    }

}
