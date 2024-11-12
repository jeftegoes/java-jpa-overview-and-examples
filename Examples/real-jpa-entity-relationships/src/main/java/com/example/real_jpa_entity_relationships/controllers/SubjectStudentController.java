package com.example.real_jpa_entity_relationships.controllers;

import com.example.real_jpa_entity_relationships.models.Subject;
import com.example.real_jpa_entity_relationships.repositories.SubjectStudentRepository;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/discipline_student")
public class SubjectStudentController {
    private final SubjectStudentRepository subjectStudentRepository;

    public SubjectStudentController(SubjectStudentRepository subjectStudentRepository) {
        this.subjectStudentRepository = subjectStudentRepository;
    }

    @PostMapping("/student/{studentId}/discipline/{disciplineId}")
    public void save(@PathVariable int studentId, @PathVariable int disciplineId) {
        subjectStudentRepository.save(disciplineId, studentId);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody Subject subject) {
        subjectStudentRepository.update(id, subject);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        subjectStudentRepository.delete(id);
    }
}
