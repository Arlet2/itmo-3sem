package com.arlet.lab4.data;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "points4")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int x;
    private float y;
    private int r;
    private String status;
    private String date;
    private String scriptTime;
    private String owner;
}
