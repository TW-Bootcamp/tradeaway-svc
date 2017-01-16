package com.tw.tradeaway.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by prateeks on 1/13/17.
 */
@Entity
@Table(name="tbl_user")
public class User {

    @Id
    private Long id;

    @Column(name="login")
    private String login;

    @Column(name="password")
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
