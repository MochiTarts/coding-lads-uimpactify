package com.utsc.project_coding_lads.service;

import java.util.List;

import com.utsc.project_coding_lads.domain.Course;
import com.utsc.project_coding_lads.domain.ImpactLearner;
import com.utsc.project_coding_lads.domain.User;

public interface ImpactLearnerService {

	public Integer storeImpactLearner(ImpactLearner impactLearner) throws Exception;
	
	public Course addCourse(Integer courseId) throws Exception;
	
	public void removeCourse(Integer courseId) throws Exception;
	
	public List<Course> getAllCoursesByLearner(ImpactLearner learnerId) throws Exception;
	
}
