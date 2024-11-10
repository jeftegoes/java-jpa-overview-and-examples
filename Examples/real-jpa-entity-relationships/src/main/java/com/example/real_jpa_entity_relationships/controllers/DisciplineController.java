package com.example.real_jpa_entity_relationships.controllers;

import com.example.real_jpa_entity_relationships.models.Discipline;
import com.example.real_jpa_entity_relationships.repositories.DisciplineRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/discipline")
public class DisciplineController {
    private final DisciplineRepository disciplineRepository;

    public DisciplineController(DisciplineRepository disciplineRepository) {
        this.disciplineRepository = disciplineRepository;
    }

    @GetMapping("/")
    public List<Discipline> getAll() {
        List<Discipline> disciplines = disciplineRepository.getAll();

        return disciplines;
    }

    @GetMapping("/{id}")
    public Discipline getById(@PathVariable int id) {
        Discipline discipline = disciplineRepository.findById(id);

        return discipline;
    }

    @PostMapping("/")
    public void save(@RequestBody Discipline discipline) {
        disciplineRepository.save(discipline);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody Discipline discipline) {
        disciplineRepository.update(id, discipline);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        disciplineRepository.delete(id);
    }
}
