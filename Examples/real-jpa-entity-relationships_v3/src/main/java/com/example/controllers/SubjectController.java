package com.example.controllers;

import com.example.models.Subject;
import com.example.repositories.SubjectRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/subject")
public class SubjectController {
    private final SubjectRepository subjectRepository;

    public SubjectController(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @GetMapping("/")
    public List<Subject> getAll() {
        List<Subject> subjects = subjectRepository.findAll();

        return subjects;
    }

    @GetMapping("/{id}")
    public Subject getById(@PathVariable int id) {
        Subject subject = subjectRepository.findById(id).orElseThrow(() -> new RuntimeException("Subject not found"));

        return subject;
    }

    @PostMapping("/")
    public void save(@RequestBody Subject subject) {
        subjectRepository.save(subject);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody Subject subject) {
        Subject subjectInDb = subjectRepository.findById(id).orElseThrow(() -> new RuntimeException("Subject not found"));

        subjectInDb.setTeacherId(subject.getTeacherId());
        subjectInDb.setName(subject.getName());
        subjectInDb.setHours(subject.getHours());

        subjectRepository.save(subjectInDb);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        subjectRepository.deleteById(id);
    }
}
