package br.com.senai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.senai.entity.Estudante;
import br.com.senai.entity.Professor;
import br.com.senai.repository.ProfessorRepository;

@Service
public class ProfessorService {
	
	@Autowired
	private ProfessorRepository professorRepo;
	
	public Professor salvar(Professor professor) {
		return professorRepo.save(professor);
	}
	
	public List<Professor> buscarTodosprofessors() {
		return professorRepo.findAll();
	}

	public Professor buscarprofessorById(Integer id) {
		return professorRepo.findById(id).orElse(null);
	}

	public Boolean removerprofessorById(Integer id) {
		Professor professor = professorRepo.findById(id).orElse(null);
		if (professorRepo != null) {
			professorRepo.deleteById(id);
			return true;
		}
		return false;
	}

	public Professor alterarprofessor(Integer id, Professor professor) {
		Professor professorBD = professorRepo.findById(id).orElse(null);
		if(professorBD!= null) {
			professorBD.setNome(professor.getNome());
			professorBD.setSobrenome(professor.getSobrenome());
			professorBD.setEspecializacao(professor.getEspecializacao());
			
			return professorRepo.save(professorBD);
		} else {
			return null;
		}

	}
	
	public Page<Professor> buscarProfessorPorPaginacao(PageRequest page) {
		return professorRepo.findAll(page);
	}

}
