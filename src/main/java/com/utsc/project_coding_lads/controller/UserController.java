package com.utsc.project_coding_lads.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.utsc.project_coding_lads.domain.User;
import com.utsc.project_coding_lads.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	
	public void storeUser(User user) throws Exception {
		userService.storeUser(user);
	}
	
	
}
