package com.example.demo.repository;

import com.example.demo.entities.AuthenticationToken;
import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken,Long> {

    public AuthenticationToken findByToken(String token);

    public AuthenticationToken findByUser(User user);
}
