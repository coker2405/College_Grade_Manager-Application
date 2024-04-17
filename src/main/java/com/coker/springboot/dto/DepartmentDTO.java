package com.coker.springboot.dto;

import com.coker.springboot.model.TimeAuditable;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class DepartmentDTO extends TimeAuditable {
    private int id;
    @NotEmpty(message = "Can not be emptied ")
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


