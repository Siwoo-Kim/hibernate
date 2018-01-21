package com.learning.jpa.service;

import com.learning.jpa.criteria.OrderSearch;
import com.learning.jpa.domain.Item;
import com.learning.jpa.domain.Member;
import com.learning.jpa.domain.Order;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface OrderService {

    Order order(Member member, Item item, int count);

    void cancelOrder(Order order);

    @Transactional(readOnly = true)
    List<Order> getOrders(OrderSearch orderSearch);
}
