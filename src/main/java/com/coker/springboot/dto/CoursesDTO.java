package com.coker.springboot.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CoursesDTO {
    private Integer id;
    @NotBlank

    private String name;


}
