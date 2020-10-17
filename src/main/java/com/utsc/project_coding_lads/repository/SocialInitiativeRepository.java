package com.utsc.project_coding_lads.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.utsc.project_coding_lads.domain.SocialInitiative;

@Repository
public interface SocialInitiativeRepository extends JpaRepository<SocialInitiative, Integer> {
	
	@Query(value = "SELECT id FROM SocialInitiative WHERE name = :socialInitName")
	public Integer findSocialInitIdByName(@Param("socialInitName")String socialInitName);
	
}
