package br.com.senai.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class EstudanteDTO {
	
	@Getter
	@Setter
	private Integer id;
	
	@Getter
	@Setter
	@NotNull
	private String nome;
	
	@Getter
	@Setter
	private String sobrenome;
	
	//Supondo que email possua uma regra específica de negócios, fazemos o get e set necessário que atenda às regras
	@NotNull
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
