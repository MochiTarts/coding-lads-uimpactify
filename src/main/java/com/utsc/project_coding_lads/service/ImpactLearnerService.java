package com.utsc.project_coding_lads.service;

import com.utsc.project_coding_lads.domain.ImpactLearner;
import com.utsc.project_coding_lads.exception.ValidationFailedException;

public interface ImpactLearnerService {

	public Integer storeImpactLearner(ImpactLearner impactLearner) throws Exception;
	
	public Boolean existsById(Integer id);
	
	public ImpactLearner findLearnerById(Integer id) throws ValidationFailedException;
	
}
