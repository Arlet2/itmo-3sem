package com.arlet.lab4.data;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="users")
@Data
public class User {
    @Id
    private String login;
    private String password;
    private String salt;

    public User() {

    }
    public User(String login, String password, String salt) {
        this.login = login;
        this.password = password;
        this.salt = salt;
    }
}
