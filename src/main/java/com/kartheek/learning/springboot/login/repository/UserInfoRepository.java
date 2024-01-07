package com.kartheek.learning.springboot.login.repository;

import com.kartheek.learning.springboot.registration.entity.RegisterUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<RegisterUser, Integer> {
    Optional<RegisterUser> findByUserName(String username);
}