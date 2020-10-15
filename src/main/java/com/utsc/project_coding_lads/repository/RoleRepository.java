package com.utsc.project_coding_lads.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.utsc.project_coding_lads.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

	@Query(value = "SELECT id FROM Role WHERE name = :roleName")
	public Integer findRoleIdByName(@Param("roleName") String roleName);
}
