package com.coker.springboot.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SearchGradeDTO extends SearchDTO{
    private int studentId;
    private int coursesId;
}
