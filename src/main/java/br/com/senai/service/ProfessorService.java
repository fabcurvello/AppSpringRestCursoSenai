package br.com.senai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senai.entity.Professor;
import br.com.senai.repository.ProfessorRepository;

@Service
public class ProfessorService {
	
	@Autowired
	private ProfessorRepository professorRepo;
	
	public Professor salvar(Professor professor) {
		return professorRepo.save(professor);
	}

}
