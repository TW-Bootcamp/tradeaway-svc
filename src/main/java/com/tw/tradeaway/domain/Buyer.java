package com.tw.tradeaway.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by poojadhupar on 1/18/17.
 */
@Entity
@Table(name="buyers")
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", length = 40, nullable = false)
    private String name;

    @Column(name="email", unique=true, length = 40, nullable = false)
    private String email;

    @Column(name="address", length = 100, nullable = false)
    private String address;

    @Column(name="mobile", length = 10, nullable = false)
    private String mobile;

    @Column(name="gender")
    private char gender;

    @Column(name="dob", nullable = false)
    @Type(type="java.time.LocalDate")
    private LocalDate dob;

    @JoinColumn(name="user_id", nullable = false)
    @OneToOne
    private User user;

    public Buyer(){

    }

    public Buyer(LocalDate dob, User user ,String name, String email,  String address, String mobile) {

        this.dob = dob;
        this.user = user;
        this.name = name;
        this.email = email;
        this.address = address;
        this.mobile = mobile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
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

}
