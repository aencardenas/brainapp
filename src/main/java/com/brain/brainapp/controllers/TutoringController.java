package com.brain.brainapp.controllers;

import com.brain.brainapp.models.Professor;
import com.brain.brainapp.models.Student;
import com.brain.brainapp.models.Tutoring;
import com.brain.brainapp.repositories.ProfessorRepository;
import com.brain.brainapp.repositories.StudentRepository;
import com.brain.brainapp.services.TutoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TutoringController {

    @Autowired
    private TutoringService tutoringService;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/classes")
    public ResponseEntity<List<Tutoring>> getAllClasses() {
        List<Tutoring> tutorings = tutoringService.getAllClasses();
        return new ResponseEntity<>(tutorings, HttpStatus.OK);
    }

    @GetMapping("/professor/{name}")
    public ResponseEntity<List<Tutoring>> getClassesByProfessorName(@PathVariable String name) {
        List<Tutoring> tutorings = tutoringService.getClassesByProfessorName(name);
        return new ResponseEntity<>(tutorings, HttpStatus.OK);
    }

    @GetMapping("/student/{name}")
    public ResponseEntity<List<Tutoring>> getClassesByStudentName(@PathVariable String name) {
        List<Tutoring> tutorings = tutoringService.getClassesByStudentName(name);
        return new ResponseEntity<>(tutorings, HttpStatus.OK);
    }

    @PostMapping("/classes")
    public ResponseEntity<Tutoring> createClass(@RequestBody Tutoring tutoring) {

        Long professorId = tutoring.getProfessor().getId();
        Long studentId = tutoring.getStudent().getId();
        String tutoringName = tutoring.getName();

        Professor professor = professorRepository.findById(professorId)
                .orElseThrow(()-> new IllegalArgumentException("Professor not found"));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(()-> new IllegalArgumentException("Student not found"));

        tutoring.setName(tutoringName);
        tutoring.setProfessor(professor);
        tutoring.setStudent(student);

        Tutoring createdTutoring = tutoringService.createClass(tutoring);

        return new ResponseEntity<>(createdTutoring, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tutoring> updateClass(@PathVariable Long id, @RequestBody Tutoring updatedTutoring) {
        try {
            Tutoring tutoringToUpdate = tutoringService.updateClass(id, updatedTutoring);
            return new ResponseEntity<>(tutoringToUpdate, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable Long id) {
        try {
            tutoringService.deleteClass(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
