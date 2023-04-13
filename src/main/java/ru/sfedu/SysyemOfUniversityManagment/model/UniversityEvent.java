package ru.sfedu.SysyemOfUniversityManagment.model;

import jakarta.persistence.Column;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.sfedu.SysyemOfUniversityManagment.Constants;
import java.util.Objects;
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class UniversityEvent extends Event {
    @Column(name = "information",nullable = false)
    private String information;
    public UniversityEvent(int ID, int scheduleId, String place, int hours, int minutes, Constants.DayOfWeek day, String information){
        super(place,hours,minutes,day);
        this.information = information;
    }
}
