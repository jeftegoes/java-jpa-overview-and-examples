package com.example.controllers;

import com.example.entities.Subject;
import com.example.repositories.SubjectStudentRepository;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/subject_student")
public class SubjectStudentController {
    private final SubjectStudentRepository subjectStudentRepository;

    public SubjectStudentController(SubjectStudentRepository subjectStudentRepository) {
        this.subjectStudentRepository = subjectStudentRepository;
    }

    @PostMapping("/student/{studentId}/subject/{subjectId}")
    public void save(@PathVariable int studentId, @PathVariable int subjectId) {
        subjectStudentRepository.save(studentId, subjectId);
    }
}
