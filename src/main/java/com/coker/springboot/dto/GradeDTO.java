package com.coker.springboot.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class GradeDTO {

    private int id;
    @NotBlank
    private double score;

    private CoursesDTO courses;
    private StudentDTO student;

    @JsonFormat(pattern = "dd/mm/yyyy HH:mm", timezone = "Asia/Ho_Chi_Minh")
    private Data createdAt;

    @JsonFormat(pattern = "dd/mm/yyyy HH:mm", timezone = "Asia/Ho_Chi_Minh")
    private Data updatedAt;


}
