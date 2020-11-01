package com.utsc.project_coding_lads.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.utsc.project_coding_lads.domain.Course;
import com.utsc.project_coding_lads.exception.BadRequestException;
import com.utsc.project_coding_lads.repository.CourseRepository;
import com.utsc.project_coding_lads.service.CourseService;
import com.utsc.project_coding_lads.validator.CourseValidator;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

	@Autowired
	CourseRepository courseRepo;
	@Autowired
	CourseValidator validator;
	
	@Override
	public Boolean existByID(Integer id) {
		return courseRepo.existsById(id);
	}

	@Override
	public Integer storeCourseService(Course course) throws Exception {
		if (course == null)
			throw new BadRequestException("Course body is null");
		validator.init(course);
		validator.validate();
		return courseRepo.save(course).getId();
	}

}
