package com.example.ticketsystem.dto.order.request;

import com.example.ticketsystem.entity.User;
import com.example.ticketsystem.entity.Voucher;
import com.example.ticketsystem.entity.Order;
import jakarta.persistence.criteria.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@Slf4j
public class GetAllOrderRequest implements Specification<Order> {
    private String keyword;
    private Boolean status;
    private Integer size = 10;
    private Integer page = 0;
    private String orderBy = "id";
    private Sort.Direction direction = Sort.Direction.ASC;

    @Override
    public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicateList = new ArrayList<>();
        if (StringUtils.isNotBlank(keyword)) {
            keyword = "%" + keyword + "%";
            Join<Order, User> userJoin = root.join(Order.Fields.user); // Join với entity User

            predicateList.add(criteriaBuilder.or(
                    criteriaBuilder.like(root.get(Order.Fields.quantity).as(String.class), keyword),
                    criteriaBuilder.like(userJoin.get(User.Fields.name), keyword),
                    criteriaBuilder.like(userJoin.get(User.Fields.email), keyword)

            ));
        }
        if(Objects.nonNull(status)){
            predicateList.add(criteriaBuilder.equal(root.get(Order.Fields.status), status));
        }
        return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
    }


    public Pageable getPageable() {
        return size != null ? PageRequest.of(page, size, Sort.by(direction, orderBy)) : Pageable.unpaged();
    }
}
