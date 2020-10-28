package com.utsc.project_coding_lads.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utsc.project_coding_lads.domain.Application;
import com.utsc.project_coding_lads.domain.Posting;
import com.utsc.project_coding_lads.domain.User;
import com.utsc.project_coding_lads.enums.PostingEnum;
import com.utsc.project_coding_lads.enums.RoleEnum;
import com.utsc.project_coding_lads.exception.EntityNotExistException;
import com.utsc.project_coding_lads.exception.EntityNotFoundException;
import com.utsc.project_coding_lads.exception.MissingInformationException;
import com.utsc.project_coding_lads.exception.UserTypeInvalidException;
import com.utsc.project_coding_lads.repository.ApplicationRepository;
import com.utsc.project_coding_lads.service.ApplicationService;
import com.utsc.project_coding_lads.service.PostingService;
import com.utsc.project_coding_lads.service.UserService;
import com.utsc.project_coding_lads.validator.ApplicationValidator;
import com.utsc.project_coding_lads.validator.PostingValidator;
import com.utsc.project_coding_lads.validator.UserValidator;

@Service
@Transactional
public class ApplicationServiceImpl implements ApplicationService {

	@Autowired
	ApplicationValidator appValidator;
	@Autowired
	UserValidator userValidator;
	@Autowired
	PostingValidator postingValidator;
	@Autowired
	UserService userService;
	@Autowired
	PostingService postingService;
	@Autowired
	ApplicationRepository appRepo;
	
	@Override
	public Application storeApplication(Application app) throws Exception {
		Application savedApp = null;
		if (app == null)
			throw new MissingInformationException("Request cannot be null");
		appValidator.init(app.getApplicant(), app.getPosting(), app.getEmail());
		appValidator.validate();
		String postingType = postingService.findPostingById(app.getPosting().getId()).getPostingType();
		String userType = userService.findUserById(app.getApplicant().getId()).getRole().getName();
		
		if (((postingType.equals(PostingEnum.EMPLOYMENT.name()) || postingType.equals(PostingEnum.VOLUNTEERING.name())) && userType.equals(RoleEnum.IMPACT_LEARNER.name()))
				|| (postingType.equals(PostingEnum.CONSULTING.name()) && userType.equals(RoleEnum.IMPACT_CONSULTANT.name()))) {
			Posting posting = postingService.findPostingById(app.getPosting().getId());
			app.setPosting(posting);
			posting.getApplications().add(app);
			User applicant = userService.findUserById(app.getApplicant().getId());
			app.setApplicant(applicant);
			applicant.getApplication().add(app);
			
			Posting savedPosting = postingService.updatePosting(posting);
			User savedApplicant = userService.updateUser(applicant);
			
			savedApp = savedApplicant.getApplication().get(applicant.getApplication().size() - 1);			
//			System.out.println(savedPosting.getApplications().get(posting.getApplications().size() - 1).getEmail());
		} else {
			throw new UserTypeInvalidException("Impact learners can only apply to employment and/or volunteering opportunities. "
					+ "Impact consultants can only apply to consultant opportunities");
		}
			
		return savedApp;
	}

	@Override
	public Application findApplicationById(Integer appId) throws Exception {
		if (!existsById(appId))
			throw new EntityNotFoundException("The application you are looking for does not exist.");
		return appRepo.findById(appId).get();
	}

	@Override
	public Boolean existsById(Integer appId) {
		return appRepo.existsById(appId);
	}

	@Override
	public void deleteApplicationById(Integer appId) throws Exception {
		appRepo.deleteById(appId);
	}

	@Override
	public Application updateApplication(Application app) throws Exception {
		if (app == null)
			throw new MissingInformationException("Application body is null.");
		appValidator.init(app.getApplicant(), app.getPosting(), app.getEmail(), app.getId());
		appValidator.validateExists();
		User savedApplicant = userService.findUserById(app.getApplicant().getId());
		app.setApplicant(savedApplicant);
		Posting savedPosting = postingService.findPostingById(app.getPosting().getId());
		app.setPosting(savedPosting);
		return appRepo.save(app);
	}

	@Override
	public List<Application> findAllApplicationsByUserId(Integer userId)
			throws Exception {
		User applicant = userService.findUserById(userId);
		userValidator.init(applicant);
		userValidator.validateHasRole();
		List<Application> applications = applicant.getApplication();
		return applications;
	}

	@Override
	public List<Application> findAllApplicationsByPostingId(Integer postingId)
			throws Exception {
		Posting posting = postingService.findPostingById(postingId);
		postingValidator.init(posting.getName(), posting.getPostingDesc(), posting.getPostingCreator(),
				posting.getPostingType(), posting.getPostingDate(), posting.getSocialInit(), posting.getId());
		postingValidator.validateExists();
		List<Application> applications = posting.getApplications();
		return applications;
	}

	@Override
	public List<Application> getAllApplications() throws Exception {
		return appRepo.findAll();
	}

}
