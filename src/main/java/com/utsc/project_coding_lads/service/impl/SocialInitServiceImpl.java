package com.utsc.project_coding_lads.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.utsc.project_coding_lads.domain.SocialInitiative;
import com.utsc.project_coding_lads.exception.EntityNotExistException;
import com.utsc.project_coding_lads.repository.SocialInitRepository;
import com.utsc.project_coding_lads.service.SocialInitService;

@Service
@Transactional
public class SocialInitServiceImpl implements SocialInitService {

	@Autowired
	SocialInitRepository socialInitRepo;
	
	@Override
	public SocialInitiative findSocialInitByName(String name) throws EntityNotExistException {
		return socialInitRepo.findSocialInitByName(name);
	}

}
