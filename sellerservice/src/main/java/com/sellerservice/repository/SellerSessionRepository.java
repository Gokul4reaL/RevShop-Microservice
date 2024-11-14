package com.sellerservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sellerservice.model.SellerSession;

public interface SellerSessionRepository extends JpaRepository<SellerSession, Long>{
    Optional<SellerSession> findByEmail(String email);
}
