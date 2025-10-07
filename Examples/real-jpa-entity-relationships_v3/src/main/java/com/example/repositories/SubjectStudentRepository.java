package com.example.repositories;

import com.example.models.Subject;
import com.example.models.Student;
import com.example.models.SubjectStudent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectStudentRepository extends JpaRepository<SubjectStudent, Integer> {
}
