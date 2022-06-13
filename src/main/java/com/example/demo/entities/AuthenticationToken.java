package com.example.demo.entities;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="tokens")
public class AuthenticationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    private String token;

    @OneToOne(targetEntity = User.class ,fetch=FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(nullable = false,name="user_id")
    private User user;

    public AuthenticationToken() {
    }

    public AuthenticationToken(User user){
        this.user=user;
        this.token= UUID.randomUUID().toString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



}
