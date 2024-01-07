package com.kartheek.learning.springboot.registration.repository;

import com.kartheek.learning.springboot.registration.entity.RegisterUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<RegisterUser, Long> {
  //  Boolean existsByUserEmail(String email);
}
