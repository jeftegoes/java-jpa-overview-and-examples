package com.example.real_jpa_entity_relationships.controllers;

import com.example.real_jpa_entity_relationships.models.Discipline;
import com.example.real_jpa_entity_relationships.repositories.DisciplineRepository;
import com.example.real_jpa_entity_relationships.repositories.DisciplineStudentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/discipline_student")
public class DisciplineStudentController {
    private final DisciplineStudentRepository disciplineStudentRepository;

    public DisciplineStudentController(DisciplineStudentRepository disciplineStudentRepository) {
        this.disciplineStudentRepository = disciplineStudentRepository;
    }

    @PostMapping("/student/{studentId}/discipline/{disciplineId}")
    public void save(@PathVariable int studentId, @PathVariable int disciplineId) {
        disciplineStudentRepository.save(disciplineId, studentId);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody Discipline discipline) {
        disciplineStudentRepository.update(id, discipline);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        disciplineStudentRepository.delete(id);
    }
}
