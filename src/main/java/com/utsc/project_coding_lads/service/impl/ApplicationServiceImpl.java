package com.utsc.project_coding_lads.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utsc.project_coding_lads.domain.Application;
import com.utsc.project_coding_lads.domain.Posting;
import com.utsc.project_coding_lads.domain.User;
import com.utsc.project_coding_lads.enums.PostingEnum;
import com.utsc.project_coding_lads.enums.RoleEnum;
import com.utsc.project_coding_lads.exception.MissingInformationException;
import com.utsc.project_coding_lads.repository.ApplicationRepository;
import com.utsc.project_coding_lads.service.ApplicationService;
import com.utsc.project_coding_lads.service.PostingService;
import com.utsc.project_coding_lads.service.UserService;
import com.utsc.project_coding_lads.validator.ApplicationValidator;

@Service
@Transactional
public class ApplicationServiceImpl implements ApplicationService {

	@Autowired
	ApplicationValidator appValidator;
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
		appValidator.init(app.getApplicant(), app.getPosting());
		appValidator.validate();
		String postingType = postingService.findPostingById(app.getPosting().getId()).getPostingType();
		String userType = userService.findUserById(app.getApplicant().getId()).getRole().getName();
		
		if ((postingType.equals(PostingEnum.EMPLOYMENT.name()) || postingType.equals(PostingEnum.VOLUNTEERING.name()))
				&& userType.equals(RoleEnum.IMPACT_LEARNER.name())) {
			Posting posting = postingService.findPostingById(app.getPosting().getId());
			app.setPosting(posting);
			posting.getApplications().add(app);
			Posting savedPosting = postingService.updatePosting(posting);
			
			User applicant = userService.findUserById(app.getApplicant().getId());
			app.setApplicant(applicant);
			applicant.getApplication().add(app);
			User savedApplicant = userService.updateUser(applicant);
			
			savedApp = savedApplicant.getApplication().get(applicant.getApplication().size() - 1);
		}
			
		return savedApp;
	}

}
