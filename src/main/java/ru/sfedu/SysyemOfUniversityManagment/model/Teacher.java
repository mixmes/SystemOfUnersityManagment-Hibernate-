package ru.sfedu.SysyemOfUniversityManagment.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.Serializable;
import java.util.*;
import static ru.sfedu.SysyemOfUniversityManagment.Constants.MAX_MARK;
@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Teacher implements Serializable {
    @Transient
    private static final Logger log = LogManager.getLogger(Discipline.class);
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private long ID;
    @Column(name = "teacherName")
    private String name;
    @OneToMany(cascade =CascadeType.ALL)
    @JoinColumn(name = "teacher_id")
    private List<Discipline> disciplines = new ArrayList<>();
    public Teacher(String name){
        this.name = name;
    }
    public void checkMaxMark(int mark) throws Exception {
        if(mark>MAX_MARK){
            log.error("Max mark exceeding");
            throw new Exception("Mark exceed max mark");
        }
    }
    public void estimateStudentWork(Student student,String nameOfWork,Discipline discipline,int mark) throws Exception {
        checkHavingDiscipline(discipline);
        student.checkHavingDiscipline(discipline);
        student.checkHavingStudentWork(nameOfWork);
        checkMaxMark(mark);
        student.getStudentWorks().stream().filter(s->s.getNameOfWork() == nameOfWork).findFirst().get().setMark(mark);
        log.info("Work estimated");
    }
    public StudentWork retrieveStudentWork(Student student, String nameOfWork,Discipline discipline) throws Exception {
        checkHavingDiscipline(discipline);
        student.checkHavingDiscipline(discipline);
        student.checkHavingStudentWork(nameOfWork);
        log.info("Work retrieved");
        return student.getStudentWorks().stream().filter(s->s.getNameOfWork() == nameOfWork).findFirst().get();
    }
    public void createEducationMaterial(Discipline discipline) throws Exception {
        checkHavingDiscipline(discipline);
        this.getDisciplines().stream().filter(s->s.equals(discipline)).findFirst().get().setEducationalMaterial(new EducationalMaterial());
        log.info("Education matarial record was created");
    }
    public void addPracticalTask(Discipline discipline,PracticalTask practicalTask) throws Exception {
        checkHavingDiscipline(discipline);
        if(discipline.getEducationalMaterial().getTasks().stream().noneMatch(s->s.equals(practicalTask))){
            discipline.getEducationalMaterial().getTasks().add(practicalTask);
            log.info("Practical task was added");
        }
        else {
            discipline.getEducationalMaterial().getTasks().stream().filter(s->s.equals(practicalTask)).forEach(s->s = practicalTask);
            log.info("Practical task was updated");
        }
    }
    public void deletePracticalTask(Discipline discipline , PracticalTask practicalTask) throws Exception {
        checkHavingDiscipline(discipline);
        if(discipline.getEducationalMaterial().getTasks().stream().noneMatch(s->s.equals(practicalTask))){
            log.error("No such task");
            throw new Exception("No such practical task");
        }
        else {
            discipline.getEducationalMaterial().getTasks().remove(practicalTask);
            log.info("Practical task was deleted");
        }
    }
    public void addLection(Discipline discipline,Lection lection) throws Exception {
        checkHavingDiscipline(discipline);
        if(discipline.getEducationalMaterial().getLections().stream().noneMatch(s->s.equals(lection))){
            discipline.getEducationalMaterial().getLections().add(lection);
            log.info("Lection was added");
        }
        else {
            discipline.getEducationalMaterial().getLections().stream().filter(s->s.equals(lection)).forEach(s->s =lection);
            log.info("Lection was updated");
        }
    }
    public void deleteLection(Discipline discipline, Lection lection) throws Exception {
        checkHavingDiscipline(discipline);
        if(discipline.getEducationalMaterial().getLections().stream().noneMatch(s->s.equals(lection))){
            log.error("No such lection");
            throw new Exception("No such lection");
        }
        else {
            discipline.getEducationalMaterial().getLections().remove(lection);
            log.info("Lection was deleted");
        }
    }
    public void checkHavingDiscipline(Discipline discipline) throws Exception {
        if(this.getDisciplines().stream().noneMatch(s->s.equals(discipline))){
            log.error("No such discipline");
            throw new Exception("Teacher doesn't have such discipline");
        }
    }
    public void appendDiscipline(Discipline discipline) throws Exception {
        if(disciplines.stream().noneMatch(s->s.equals(discipline))){
            disciplines.add(discipline);
            log.info("Discipline was added");
        }
        else {
            log.error("Discipline already exists");
            throw new Exception("Discipline already exists in this list");
        }
    }
}
