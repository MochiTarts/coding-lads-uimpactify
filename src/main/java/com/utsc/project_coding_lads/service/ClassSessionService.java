package com.utsc.project_coding_lads.service;

import java.time.LocalDateTime;
import java.util.List;

import com.utsc.project_coding_lads.domain.ClassSession;
import com.utsc.project_coding_lads.exception.ValidationFailedException;

public interface ClassSessionService {

	public ClassSession findSessionById(Integer id);
	
	public List<ClassSession> findAllSessionByCourseId(Integer id);
	
	public List<ClassSession> findAllSessionByCourseIdDate(Integer id, LocalDateTime date);
	
	public void updateSingleSession(ClassSession classSession) throws ValidationFailedException;
	
	public void batchUpdateSession(List<ClassSession> sessions) throws ValidationFailedException;
	
	public void deleteSingleSession(ClassSession classSession) throws ValidationFailedException;
	
	public void batchDeleteSession(List<ClassSession> sessions) throws ValidationFailedException;
	
	public ClassSession storeClassSession(ClassSession classSession) throws Exception;
}
