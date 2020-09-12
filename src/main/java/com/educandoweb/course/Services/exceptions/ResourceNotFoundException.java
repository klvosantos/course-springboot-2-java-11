package com.educandoweb.course.Services.exceptions;

public class ResourceNotFoundException extends RuntimeException{ // exceção personalizada da camada de serviços
	
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(Object id) {
		super("Resource not found. Id " + id);  // mensagem padrão da classe. No body da requisição é o valor do campo "message"
	}
}
