package com.coker.springboot.service;

import com.coker.springboot.dto.GradeDTO;
import com.coker.springboot.dto.StaticAVG;
import com.coker.springboot.model.Grade;
import com.coker.springboot.repository.GradeRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class GradesService{
    @Autowired
     GradeRepo gradeRepo;
     public List<StaticAVG> staticAVGS(){
       return gradeRepo.staticAVG();
    }

    @Transactional
    public List<GradeDTO> findAll() {
        List<Grade> list = gradeRepo.findAll();
       return list.stream().map(u-> convert(u)).collect(Collectors.toList());
    }
    @Transactional

    public void update(GradeDTO gradeDTO) {
        Grade currentGrade = gradeRepo.findById(gradeDTO.getId()).orElse(null);
        currentGrade.setScore(gradeDTO.getScore());
        gradeRepo.save(currentGrade);
    }
    @Transactional
    public void create(GradeDTO gradeDTO) {
        Grade grade = new ModelMapper().map(gradeDTO,Grade.class);
        gradeRepo.save(grade);
    }
    @Transactional
    public void delete(int id) {
        Grade deleteGrade = gradeRepo.findById(id).orElse(null);
        gradeRepo.delete(deleteGrade);
    }
    @Transactional

    public List<GradeDTO> searchByUserId(int id) {
        List<GradeDTO> grades = gradeRepo.searchByUserid(id).stream().map(u->convert(u)).collect(Collectors.toList());
        return grades;
    }
    @Transactional
    private GradeDTO convert(Grade grade) {
        ModelMapper modelMapper = new ModelMapper();
        return  modelMapper.map(grade,GradeDTO.class);
    }

    public List<GradeDTO> searchByCoursesId(int id) {
        List<GradeDTO> grades =  gradeRepo.searchByCourseId(id).stream().map(u->convert(u)).collect(Collectors.toList());
        return grades;
    }
}
