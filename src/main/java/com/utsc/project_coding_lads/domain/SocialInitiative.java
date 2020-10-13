package com.utsc.project_coding_lads.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = SocialInitiative.TABLE_NAME)
public class SocialInitiative extends BaseDataEntity {

	public static final String TABLE_NAME = "SOCIAL_INITIATIVE";
	
	private String name;

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
