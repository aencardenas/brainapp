package com.brain.brainapp.services;


import com.brain.brainapp.models.Professor;
import com.brain.brainapp.models.Student;
import com.brain.brainapp.models.Tutoring;
import com.brain.brainapp.repositories.ProfessorRepository;
import com.brain.brainapp.repositories.StudentRepository;
import com.brain.brainapp.repositories.TutoringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TutoringService {

    @Autowired
    private TutoringRepository tutoringRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private StudentRepository studentRepository;

    public List<Tutoring> getAllClasses() {
        return tutoringRepository.findAll();
    }

    public List<Tutoring> getClassesByProfessorName(String professorName) {
        return tutoringRepository.findByProfessorName(professorName);
    }

    public List<Tutoring> getClassesByStudentName(String studentName) {
        return tutoringRepository.findByStudentName(studentName);
    }

    public Tutoring createClass(Tutoring tutoring) {
        Professor professor = professorRepository.findById(tutoring.getProfessor().getId())
                .orElseThrow(()-> new IllegalArgumentException("Professor not found"));

        Student student = studentRepository.findById(tutoring.getStudent().getId())
                .orElseThrow(()-> new IllegalArgumentException("Student not found"));

        tutoring.setProfessor(professor);
        tutoring.setStudent(student);

        return tutoringRepository.save(tutoring);
    }

    public Tutoring updateClass(Long id, Tutoring updatedTutoring) {
        Optional<Tutoring> existingClassOptional = tutoringRepository.findById(id);
        if (existingClassOptional.isEmpty()) {
            throw new IllegalArgumentException("Class not found");
        }

        Tutoring existingTutoring = existingClassOptional.get();

        existingTutoring.setName(updatedTutoring.getName());
        existingTutoring.setProfessor(updatedTutoring.getProfessor());
        existingTutoring.setStudent(updatedTutoring.getStudent());

        return tutoringRepository.save(existingTutoring);
    }

    public void deleteClass(Long id) {
        Optional<Tutoring> existingClass = tutoringRepository.findById(id);
        if (existingClass.isEmpty()) {
            throw new IllegalArgumentException("Class not found");
        }

        tutoringRepository.deleteById(id);
    }
}
