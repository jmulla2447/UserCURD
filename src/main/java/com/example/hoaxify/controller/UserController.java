package com.example.hoaxify.controller;

import com.example.hoaxify.dto.UserDto;
import com.example.hoaxify.error.APIError;
import com.example.hoaxify.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/users")
    public UserDto createUser(@Valid @RequestBody UserDto user){
        return userService.createUser(user);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public APIError handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest req){
        APIError error = new APIError(400, exception.getMessage(), req.getRequestURI());
        BindingResult bindResults = exception.getBindingResult();
        for(FieldError error1 : bindResults.getFieldErrors()){
            error.getErrorDetails().put(error1.getField(),error1.getDefaultMessage());
        }
        return error;
    }
}
