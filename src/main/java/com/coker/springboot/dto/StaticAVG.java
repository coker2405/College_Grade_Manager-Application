package com.coker.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class StaticAVG {

    private int courseId;
    private String courseName;
    private double avg;
}
