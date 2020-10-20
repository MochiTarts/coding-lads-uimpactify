package com.utsc.project_coding_lads.service;

import com.utsc.project_coding_lads.domain.ClassSession;

public interface ClassSessionService {

	public ClassSession findSessionById(Integer id);
	
	public Integer storeClassSession(ClassSession classSession) throws Exception;
}
