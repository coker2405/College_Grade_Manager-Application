package com.coker.springboot.controller;

import com.coker.springboot.dto.CoursesDTO;
import com.coker.springboot.dto.ResponseDTO;
import com.coker.springboot.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    CourseService courseService;
    @GetMapping("/")
    public ResponseDTO<List<CoursesDTO>> getALL(){

        List<CoursesDTO> courses = courseService.findAll();
        return ResponseDTO.<List<CoursesDTO>>builder().
                message("List of Courses").
                data(courses).build();
    }
    @PutMapping("/")
    public ResponseDTO<Void> updateCourse(CoursesDTO coursesDTO){
        courseService.update(coursesDTO);
        return ResponseDTO.<Void>builder()
                .status(200)
                .message("Update successfully")
                .build();
    }
    @DeleteMapping("/")
    public ResponseDTO<Void> deleteCourse(int id){
        courseService.delete(id);
        return ResponseDTO.<Void>builder()
                .status(200)
                .message("Delete Successfully")
                .build();
    }
    @PostMapping("/")
    public ResponseDTO<Void> createCourse(CoursesDTO coursesDTO) {
        courseService.create(coursesDTO);
        return ResponseDTO.<Void>builder().build();
    }

}
