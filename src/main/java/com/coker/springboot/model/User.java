package com.coker.springboot.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String first_Name;

    @Column(name = "last_name")
    private String last_Name;

    @Column(unique = true)
    private String email;

    private String password;

    private int age;

    private String sex;

    @Temporal(TemporalType.DATE)
    private Date DOB;

    private String address;

//    @OneToMany(mappedBy = "user")
//    private List<UserRole> roles;
    //CHỈ CÓ THỂ SỬ DỤNG COLLECTION TABLE NẾU BẢNG ĐÓ CHỈ CÓ 2 THUỘC TÍNH
    // LÀM CÁCH NÀY SẼ KHÔNG CẦN PHẢI TẠO THÊM ENTITY

    @ElementCollection
    @CollectionTable(name = "user_role",
    joinColumns =  @JoinColumn(name = "user_id"))

    @Column(name = "roles")
    private List<String> roles;

    @ManyToOne
    private Department department;

}
