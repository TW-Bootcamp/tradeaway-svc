package com.tw.tradeaway.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tw.tradeaway.LocalDateDeserializer;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;

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


    @Column(name="email_verified")
    private boolean email_verified;

    @Column(name = "authority", nullable = false)
    private String authority;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public boolean isEmail_verified() {
        return email_verified;
    }

    public void setEmail_verified(boolean email_verified) {
        this.email_verified = email_verified;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
