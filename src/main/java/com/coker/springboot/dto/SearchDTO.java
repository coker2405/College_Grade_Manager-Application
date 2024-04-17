package com.coker.springboot.dto;

import lombok.Data;

@Data
public class SearchDTO {
    private String keyword;
    private Integer size = 10;
    private Integer currentPage = 0;
    private String sortedByColumn = "id";

}
