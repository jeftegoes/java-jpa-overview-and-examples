package com.example.controllers;

import com.example.models.Teacher;
import com.example.repositories.TeacherRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/teacher")
public class TeacherController {
    private final TeacherRepository teacherRepository;

    public TeacherController(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @GetMapping("/")
    public List<Teacher> getAll() {
        List<Teacher> teachers = teacherRepository.getAll();

        return teachers;
    }

    @GetMapping("/{id}")
    public Teacher getById(@PathVariable int id) {
        Teacher teacher = teacherRepository.findById(id);

        return teacher;
    }

    @PostMapping("/")
    public void save(@RequestBody Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody Teacher teacher) {
        teacherRepository.update(id, teacher);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        teacherRepository.delete(id);
    }
}
