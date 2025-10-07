package com.example.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class SubjectStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "subject_id")
    private int subjectId;

    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_subject_student_subject_subject_id"), insertable = false, updatable = false)
    private Subject subject;

    @Column(name = "student_id")
    private int studentId;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_subject_student_student_student_id"), insertable = false, updatable = false)
    private Student student;

    public SubjectStudent() {
    }

    public SubjectStudent(Subject subject, Student student) {
        this.subject = subject;
        this.student = student;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
}
