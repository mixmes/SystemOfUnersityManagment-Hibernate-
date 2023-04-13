package ru.sfedu.SysyemOfUniversityManagment.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class StudentGroup {
    @Transient
    private static final Logger log =LogManager.getLogger(Discipline.class);
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "groupId")
    private long ID;
    @Column(name = "course",nullable = false)
    private int course;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "codeOfGroup",nullable = false)
    private String codeOfGroup;
    @OneToMany(mappedBy = "myGroup",cascade = CascadeType.ALL)
    private List<Student> groupComposition = new ArrayList<>();
    public StudentGroup(int course, String name, String codeOfGroup){
        this.course = course;
        this.name = name;
        this.codeOfGroup = codeOfGroup;
    }
    public void addStudentToGroup(Student student) throws Exception {
        if(groupComposition.stream().noneMatch(s->s.equals(student))){
            groupComposition.add(student);
            log.info("Student was added to group");
        }
        else {
            log.error("Student already in this group");
            throw new Exception("Student already in this group");
        }

    }
    public void deleteStudentFromGroup(Student student) throws Exception {
        if(groupComposition.stream().anyMatch(s->s.equals(student))){
            groupComposition.remove(student);
            log.info("Student was deleted from group");
        }
        else {
            log.error("Student doesn't exists");
            throw new Exception("Student doesn't exist in this group");
        }
    }
    public Student getStudentById(int id) throws Exception {
        Optional<Student> student = groupComposition.stream().filter(s->s.getID() == id).findFirst();
        if(!student.isPresent()){
            throw new Exception("Student doesn't exist");
        }
        log.info("Student was obtained");
        return student.get();
    }
}
