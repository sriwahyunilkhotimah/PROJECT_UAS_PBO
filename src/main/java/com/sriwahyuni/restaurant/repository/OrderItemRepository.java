package com.sriwahyuni.restaurant.repository;

import com.sriwahyuni.restaurant.model.OrderItem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}