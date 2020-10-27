package com.utsc.project_coding_lads.service;

import com.utsc.project_coding_lads.domain.Course;
import com.utsc.project_coding_lads.domain.ImpactLearner;

public interface ImpactLearnerService {

	public Integer storeImpactLearner(ImpactLearner impactLearner) throws Exception;
	
	public Course addCourse(Course course) throws Exception;
	
}
