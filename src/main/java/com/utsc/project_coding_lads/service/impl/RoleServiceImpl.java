package com.utsc.project_coding_lads.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utsc.project_coding_lads.repository.RoleRepository;
import com.utsc.project_coding_lads.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepo;

	@Override
	public Integer findRoleIdByName(String roleName) throws Exception {
		return roleRepo.findRoleIdByName(roleName);
	}
	
	
}
