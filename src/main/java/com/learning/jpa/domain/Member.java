package com.learning.jpa.domain;

import com.learning.jpa.domain.embeddable.Address;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @ToString(exclude = "orders") @Builder
@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "MEMBER")
@SequenceGenerator(name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ",
        initialValue = 1, allocationSize = 1)
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
    @Column(name = "MEMBER_ID")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member") @Builder.Default
    private List<Order> orders = new ArrayList<>();

}