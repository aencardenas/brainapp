package com.brain.brainapp.repositories;

import com.brain.brainapp.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
