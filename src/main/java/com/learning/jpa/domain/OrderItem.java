package com.learning.jpa.domain;

import lombok.*;

import javax.persistence.*;

@Getter @Setter @ToString @Builder
@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "ORDER_ITEM")
@SequenceGenerator(name = "ORDERITEM_SEQ_GENERATOR",
        sequenceName = "ORDERITEM_SEQ",
        initialValue = 1, allocationSize = 1)
public class OrderItem {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDERITEM_SEQ_GENERATOR")
    @Column(name = "ORDERITEM_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name="ORDER_ID")
    private Order order;

    private int orderPrice;

    private int count;

}