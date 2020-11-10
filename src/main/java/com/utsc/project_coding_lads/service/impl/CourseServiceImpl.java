package com.utsc.project_coding_lads.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.utsc.project_coding_lads.domain.Course;
import com.utsc.project_coding_lads.domain.ImpactConsultant;
import com.utsc.project_coding_lads.exception.BadRequestException;
import com.utsc.project_coding_lads.exception.EntityNotExistException;
import com.utsc.project_coding_lads.exception.MissingInformationException;
import com.utsc.project_coding_lads.exception.ValidationFailedException;
import com.utsc.project_coding_lads.repository.CourseRepository;
import com.utsc.project_coding_lads.service.ClassSessionService;
import com.utsc.project_coding_lads.service.CourseService;
import com.utsc.project_coding_lads.service.ImpactConsultantService;
import com.utsc.project_coding_lads.validator.CourseValidator;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

	@Autowired
	CourseRepository courseRepo;
	@Autowired
	CourseValidator validator;
	@Autowired
	ClassSessionService classSessionService;
	@Autowired
	ImpactConsultantService impactConsultantService;
	
	@Override
	public Boolean existByID(Integer id) {
		return courseRepo.existsById(id);
	}

	@Override
	public Integer storeCourse(Course course) throws Exception {
		if (course == null)
			throw new BadRequestException("Course body is null");
		validator.init(course);
		validator.validate();
		return courseRepo.save(course).getId();
	}

	@Override
	public Course findCourseById(Integer id) throws ValidationFailedException {
		if (!existByID(id))
			throw new EntityNotExistException("The course does not exist.");
		return courseRepo.findById(id).get();
	}

	@Override
	public Integer updateCourse(Course course) throws ValidationFailedException {
		if (course == null)
			throw new MissingInformationException("Course body is null");
		validator.init(course);
		validator.validateExist();
		ImpactConsultant savedImpactConsultant = impactConsultantService.findImpactConsultantById(course.getInstructor().getId());
		course.setInstructor(savedImpactConsultant);
		// batch update class sessions
		classSessionService.batchUpdateSession(course.getSessions());
		return courseRepo.save(course).getId();
	}

	@Override
	public void deleteCourseById(Course course) throws ValidationFailedException {
		if (course == null)
			throw new MissingInformationException("Course body is null");
		courseRepo.delete(course);
	}
}
