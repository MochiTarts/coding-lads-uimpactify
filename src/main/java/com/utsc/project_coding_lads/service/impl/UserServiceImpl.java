package com.utsc.project_coding_lads.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utsc.project_coding_lads.domain.User;
import com.utsc.project_coding_lads.repository.UserRepository;
import com.utsc.project_coding_lads.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;
	
	@Override
	public void storeUser(User user) throws Exception {
		userRepo.save(user);
	}

}
