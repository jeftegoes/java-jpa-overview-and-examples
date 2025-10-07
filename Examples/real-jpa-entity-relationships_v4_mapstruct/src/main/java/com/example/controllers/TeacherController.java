package com.example.controllers;

import com.example.models.Teacher;
import com.example.models.TeacherDetail;
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
        List<Teacher> teachers = teacherRepository.findAll();

        return teachers;
    }

    @GetMapping("/{id}")
    public Teacher getById(@PathVariable int id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("Teacher not found"));

        return teacher;
    }

    @PostMapping("/")
    public void save(@RequestBody Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody Teacher teacher) {
        Teacher teacherInDb = teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("Teacher not found"));

        teacherInDb.setFirstName(teacher.getFirstName());
        teacherInDb.setLastName(teacher.getLastName());
        teacherInDb.setEmail(teacher.getEmail());

        TeacherDetail teacherDetailInDb = teacherInDb.getTeacherDetail();
        teacherDetailInDb.setLinkedin(teacher.getTeacherDetail().getLinkedin());
        teacherDetailInDb.setHobby(teacher.getTeacherDetail().getHobby());

        teacherRepository.save(teacherInDb);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        teacherRepository.deleteById(id);
    }
}