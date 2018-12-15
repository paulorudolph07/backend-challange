package com.invillia.acme.orderservice.controller.exception.handler;

import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.invillia.acme.orderservice.exception.RefundException;

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
	
	@ExceptionHandler(RefundException.class)
	public ResponseEntity<?> validateRefundError(RefundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ex.getMessage());
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> otherErrors(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
	}
}
