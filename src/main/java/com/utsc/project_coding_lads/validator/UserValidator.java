package com.utsc.project_coding_lads.validator;

import com.utsc.project_coding_lads.domain.Role;
import com.utsc.project_coding_lads.domain.SocialInitiative;
import com.utsc.project_coding_lads.domain.User;
import com.utsc.project_coding_lads.exception.MissingInformationException;
import com.utsc.project_coding_lads.exception.ValidationFailedException;

public class UserValidator implements Validator {

	private String firstName;
	private String lastName;
	private String userName;
	private String hashedPassword;
	private Integer age;
	//private String role;
	//private String socialInitName;
	private Role role;
	private SocialInitiative socialInit;
	
	public UserValidator(User user) {
		super();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.userName = user.getUsername();
		this.hashedPassword = user.getHashedPassword();
		this.age = user.getAge();
		//this.role = user.getRole() != null ? user.getRole().getName() : null;
		//this.socialInitName = user.getSocialInit() != null ? user.getSocialInit().getName() : null;
		this.role = user.getRole(); //I can check if Role and SocialInitiative are both null here
		this.socialInit = user.getSocialInit();
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
		if (firstName == null || lastName == null || userName == null || hashedPassword == null || age == null
				|| firstName.trim().isEmpty() || lastName.trim().isEmpty() || userName.trim().isEmpty() || hashedPassword.trim().isEmpty()) 
			throw new MissingInformationException("Required information is missing");
		if (role == null && socialInit == null) {
			throw new ValidationFailedException("Role and Social Initiative cannot both be empty");
		}
		
	}
	
}
