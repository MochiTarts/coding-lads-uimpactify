package com.utsc.project_coding_lads.service;

import java.util.List;

import com.utsc.project_coding_lads.domain.Course;
import com.utsc.project_coding_lads.domain.ImpactLearner;
import com.utsc.project_coding_lads.domain.ImpactLearnerCourse;
import com.utsc.project_coding_lads.exception.EntityNotFoundException;
import com.utsc.project_coding_lads.exception.ValidationFailedException;

public interface ImpactLearnerCourseService {

	public ImpactLearnerCourse addLearnerCourse(ImpactLearnerCourse learnerCourse) throws Exception;
	
	public void removeLearnerCourse(ImpactLearnerCourse learnerCourse) throws Exception;
	
	public ImpactLearnerCourse findLearnerCourseById(Integer id) throws EntityNotFoundException;
	
	public List<ImpactLearnerCourse> findAllCoursesByLearnerId(Integer id) throws ValidationFailedException;
	
	public Boolean existsById(Integer id);
	
}
