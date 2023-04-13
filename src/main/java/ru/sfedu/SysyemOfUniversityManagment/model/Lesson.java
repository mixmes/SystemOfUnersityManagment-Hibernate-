package ru.sfedu.SysyemOfUniversityManagment.model;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.sfedu.SysyemOfUniversityManagment.Constants;

import java.util.Date;
import java.util.Objects;
@Table(name = "lessons")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Lesson extends Event{
    @Column(name = "nameOfDiscipline",nullable = false)
    private String nameOfDiscipline;
    @Column(name = "type",nullable = false)
    private String type;
    @Column(name = "nameOfTeacher")
    private String nameOfTeacher;
    public Lesson(int ID, int scheduleId, String place, int hours, int minutes, Constants.DayOfWeek day, String nameOfDiscipline, String type , String nameOfTeacher){
        super(place,hours,minutes,day);
        this.nameOfDiscipline = nameOfDiscipline;
        this.nameOfTeacher = nameOfTeacher;
        this.type = type;
    }
}
