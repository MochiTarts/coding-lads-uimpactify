package com.utsc.project_coding_lads.service;

import java.util.List;

import com.utsc.project_coding_lads.domain.Course;
import com.utsc.project_coding_lads.domain.ImpactLearner;
import com.utsc.project_coding_lads.domain.User;
import com.utsc.project_coding_lads.exception.EntityNotFoundException;

public interface ImpactLearnerService {

	public Integer storeImpactLearner(ImpactLearner impactLearner) throws Exception;
	
	public Course addCourse(Integer courseId) throws Exception;
	
	public void removeCourse(Integer courseId) throws Exception;
	
	public ImpactLearner findImpactLearnerById(Integer id) throws EntityNotFoundException;
	
	public Boolean existsById(Integer id);
	
	public List<Course> findAllCoursesByLearner(ImpactLearner learnerId) throws Exception;
	
}
