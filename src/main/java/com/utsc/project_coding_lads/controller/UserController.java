package com.utsc.project_coding_lads.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.utsc.project_coding_lads.domain.ImpactConsultant;
import com.utsc.project_coding_lads.domain.ImpactLearner;
import com.utsc.project_coding_lads.domain.Role;
import com.utsc.project_coding_lads.domain.SocialInitiative;
import com.utsc.project_coding_lads.domain.User;
import com.utsc.project_coding_lads.exception.BadRequestException;
import com.utsc.project_coding_lads.service.ImpactConsultantService;
import com.utsc.project_coding_lads.service.ImpactLearnerService;
import com.utsc.project_coding_lads.service.RoleService;
import com.utsc.project_coding_lads.service.SocialInitiativeService;
import com.utsc.project_coding_lads.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	ImpactConsultantService impactConsultantService;
	
	@Autowired
	ImpactLearnerService impactLearnerService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	SocialInitiativeService socialInitService;
	
	Role userRole;
	SocialInitiative socialInit;
	String userType;
	String userSocialInit;
	
	@PostMapping(value="/signup")
	public Integer storeUser(@RequestBody String request) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.readValue(request, ObjectNode.class);

		userType = node.get("userType").textValue();
		userSocialInit = node.get("userSocialInit").textValue();
		node.remove("userType");
		node.remove("userSocialInit");
		
		User user = new User();
		user.setFirstName(node.get("firstName").textValue());
		user.setLastName(node.get("lastName").textValue());
		user.setUsername(node.get("username").textValue());
		user.setHashedPassword(node.get("hashedPassword").textValue()); //Make password hashing method later
		user.setAge(node.get("age").asInt());
		user.setEvents(null);
		
		userRole = new Role(userType);
		userRole.setId(roleService.findRoleIdByName(userType));
		System.out.println(userRole.getName());
		user.setRole(userRole);
		
		socialInit = new SocialInitiative();
		socialInit.setName(userSocialInit);
		user.setSocialInit(socialInit);
		
		return userService.storeUser(user);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleException(Exception e) {
		System.out.println("Here");
		Map<String, Object> body = new HashMap<>();
		body.put("message", e.getMessage());
		body.put("timestamp", LocalDate.now());
		body.put("status", 400);
		
		return new ResponseEntity<Object>(body, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e) {
		Map<String, Object> body = new HashMap<>();
		body.put("message", "Username must be unique");
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
	public ResponseEntity<Object> handleMismatchedInputException() {
		Map<String, Object> body = new HashMap<>();
		body.put("message", "JSON request is improperly formatted");
		body.put("timestamp", LocalDate.now());
		body.put("status", 400);
		
		return new ResponseEntity<Object>(body, HttpStatus.BAD_REQUEST);
	}
	
}