package com.utsc.project_coding_lads.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.utsc.project_coding_lads.domain.User;
import com.utsc.project_coding_lads.exception.WrongPasswordException;
import com.utsc.project_coding_lads.repository.UserRepository;

@Service
public class SecurityConfig {
	@Autowired
	UserRepository userRepo;
	public Integer authentication(User entrant) throws WrongPasswordException{
		String eUsername = entrant.getUsername();
		String hashedPassword = entrant.getHashedPassword();
		
		User confirmation = new User();
		confirmation = loadUserByUsername(eUsername);
		
		
		
		if (hashedPassword != confirmation.getHashedPassword()) {
			throw new WrongPasswordException("Password is incorrect");
		}
		
		return confirmation.getId();
	}
	
	public User loadUserByUsername(String username) throws UsernameNotFoundException {	
			return userRepo.findUserByUsername(username);
	}
	
}
