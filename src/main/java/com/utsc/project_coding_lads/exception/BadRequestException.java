package com.utsc.project_coding_lads.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BadRequestException extends ResponseStatusException {
	
	public BadRequestException(HttpStatus status, String reason) {
		super(status, reason);
		// TODO Auto-generated constructor stub
	}
	
}