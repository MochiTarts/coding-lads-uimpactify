package com.utsc.project_coding_lads.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.utsc.project_coding_lads.domain.ClassSession;
import com.utsc.project_coding_lads.exception.MissingInformationException;
import com.utsc.project_coding_lads.exception.ValidationFailedException;
import com.utsc.project_coding_lads.repository.ClassSessionRepository;
import com.utsc.project_coding_lads.service.ClassSessionService;
import com.utsc.project_coding_lads.validator.ClassSessionValidator;

@Service
@Transactional
public class ClassSessionServiceImpl implements ClassSessionService {
	
	@Autowired
	ClassSessionRepository classSessionRepo;

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
		ClassSessionValidator validator = new ClassSessionValidator(classSession);
		validator.validate();
		return classSessionRepo.save(classSession);
	}

	@Override
	public void updateSingleSession(ClassSession classSession) throws ValidationFailedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchUpdateSession(List<ClassSession> sessions) throws ValidationFailedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteSingleSession(ClassSession classSession) throws ValidationFailedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDeleteSession(List<ClassSession> sessions) throws ValidationFailedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ClassSession> findAllSessionByCourseId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClassSession> findAllSessionByCourseIdDate(Integer id, LocalDateTime date) {
		// TODO Auto-generated method stub
		return null;
	}

}
