package com.utsc.project_coding_lads.validator;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.utsc.project_coding_lads.domain.Course;
import com.utsc.project_coding_lads.domain.ImpactLearner;
import com.utsc.project_coding_lads.domain.User;
import com.utsc.project_coding_lads.exception.EntityNotExistException;
import com.utsc.project_coding_lads.exception.EntityNotFoundException;
import com.utsc.project_coding_lads.exception.ValidationFailedException;
import com.utsc.project_coding_lads.repository.ImpactLearnerRepository;

@Component
@Transactional
public class ImpactLearnerValidator implements Validator {
	
	private User user;
	private Integer id;
	
	@Autowired
	ImpactLearnerRepository learnerRepo;
	@Autowired
	UserValidator userValidator;
	
	public void init(User user, Integer id) {
		this.user = user;
		this.id = id;
	}

	@Override
	public void validate() throws ValidationFailedException {
		if (user == null)
			throw new EntityNotExistException("The learner has no associated user.");
		if (id == null)
			throw new EntityNotExistException("The id cannot be null.");
		userValidator.init(user);
		userValidator.validateExists();
	}
	
	public void validateExist() throws ValidationFailedException {
		validate();
		if (!learnerRepo.existsById(id))
			throw new EntityNotFoundException("The learner does not exist in the db.");
	}

}
