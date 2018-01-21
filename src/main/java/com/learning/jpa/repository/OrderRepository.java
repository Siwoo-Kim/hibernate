package com.learning.jpa.repository;

import com.learning.jpa.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long>,CustomOrderRepository{

}
