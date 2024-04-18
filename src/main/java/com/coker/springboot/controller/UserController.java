package com.coker.springboot.controller;

import com.coker.springboot.dto.PageDTO;
import com.coker.springboot.dto.ResponseDTO;
import com.coker.springboot.dto.SearchDTO;
import com.coker.springboot.dto.UserDTO;
import com.coker.springboot.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseDTO<List<UserDTO>> listUser(){

        List<UserDTO> users = userService.findAll();
        return ResponseDTO.<List<UserDTO>>builder()
                        .status(200)
                        .message("List of users:")
                        .data(users)
                        .build();

    }
    @PostMapping("/create")
    public ResponseDTO<Void> create(@ModelAttribute @Valid UserDTO userDTO)
            throws Exception{

            userService.add(userDTO);
            return   ResponseDTO.<Void>builder()
                    .status(200)
                    .message("Create successfully")
                    .build();
    }

    @PutMapping("/")
    public ResponseDTO<Void> update(@ModelAttribute UserDTO userDTO){

        userService.update(userDTO);
        return ResponseDTO.<Void>builder()
                .status(200)
                .message("Update successfully")
                .build();
    }

    @DeleteMapping("/")
    public ResponseDTO<Void> deleteUser(@RequestParam("id") int id){
        userService.deleteUser(id);
        return ResponseDTO.<Void>builder()
                .status(200)
                .message("Delete Successfully")
                .build();
    }


    @PostMapping("/search")
    public ResponseDTO<PageDTO<List<UserDTO>>> searchUser(@ModelAttribute SearchDTO searchDTO) throws Exception {

        PageDTO<List<UserDTO>> pageDTO  = userService.searchUser(searchDTO);
        return ResponseDTO.<PageDTO<List<UserDTO>>>builder()
                .message("User List")
                .status(200)
                .data(pageDTO)
                .build();
    }


}
