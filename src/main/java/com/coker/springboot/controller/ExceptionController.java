package com.coker.springboot.controller;

import com.coker.springboot.dto.ResponseDTO;
import jakarta.persistence.NoResultException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@ControllerAdvice
@Slf4j
public class ExceptionController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler({HttpClientErrorException.NotFound.class})
    public ResponseDTO<String> notFound(Exception e){
        e.printStackTrace();
        return ResponseDTO.<String>builder()
                .message("Not found")
                .status(404)
                .build();

    }

    @ExceptionHandler({NoResultException.class})
    public ResponseDTO<String> noResult(Exception e) {
        e.printStackTrace();
      return   ResponseDTO.<String>builder()
                .status(401)
                .message("not found data")
                .build();
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseDTO<String> notValidated(MethodArgumentNotValidException e){
        e.printStackTrace();

        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        String msg="";

        for(ObjectError err: allErrors){
            FieldError fieldError = (FieldError) err;
            msg += fieldError.getField() + ": " + err.getDefaultMessage() + ";";
        }
        return ResponseDTO.<String>builder()
                .status(400)
                .message("Invalid data")
                .build();
    }
    @ExceptionHandler({SQLIntegrityConstraintViolationException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseDTO<String> duplicate(SQLIntegrityConstraintViolationException e) {
        e.printStackTrace();
        return ResponseDTO.<String>builder()
                .status(401)
                .message("Duplicate Data")
                .build();
    }

}
