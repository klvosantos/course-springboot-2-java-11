package com.educandoweb.course.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.educandoweb.course.Services.exceptions.DatabaseException;
import com.educandoweb.course.Services.exceptions.ResourceNotFoundException;

@ControllerAdvice // notation que vai interceptar as exceções que acontecerem para que esse objeto possa executar um possivel tratamento
public class ResourceExceptionHandler { // Classe responsavel pelo tratamento manual dos erros

	@ExceptionHandler(ResourceNotFoundException.class) // intercepta a requisição que deu exceção 
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		String error= "Resource not found"; // adiciona no body da requisição no campo "error"
		HttpStatus status = HttpStatus.NOT_FOUND; // adiciona no status nº 404
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(status).body(err); // para retornar uma resposta com codigo personalizado status() que vai aceitar o status que foi definido, no caso a variavel status acima
	}
	
	@ExceptionHandler(DatabaseException.class) 
	public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
		String error= "Database error"; 
		HttpStatus status = HttpStatus.BAD_REQUEST; 
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(status).body(err); 
	}
	
}
