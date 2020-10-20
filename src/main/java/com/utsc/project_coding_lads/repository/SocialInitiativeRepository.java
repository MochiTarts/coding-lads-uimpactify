package com.utsc.project_coding_lads.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.utsc.project_coding_lads.domain.SocialInitiative;

@Repository
@Transactional
public interface SocialInitiativeRepository extends JpaRepository<SocialInitiative, Integer> {
	
	@Query(value = "SELECT s FROM SocialInitiative s WHERE s.name = :socialInitName")
	public SocialInitiative findSocialInitByName(@Param("socialInitName")String socialInitName);
	
}
