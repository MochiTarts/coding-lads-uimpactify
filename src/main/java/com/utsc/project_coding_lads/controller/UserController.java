package com.utsc.project_coding_lads.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.utsc.project_coding_lads.domain.Posting;
import com.utsc.project_coding_lads.domain.User;
import com.utsc.project_coding_lads.exception.BadRequestException;
import com.utsc.project_coding_lads.exception.EntityAlreadyExistsException;
import com.utsc.project_coding_lads.exception.InvalidSocialInitNameException;
import com.utsc.project_coding_lads.exception.MissingRequiredInfoException;
import com.utsc.project_coding_lads.exception.UserTypeInvalidException;
import com.utsc.project_coding_lads.security.PasswordHash;
import com.utsc.project_coding_lads.service.PostingService;
import com.utsc.project_coding_lads.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	@Autowired 
	PostingService postingService;
	final static Logger log = LoggerFactory.getLogger(UserController.class);
	
	@PostMapping(path="/signup")
	public Integer storeUser(@RequestBody User user) throws Exception {
		try {
			PasswordHash encoder = new PasswordHash();
			user.setHashedPassword(encoder.passwordEncoder(user.getHashedPassword()));
			
			return userService.storeUser(user);
		} catch(NullPointerException e) {
			throw new BadRequestException("Request cannot be null");
		}
	}
	
	@ExceptionHandler(UserTypeInvalidException.class)
	public ResponseEntity<Object> handleUserTypeInvalidException(UserTypeInvalidException e) {
		Map<String, Object> body = new HashMap<>();
		body.put("message", e.getMessage());
		body.put("timestamp", LocalDate.now());
		body.put("status", 400);

		return new ResponseEntity<Object>(body, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EntityAlreadyExistsException.class)
	public ResponseEntity<Object> handleEntityAlreadyExistsException(EntityAlreadyExistsException e) {
		Map<String, Object> body = new HashMap<>();
		body.put("message", e.getMessage());
		body.put("timestamp", LocalDate.now());
		body.put("status", 400);

		return new ResponseEntity<Object>(body, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MissingRequiredInfoException.class)
	public ResponseEntity<Object> handleMissingRequiredInfoException(MissingRequiredInfoException e) {
		Map<String, Object> body = new HashMap<>();
		body.put("message", e.getMessage());
		body.put("timestamp", LocalDate.now());
		body.put("status", 400);
		
		return new ResponseEntity<Object>(body, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<Object> handleBadRequestException(BadRequestException e) {
		Map<String, Object> body = new HashMap<>();
		body.put("message", e.getMessage());
		body.put("timestamp", LocalDate.now());
		body.put("status", 400);
		
		return new ResponseEntity<Object>(body, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({MismatchedInputException.class, JsonParseException.class})
	public ResponseEntity<Object> handleMismatchedInputException(Exception e) {
		Map<String, Object> body = new HashMap<>();
		body.put("message", "JSON request is improperly formatted");
		body.put("timestamp", LocalDate.now());
		body.put("status", 400);
		
		return new ResponseEntity<Object>(body, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidSocialInitNameException.class)
	public ResponseEntity<Object> handleInvalidSocialInitNameException(InvalidSocialInitNameException e) {
		Map<String, Object> body = new HashMap<>();
		body.put("message", e.getMessage());
		body.put("timestamp", LocalDate.now());
		body.put("status", 400);

		return new ResponseEntity<Object>(body, HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping(path = "/updatePosting")
	public Integer updatePosting(@RequestBody Posting posting) throws Exception {
		return postingService.updatePosting(posting);
	}
	
	@PostMapping(path = "/delete/{id}")
	public Boolean deletePosting(@PathVariable("id") Integer id) {
		Boolean ok = true;
		try {
			postingService.deletePostingById(id);
		} catch (Exception e) {
			ok = false;
			log.info("Could not delete posting: ", e.getMessage());
		}
		return ok;
	}
	
	
	@GetMapping(path = "/{id}")
	public Posting getPosting(@PathVariable("id") Integer id) {
		Posting posting = null;
		try {
			posting = postingService.findPostingById(id);
		} catch (Exception e) {
			log.info("Could not get posting with id: " + id + ", ", e.getMessage());
		}
		return posting;
	}
	
}