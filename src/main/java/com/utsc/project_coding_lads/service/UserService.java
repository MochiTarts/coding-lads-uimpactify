package com.utsc.project_coding_lads.service;

import com.utsc.project_coding_lads.domain.User;

public interface UserService {

	final static String SERVICE_NAME = "users";
	
	public Integer storeUser(User user) throws Exception;
	
	public User findUserById(Integer id) throws Exception;
	
	public Boolean existsById(Integer id) throws Exception;
}