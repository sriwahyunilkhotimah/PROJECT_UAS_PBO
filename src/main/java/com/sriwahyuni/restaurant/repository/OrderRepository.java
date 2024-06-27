package com.sriwahyuni.restaurant.repository;

import com.sriwahyuni.restaurant.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}