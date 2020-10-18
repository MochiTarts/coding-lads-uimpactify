package com.utsc.project_coding_lads.service;

import com.utsc.project_coding_lads.domain.Role;

public interface RoleService {

	public Role findRoleByName(String roleName) throws Exception;
	
	public Role saveRole(Role role);
}
