package com.userservice.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;


import com.userservice.model.Session;

public interface SessionRepository extends JpaRepository<Session, Long>{
	
}
