package com.example.responses;

import com.example.models.SubjectStudent;
import lombok.Data;

import java.util.List;

@Data
public class StudentResponse {
    private int id;
    private String name;
    private List<SubjectStudentResponse> subjectStudents;
}
