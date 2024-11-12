package com.orderservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.orderservice.model.OrderDetails;
import com.orderservice.model.Orders;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long>{

	List<OrderDetails> findByOrder_OrderId(Long orderId);
	@Query("SELECT od.order FROM OrderDetails od WHERE od.product.seller.sellerId = :sellerId")
    List<Orders> findOrdersBySellerId(Long sellerId);
}