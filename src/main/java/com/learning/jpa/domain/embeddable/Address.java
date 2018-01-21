package com.learning.jpa.domain.embeddable;

import lombok.*;

import javax.persistence.*;

@Getter @Setter @ToString @Builder
@NoArgsConstructor
@AllArgsConstructor @EqualsAndHashCode
public class Address {

    private String city;

}