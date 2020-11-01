package com.utsc.project_coding_lads.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.utsc.project_coding_lads.domain.Course;
import com.utsc.project_coding_lads.domain.ImpactConsultant;
import com.utsc.project_coding_lads.domain.Posting;
import com.utsc.project_coding_lads.exception.BadRequestException;
import com.utsc.project_coding_lads.exception.EntityNotFoundException;
import com.utsc.project_coding_lads.exception.MissingInformationException;
import com.utsc.project_coding_lads.exception.ValidationFailedException;
import com.utsc.project_coding_lads.repository.CourseRepository;
import com.utsc.project_coding_lads.service.CourseService;
import com.utsc.project_coding_lads.service.ImpactConsultantService;
import com.utsc.project_coding_lads.validator.CourseValidator;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

	@Autowired
	CourseRepository courseRepo;
	@Autowired
	CourseValidator courseValidator;
	@Autowired
	ImpactConsultantService icService;
	
	@Override
	public Boolean existByID(Integer id) {
		return courseRepo.existsById(id);
	}

	@Override
	public Integer storeCourseService(Course course) throws Exception {
		if (course == null)
			throw new BadRequestException("Course body is null");
		courseValidator.init(course);
		courseValidator.validate();
		return courseRepo.save(course).getId();
	}

	@Override
	public Course findCourseById(Integer id) throws EntityNotFoundException {
		if (!existByID(id))
			throw new EntityNotFoundException("Course does not exist in the db.");
		return courseRepo.findById(id).get();
	}

	@Override
	public Course updateCourse(Course course) throws ValidationFailedException {
		if (course == null)
			throw new MissingInformationException("Course cannot be null.");
		courseValidator.init(course);
		courseValidator.validateExist();
		ImpactConsultant savedInstructor = icService.findImpactConsultantById(course.getInstructor().getId());
		course.setInstructor(savedInstructor);
		return courseRepo.save(course);
	}

}
