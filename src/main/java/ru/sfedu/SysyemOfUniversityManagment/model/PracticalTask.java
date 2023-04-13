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
public class PracticalTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private long id;
    @Column(name = "TaskInformation")
    private String information;
    @Column(name = "task",nullable = false)
    private File task;
    public PracticalTask(String URI, String information){
        this.task = new File(URI);
        this.information = information;
    }
}
