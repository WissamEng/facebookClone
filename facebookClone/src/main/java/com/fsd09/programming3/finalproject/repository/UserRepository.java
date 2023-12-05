package com.fsd09.programming3.finalproject.repository;

import com.fsd09.programming3.finalproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 *
 */
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUserNameIgnoreCase(String userName);
}
