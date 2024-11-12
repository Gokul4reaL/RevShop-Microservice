package com.cartservice.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.cartservice.model.Session;

public interface SessionRepository extends JpaRepository<Session, Long>{
	
}
