package com.utsc.project_coding_lads.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utsc.project_coding_lads.domain.ImpactLearner;

public interface ImpactLearnerRepository extends JpaRepository<ImpactLearner, Integer> {

}
