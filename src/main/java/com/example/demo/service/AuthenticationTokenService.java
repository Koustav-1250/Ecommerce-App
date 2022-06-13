package com.example.demo.service;

import com.example.demo.entities.AuthenticationToken;
import com.example.demo.entities.User;

public interface AuthenticationTokenService {
    void saveConfirmationToken(AuthenticationToken authenticationToken);
    AuthenticationToken getToken(User user);
    AuthenticationToken authenticate(String token);

}
