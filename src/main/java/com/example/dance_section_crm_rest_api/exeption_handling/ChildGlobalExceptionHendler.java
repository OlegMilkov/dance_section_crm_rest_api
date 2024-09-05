package com.example.dance_section_crm_rest_api.exeption_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ChildGlobalExceptionHendler {
    @ExceptionHandler
    public ResponseEntity<ChildIncorrectData> handleException(NoSuchChildException exception){
        ChildIncorrectData data =  new ChildIncorrectData();
        data.setInfo(exception.getMessage());
        return  new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ChildIncorrectData> handleException(Exception exception){
        ChildIncorrectData data =  new ChildIncorrectData();
        data.setInfo(exception.getMessage());
        return  new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }


}
