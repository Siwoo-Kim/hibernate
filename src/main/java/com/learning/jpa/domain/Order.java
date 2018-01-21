package com.learning.jpa.domain;

import lombok.*;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Getter @Setter @ToString(exclude = {"orderItems"}) @Builder
@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "TBL_ORDER")
@SequenceGenerator(name = "ORDER_SEQ_GENERATOR",
        sequenceName = "ORDER_SEQ",
        initialValue = 1, allocationSize = 1)
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_SEQ_GENERATOR")
    @Column(name = "ORDER_ID")
    private Long id;


    @ManyToOne
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    public void setMember(Member member){
        this.member = member;
        getMember().getOrders().add(this);
    }


    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL) @Builder.Default
    List<OrderItem> orderItems = new ArrayList<>();

    public void addOrderItem(OrderItem orderItem){
        getOrderItems().add(orderItem);
        orderItem.setOrder(this);
    }


    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="DELIVERY_ID")
    private Delivery delivery;

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        getDelivery().setOrder(this);
    }


    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public enum OrderStatus{
        ORDER , CANCEL;
    }

    private int orderPrice;

    private int count;

    public static Order createOrder(Member member,Delivery delivery,OrderItem... orderItems){
        Order order = new Order();
        //Must use setter to make a bi-relationship
        order.setMember(member);
        order.setDelivery(delivery);

        Stream.of(orderItems).forEach(orderItem -> order.addOrderItem(orderItem) );

        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    public void cacnel(){
        if(delivery.getStatus().equals(Delivery.DeliveryStatus.COMPLETE)){
            throw new RuntimeException("CANNOT NOT CANCEL ORDER WHICH IS ALREADY SENT");
        }

        this.setStatus(OrderStatus.CANCEL);

        getOrderItems().stream().forEach(orderItem -> orderItem.cancel());
    }

    public int getTotalPrice(){
        int totalPrice = 0;

        for(OrderItem orderItem: getOrderItems()){
            totalPrice += orderItem.getTotalPrice();
        }

        return totalPrice;
    }
}