package com.arlet.lab4.data;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users")

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String login;
    private String password;
    private String salt;
}
