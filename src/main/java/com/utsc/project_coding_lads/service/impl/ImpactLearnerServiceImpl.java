package com.utsc.project_coding_lads.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.utsc.project_coding_lads.domain.Course;
import com.utsc.project_coding_lads.domain.ImpactLearner;
import com.utsc.project_coding_lads.repository.ImpactLearnerRepository;
import com.utsc.project_coding_lads.service.ImpactLearnerService;

@Service
@Transactional
public class ImpactLearnerServiceImpl implements ImpactLearnerService {

	@Autowired
	ImpactLearnerRepository impactLearnerRepo;
	
	@Override
	public Integer storeImpactLearner(ImpactLearner impactLearner) throws Exception {
		return impactLearnerRepo.save(impactLearner).getId();
	}

	@Override
	public Course addCourse(Course course) throws Exception {

		return null;
	}

}