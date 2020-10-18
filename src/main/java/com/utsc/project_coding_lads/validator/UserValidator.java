package com.utsc.project_coding_lads.validator;

import com.utsc.project_coding_lads.domain.User;

public class UserValidator {

	public boolean validate(User user) {
		if (user != null && user.getFirstName() != null && !user.getFirstName().trim().isEmpty()
				&& user.getLastName() != null && !user.getLastName().trim().isEmpty()
				&& user.getUsername() != null && !user.getUsername().trim().isEmpty()
				&& user.getHashedPassword() != null && !user.getHashedPassword().trim().isEmpty() && user.getAge() != 0) {
			return true;
		}
		return false;
	}
	
}
