package ru.sfedu.SysyemOfUniversityManagment.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.sfedu.SysyemOfUniversityManagment.Constants;
import java.util.Objects;
@Entity
@Table(name = "Exams")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Exam extends Event{
    @Column(name = "nameOfDisc",nullable = false)
    private String nameOfDiscipline ;
    @Column(name = "type",nullable = false)
    private String type;
    @Column(name = "teacher's name",nullable = false)
    private String nameOfTeacher;
    public Exam(String place, int hours, int minutes, Constants.DayOfWeek day, String nameOfDiscipline , String type , String nameOfTeacher){
        super(place,hours,minutes,day);
        this.nameOfDiscipline = nameOfDiscipline;
        this.type = type;
        this.nameOfTeacher = nameOfTeacher;
    }
}
