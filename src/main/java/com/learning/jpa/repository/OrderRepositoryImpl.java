package com.learning.jpa.repository;

import com.learning.jpa.criteria.OrderSearch;
import com.learning.jpa.domain.Order;
import com.learning.jpa.domain.QOrder;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import static com.learning.jpa.domain.QOrder.*;
import static com.learning.jpa.domain.QMember.*;
import static com.learning.jpa.domain.Order.OrderStatus;

public class OrderRepositoryImpl extends QuerydslRepositorySupport implements CustomOrderRepository {

    @PersistenceContext
    EntityManager entityManager;

    public OrderRepositoryImpl(Class<?> domainClass) {
        super(Order.class);
    }

    @Override
    public List<Order> getOrders(OrderSearch orderSearch){

        JPQLQuery query = from(QOrder.order);

        OrderStatus orderStatus = orderSearch.getOrderStatus();
        String memberName = orderSearch.getMemberName();

        BooleanBuilder booleanBuilder = new BooleanBuilder();


        if(orderSearch.getOrderStatus() != null){
            booleanBuilder.and(order.status.eq(orderStatus));
        }

        if(StringUtils.hasText(memberName)){
            booleanBuilder.and(member.name.contains(memberName));
        }

        query.where(booleanBuilder);

        return  query.select(order).from(order).innerJoin(member).fetch();
    }

}
