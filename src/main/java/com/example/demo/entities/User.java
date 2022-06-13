package com.example.demo.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long uid;
    String userName;
    String address;
    String email;
    String password;
    Date dob;

    ROLE role = ROLE.NORMAL;

    public User() {

    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ROLE getRole() {
        return role;
    }

    public void setRole(ROLE role) {
        this.role = role;
    }



    public String getAddress() {
        return address;
    }

    public Date getDob() {
        return dob;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
}
