package com.utsc.project_coding_lads.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.utsc.project_coding_lads.domain.Posting;
import com.utsc.project_coding_lads.domain.User;
import com.utsc.project_coding_lads.exception.EntityNotExistException;
import com.utsc.project_coding_lads.exception.ValidationFailedException;
import com.utsc.project_coding_lads.service.PostingService;
import com.utsc.project_coding_lads.service.UserService;

@Component
@Transactional
public class ApplicationValidator implements Validator {

	private User applicant;
	private Posting posting;
	
	@Autowired
	private UserService userService;
	@Autowired
	private PostingService postingService;
	
	public void init(User applicant, Posting posting) {
		this.applicant = applicant;
		this.posting = posting;
	}

	@Override
	public void validate() throws ValidationFailedException {
		if (applicant == null)
			throw new EntityNotExistException("The user cannot be null.");
		if (applicant.getId() == null)
			throw new EntityNotExistException("The user id cannot be null.");
		if (!userService.existsById(applicant.getId()))
			throw new EntityNotExistException("The user does not exist in the database.");
		if (posting == null)
			throw new EntityNotExistException("The posting cannot be null.");
		if (posting.getId() == null)
			throw new EntityNotExistException("The posting id cannot be null.");
		if (!postingService.existsById(posting.getId()))
			throw new EntityNotExistException("The posting does not exist in the database.");
	}
	
}
