package com.utsc.project_coding_lads.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utsc.project_coding_lads.domain.ImpactLearner;
import com.utsc.project_coding_lads.exception.BadRequestException;
import com.utsc.project_coding_lads.repository.ImpactLearnerRepository;
import com.utsc.project_coding_lads.service.ImpactLearnerService;

@Service
public class ImpactLearnerServiceImpl implements ImpactLearnerService {

	@Autowired
	ImpactLearnerRepository impactLearnerRepo;
	
	@Override
	public Integer storeImpactLearner(ImpactLearner impactLearner) throws Exception {
		if (impactLearner.getUser() != null) {
			return impactLearnerRepo.save(impactLearner).getId();
		} else {
			throw new BadRequestException("Attempted to store ImpactLearner with no associated User");
		}
	}

}
