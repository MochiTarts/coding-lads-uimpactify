package com.utsc.project_coding_lads.exception;

import javax.servlet.ServletException;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BadRequestException extends ServletException {
	
	public BadRequestException(String message) {
		super(message);
	}
	
}