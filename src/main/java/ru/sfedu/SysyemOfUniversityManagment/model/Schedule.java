package ru.sfedu.SysyemOfUniversityManagment.model;
import com.mongodb.lang.NonNull;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.SysyemOfUniversityManagment.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Schedule  {
    @Transient
    private static Logger log = LogManager.getLogger();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scheduleId")
    private long ID;
    @Column(name = "semester",nullable = false)
    private int semester;
    @Enumerated(EnumType.STRING)
    @Column(name = "typeOfSchedule",nullable = false)
    private Constants.TypeOfSchedule type;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "scheduleId")
    private List<Event> events = new ArrayList<>();
    public Schedule(int semester, Constants.TypeOfSchedule type){
        this.semester = semester;
        this.type = type;
    }
    public void appendEventToSchedule(Event event) throws Exception {
        if(events.stream().noneMatch(s->s.equals(event))){
            events.add(event);
            log.info("Event was appended");
        }
        else {
            log.error("Event record already exists");
            throw new Exception("Event record already exists");
        }
    }
}
