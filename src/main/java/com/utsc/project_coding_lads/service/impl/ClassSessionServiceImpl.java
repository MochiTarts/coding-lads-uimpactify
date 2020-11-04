package com.utsc.project_coding_lads.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.utsc.project_coding_lads.domain.ClassSession;
import com.utsc.project_coding_lads.exception.BadRequestException;
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
	public Integer storeClassSession(ClassSession classSession) throws Exception {
		if (classSession == null)
			throw new BadRequestException("Class session is null");
		ClassSessionValidator validator = new ClassSessionValidator(classSession);
		validator.validate();
		return classSessionRepo.save(classSession).getId();
	}

}