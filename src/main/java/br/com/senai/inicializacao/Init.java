package br.com.senai.inicializacao;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.senai.entity.Estudante;
import br.com.senai.entity.Professor;
import br.com.senai.repository.EstudanteRepository;
import br.com.senai.repository.ProfessorRepository;
import br.com.senai.service.EstudanteService;
import br.com.senai.service.ProfessorService;

@Component //Para que o Spring entenda que isso aqui é um Listener
public class Init implements ApplicationListener<ContextRefreshedEvent>{
	
	@Autowired
	private EstudanteService estudanteService;
	
	@Autowired
	private EstudanteRepository estudanteRepo;
	
	@Autowired
	private ProfessorService professorService;
	
	@Autowired
	private ProfessorRepository professorRepo;
	

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		// ----- ESTUDANTE -----
		//Inserir
		Estudante estudante1 = new Estudante();
		estudante1.setNome("Fabricio");
		estudante1.setSobrenome("Curvello");
		estudante1.setEmail("curvello@email.com");
		estudante1.setDataNascimento(LocalDate.of(1975, 9, 04));
		//estudanteService.salvar(estudante1);
		
		Estudante estudante2 = new Estudante();
		estudante2.setNome("Vicente");
		estudante2.setSobrenome("Orsino");
		estudante2.setEmail("orsino@email.com");
		estudante2.setDataNascimento(LocalDate.of(1965, 6, 14));
		//estudanteService.salvar(estudante2);
		
		Estudante estudante3 = new Estudante();
		estudante3.setNome("Alex");
		estudante3.setSobrenome("William");
		estudante3.setEmail("william@email.com");
		estudante3.setDataNascimento(LocalDate.of(1978, 10, 10));
		//estudanteService.salvar(estudante3);
		
		estudanteRepo.saveAll(Arrays.asList(estudante1, estudante2, estudante3));
		
		//buscar todos os estudantes
		List<Estudante> listaEstudante = estudanteService.buscarTodosEstudantes();
		listaEstudante.forEach(estudante -> System.out.println(estudante.getNome())); //lambda
		
		
		//buscar um estudante pelo id
		Estudante estudante = estudanteService.buscarEstudanteById(1);
		System.out.println("Localizou? " + estudante.getNome());
		
		//excluir estudante pelo id
		Boolean flagEstud = estudanteService.removerEstudanteById(1);
		System.out.println("Removeu ? " + flagEstud);
		
		
		//alterar estudante
		Estudante estudante4 = new Estudante();
		estudante4.setNome("George");
		estudante4.setSobrenome("kleinau");
		estudante4.setEmail("kleinau@email.com");
		estudante4.setDataNascimento(LocalDate.of(1978, 10, 10));
		//estudanteService.salvar(estudante4);
		
		Estudante estudanteAlterado = estudanteService.alterarEstudante(3, estudante4);
		System.out.println("Nome do Estudante Alterado: " + estudanteAlterado.getNome());
		
		// ----- PROFESSOR -----
		//Inserir
		Professor professor1 = new Professor();
		professor1.setNome("Carla");
		professor1.setSobrenome("Regina");
		professor1.setEspecializacao("Inglês");
		//professorService.salvar(professor1);
		
		Professor professor2 = new Professor();
		professor2.setNome("Valéria");
		professor2.setSobrenome("Creuza");
		professor2.setEspecializacao("Português");
	
		
		Professor professor3 = new Professor();
		professor3.setNome("Jolinalva");
		professor3.setSobrenome("Cordeiro");
		professor3.setEspecializacao("Matemática");

		
		professorRepo.saveAll(Arrays.asList(professor1, professor2, professor3));
		
		//buscar todos os professores
		List<Professor> listaProfessor = professorService.buscarTodosprofessors();
		listaProfessor.forEach(professor -> System.out.println(professor.getNome())); //lambda
		
		
		//buscar um professor pelo id
		Professor professor = professorService.buscarprofessorById(1);
		System.out.println("Localizou? " + professor.getNome());
		
		//excluir professor pelo id
		Boolean flagProf = professorService.removerprofessorById(1);
		System.out.println("Removeu ? " + flagProf);
		
		
		//alterar professor
		Professor professor4 = new Professor();
		professor4.setNome("Gerusa");
		professor4.setSobrenome("Kubitsheck");
		professor4.setEspecializacao("Biologia");
		
		
		Professor professorAlterado = professorService.alterarprofessor(3, professor4);
		System.out.println("Nome do Professor Alterado: " + professorAlterado.getNome());
		
	}
	
	

}
