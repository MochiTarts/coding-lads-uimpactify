package com.utsc.project_coding_lads.service;

import java.time.LocalDateTime;
import java.util.List;

import com.utsc.project_coding_lads.domain.ClassSession;
import com.utsc.project_coding_lads.exception.MissingInformationException;
import com.utsc.project_coding_lads.exception.ValidationFailedException;

public interface ClassSessionService {


	public ClassSession findSessionById(Integer id) throws ValidationFailedException;
	
	public List<ClassSession> findAllSessionByCourseId(Integer id) throws ValidationFailedException;
	
	public List<ClassSession> findAllSessionByCourseIdPeriod(Integer id, LocalDateTime startDate, LocalDateTime endDate) throws MissingInformationException, ValidationFailedException;
	
	public ClassSession updateSingleSession(ClassSession classSession) throws ValidationFailedException;
	
	public List<ClassSession> batchUpdateSession(List<ClassSession> sessions) throws ValidationFailedException;
	
	public void deleteSingleSession(ClassSession classSession) throws ValidationFailedException;
	
	public void batchDeleteSession(List<ClassSession> sessions) throws ValidationFailedException;
	
	public ClassSession storeClassSession(ClassSession classSession) throws Exception;
}
