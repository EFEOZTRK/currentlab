package com.spring.springsecurityapp.repository;

import com.spring.springsecurityapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
