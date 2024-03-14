package com.example.ticketsystem.dto.film.request;

import com.example.ticketsystem.entity.Film;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

@Getter
@Setter
@ToString
public class GetAllFilmRequest  {
    private String keyword;
    private Integer size = 10;
    private Integer page = 0;
    private String orderBy = "createdAt";
    private Sort.Direction direction = Sort.Direction.ASC;
}
