package com.coker.springboot.controller;

import com.coker.springboot.dto.*;
import com.coker.springboot.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/create")
    public ResponseDTO<Void> createDepartment(@ModelAttribute  @Valid DepartmentDTO departmentDTO){

        departmentService.create(departmentDTO);
        return ResponseDTO.<Void>builder()
                .message("Create successfully")
                .status(200)
                .build();
    }
    @PostMapping("/create-json")
    public ResponseDTO<Void> createJSON(@RequestBody  @ModelAttribute  @Valid DepartmentDTO departmentDTO){

        departmentService.create(departmentDTO);
        return ResponseDTO.<Void>builder()
                .status(200)
                .message("Create successfully")
                .build();

    }
    @DeleteMapping("/")
    public ResponseDTO<Void> deleteDepartment(@ModelAttribute int id){

        departmentService.delete(id);
        return ResponseDTO.<Void>builder()
                .message("Delete Successfully")
                .status(200)
                .build();

    }
    @GetMapping("/")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseDTO<DepartmentDTO> get(@Param("id") int id){

        DepartmentDTO departmentDTO = departmentService.getById(id);

       return ResponseDTO.<DepartmentDTO>builder()
               .status(200)
               .data(departmentDTO)
               .build();

    }

    @PutMapping("/")
    public ResponseDTO<Void> pushUpdateDepartment(@ModelAttribute DepartmentDTO departmentDTO){
        departmentService.update(departmentDTO);
        return ResponseDTO.<Void>builder()
                .status(200)
                .message("Update successfully")
                .build();

    }
    @PostMapping("/search")
    public ResponseDTO<PageDTO<List<DepartmentDTO>>> searchUser(@ModelAttribute SearchDTO searchDTO) {

        PageDTO<List<DepartmentDTO>> pageDTO  = departmentService.searchByName(searchDTO);
        return ResponseDTO.<PageDTO<List<DepartmentDTO>>>builder()
                .message("Department List")
                .status(200)
                .data(pageDTO)
                .build();
    }


}
