package com.utsc.project_coding_lads.service;

import com.utsc.project_coding_lads.domain.Course;
import com.utsc.project_coding_lads.exception.ValidationFailedException;

public interface CourseService {

	public Integer storeCourseService(Course course) throws Exception;

	public Boolean existByID(Integer id);
	
	public Course findCourseById(Integer id) throws ValidationFailedException;
	
}