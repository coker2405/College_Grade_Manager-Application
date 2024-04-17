package com.coker.springboot.dto;

import lombok.Data;

@Data
public class StudentDTO {
    private long userId;

    private String studentCode;

    private UserDTO user;

}
