package com.utsc.project_coding_lads.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utsc.project_coding_lads.domain.ImpactConsultant;
import com.utsc.project_coding_lads.exception.BadRequestException;
import com.utsc.project_coding_lads.repository.ImpactConsultantRepository;
import com.utsc.project_coding_lads.service.ImpactConsultantService;

@Service
public class ImpactConsultantServiceImpl implements ImpactConsultantService {

	@Autowired
	ImpactConsultantRepository impactConsultantRepo;
	
	@Override
	public Integer storeImpactConsultantService(ImpactConsultant impactConsultant) throws Exception {
		return impactConsultantRepo.save(impactConsultant).getId();
	}

}
