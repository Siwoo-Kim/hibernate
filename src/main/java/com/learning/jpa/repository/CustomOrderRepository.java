package com.learning.jpa.repository;

import com.learning.jpa.criteria.OrderSearch;
import com.learning.jpa.domain.Order;

import java.util.List;

public interface CustomOrderRepository {

    List<Order> getOrders(OrderSearch orderSearch);
}
