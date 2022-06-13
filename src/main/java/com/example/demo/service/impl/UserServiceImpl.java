package com.example.demo.service.impl;

import java.lang.*;
import java.util.Optional;

import com.example.demo.Exceptions.CustomException;
import com.example.demo.dto.UserDto;
import com.example.demo.entities.AuthenticationToken;
import com.example.demo.entities.User;
import com.example.demo.helper.ApiResponse;
import com.example.demo.repository.TokenRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.demo.service.AuthenticationTokenService;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;


@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationTokenService authenticationTokenService;

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    @Transactional
    public ResponseEntity<ApiResponse> registerUser(@NotNull User user) throws Exception {
        User local = this.userRepository.findByUserName(user.getUserName());
        if (local == null) {
            local = this.userRepository.save(user);

            final AuthenticationToken authenticationToken = new AuthenticationToken(user);
            authenticationTokenService.saveConfirmationToken(authenticationToken);

            return new ResponseEntity<>(new ApiResponse(true, local.getUserName()), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(new ApiResponse(false, "User already Exists"), HttpStatus.ALREADY_REPORTED);
        }


    }

    @Override
    public ResponseEntity<ApiResponse> loginUser(@NotNull UserDto userDto) throws Exception {
        User local = this.userRepository.findByUserName(userDto.getUserName());

        if (local == null) {
            throw new CustomException("User not registered");
        } else {

            if (local.getPassword().equals(local.getPassword())) {
                AuthenticationToken token=authenticationTokenService.getToken(local);
                if(token==null){
                    throw new CustomException("Please logIn Again");
                }
                return new ResponseEntity<>(new ApiResponse(true, token.getToken() ), HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<>(new ApiResponse(false, "Bad Credentials"), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<ApiResponse> logOutUser(String token) throws Exception {

        AuthenticationToken localToken = this.tokenRepository.findByToken(token);
        Optional<User> savedUser=this.userRepository.findById(Long.valueOf(localToken.getUser().getUid()));
        if(savedUser.isPresent()==false){
            throw new CustomException("User Not preset");
        }
       AuthenticationToken newToken =new AuthenticationToken(savedUser.get());
       authenticationTokenService.saveConfirmationToken(newToken);
       this.tokenRepository.deleteById(localToken.getId());
       return new ResponseEntity<>(new ApiResponse(true,"User LogOut Successfully" + newToken.getToken()),HttpStatus.ACCEPTED);

    }


}
