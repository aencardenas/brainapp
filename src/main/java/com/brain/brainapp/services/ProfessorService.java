package com.brain.brainapp.services;

import com.brain.brainapp.models.Professor;
import com.brain.brainapp.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public List<Professor> getAllProfessors() {
        return this.professorRepository.findAll();
    }

    public Optional<Professor> getProfessorById(Long id) {
        return professorRepository.findById(id);
    }

    public Professor createProfessor(Professor professor) {
        return professorRepository.save(professor);
    }

    public Professor updateProfessor(Long id, Professor professorDetails) {
        Professor professor = professorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Professor not found"));
        professor.setName(professorDetails.getName());
        professor.setEmail(professorDetails.getEmail());
        return professorRepository.save(professor);
    }

    public void deleteProfessor(Long id) {
        professorRepository.deleteById(id);
    }

}
