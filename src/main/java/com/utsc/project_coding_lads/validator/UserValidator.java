package com.utsc.project_coding_lads.validator;

import com.utsc.project_coding_lads.domain.User;
import com.utsc.project_coding_lads.exception.MissingInformationException;
import com.utsc.project_coding_lads.exception.ValidationFailedException;

public class UserValidator implements Validator {

	private String firstName;
	private String lastName;
	private String userName;
	private String hashedPassword;
	private Integer age;
	private String role;
	private String socialInitName;
	
	public UserValidator(User user) {
		super();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.userName = user.getUsername();
		this.hashedPassword = user.getHashedPassword();
		this.age = user.getAge();
		this.role = user.getRole().getName();
		this.socialInitName = user.getSocialInit() != null ? user.getSocialInit().getName() : null;
	}
//
//	public boolean validate(User user) {
//		if (user != null && user.getFirstName() != null && !user.getFirstName().trim().isEmpty()
//				&& user.getLastName() != null && !user.getLastName().trim().isEmpty()
//				&& user.getUsername() != null && !user.getUsername().trim().isEmpty()
//				&& user.getHashedPassword() != null && !user.getHashedPassword().trim().isEmpty() && user.getAge() != 0) {
//			return true;
//		}
//		return false;
//	}

	@Override
	public void validate() throws ValidationFailedException {
		if (firstName == null || lastName == null || userName == null || hashedPassword == null || age == null) 
			throw new MissingInformationException("Required information is missing.");
		if (role == null && socialInitName == null) {
			throw new ValidationFailedException("userType and userSocialInit cannot both be empty");
		}
		
	}
	
}
