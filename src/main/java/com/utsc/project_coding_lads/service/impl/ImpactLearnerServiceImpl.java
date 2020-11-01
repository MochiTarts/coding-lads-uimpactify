package com.utsc.project_coding_lads.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.utsc.project_coding_lads.domain.Course;
import com.utsc.project_coding_lads.domain.ImpactLearner;
import com.utsc.project_coding_lads.domain.ImpactLearnerCourse;
import com.utsc.project_coding_lads.exception.EntityNotExistException;
import com.utsc.project_coding_lads.exception.MissingInformationException;
import com.utsc.project_coding_lads.exception.ValidationFailedException;
import com.utsc.project_coding_lads.repository.ImpactLearnerRepository;
import com.utsc.project_coding_lads.service.CourseService;
import com.utsc.project_coding_lads.service.ImpactLearnerService;

@Service
@Transactional
public class ImpactLearnerServiceImpl implements ImpactLearnerService {

	@Autowired
	ImpactLearnerRepository learnerRepo;
	@Autowired
	CourseService courseService;
	
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
		//Add in validation stuff later
		ImpactLearnerCourse learnerCourse = new ImpactLearnerCourse();
		ImpactLearner savedStudent = findLearnerById(student.getId());
		Course savedCourse = courseService.findCourseById(course.getId());
		learnerCourse.setCourse(savedCourse);
		learnerCourse.setStudent(savedStudent);
		savedStudent.getCourses().add(learnerCourse);
		savedCourse.getStudents().add(learnerCourse);
		learnerRepo.save(savedStudent);
		System.out.println(savedStudent.getCourses().size());
	}

	@Override
	public List<ImpactLearnerCourse> findCoursesByLearnerId(Integer id) throws Exception {
		ImpactLearner savedStudent = findLearnerById(id);
		savedStudent.getCourses().size();
		return savedStudent.getCourses();
	}

}