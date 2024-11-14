package com.cartservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cartservice.model.SellerSession;

public interface SellerSessionRepository extends JpaRepository<SellerSession, Long>{ 

}

