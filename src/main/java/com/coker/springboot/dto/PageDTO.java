package com.coker.springboot.dto;

import lombok.Data;

@Data
public class PageDTO<T> {
    private int totalPage;
    private long totalElement;
    private T data;

}
