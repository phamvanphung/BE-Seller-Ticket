package com.example.ticketsystem.dto.common.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;


@Getter
@Setter
@Accessors(chain = true)
public class PageDataResponse<I> {
    private int size;
    private int page;
    private long totalSize;
    private int totalPage;
    private List<I> items;
}
