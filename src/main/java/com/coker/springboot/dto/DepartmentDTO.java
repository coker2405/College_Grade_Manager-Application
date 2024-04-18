package com.coker.springboot.dto;

import com.coker.springboot.model.TimeAuditable;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DepartmentDTO extends TimeAuditable {
    private Integer id;
    @NotEmpty(message = "Can not be emptied ")
    private String name;

}


