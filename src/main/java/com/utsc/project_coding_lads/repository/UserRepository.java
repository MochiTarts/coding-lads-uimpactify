package com.utsc.project_coding_lads.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.utsc.project_coding_lads.domain.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer>{

	
//	public void storeUser(User user) throws Exception;
}
