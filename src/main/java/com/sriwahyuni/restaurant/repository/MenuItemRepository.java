package com.sriwahyuni.restaurant.repository;


import com.sriwahyuni.restaurant.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
}