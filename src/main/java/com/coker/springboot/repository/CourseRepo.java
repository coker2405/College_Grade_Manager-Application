package com.coker.springboot.repository;

import com.coker.springboot.model.Courses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepo extends JpaRepository<Courses,Integer> {
    @Query("SELECT c FROM Courses c WHERE c.name LIKE: name")
    Page<Courses> searchByName(@Param("name") String name, Pageable pageable);
}
