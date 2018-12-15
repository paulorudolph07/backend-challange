package com.invillia.acme.paymentservice.controller.exeption.handler;

import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> validateConstraintError(ConstraintViolationException ex) {
		return ResponseEntity.badRequest()
				.body(ex.getConstraintViolations().stream().map(cv -> cv.getMessage()).collect(Collectors.toList()));
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> validateNotFoundError(EntityNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
	
	
	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<?> validateHttpClientError(HttpClientErrorException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getResponseBodyAsString());
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> otherErrors(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
	}
}
