package com.utsc.project_coding_lads.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = Posting.TABLE_NAME)
public class Posting extends BaseDataEntity {

	public static final String TABLE_NAME = "POSTING";
	
//	private List<Application> application;
	private String name;
	private String desc;
	
//	public List<Application> getApplication() {
//		return application;
//	}
//	public void setApplication(List<Application> application) {
//		this.application = application;
//	}
	@Column(name = "name", length = 32)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "desc", length = 32)
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
