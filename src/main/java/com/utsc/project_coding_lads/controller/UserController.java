package com.utsc.project_coding_lads.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.utsc.project_coding_lads.domain.User;
import com.utsc.project_coding_lads.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping(value="/signup")
	public void storeUser(@RequestBody User user) throws Exception {
		try {
			if (user != null) {
				userService.storeUser(user);
				System.out.println("Added a user");
			} else {
				System.out.println("Request body was empty");
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Could not add user");
		}
	}
	
	
}