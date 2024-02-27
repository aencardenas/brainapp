package com.brain.brainapp.repositories;

import com.brain.brainapp.models.Tutoring;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TutoringRepository extends JpaRepository<Tutoring, Long> {
    List<Tutoring> findByProfessorName(String professorName);
    List<Tutoring> findByStudentName(String studentName);
}
