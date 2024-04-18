package com.coker.springboot.controller;

import com.coker.springboot.dto.ResponseDTO;
import com.coker.springboot.dto.StudentDTO;
import com.coker.springboot.model.Student;
import com.coker.springboot.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class  StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/create")
    public ResponseDTO<Void> create(@RequestBody @Valid StudentDTO studentDTO) throws Exception{
        studentService.create(studentDTO);
        return ResponseDTO.<Void>builder()
                .status(200)
                .message("Create Successfully")
                .build();
    }
    @PutMapping("/")
    public ResponseDTO<Student>update(@RequestBody StudentDTO studentDTO){

        studentService.update(studentDTO);

      return ResponseDTO.<Student>builder().
              status(200)
              .message("update successfully")
              .build();
   }
   @DeleteMapping("/")
    public ResponseDTO<Void> delete(@RequestParam("id") int id){
        studentService.delete(id);
        return  ResponseDTO.<Void>builder().status(200).build();
   }

    @GetMapping("/{id}")
    public ResponseDTO<StudentDTO> get1(@PathVariable("id") int id) {
        return ResponseDTO.<StudentDTO>builder().status(200).data(studentService.getById(id)).build();
    }
}
