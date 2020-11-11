package com.utsc.project_coding_lads.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.utsc.project_coding_lads.domain.ClassSession;
import com.utsc.project_coding_lads.domain.Course;
import com.utsc.project_coding_lads.exception.MissingInformationException;
import com.utsc.project_coding_lads.exception.ValidationFailedException;
import com.utsc.project_coding_lads.repository.ClassSessionRepository;
import com.utsc.project_coding_lads.service.ClassSessionService;
import com.utsc.project_coding_lads.service.CourseService;
import com.utsc.project_coding_lads.validator.ClassSessionValidator;
import com.utsc.project_coding_lads.validator.CourseValidator;

@Service
@Transactional
public class ClassSessionServiceImpl implements ClassSessionService {
	
	@Autowired
	ClassSessionRepository classSessionRepo;

	@Autowired
	CourseService courseService;

	@Autowired
	ClassSessionValidator classSessionValidator;

	@Autowired
	CourseValidator courseValidator;

	@Override
	public ClassSession findSessionById(Integer id) {
		if (classSessionRepo.existsById(id))
			return classSessionRepo.getOne(id);
		return null;
	}

	@Override
	public ClassSession storeClassSession(ClassSession classSession) throws Exception {
		if (classSession == null)
			throw new MissingInformationException("Class session is null");
		classSessionValidator.init(classSession);
		classSessionValidator.validate();
		return classSessionRepo.save(classSession);
	}

	@Override
	public ClassSession updateSingleSession(ClassSession classSession) throws ValidationFailedException {
		if (classSession == null)
			throw new MissingInformationException("Class session is null");
		classSessionValidator.init(classSession);
		classSessionValidator.validateExist(classSession);
		Course savedCourse = courseService.findCourseById(classSession.getCourse().getId());
		classSession.setCourse(savedCourse);
		return classSessionRepo.save(classSession);
	}

	@Override
	public List<ClassSession> batchUpdateSession(List<ClassSession> sessions) throws ValidationFailedException {
		List<ClassSession> savedSessions = new ArrayList<>();
		for (ClassSession session : sessions) {
			savedSessions.add(updateSingleSession(session));
		}
		return savedSessions;
	}

	@Override
	public void deleteSingleSession(ClassSession classSession) throws ValidationFailedException {
		classSessionRepo.deleteById(classSession.getId());
	}

	@Override
	public void batchDeleteSession(List<ClassSession> sessions) throws ValidationFailedException {
		for (ClassSession session : sessions) {
			classSessionRepo.deleteById(session.getId());
		}
	}

	@Override
	public List<ClassSession> findAllSessionByCourseId(Integer id) throws ValidationFailedException {
		if (id == null)
			throw new MissingInformationException("Course id is null");
		Course course = courseService.findCourseById(id);
		courseValidator.init(course);
		courseValidator.validateExist();
		List<ClassSession> sessions = course.getSessions();
		return sessions;
	}

	@Override
	public List<ClassSession> findAllSessionByCourseIdPeriod(Integer id, LocalDateTime startDate, LocalDateTime endDate)
			throws ValidationFailedException {
		if (id == null)
			throw new MissingInformationException("Instructor id is null");
		if (startDate == null || endDate == null)
			throw new MissingInformationException("Date is null");
		List<ClassSession> sessions = courseService.findCourseById(id).getSessions();
		for (ClassSession session : sessions) {
			if (session.getStartDate().isBefore(startDate) || session.getEndDate().isAfter(endDate))
			sessions.remove(session);
		}
		return sessions;
	}

}
