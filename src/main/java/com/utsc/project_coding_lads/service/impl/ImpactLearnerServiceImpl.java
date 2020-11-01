package com.utsc.project_coding_lads.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.utsc.project_coding_lads.domain.ImpactLearner;
import com.utsc.project_coding_lads.exception.EntityNotExistException;
import com.utsc.project_coding_lads.exception.MissingInformationException;
import com.utsc.project_coding_lads.exception.ValidationFailedException;
import com.utsc.project_coding_lads.repository.ImpactLearnerRepository;
import com.utsc.project_coding_lads.service.ImpactLearnerService;

@Service
@Transactional
public class ImpactLearnerServiceImpl implements ImpactLearnerService {

	@Autowired
	ImpactLearnerRepository impactLearnerRepo;
	
	@Override
	public Integer storeImpactLearner(ImpactLearner impactLearner) throws Exception {
		if (impactLearner == null)
			throw new MissingInformationException("Impact learner cannot be null.");
		return impactLearnerRepo.save(impactLearner).getId();
	}

	@Override
	public Boolean existsById(Integer id) {
		return impactLearnerRepo.existsById(id);
	}

	@Override
	public ImpactLearner findLearnerById(Integer id) throws ValidationFailedException {
		if (!existsById(id))
			throw new EntityNotExistException("The impact learner does not exist.");
		return impactLearnerRepo.findById(id).get();
	}

}