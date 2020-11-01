package com.utsc.project_coding_lads.service;

import com.utsc.project_coding_lads.domain.Course;

public interface CourseService {

	public Integer storeCourseService(Course course) throws Exception;

	public Boolean existByID(Integer id);
	
}
