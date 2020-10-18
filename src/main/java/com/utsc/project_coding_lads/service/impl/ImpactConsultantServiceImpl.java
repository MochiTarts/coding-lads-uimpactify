package com.utsc.project_coding_lads.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.utsc.project_coding_lads.domain.ImpactConsultant;
import com.utsc.project_coding_lads.repository.ImpactConsultantRepository;
import com.utsc.project_coding_lads.service.ImpactConsultantService;

@Service
@Transactional
public class ImpactConsultantServiceImpl implements ImpactConsultantService {

	@Autowired
	ImpactConsultantRepository impactConsultantRepo;
	
	@Override
	public Integer storeImpactConsultantService(ImpactConsultant impactConsultant) throws Exception {
		return impactConsultantRepo.save(impactConsultant).getId();
	}

}