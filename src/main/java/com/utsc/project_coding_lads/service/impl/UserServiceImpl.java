package com.utsc.project_coding_lads.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utsc.project_coding_lads.domain.User;
import com.utsc.project_coding_lads.exception.BadRequestException;
import com.utsc.project_coding_lads.repository.UserRepository;
import com.utsc.project_coding_lads.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;

	@Override
	public Integer storeUser(User user) throws Exception {
		// Check user object for all necessary fields and make sure is not null
		if (user != null && user.getFirstName() != null && !user.getFirstName().trim().isEmpty()
				&& user.getLastName() != null && !user.getLastName().trim().isEmpty()
				&& user.getUsername() != null && !user.getUsername().trim().isEmpty()
				&& user.getHashedPassword() != null && !user.getHashedPassword().trim().isEmpty()
				&& user.getAge() != null) {
			return userRepo.save(user).getId();
		} else {
			throw new BadRequestException("Request is either improperly formatted or missing info");
		}
	}

}
