package com.utsc.project_coding_lads.service;

import java.util.List;

import com.utsc.project_coding_lads.domain.Course;
import com.utsc.project_coding_lads.domain.ImpactLearner;
import com.utsc.project_coding_lads.domain.ImpactLearnerCourse;
import com.utsc.project_coding_lads.exception.ValidationFailedException;

public interface ImpactLearnerService {

	public Integer storeImpactLearner(ImpactLearner impactLearner) throws Exception;
	
	public Boolean existsById(Integer id);
	
	public ImpactLearner findLearnerById(Integer id) throws ValidationFailedException;
	
	public void addCourseToLearner(ImpactLearner student, Course course) throws Exception;
	
	public List<ImpactLearnerCourse> findCoursesByLearnerId(Integer id) throws Exception;
	
	public void removeCourseFromLearner(ImpactLearner student, Course course) throws Exception;
	
}
