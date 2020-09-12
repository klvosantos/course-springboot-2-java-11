package com.educandoweb.course.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.educandoweb.course.Services.exceptions.ResourceNotFoundException;

@ControllerAdvice // notation que vai interceptar as exceções que acontecerem para que esse objeto possa executar um possivel tratamento
public class ResourceExceptionHandler { // Classe responsavel pelo tratamento manual dos erros

	@ExceptionHandler(ResourceNotFoundException.class) // intercepta a requisição que deu exceção 
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		String error= "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
