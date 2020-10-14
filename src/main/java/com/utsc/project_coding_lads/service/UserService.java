package com.utsc.project_coding_lads.service;

import org.springframework.validation.annotation.Validated;

import com.sun.istack.NotNull;
import com.utsc.project_coding_lads.domain.User;

@Validated
public interface UserService {

	public void storeUser(@NotNull User user) throws Exception;
}
