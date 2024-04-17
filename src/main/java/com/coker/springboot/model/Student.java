package com.coker.springboot.model;

import jakarta.persistence.*;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;

    private String studentCode;

    @OneToOne
    @PrimaryKeyJoinColumn
    private User user;


}
