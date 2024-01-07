package com.kartheek.learning.springboot.registration.service;

import com.kartheek.learning.springboot.registration.entity.RegisterUser;
import org.springframework.http.ResponseEntity;

public interface RegisterService {
    ResponseEntity<String> saveUser(RegisterUser user);
    ResponseEntity<?> confirmEmail(String confirmationToken);
}
