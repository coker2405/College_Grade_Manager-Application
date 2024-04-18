package com.coker.springboot.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class GradeDTO {
    private Integer id;
    @NotBlank
    private double score;
    private CoursesDTO courses;

    private StudentDTO student;


}
