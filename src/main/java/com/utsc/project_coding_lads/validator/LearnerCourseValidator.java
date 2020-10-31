package com.utsc.project_coding_lads.validator;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.utsc.project_coding_lads.domain.Course;
import com.utsc.project_coding_lads.domain.ImpactLearner;
import com.utsc.project_coding_lads.enums.RoleEnum;
import com.utsc.project_coding_lads.exception.EntityNotExistException;
import com.utsc.project_coding_lads.exception.EntityNotFoundException;
import com.utsc.project_coding_lads.exception.UnauthenticatedException;
import com.utsc.project_coding_lads.exception.UserTypeInvalidException;
import com.utsc.project_coding_lads.exception.ValidationFailedException;
import com.utsc.project_coding_lads.service.ImpactLearnerCourseService;
import com.utsc.project_coding_lads.service.ImpactLearnerService;

@Component
@Transactional
public class LearnerCourseValidator implements Validator {
	
	private ImpactLearner student;
	private Course course;
	private Integer id;
	
	@Autowired
	ImpactLearnerCourseService learnerCourseService;
	@Autowired
	CourseValidator courseValidator;
	@Autowired
	UserValidator userValidator;
	@Autowired
	ImpactLearnerService ilService;
	
	public void init(ImpactLearner student, Course course) {
		this.student = student;
		this.course = course;
	}
	
	public void init(ImpactLearner student, Course course, Integer id) {
		this.student = student;
		this.course = course;
		this.id = id;
	}

	@Override
	public void validate() throws ValidationFailedException {
		if (student == null)
			throw new EntityNotExistException("Student cannot be null.");
		if (course == null)
			throw new EntityNotExistException("Course cannot be null.");
		if (student.getId() == null)
			throw new EntityNotExistException("Student id cannot be null.");
		if (course.getId() == null)
			throw new EntityNotExistException("Course id cannot be null.");
		if (student.getUser() == null)
			throw new EntityNotExistException("Student's associated user cannot be null");
		if (!ilService.existsById(student.getId()))
			throw new EntityNotFoundException("Student does not exist in the db.");
		courseValidator = new CourseValidator(course);
		courseValidator.validateExist();
		userValidator.init(student.getUser());
		userValidator.validateExists();
		userValidator.validateHasRole();
		if (student.getUser().getRole().getName() == null)
			throw new UnauthenticatedException("Student user's role name cannot be null.");
		if (!student.getUser().getRole().getName().equals(RoleEnum.IMPACT_LEARNER.name()))
			throw new UnauthenticatedException("Student user's role is not Impact Learner.");
	}
	
	public void validateExists() throws ValidationFailedException {
		validate();
		if (!learnerCourseService.existsById(id));
			throw new EntityNotFoundException("Learner course association does not exist.");
	}

}
