package com.alura.forumhub.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(Exception.class)
    public ResponseEntity tratarErro(Exception ex){

        return ResponseEntity.badRequest().body(ex.getMessage());

    }

}
