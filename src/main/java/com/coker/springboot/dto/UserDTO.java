package com.coker.springboot.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class UserDTO {

    private int id;
    @NotEmpty(message = "Can not be blanked")
    private String first_name;
    @NotEmpty(message = "Can not be blanked")

    private String last_name;
    @Email(message = "Email is not validated")
    private String email;
    @Min(value = 3,message = "The password's length must be 8 at least ")
    private String password;
    @NotNull
    private int age;
    @NotEmpty(message = " Can not be blanked")
    private String sex;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date DOB;
    @NotEmpty(message = "Can not be blanked")

    private String address;
    private DepartmentDTO department;
    private List<String> roles;

}
