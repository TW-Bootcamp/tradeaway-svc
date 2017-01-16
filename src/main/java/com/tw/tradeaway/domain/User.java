package com.tw.tradeaway.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Calendar;
import java.util.Date;

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
    @Temporal(TemporalType.DATE)
    private java.util.Calendar dob;

    @Column(name="is_email_verified")
    private boolean is_email_verified;

    public Long getId() {
        return id;
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

    public String getType() {
        return type;
    }

    public char getGender() {
        return gender;
    }

    public Calendar getDob() {
        return dob;
    }

    public boolean isIs_email_verified() {
        return is_email_verified;
    }

    public void setIs_email_verified(boolean is_email_verified) {
        this.is_email_verified = is_email_verified;
    }
}





