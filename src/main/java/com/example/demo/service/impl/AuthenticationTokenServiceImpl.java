package com.example.demo.service.impl;


import com.example.demo.Exceptions.CustomException;
import com.example.demo.entities.AuthenticationToken;
import com.example.demo.entities.User;
import com.example.demo.repository.TokenRepository;
import com.example.demo.service.AuthenticationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationTokenServiceImpl implements AuthenticationTokenService {

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        this.tokenRepository.save(authenticationToken);
    }

    @Override
    public AuthenticationToken getToken(User user) {
       AuthenticationToken token = this.tokenRepository.findByUser(user);
       return token;
    }

    @Override
    public AuthenticationToken authenticate(String token){
        AuthenticationToken savedToken=this.tokenRepository.findByToken(token);
        if(savedToken==null){
            throw new CustomException("Wrong Token or Token not Exist");
        }
        return savedToken;
    }



}
