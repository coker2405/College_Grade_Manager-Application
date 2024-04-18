package com.coker.springboot.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Student {
    @Id
    private Integer Id;

    @OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    @MapsId
    private User user;

    private String studentCode;

    @OneToMany(mappedBy = "student")
    private List<Grade> grades;

}
