package ru.sfedu.SysyemOfUniversityManagment.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class EducationalMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "edMat_id")
    private long id;
    @OneToMany(cascade =CascadeType.ALL)
    @JoinColumn(name = "edMat_id")
    private List<Lection> lections = new ArrayList<>();
    @OneToMany(cascade =CascadeType.ALL)
    @JoinColumn(name = "edMat_id")
    private List<PracticalTask> tasks = new ArrayList<>();

}
