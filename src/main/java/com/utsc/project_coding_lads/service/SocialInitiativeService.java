package com.utsc.project_coding_lads.service;

import com.utsc.project_coding_lads.domain.SocialInitiative;

public interface SocialInitiativeService {

	public Integer storeSocialInit(SocialInitiative socialInit) throws Exception;
	
	public SocialInitiative findSocialInitByName(String socialInitName) throws Exception;
	
}
