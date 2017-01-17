package com.tw.tradeaway.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tw.tradeaway.LocalDateDeserializer;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by prateeks on 1/13/17.
 */
@Entity
@Table(name="users")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", length = 40, nullable = false)
    private String name;

    @Column(name="email", unique=true, length = 40, nullable = false)
    private String email;

    @Column(name="username", unique=true, length = 15, nullable = false)
    private String username;

    @Column(name="password", nullable = false, length = 16)
    private String password;

    @Column(name="address", length = 100, nullable = false)
    private String address;

    @Column(name="mobile", length = 10, nullable = false)
    private String mobile;

    @Column(name="type", length = 6, nullable = false)
    private String type;

    @Column(name="gender")
    private char gender;

    @Column(name="dob", nullable = false)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dob; //YYYY-MM-DD

    @Column(name="email_verified")
    private boolean email_verified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public void setType(String type) {
        this.type = type;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setEmail_verified(boolean email_verified) {
        this.email_verified = email_verified;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getMobile() {
        return mobile;
    }

    public char getGender() {
        return gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    public boolean isEmail_verified() {
        return email_verified;
    }

}





