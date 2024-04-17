package com.coker.springboot.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CoursesDTO {
    private int id;
    @NotBlank

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
