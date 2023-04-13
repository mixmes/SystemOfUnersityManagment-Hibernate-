package ru.sfedu.SysyemOfUniversityManagment.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;
import java.util.Objects;
@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class StudentWork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID ;
    @Column(name = "nameOfWork",nullable = false)
    private String nameOfWork ;
    @Column(name = "discipline",nullable = false)
    private String disciplines;
    @Column(name = "mark")
    private int mark ;
    @Column(name = "isHomework",nullable = false)
    private boolean homework;
    @Column(name = "fileOfWork",nullable = false)
    private File fileOfWork ;
}
