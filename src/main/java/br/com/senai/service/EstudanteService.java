package br.com.senai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.senai.entity.Estudante;
import br.com.senai.repository.EstudanteRepository;

@Service
public class EstudanteService {

	@Autowired // instanciar quando for necessário (Isso é injeção de dependência do Spring)
	private EstudanteRepository estudanteRepo;

	public Estudante salvar(Estudante estudante) {
		return estudanteRepo.save(estudante);
	}

	public List<Estudante> buscarTodosEstudantes() {
		return estudanteRepo.findAll();
	}

	public Estudante buscarEstudanteById(Integer id) {
		return estudanteRepo.findById(id).orElse(null);
	}

	public Boolean removerEstudanteById(Integer id) {
		Estudante estudante = estudanteRepo.findById(id).orElse(null);
		if (estudanteRepo != null) {
			estudanteRepo.deleteById(id);
			return true;
		}
		return false;
	}

	public Estudante alterarEstudante(Integer id, Estudante estudante) {
		Estudante estudanteBD = estudanteRepo.findById(id).orElse(null);
		if(estudanteBD!= null) {
			estudanteBD.setNome(estudante.getNome());
			estudanteBD.setSobrenome(estudante.getSobrenome());
			estudanteBD.setEmail(estudante.getEmail());
			estudanteBD.setDataNascimento(estudante.getDataNascimento());
			return estudanteRepo.save(estudanteBD);
		} else {
			return null;
		}

	}
	
	public Page<Estudante> buscarEstudantePorPaginacao(PageRequest page) {
		return estudanteRepo.findAll(page);
	}
}
