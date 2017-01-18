package com.tw.tradeaway.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by poojadhupar on 1/18/17.
 */
@Entity
@Table(name="buyer")
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="gender")
    private char gender;

    @Column(name="dob", nullable = false)
    @Type(type="java.time.LocalDate")
    private LocalDate dob;

    @JoinColumn(name="user_id", nullable = false)
    @OneToOne
    private User user;

    public Buyer(char gender, LocalDate dob, User user) {
        this.gender = gender;
        this.dob = dob;
        this.user = user;
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
}
