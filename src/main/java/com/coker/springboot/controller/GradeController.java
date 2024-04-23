package com.coker.springboot.controller;

import com.coker.springboot.dto.GradeDTO;
import com.coker.springboot.dto.ResponseDTO;
import com.coker.springboot.dto.StaticAVG;
import com.coker.springboot.service.GradesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/grade")
public class GradeController {
    @Autowired
    GradesService gradesService;

    @GetMapping("/")
    public ResponseDTO<List<GradeDTO>>  getAll(){

        List<GradeDTO> gradeDTOS = gradesService.findAll();
        return ResponseDTO.<List<GradeDTO>>builder()
                .status(200)
                .message("List of Grade")
                .build();
    }
    @GetMapping("/avg-by-course")
    public ResponseDTO<List<StaticAVG>> staticByCourse(){

    return ResponseDTO.<List<StaticAVG>>builder()
            .message("Average grade by course")
            .status(200)
            .data(gradesService.staticAVGS())
            .build();
    }
    @PostMapping("/")
    public ResponseDTO<Void> addGrade(GradeDTO gradeDTO) {

        gradesService.create(gradeDTO);
        return ResponseDTO.<Void>builder().status(200).message("Create Successfully").build();
    }
    @PostMapping("/filterByCourse")
    public ResponseDTO<List<GradeDTO>> searchByCourse(int id){
        return ResponseDTO.<List<GradeDTO>>builder()
                .message("All the grade of course:")
                .data(gradesService.searchByUserId(id))
                .status(200)
                .build();
    }
    @PostMapping("/filterByUser")
    public ResponseDTO<List<GradeDTO>> searchByUser(int id){
        return ResponseDTO.<List<GradeDTO>>builder()
                .message("All the grade of user:")
                .data(gradesService.searchByCoursesId(id))
                .status(200)
                .build();
    }
    @PutMapping("/")
    public ResponseDTO<Void> updateGrade(GradeDTO gradeDTO){

        gradesService.update(gradeDTO);
        return ResponseDTO.<Void>builder()
                .message("Update successfully")
                .status(200)
                .build();
    }
    @DeleteMapping("/")
    public ResponseDTO<Void> deleteGrade(int id){

        gradesService.delete(id);
        return ResponseDTO.<Void>builder()
                .message("Delete successfully")
                .status(200)
                .build();
    }

}
