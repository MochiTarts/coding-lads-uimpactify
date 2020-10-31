package com.utsc.project_coding_lads.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.utsc.project_coding_lads.domain.Course;
import com.utsc.project_coding_lads.domain.ImpactLearner;
import com.utsc.project_coding_lads.domain.ImpactLearnerCourse;
import com.utsc.project_coding_lads.domain.User;
import com.utsc.project_coding_lads.exception.EntityNotFoundException;
import com.utsc.project_coding_lads.exception.MissingInformationException;
import com.utsc.project_coding_lads.exception.ValidationFailedException;
import com.utsc.project_coding_lads.repository.ImpactLearnerRepository;
import com.utsc.project_coding_lads.service.CourseService;
import com.utsc.project_coding_lads.service.ImpactLearnerService;
import com.utsc.project_coding_lads.service.UserService;
import com.utsc.project_coding_lads.validator.CourseValidator;
import com.utsc.project_coding_lads.validator.ImpactLearnerValidator;
import com.utsc.project_coding_lads.validator.UserValidator;

@Service
@Transactional
public class ImpactLearnerServiceImpl implements ImpactLearnerService {

	@Autowired
	ImpactLearnerRepository learnerRepo;
	@Autowired
	ImpactLearnerValidator learnerValidator;
	@Autowired
	UserService userService;
	
	@Override
	public Integer storeImpactLearner(ImpactLearner impactLearner) throws Exception {
		if (impactLearner == null)
			throw new MissingInformationException("Impact Learner body is null.");
		learnerValidator.init(impactLearner.getUser(), impactLearner.getId());
		return learnerRepo.save(impactLearner).getId();
	}
	
	@Override
	public ImpactLearner findImpactLearnerById(Integer id) throws EntityNotFoundException {
		if (!existsById(id))
			throw new EntityNotFoundException("Impact Learner does not exist.");
		return learnerRepo.findById(id).get();
	}
	
	@Override
	public Boolean existsById(Integer id) {
		return learnerRepo.existsById(id);
	}
	
	@Override
	public ImpactLearner updateImpactLearner(ImpactLearner impactLearner) throws ValidationFailedException {
		if (impactLearner == null)
			throw new MissingInformationException("Impact Learner cannot be null.");
		learnerValidator.init(impactLearner.getUser(), impactLearner.getId());
		User savedUser = userService.findUserById(impactLearner.getUser().getId());
		impactLearner.setUser(savedUser);
		return learnerRepo.save(impactLearner);
	}

}