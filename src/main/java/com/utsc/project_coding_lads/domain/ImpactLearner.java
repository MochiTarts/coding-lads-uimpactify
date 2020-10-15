package com.utsc.project_coding_lads.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = ImpactLearner.TABLE_NAME)
public class ImpactLearner extends BaseDataEntity {

	public static final String TABLE_NAME = "IMPACT_LEARNER";
	
	private User user;

	@OneToOne(optional = false)
	@JoinColumn(name = "id")
	@MapsId
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
}
