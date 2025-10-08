package com.example.responses;

import com.example.models.Student;
import com.example.models.Subject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
public class SubjectStudentResponse {
    private int id;
    private int subjectId;
    private int studentId;
    private Subject subject;
    private StudentResponse student;
}
