package com.example.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "subject_student")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "subject_id")
    private int subjectId;

    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_subject_student_subject_subject_id"), insertable = false, updatable = false)
//    @JsonIgnoreProperties("subjectStudents")
    private Subject subject;

    @Column(name = "student_id")
    private int studentId;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_subject_student_student_student_id"), insertable = false, updatable = false)
//    @JsonIgnoreProperties("subjectStudents")
    private Student student;
}
