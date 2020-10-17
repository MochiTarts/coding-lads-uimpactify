package com.utsc.project_coding_lads.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import com.utsc.project_coding_lads.domain.Role;
import com.utsc.project_coding_lads.domain.SocialInitiative;
import com.utsc.project_coding_lads.domain.User;
import com.utsc.project_coding_lads.exception.BadRequestException;
import com.utsc.project_coding_lads.exception.EntityAlreadyExistsException;
import com.utsc.project_coding_lads.exception.MissingRequiredInfoException;
import com.utsc.project_coding_lads.exception.UserTypeInvalidException;
import com.utsc.project_coding_lads.security.PasswordHash;
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
	
	@PostMapping(path="/signup")
	public Integer storeUser(@RequestBody User user) throws Exception {
		/*ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.readValue(request, ObjectNode.class);

		userType = node.get("userType").textValue();
		userSocialInit = node.get("userSocialInit").textValue();
		node.remove("userType");
		node.remove("userSocialInit");*/
		try {
			PasswordHash encoder = new PasswordHash();
			user.setHashedPassword(encoder.passwordEncoder(user.getHashedPassword()));
			
			return userService.storeUser(user);
		} catch(NullPointerException e) {
			throw new BadRequestException("Request cannot be null");
		}
		/*User user = new User();
		user.setFirstName(node.get("firstName").textValue());
		user.setLastName(node.get("lastName").textValue());
		user.setUsername(node.get("username").textValue());
		user.setAge(node.get("age").asInt());
		user.setEvents(null);

		userRole = new Role(userType);
		userRole.setId(roleService.findRoleIdByName(userType));
		user.setRole(userRole);

		socialInit = new SocialInitiative();
		socialInit.setName(userSocialInit);
		user.setSocialInit(socialInit);*/
		
		//return 1;
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
	
}