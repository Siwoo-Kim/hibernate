package com.learning.jpa.criteria;

import com.learning.jpa.domain.Order.*;
import lombok.*;

@Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor(access = AccessLevel.PACKAGE)
public class OrderSearch {

    private String memberName;
    private OrderStatus orderStatus;


}
