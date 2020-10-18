package com.utsc.project_coding_lads.validator;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.utsc.project_coding_lads.domain.SocialInitiative;
import com.utsc.project_coding_lads.domain.User;
import com.utsc.project_coding_lads.enums.PostingEnum;
import com.utsc.project_coding_lads.exception.EntityNotExistException;
import com.utsc.project_coding_lads.exception.MissingInformationException;
import com.utsc.project_coding_lads.exception.UnauthenticatedException;
import com.utsc.project_coding_lads.exception.ValidationFailedException;
import com.utsc.project_coding_lads.service.PostingService;
import com.utsc.project_coding_lads.service.SocialInitService;

@Component
public class PostingValidator implements Validator {

	private String name;
	private String desc;
	private User postingCreator;
	private PostingEnum postingType;
	private LocalDateTime date;
	private Integer postingId;
	
	@Autowired
	SocialInitService socialInitService;
	
	@Autowired
	PostingService postingService;
	
	public PostingValidator(String name, String desc, User postingCreator, PostingEnum postingType,
			LocalDateTime date) {
		super();
		this.name = name;
		this.desc = desc;
		this.postingCreator = postingCreator;
		this.postingType = postingType;
		this.date = date;
	}
	
	public PostingValidator(String name, String desc, User postingCreator, PostingEnum postingType,
			LocalDateTime date, Integer postingId) {
		super();
		this.name = name;
		this.desc = desc;
		this.postingCreator = postingCreator;
		this.postingType = postingType;
		this.date = date;
		this.postingId = postingId;
	}
	
	public PostingValidator() {
		super();
	}

	@Override
	public void validate() throws ValidationFailedException {
		if (date == null || desc == null || name == null || postingType == null) 
			throw new MissingInformationException("Required fields are missing.");
		if (postingCreator == null)
			throw new EntityNotExistException("The posting creator does not exist.");
		
		if (postingCreator.getSocialInit() == null) 
			throw new UnauthenticatedException("This user is not an employee.");
		SocialInitiative socialInit = socialInitService.findSocialInitByName(postingCreator.getSocialInit().getName());
		if (socialInit == null) 
			throw new UnauthenticatedException("This user is not an employee.");
	}
	
	public void validateExists() throws Exception {
		validate();
		if (postingService.existsById(postingId)) {
			throw new EntityNotExistException("That posting does not exist.");
		}
	}

	
}
