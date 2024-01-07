package com.kartheek.learning.springboot.login.service;

import com.kartheek.learning.springboot.login.RegisterUserDetails;
import com.kartheek.learning.springboot.login.repository.UserInfoRepository;
import com.kartheek.learning.springboot.registration.entity.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {
   @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<RegisterUser> userDetail = userInfoRepository.findByUserName(username);
        // Converting userDetail to UserDetails
        return userDetail.map(RegisterUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }
}
