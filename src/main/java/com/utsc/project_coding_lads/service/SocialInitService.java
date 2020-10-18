package com.utsc.project_coding_lads.service;

import com.utsc.project_coding_lads.domain.SocialInitiative;
import com.utsc.project_coding_lads.exception.EntityNotExistException;

public interface SocialInitService {

	public SocialInitiative findSocialInitByName(String name) throws EntityNotExistException;
}