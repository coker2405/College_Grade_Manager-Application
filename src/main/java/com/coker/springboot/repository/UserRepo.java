package com.coker.springboot.repository;

import com.coker.springboot.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    @Query("SELECT u FROM User u WHERE u.email = :email ")
    User findByUsername(@Param("email") String email);
    @Query("SELECT u FROM User u WHERE u.email LIKE :email")
    Page<User> findAllUserName(@Param("email") String email, Pageable pageable);
    @Query("SELECT u FROM User u WHERE u.id = :id")
    User findById(@Param("id") int id);

    @Query("SELECT u FROM User u WHERE DAY(u.DOB) = :day AND MONTH(u.DOB) = :month")
    List<User> searchByDOB(@Param("day") int day,
                           @Param("month") int month);
}
