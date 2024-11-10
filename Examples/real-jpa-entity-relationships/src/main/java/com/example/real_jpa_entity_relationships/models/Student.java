package com.example.real_jpa_entity_relationships.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "discipline_student",
//            joinColumns = @JoinColumn(name = "student_id"),
//            inverseJoinColumns = @JoinColumn(name = "discipline_id")
//    )
//    private List<Discipline> disciplines;

    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public List<Discipline> getDisciplines() {
//        return disciplines;
//    }
//
//    public void setDisciplines(List<Discipline> disciplines) {
//        this.disciplines = disciplines;
//    }
//
//    public void addDiscipline(Discipline discipline) {
//        if (disciplines == null) {
//            disciplines = new ArrayList<>();
//        }
//
//        disciplines.add(discipline);
//    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
