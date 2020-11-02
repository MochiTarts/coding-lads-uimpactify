package com.utsc.project_coding_lads.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.utsc.project_coding_lads.domain.Course;
import com.utsc.project_coding_lads.domain.ImpactConsultant;
import com.utsc.project_coding_lads.domain.ImpactLearner;
import com.utsc.project_coding_lads.domain.ImpactLearnerCourse;
import com.utsc.project_coding_lads.exception.EntityNotExistException;
import com.utsc.project_coding_lads.exception.MissingInformationException;
import com.utsc.project_coding_lads.exception.ValidationFailedException;
import com.utsc.project_coding_lads.repository.ImpactLearnerCourseRepository;
import com.utsc.project_coding_lads.repository.ImpactLearnerRepository;
import com.utsc.project_coding_lads.service.CourseService;
import com.utsc.project_coding_lads.service.ImpactConsultantService;
import com.utsc.project_coding_lads.service.ImpactLearnerCourseService;
import com.utsc.project_coding_lads.service.ImpactLearnerService;
import com.utsc.project_coding_lads.validator.CourseValidator;
import com.utsc.project_coding_lads.validator.UserValidator;

@Service
@Transactional
public class ImpactLearnerServiceImpl implements ImpactLearnerService {

	@Autowired
	ImpactLearnerRepository learnerRepo;
	@Autowired
	CourseService courseService;
	@Autowired
	ImpactConsultantService consultantService;
	@Autowired
	CourseValidator courseValidator;
	@Autowired
	UserValidator userValidator;
	@Autowired
	ImpactLearnerCourseService learnerCourseService;
	
	@Override
	public Integer storeImpactLearner(ImpactLearner impactLearner) throws Exception {
		if (impactLearner == null)
			throw new MissingInformationException("Impact learner cannot be null.");
		return learnerRepo.save(impactLearner).getId();
	}

	@Override
	public Boolean existsById(Integer id) {
		return learnerRepo.existsById(id);
	}

	@Override
	public ImpactLearner findLearnerById(Integer id) throws ValidationFailedException {
		if (!existsById(id))
			throw new EntityNotExistException("The impact learner does not exist.");
		return learnerRepo.findById(id).get();
	}
	
	@Override
	public void addCourseToLearner(ImpactLearner student, Course course) throws Exception {
		if (student == null || course == null)
			throw new MissingInformationException("Student or course cannot be null.");
		courseValidator.init(course);
		courseValidator.validateExist();
		findLearnerById(student.getId());
		if (student.getUser() == null)
			throw new EntityNotExistException("Student's associated user cannot be null.");
		userValidator.init(student.getUser());
		userValidator.validate();
		userValidator.validateExists();
		userValidator.validateHasRole();
		ImpactLearnerCourse learnerCourse = new ImpactLearnerCourse();
		ImpactLearner savedStudent = findLearnerById(student.getId());
		Course savedCourse = courseService.findCourseById(course.getId());
		learnerCourse.setCourse(savedCourse);
		learnerCourse.setStudent(savedStudent);
		savedStudent.getCourses().size();
		savedStudent.getCourses().add(learnerCourse);
		learnerRepo.save(savedStudent);
	}

	@Override
	public List<ImpactLearnerCourse> findCoursesByLearnerId(Integer id) throws Exception {
		ImpactLearner savedStudent = findLearnerById(id);
		savedStudent.getCourses().size();
		List<ImpactLearnerCourse> courses = savedStudent.getCourses();
		return courses;
	}

	@Override
	public void removeCourseFromLearner(ImpactLearner student, Course course) throws Exception {
		if (student == null || course == null)
			throw new MissingInformationException("Student or course cannot be null.");
		courseValidator.init(course);
		courseValidator.validateExist();
		findLearnerById(student.getId());
		if (student.getUser() == null)
			throw new EntityNotExistException("Student's associated user cannot be null.");
		userValidator.init(student.getUser());
		userValidator.validate();
		userValidator.validateExists();
		userValidator.validateHasRole();
		ImpactLearnerCourse learnerCourse = new ImpactLearnerCourse();
		ImpactLearner savedStudent = findLearnerById(student.getId());
		Course savedCourse = courseService.findCourseById(course.getId());
		learnerCourse.setCourse(savedCourse);
		learnerCourse.setStudent(savedStudent);
		savedStudent.getCourses().size();
		List<ImpactLearnerCourse> courses = savedStudent.getCourses();
		for (ImpactLearnerCourse ilc: courses) {
			if (ilc.getCourse().getId() == savedCourse.getId()) {
				learnerCourseService.deleteById(ilc.getId());
				courses.remove(ilc);
				learnerRepo.save(savedStudent);
				return;
			}
		}
	}

	@Override
	public List<ImpactLearnerCourse> findCoursesByInstructorId(Integer studentId, ImpactConsultant instructor) throws Exception {
		if (studentId == null || instructor == null)
			throw new MissingInformationException("Student Id or instructor cannot be null");
		consultantService.findImpactConsultantById(instructor.getId());
		if (instructor.getUser() == null)
			throw new EntityNotExistException("Instructor's associated user cannot be null.");
		userValidator.init(instructor.getUser());
		userValidator.validate();
		userValidator.validateExists();
		userValidator.validateHasRole();
		ImpactLearner savedStudent = findLearnerById(studentId);
		ImpactConsultant savedInstructor = consultantService.findImpactConsultantById(instructor.getId());
		savedStudent.getCourses().size();
		List<ImpactLearnerCourse> foundCourses = new ArrayList<ImpactLearnerCourse>();
		for (ImpactLearnerCourse ilc: savedStudent.getCourses()) {
			if (ilc.getCourse().getInstructor().getId() == savedInstructor.getId())
				foundCourses.add(ilc);
		}
		return foundCourses;
	}

}