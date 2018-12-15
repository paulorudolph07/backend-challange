package com.invillia.acme.providerservice.controller.exception.handler;

import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> otherErrors(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
	}
}
