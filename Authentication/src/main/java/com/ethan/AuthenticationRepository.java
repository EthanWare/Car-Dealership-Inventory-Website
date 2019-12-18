package com.ethan;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthenticationRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String userName);
}