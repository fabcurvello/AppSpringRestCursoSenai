package br.com.senai.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

//@Builder //Instancia objetos

@Entity //Esta classe representa uma tabela Estudante na base de dados
@Data //Isso faz get, set, toString....
public class Estudante {
	
	@Id //Este atributo é a chave primária lá na base de dados
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
	private Integer id;
	
	private String nome;
	
	private String sobrenome;
	
	private String email;
	
	private LocalDate dataNascimento;
	

}
