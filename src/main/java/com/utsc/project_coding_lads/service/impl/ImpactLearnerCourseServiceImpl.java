package com.utsc.project_coding_lads.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utsc.project_coding_lads.domain.Course;
import com.utsc.project_coding_lads.domain.ImpactLearner;
import com.utsc.project_coding_lads.domain.ImpactLearnerCourse;
import com.utsc.project_coding_lads.domain.User;
import com.utsc.project_coding_lads.exception.EntityNotFoundException;
import com.utsc.project_coding_lads.exception.MissingInformationException;
import com.utsc.project_coding_lads.exception.ValidationFailedException;
import com.utsc.project_coding_lads.repository.ImpactLearnerCourseRepository;
import com.utsc.project_coding_lads.service.CourseService;
import com.utsc.project_coding_lads.service.ImpactLearnerCourseService;
import com.utsc.project_coding_lads.service.ImpactLearnerService;
import com.utsc.project_coding_lads.service.UserService;
import com.utsc.project_coding_lads.validator.ImpactLearnerValidator;
import com.utsc.project_coding_lads.validator.LearnerCourseValidator;

@Service
@Transactional
public class ImpactLearnerCourseServiceImpl implements ImpactLearnerCourseService {
	
	@Autowired
	ImpactLearnerCourseRepository learnerCourseRepo;
	@Autowired
	LearnerCourseValidator learnerCourseValidator;
	@Autowired
	ImpactLearnerValidator learnerValidator;
	@Autowired
	ImpactLearnerService learnerService;
	@Autowired
	CourseService courseService;
	@Autowired
	UserService userService;

	@Override
	public ImpactLearnerCourse addLearnerCourse(ImpactLearnerCourse learnerCourse) throws Exception {
		if (learnerCourse == null)
			throw new MissingInformationException("Request body cannot be null.");
		learnerCourseValidator.init(learnerCourse.getStudent(), learnerCourse.getCourse());
		learnerCourseValidator.validate();
		ImpactLearner student = learnerService.findImpactLearnerById(learnerCourse.getStudent().getId());
		Course course = courseService.findCourseById(learnerCourse.getCourse().getId());
		User user = userService.findUserById(learnerCourse.getStudent().getUser().getId());
		learnerCourse.setStudent(student);
		learnerCourse.setCourse(course);
		student.getCourses().add(learnerCourse);
		course.getStudents().add(learnerCourse);
		ImpactLearner savedLearner = learnerService.updateImpactLearner(student);
		Course savedCourse = courseService.updateCourse(course);
		User savedUser = userService.updateUser(user);
		return savedLearner.getCourses().get(student.getCourses().size() - 1);
	}

	@Override
	public void removeLearnerCourse(ImpactLearnerCourse learnerCourse) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ImpactLearnerCourse findLearnerCourseById(Integer id) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<ImpactLearnerCourse> findAllCoursesByLearnerId(Integer id) throws ValidationFailedException {
		ImpactLearner learner = learnerService.findImpactLearnerById(id);
		learnerValidator.init(learner.getUser(), learner.getId());
		learnerValidator.validateExist();
		learner.getCourses().size();
		return learner.getCourses();
	}

	@Override
	public Boolean existsById(Integer id) {
		return learnerCourseRepo.existsById(id);
	}

}
