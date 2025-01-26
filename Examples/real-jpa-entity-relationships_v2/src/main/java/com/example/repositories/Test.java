package com.example.repositories;

import com.example.models.SubjectStudent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Test extends JpaRepository<SubjectStudent, Integer> {
}
