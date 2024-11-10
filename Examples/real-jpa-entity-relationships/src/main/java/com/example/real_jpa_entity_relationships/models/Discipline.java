package com.example.real_jpa_entity_relationships.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "discipline")
public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "hours")
    private int hours;

    @ManyToOne()
    @JoinColumn(name = "teacherId")
    private Teacher teacher;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "discipline_student",
//            joinColumns = @JoinColumn(name = "discipline_id"),
//            inverseJoinColumns = @JoinColumn(name = "student_id")
//    )
//    private List<Student> students;

    public Discipline() {
    }

    public Discipline(String name, int hours, Teacher teacher) {
        this.name = name;
        this.hours = hours;
        this.teacher = teacher;
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

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Discipline{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hours=" + hours +
                ", teacher=" + teacher +
//                ", students=" + students +
                '}';
    }
}
