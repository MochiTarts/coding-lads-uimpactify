package com.utsc.project_coding_lads.service;

import java.util.List;

import com.utsc.project_coding_lads.domain.ClassSession;
import com.utsc.project_coding_lads.exception.ValidationFailedException;

public interface ClassSessionService {

	public ClassSession findSessionById(Integer id);
	
	public Integer storeClassSession(ClassSession classSession) throws Exception;
	
	public void batchUpdateSession(List<ClassSession> sessions) throws ValidationFailedException;
}
