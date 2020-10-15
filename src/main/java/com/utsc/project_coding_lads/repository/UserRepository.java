package com.utsc.project_coding_lads.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utsc.project_coding_lads.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	
//	public void storeUser(User user) throws Exception;
}
