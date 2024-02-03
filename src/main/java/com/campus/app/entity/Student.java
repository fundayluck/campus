package com.campus.app.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name ="name", nullable = false)
    private String name;
    @Column(name ="nim", unique = true, nullable = false)
    private String nim;
    @Column(name ="address", nullable = false)
    private String address;
    @Column(name ="dateOfBirth", nullable = false)
    private Date dateOfBirth;

}
