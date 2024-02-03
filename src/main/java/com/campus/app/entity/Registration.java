package com.campus.app.entity;

import com.campus.app.helper.AcceptanceStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int idStudent;
    private int idStudyProgram;
    private Date registrationDate;
    @Enumerated(EnumType.STRING)
    private AcceptanceStatus acceptanceStatus;

}
