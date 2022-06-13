package com.example.demo.service;
import com.example.demo.dto.UserDto;
import com.example.demo.entities.AuthenticationToken;
import  com.example.demo.entities.User;
import com.example.demo.helper.ApiResponse;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

public interface UserService {

    public ResponseEntity<ApiResponse> registerUser(User user) throws Exception;
    public ResponseEntity<ApiResponse> logOutUser(String token) throws Exception;
    public ResponseEntity<ApiResponse> loginUser(UserDto userDto) throws Exception;



}
