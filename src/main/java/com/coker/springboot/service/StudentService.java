package com.coker.springboot.service;

import com.coker.springboot.dto.StudentDTO;
import com.coker.springboot.model.Student;
import com.coker.springboot.model.User;
import com.coker.springboot.repository.StudentRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
    public class StudentService{
    @Autowired
     StudentRepo studentRepo;
//    @Autowired
//     UserRepo userRepo;

    @Transactional
    public void create(StudentDTO studentDTO) {

        //If dont use cascade, first we need to create and save User entity into db and then
        //create a Student entity base on the User entity we have just save. So that we can make sure the synchronization
        // between User and Student

        User user = new ModelMapper().map(studentDTO.getUser(), User.class);

        Student student = new Student();

        student.setStudentCode(studentDTO.getStudentCode());
        student.setUser(user);
        studentRepo.save(student);


    }


    @Transactional
    public void update(StudentDTO studentDTO) {
        Student student = studentRepo.getById(studentDTO.getUser().getId());

        student.setStudentCode(studentDTO.getStudentCode());
        studentRepo.save(student);
    }


    @Transactional
    public void delete(int id) {
        studentRepo.deleteById(id);
    }



    @Transactional
    public StudentDTO getById(int id) {
       return new ModelMapper().map(studentRepo.getById(id),StudentDTO.class);
    }


    public StudentDTO convert(Student student){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(student,StudentDTO.class);
    }

 }
