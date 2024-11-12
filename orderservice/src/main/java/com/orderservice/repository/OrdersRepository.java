package com.orderservice.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.orderservice.model.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long>{

	 List<Orders> findAll();
	List<Orders> findOrdersByUser_UserId(Long userId);
}