package com.example.ticketsystem.dto.film.request;

import com.example.ticketsystem.entity.Film;
import com.example.ticketsystem.entity.User;
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

@Getter
@Setter
@ToString
@Slf4j
public class GetAllFilmRequest implements Specification<Film> {
    private String keyword;
    private Integer size = 10;
    private Integer page = 0;
    private String orderBy = "id";
    private Sort.Direction direction = Sort.Direction.ASC;

    @Override
    public Predicate toPredicate(Root<Film> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicateList = new ArrayList<>();

        //change Enum -> String before used
        //Expression<String> countryExpression = root.get(Film.Fields.country).as(String.class);

        if (StringUtils.isNotBlank(keyword)) {
            keyword = "%" + keyword + "%";
            predicateList.add(criteriaBuilder.or(
                    criteriaBuilder.like(root.get(Film.Fields.name), keyword),
                    criteriaBuilder.like(root.get(Film.Fields.country).as(String.class), keyword),
                    criteriaBuilder.like(root.get(Film.Fields.typeFilm).as(String.class), keyword),
                    criteriaBuilder.like(root.get(Film.Fields.language).as(String.class), keyword),
                    criteriaBuilder.like(root.get(Film.Fields.price).as(String.class), keyword)
            ));
        }
        return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
    }


    public Pageable getPageable() {
        return size != null ? PageRequest.of(page, size, Sort.by(direction, orderBy)) : Pageable.unpaged();
    }
}
