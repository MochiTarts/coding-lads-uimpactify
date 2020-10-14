package com.utsc.project_coding_lads.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = ImpactConsultant.TABLE_NAME)
public class ImpactConsultant extends BaseDataEntity {

	public static final String TABLE_NAME = "IMPACT_CONSULTANTS";
	
	
	private User user;

	@OneToOne(optional = false)
	@MapsId
	@JoinColumn(name = "id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
