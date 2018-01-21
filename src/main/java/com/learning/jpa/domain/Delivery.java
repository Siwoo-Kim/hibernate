package com.learning.jpa.domain;

import com.learning.jpa.domain.embeddable.Address;
import lombok.*;

import javax.persistence.*;

@Getter @Setter @ToString(exclude = "order") @Builder
@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "DELIVERY")
@SequenceGenerator(name = "DELIVERY_SEQ_GENERATOR",
        sequenceName = "DELIVERY_SEQ",
        initialValue = 1, allocationSize = 1)
public class Delivery {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DELIVERY_SEQ_GENERATOR")
    @Column(name = "DELIVERY_ID")
    private Long id;

    @OneToOne(mappedBy = "order")
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) @Builder.Default
    private DeliveryStatus status = DeliveryStatus.READY;

    public enum DeliveryStatus{
        READY, COMPLETE;
        //READY (BEFORE SENDING) COMPLETE (AFTER SENDING)
    }


}