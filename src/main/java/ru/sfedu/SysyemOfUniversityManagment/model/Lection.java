package ru.sfedu.SysyemOfUniversityManagment.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;
import java.io.Serializable;
import java.util.Objects;
@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Lection   {
    @Id
    @GeneratedValue
    @Column(name = "lection_id")
    private long id;
    @Column(name = "LectionInformation",nullable = false)
    private String information;
    @Column(name = "information")
    private File lection ;
    public Lection(String URI,String information){
        this.lection = new File(URI);
        this.information = information;
    }
}
