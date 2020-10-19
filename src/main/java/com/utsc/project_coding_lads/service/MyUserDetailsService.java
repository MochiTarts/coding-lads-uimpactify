package com.utsc.project_coding_lads.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.utsc.project_coding_lads.repository.UserRepository;
import com.utsc.project_coding_lads.domain.User;
@Service 
public class MyUserDetailsService implements UserDetailsService {
	UserRepository userRepo;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		String hashedPassword = userRepo.findUserByUsername(username).getHashedPassword().toString();
		
		
		
		return new org.springframework.security.core.userdetails.User(username, hashedPassword, null);
	}
	
	

}
