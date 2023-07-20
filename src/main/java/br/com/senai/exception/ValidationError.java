package br.com.senai.exception;

import java.util.ArrayList;
import java.util.List;

//Classe responsável por construir o erro

public class ValidationError extends StandardError {
	
	//Utilizado quando fizermos a validação de campo
	private List<FieldMessage> errors = new ArrayList<>();

	public ValidationError() {
		super();
		
	}

	public ValidationError(Long timeStamp, Integer status, String error, String message, String path) {
		super(timeStamp, status, error, message, path);
		
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addErros(String fileName, String message) {
		this.errors.add(new FieldMessage(fileName, message));
	}

}
