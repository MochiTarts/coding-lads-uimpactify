package com.utsc.project_coding_lads.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.utsc.project_coding_lads.domain.Role;
import com.utsc.project_coding_lads.domain.SocialInitiative;
import com.utsc.project_coding_lads.domain.User;
import com.utsc.project_coding_lads.exception.EntityNotExistException;
import com.utsc.project_coding_lads.exception.MissingInformationException;
import com.utsc.project_coding_lads.exception.UnauthenticatedException;
import com.utsc.project_coding_lads.exception.ValidationFailedException;
import com.utsc.project_coding_lads.service.SocialInitService;
import com.utsc.project_coding_lads.service.UserService;

@Component
@Transactional
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
	private Integer userId;
	
	@Autowired
	SocialInitService socialInitService;
	@Autowired
	UserService userService;
	
	public void init(User user) {
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.userName = user.getUsername();
		this.hashedPassword = user.getHashedPassword();
		this.age = user.getAge();
		//this.role = user.getRole() != null ? user.getRole().getName() : null;
		//this.socialInitName = user.getSocialInit() != null ? user.getSocialInit().getName() : null;
		this.role = user.getRole(); //I can check if Role and SocialInitiative are both null here
		this.socialInit = user.getSocialInit();
		this.userId = userId;
	}
	
	@Override
	public void validate() throws ValidationFailedException {
		if (firstName == null || lastName == null || userName == null || hashedPassword == null || age == null
				|| firstName.trim().isEmpty() || lastName.trim().isEmpty() || userName.trim().isEmpty() || hashedPassword.trim().isEmpty()) 
			throw new MissingInformationException("Required information is missing");
		if (role == null && socialInit == null) {
			throw new ValidationFailedException("Role and Social Initiative cannot both be empty");
		}
	}
	
	public void validateEmployee() throws ValidationFailedException {
		validate();
		if (socialInit != null) {
			SocialInitiative savedSocialInit = socialInitService.findSocialInitByName(socialInit.getName());
			if (savedSocialInit == null) throw new UnauthenticatedException("This user is not an employee");
		}
	}
	
	public void validateExists() throws ValidationFailedException {
		if (!userService.existsById(userId))
			throw new EntityNotExistException("That user does not exist in the db.");
	}
	
	
}
