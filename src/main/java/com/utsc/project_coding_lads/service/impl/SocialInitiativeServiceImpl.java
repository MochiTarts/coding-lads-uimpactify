package com.utsc.project_coding_lads.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utsc.project_coding_lads.domain.SocialInitiative;
import com.utsc.project_coding_lads.exception.BadRequestException;
import com.utsc.project_coding_lads.repository.SocialInitiativeRepository;
import com.utsc.project_coding_lads.service.SocialInitiativeService;

@Service
public class SocialInitiativeServiceImpl implements SocialInitiativeService {

	@Autowired
	SocialInitiativeRepository socialInitRepo;
	
	@Override
	public Integer storeSocialInit(SocialInitiative socialInit) throws Exception {
		return socialInitRepo.save(socialInit).getId();
		
	}

	@Override
	public SocialInitiative findSocialInitByName(String socialInitName) throws Exception {
		return socialInitRepo.findSocialInitByName(socialInitName);
	}

}
