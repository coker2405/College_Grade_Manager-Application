package com.coker.springboot.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.sql.Date;

@Data
public class CoursesDTO {

    private Integer id;
    @NotBlank

    private String name;

    @JsonFormat(pattern = "dd/mm/yyyy HH:mm",timezone = "Asia/Ho_Chi_Minh")
    private Date createdAt;

    @JsonFormat(pattern = "dd/mm/yyyy HH:mm",timezone = "Asia/Ho_Chi_Minh")
    private Date  updatedAt;

}
