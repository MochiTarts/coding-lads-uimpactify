package com.utsc.project_coding_lads.service;

import com.utsc.project_coding_lads.domain.Course;
import com.utsc.project_coding_lads.exception.ValidationFailedException;

public interface CourseService {

	public Integer storeCourse(Course course) throws Exception;

	public Boolean existByID(Integer id);
	
	public Course findCourseById(Integer id) throws ValidationFailedException;
	
	public Integer updateCourse(Course course) throws ValidationFailedException;
	
	public void deleteCourseById(Course course) throws ValidationFailedException;
}
