package com.coker.springboot.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = true)
public class Grade extends TimeAuditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private double score;
    @ManyToOne
    private Courses courses;

    @ManyToOne
    private Student student;


}
