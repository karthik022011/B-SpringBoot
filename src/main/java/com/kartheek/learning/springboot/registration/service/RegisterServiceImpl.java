package com.kartheek.learning.springboot.registration.service;

import com.kartheek.learning.springboot.email.entity.ConfirmationToken;
import com.kartheek.learning.springboot.email.repository.ConfirmationTokenRepository;
import com.kartheek.learning.springboot.email.service.EmailSenderService;
import com.kartheek.learning.springboot.registration.entity.RegisterUser;
import com.kartheek.learning.springboot.registration.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RegisterServiceImpl implements RegisterService{
    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;

    @Override
    public ResponseEntity<String> saveUser(RegisterUser user) {
        registrationRepository.save(user);
        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();
        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setConfirmationToken(uuidAsString);
        confirmationToken.setEmail(user.getEmail());
        confirmationToken.setVerifiedEmail(false);
        confirmationTokenRepository.save(confirmationToken);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText("To confirm your account, please click here : "
                +"http://localhost:8090/api/register/confirm-account?token="+uuidAsString);
        emailSenderService.sendEmail(mailMessage);
        return new ResponseEntity<>("Verify email by the link sent on your email address", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> confirmEmail(String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        if(token != null) {
            token.setVerifiedEmail(true);
            confirmationTokenRepository.save(token);
            return ResponseEntity.ok("Email verified successfully!");
        }
        return ResponseEntity.badRequest().body("Error: Couldn't verify email");
    }
}
