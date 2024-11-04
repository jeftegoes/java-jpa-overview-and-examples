package com.example.models;

import jakarta.persistence.*;

@Entity
@Table(name = "teacher_detail")
public class TeacherDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "hobby")
    private String hobby;

    @Column(name = "linkedin")
    private String linkedin;

    @OneToOne(mappedBy = "teacherDetail",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Teacher teacher;

    public TeacherDetail() {
    }

    public TeacherDetail(String hobby, String linkedin) {
        this.hobby = hobby;
        this.linkedin = linkedin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "TeacherDetail{" +
                "id=" + id +
                ", linkedin='" + linkedin + '\'' +
                ", teacher=" + teacher +
                '}';
    }
}