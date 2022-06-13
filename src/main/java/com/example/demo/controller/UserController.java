package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.entities.User;
import com.example.demo.helper.ApiResponse;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    //creating user
    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody User user) throws Exception {

              return  this.userService.registerUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse>  loginUser(@RequestBody UserDto userDto) throws Exception{
        return this.userService.loginUser(userDto);
    }

    @GetMapping("/logout/{token}")
    public ResponseEntity<ApiResponse>  logOutUser(@PathVariable String token) throws Exception{
        return this.userService.logOutUser(token);
    }



}
