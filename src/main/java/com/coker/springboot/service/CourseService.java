package com.coker.springboot.service;

import com.coker.springboot.dto.CoursesDTO;
import com.coker.springboot.dto.PageDTO;
import com.coker.springboot.dto.SearchDTO;
import com.coker.springboot.model.Courses;
import com.coker.springboot.repository.CourseRepo;
import jakarta.persistence.NoResultException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService{
    @Autowired
    CourseRepo courseRepo ;


    public void create(CoursesDTO coursesDTO) {
        Courses courses = new ModelMapper().map(coursesDTO,Courses.class);
        courseRepo.save(courses);
    }


    public void delete(int id) {
        courseRepo.deleteById(id);
    }


    public void update(CoursesDTO coursesDTO) {

            Courses courses = courseRepo.findById(coursesDTO.getId()).orElseThrow(NoResultException ::new);
            courses.setName(coursesDTO.getName());

            courseRepo.save(courses);
    }


    public List<CoursesDTO> findAll() {

        List<Courses> coursesList = courseRepo.findAll();
        return coursesList.stream().map(u-> convert(u)).collect(Collectors.toList());
    }


    public PageDTO<List<CoursesDTO>> searchByName(SearchDTO searchDTO) {

        Sort sort = Sort.by("id").ascending();
        if (StringUtils.hasText(searchDTO.getSortedByColumn())){
            sort = Sort.by(searchDTO.getSortedByColumn()).ascending();
        }
        if(searchDTO.getCurrentPage() == null){
            searchDTO.setCurrentPage(0);
        }
        if(searchDTO.getSize() == null){
            searchDTO.setSize(10);
        }
        if(searchDTO.getKeyword() == null){
            searchDTO.setKeyword("");
        }

        PageRequest pageRequest = PageRequest.of(searchDTO.getCurrentPage(), searchDTO.getSize());
        Page<Courses> departmentPage = courseRepo.searchByName("%" +searchDTO.getKeyword()+"%",pageRequest);

        PageDTO<List<CoursesDTO>> pageDTO = new PageDTO<>();
        pageDTO.setTotalPage(departmentPage.getTotalPages());
        pageDTO.setTotalElement(departmentPage.getTotalElements());

        List<CoursesDTO> departmentDTOS = departmentPage.get().map(u -> convert(u)).collect(Collectors.toList());

        pageDTO.setData(departmentDTOS);

        return pageDTO;
    }

    private CoursesDTO convert(Courses courses){
        return new ModelMapper().map(courses,CoursesDTO.class);
    }
}
