package ru.sfedu.SysyemOfUniversityManagment.model;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.sfedu.SysyemOfUniversityManagment.Constants;
import java.sql.Time;
import java.util.Objects;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long ID;
    @Column(name = "place",nullable = false)
    protected String place;

    @Temporal(TemporalType.TIME)
    @Column(name = "time",nullable = false)
    protected Time time;
    @Enumerated(EnumType.STRING)
    @Column(name = "dayOfWeek")
    protected Constants.DayOfWeek dayOfWeek;
    protected Event(String place,int hours,int minutes , Constants.DayOfWeek day){
        this.place = place;
        this.time = new Time(hours,minutes,0);
        this.dayOfWeek = day;
    }
}
