package com.utsc.project_coding_lads.service;

import java.util.List;

import com.utsc.project_coding_lads.domain.Course;
import com.utsc.project_coding_lads.domain.ImpactLearner;
import com.utsc.project_coding_lads.domain.ImpactLearnerCourse;
import com.utsc.project_coding_lads.domain.User;
import com.utsc.project_coding_lads.exception.EntityNotFoundException;
import com.utsc.project_coding_lads.exception.ValidationFailedException;

public interface ImpactLearnerService {

	public Integer storeImpactLearner(ImpactLearner impactLearner) throws Exception;
	
	public ImpactLearner findImpactLearnerById(Integer id) throws EntityNotFoundException;
	
	public Boolean existsById(Integer id);
	
	public ImpactLearner updateImpactLearner(ImpactLearner impactLearner) throws ValidationFailedException;
	
}
