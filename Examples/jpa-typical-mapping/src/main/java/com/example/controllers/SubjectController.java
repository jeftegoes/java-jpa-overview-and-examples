package com.example.controllers;

import com.example.entities.Subject;
import com.example.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@RequestScope
@RestController()
@RequestMapping("/subject")
public class SubjectController {
    private final SubjectRepository subjectRepository;

    @Autowired
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
