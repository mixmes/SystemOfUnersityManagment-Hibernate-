package ru.sfedu.SysyemOfUniversityManagment.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Student {
    @Transient
    private static final Logger log = LogManager.getLogger(Discipline.class);
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "studentId")
    private long ID;
    @Column(name = "name",nullable = false)
    private String name ;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "studentId")
    private List<StudentWork> studentWorks = new ArrayList<>();
    @ManyToOne
    private StudentGroup myGroup ;
    public Student(String name){
        this.name = name;
    }

    public void checkHavingDiscipline(Discipline discipline) throws Exception {
        if(this.studentWorks.stream().noneMatch(s->s.getDisciplines() == discipline.getName())){
            log.error("No such discipline");
            throw new Exception("Student doesn't have such discipline");
        }
    }
    public void checkHavingStudentWork(String nameOfWork) throws Exception {
        if(this.studentWorks.stream().noneMatch(s->s.getNameOfWork() == nameOfWork)){
            log.error("No such work");
            throw new Exception("Student doesn't have such work");
        }
    }
    public void createStudentWork(File work,String nameOfWork,Discipline discipline){
        StudentWork studWork = new StudentWork();
        studWork.setNameOfWork(nameOfWork);
        studWork.setDisciplines(discipline.getName());
        studWork.setFileOfWork(work);
        studWork.setHomework(true);

        this.studentWorks.add(studWork);
        log.info("Student work was created");
    }
}
