package br.com.senai.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.commons.ExampleValues;
import br.com.senai.dto.EstudanteDTO;
import br.com.senai.entity.Estudante;
import br.com.senai.service.EstudanteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("estudantes") // o que vai vir depois da barra na URL
public class EstudanteResource {

	@Autowired
	private EstudanteService estudanteService;

	@Autowired
	private ModelMapper mapper;

	@GetMapping
	public ResponseEntity<List<EstudanteDTO>> buscarTodosEstudantes() {
		List<Estudante> listaEstudantes = estudanteService.buscarTodosEstudantes();
		List<EstudanteDTO> listaEstudantesDTO = listaEstudantes.stream()
				.map(estudante -> mapper.map(estudante, EstudanteDTO.class)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaEstudantesDTO);
	}

	@GetMapping("/{id}")
	@Operation(description = "Retorna o registro de estudante pelo id")
	public ResponseEntity<EstudanteDTO> buscarEstudanteById(@PathVariable("id") @Schema(example = ExampleValues.idEstudante)Integer id) {
	
		Estudante estudante = estudanteService.buscarEstudanteById(id);
		EstudanteDTO estudanteDTO = mapper.map(estudante, EstudanteDTO.class);
		return ResponseEntity.ok().body(estudanteDTO);
	}

	@PostMapping
	public ResponseEntity<EstudanteDTO> cadastrarEstudante(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(
					content = @Content
					(examples = { @ExampleObject(name="Exemplo de Estudante", value = ExampleValues.exemploEstudante)}))
			@RequestBody EstudanteDTO estudanteDTO) {

		Estudante estudante = mapper.map(estudanteDTO, Estudante.class);
		estudante = estudanteService.salvar(estudante);
		EstudanteDTO novoEstudante = mapper.map(estudante, EstudanteDTO.class);
		return ResponseEntity.ok().body(novoEstudante);
	}

	@PutMapping("/{id}")
	public ResponseEntity<EstudanteDTO> atualizarEstudante(@PathVariable Integer id,
			@RequestBody EstudanteDTO estudanteDTO) {

		Estudante estudante = mapper.map(estudanteDTO, Estudante.class);
		estudante = estudanteService.alterarEstudante(id, estudante);
		EstudanteDTO novoEstudante = mapper.map(estudante, EstudanteDTO.class);
		return ResponseEntity.ok().body(novoEstudante);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> excluirEstudante(@PathVariable Integer id) {
		Boolean flag = estudanteService.removerEstudanteById(id);
		return ResponseEntity.ok(flag);
	}

	@GetMapping("paginacao")
	public Page<Estudante> buscarEstudantePorpaginacao(@RequestParam Integer pagina, @RequestParam Integer itensPorPagina, @RequestParam String ordenacao, @RequestParam String tipoOrdenacao) {
	
		PageRequest page = PageRequest.of(pagina, itensPorPagina, (tipoOrdenacao.equals("ASC")?Sort.by(ordenacao).ascending(): Sort.by(ordenacao).descending()));
		return estudanteService.buscarEstudantePorPaginacao(page);
	}


}
