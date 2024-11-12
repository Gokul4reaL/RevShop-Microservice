package com.orderservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orderservice.model.Orders;
import com.orderservice.model.Session;

public interface SessionRepository extends JpaRepository<Session, Long>{
	
}
