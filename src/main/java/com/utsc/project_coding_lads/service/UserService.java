package com.utsc.project_coding_lads.service;

import com.utsc.project_coding_lads.domain.User;

public interface UserService {

	public Integer storeUser(User user, String role, String socialInit) throws Exception;
}