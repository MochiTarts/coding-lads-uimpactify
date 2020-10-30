package com.utsc.project_coding_lads.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.utsc.project_coding_lads.domain.Course;
import com.utsc.project_coding_lads.domain.ImpactConsultant;
import com.utsc.project_coding_lads.domain.User;
import com.utsc.project_coding_lads.exception.EntityNotExistException;
import com.utsc.project_coding_lads.exception.MissingInformationException;
import com.utsc.project_coding_lads.exception.UnauthenticatedException;
import com.utsc.project_coding_lads.exception.ValidationFailedException;
import com.utsc.project_coding_lads.service.ImpactConsultantService;
import com.utsc.project_coding_lads.service.UserService;

public class ImpactConsultantValidator implements Validator {
	
	private Integer instructorId;
	private User user;
	private List<Course> courses;
	
	@Autowired
	ImpactConsultantService impactConsultantService;
	
	@Autowired
	UserService userService;
	
	public ImpactConsultantValidator() {
		super();
	}
	
	public ImpactConsultantValidator(ImpactConsultant instructor) {
		instructorId = instructor.getId();
		user = instructor.getUser();
		courses = instructor.getCourses();
	}

	@Override
	public void validate() throws ValidationFailedException {
		if (user == null)
			throw new MissingInformationException("The required field is missing");
		if (user.getId() == null)
			throw new EntityNotExistException("The user id cannot be null");
		if (userService.findUserById(user.getId()) == null)
			throw new UnauthenticatedException("The given user is not an user");
	}
	
	public void validateExist() throws ValidationFailedException {
		validate();
		if (!impactConsultantService.existById(instructorId))
			throw new EntityNotExistException("This course does not exist");
	}

}
