package com.learning.jpa.domain;

import lombok.*;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
}