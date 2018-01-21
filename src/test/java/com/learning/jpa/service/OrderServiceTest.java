package com.learning.jpa.service;

import com.learning.jpa.domain.Book;
import com.learning.jpa.domain.Item;
import com.learning.jpa.domain.Member;
import com.learning.jpa.domain.Order;
import com.learning.jpa.domain.embeddable.Address;
import com.learning.jpa.repository.OrderRepository;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

@Log
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    public void Order(){

        Member member = Member.builder().name("member1").address(
                Address.builder().city("Toronto").street("Altamontd").zipcode("MS2 123").build()
        ).build();

        entityManager.persist(member);

        Item item = Book.builder().name("Learning JPA").price(300).stockQuantity(10).build();
        int orderCount =2;

        Order order = orderService.order(member,item,orderCount);

        Order foundOrder = orderRepository.findById(order.getId()).get();
        log.warning(foundOrder::toString);
        log.warning(foundOrder.getOrderItems().toString());

        assertThat(foundOrder.getStatus(), is(Order.OrderStatus.ORDER));
        assertThat(foundOrder.getOrderItems().size(), is(1));
        assertThat(foundOrder.getTotalPrice(), is(300 * 2));
        assertThat(item.getStockQuantity(), is(8));

    }

    @Test(expected = IllegalStateException.class)
    public void OverOrders(){


        Member member = Member.builder().name("member1").address(
                Address.builder().city("Toronto").street("Altamontd").zipcode("MS2 123").build()
        ).build();

        entityManager.persist(member);

        Item item = Book.builder().name("Learning JPA").price(300).stockQuantity(5).build();
        int orderCount = 10;

        Order order = orderService.order(member,item,orderCount);

        fail();
    }

    @Test
    public void cancel(){

        Member member = Member.builder().name("member1").address(
                Address.builder().city("Toronto").street("Altamontd").zipcode("MS2 123").build()
        ).build();

        entityManager.persist(member);

        Item item = Book.builder().name("Learning JPA").price(300).stockQuantity(5).build();
        int orderCount = 1;

        Order order = orderService.order(member,item,orderCount);

        orderService.cancelOrder(order);

        assertThat(order.getStatus(),is(Order.OrderStatus.CANCEL));
        assertThat(item.getStockQuantity(),is(5));

    }
}
