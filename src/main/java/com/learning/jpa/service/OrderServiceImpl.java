package com.learning.jpa.service;

import com.learning.jpa.criteria.OrderSearch;
import com.learning.jpa.domain.*;
import com.learning.jpa.repository.ItemRepository;
import com.learning.jpa.repository.MemberRepository;
import com.learning.jpa.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ItemRepository itemRepository;

    @PersistenceUnit
    EntityManagerFactory entityManagerFactory;

    @Override
    public Order order(Member member, Item item, int count){

        if(!entityManagerFactory.getPersistenceUnitUtil().isLoaded(member))
            member = this.memberRepository.findById(member.getId()).get();

        if(!entityManagerFactory.getPersistenceUnitUtil().isLoaded(item))
            item = this.itemRepository.findById(item.getId()).get();

        Delivery delivery = Delivery.builder().address(member.getAddress()).build();

        OrderItem orderItem = OrderItem.createOrderItem(item,item.getPrice(),count);
        Order order = Order.createOrder(member,delivery,orderItem);

        return orderRepository.save(order);
    }

    @Override
    public void cancelOrder(Order order){
        if(!entityManagerFactory.getPersistenceUnitUtil().isLoaded(order))
            order = this.orderRepository.findById(order.getId()).get();

        order.cacnel();
    }


    @Override
    public List<Order> getOrders(OrderSearch orderSearch){
        return orderRepository.getOrders(orderSearch);
    }
}
