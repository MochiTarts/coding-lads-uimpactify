package com.utsc.project_coding_lads.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.utsc.project_coding_lads.domain.ImpactConsultant;
import com.utsc.project_coding_lads.domain.ImpactLearner;
import com.utsc.project_coding_lads.domain.Role;
import com.utsc.project_coding_lads.domain.User;
import com.utsc.project_coding_lads.exception.BadRequestException;
import com.utsc.project_coding_lads.repository.UserRepository;
import com.utsc.project_coding_lads.service.ImpactConsultantService;
import com.utsc.project_coding_lads.service.ImpactLearnerService;
import com.utsc.project_coding_lads.service.RoleService;
import com.utsc.project_coding_lads.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ImpactConsultantService impactConsultantService;
	
	@Autowired
	ImpactLearnerService impactLearnerService;
	
	@Autowired
	RoleService roleService;
	
	ObjectMapper mapper = new ObjectMapper();
	String userType;
	
	@PostMapping(value="/signup")
	public Integer storeUser(@RequestBody String request) throws Exception {
		if (request == null) {
			throw new BadRequestException("Request is null");
		}
		
		ObjectNode node = mapper.readValue(request, ObjectNode.class);

		if (node.has("userType") && node.has("id") && node.has("firstName") && node.has("lastName")
				&& node.has("username") && node.has("hashedPassword") && node.has("socialInit")
				&& node.has("role") && node.has("age") && node.has("events")) {
			userType = node.get("userType").textValue();
			node.remove("userType");
		} else {
			throw new BadRequestException("Request is either improperly formatted or missing info");
		}
		
		User user = mapper.convertValue(node, User.class);
		
		if (userType != null) {
			if (userType.equals("impact_consultant")) {
				ImpactConsultant impactConsultant = new ImpactConsultant();
				impactConsultant.setUser(user);
				impactConsultantService.storeImpactConsultantService(impactConsultant);
			} else if (userType.equals("impact_learner")) {
				ImpactLearner impactLearner = new ImpactLearner();
				impactLearner.setUser(user);
				impactLearnerService.storeImpactLearner(impactLearner);
			} else {
				throw new BadRequestException("Request is either improperly formatted or missing info");
			}
			
			//Role userRole = new Role(userType);
			//userRole.setId(roleService.findRoleIdByName(userType));
			//user.setRole(userRole);
		} else {
			//Is a social initiative employee
		}
		
		return userService.storeUser(user);
	}
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<Object> handleBadRequestException(BadRequestException e) {
		Map<String, Object> body = new HashMap<>();
		body.put("message", e.getMessage());
		body.put("timestamp", LocalDate.now());
		body.put("status", 400);
		
		return new ResponseEntity<Object>(body, HttpStatus.BAD_REQUEST);
	}
	
}