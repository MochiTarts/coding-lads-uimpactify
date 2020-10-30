package com.utsc.project_coding_lads.service;

import java.time.LocalDateTime;
import java.util.List;

import com.utsc.project_coding_lads.domain.Course;
import com.utsc.project_coding_lads.exception.ValidationFailedException;

public interface CourseService {
	
	public Course findCourseById(Integer id) throws ValidationFailedException;
	
	public void updateCourse(Course course) throws ValidationFailedException;
	
	public void deleteCourseById(Course course) throws Exception;

	public Course saveCourse(Course course) throws ValidationFailedException;
	
	public List<Course> findAllCourseByInstructorId(Integer id) throws ValidationFailedException;
	
//	public List<Course> findAllCourseByInstructorIdDate(Integer id, LocalDateTime date) throws ValidationFailedException;

	public Boolean existById(Integer id);
	
}
