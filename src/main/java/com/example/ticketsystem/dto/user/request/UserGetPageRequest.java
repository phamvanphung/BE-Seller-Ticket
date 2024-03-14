package com.example.ticketsystem.dto.user.request;

import com.example.ticketsystem.entity.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class UserGetPageRequest implements Specification<User> {
    private String keyword;
    private Integer size = 10;
    private Integer page = 0;
    private String orderBy = "id";
    private Sort.Direction direction = Sort.Direction.ASC;

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicateList = new ArrayList<>();
        if (StringUtils.isNotBlank(keyword)) {
            keyword = "%" + keyword + "%";
            predicateList.add(criteriaBuilder.or(
                    criteriaBuilder.like(root.get(User.Fields.name), keyword),
                    criteriaBuilder.like(root.get(User.Fields.email), keyword)));
        }
        return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
    }


    public Pageable getPageable() {
        return size != null ? PageRequest.of(page, size, Sort.by(direction, orderBy)) : Pageable.unpaged();
    }
}
