package com.example.real_jpa_entity_relationships.controllers;

import com.example.real_jpa_entity_relationships.models.Subject;
import com.example.real_jpa_entity_relationships.repositories.SubjectRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/discipline")
public class SubjectController {
    private final SubjectRepository subjectRepository;

    public SubjectController(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @GetMapping("/")
    public List<Subject> getAll() {
        List<Subject> subjects = subjectRepository.getAll();

        return subjects;
    }

    @GetMapping("/{id}")
    public Subject getById(@PathVariable int id) {
        Subject subject = subjectRepository.findById(id);

        return subject;
    }

    @PostMapping("/")
    public void save(@RequestBody Subject subject) {
        subjectRepository.save(subject);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody Subject subject) {
        subjectRepository.update(id, subject);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        subjectRepository.delete(id);
    }
}
