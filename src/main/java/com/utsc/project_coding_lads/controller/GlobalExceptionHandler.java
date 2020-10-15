package com.utsc.project_coding_lads.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.utsc.project_coding_lads.exception.BadRequestException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<Object> handleBadRequestException(BadRequestException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
		System.out.println("Reached exception handler");
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("timestamp", LocalDate.now());
		body.put("status", status.value());
        body.put("errors", e.getMessage());
        
        return new ResponseEntity<Object>(body, headers, status);
		
	}
	
}
