package com.userservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.userservice.model.Session;
import com.userservice.model.User;

public interface SessionRepository extends JpaRepository<Session, Long>{
    Optional<Session> findByEmail(String email);
}
