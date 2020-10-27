package com.utsc.project_coding_lads.service;

import com.utsc.project_coding_lads.domain.Application;

public interface ApplicationService {

	public Application storeApplication(Application app) throws Exception;
	
	public Application findApplicationById(Integer appId) throws Exception;
	
	public Boolean existsById(Integer appId) throws Exception;
	
}
