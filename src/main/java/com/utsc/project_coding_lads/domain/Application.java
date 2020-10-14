package com.utsc.project_coding_lads.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//@Entity
//@Table(name = Application.TABLE_NAME)
public class Application extends BaseDataEntity {

	public static final String TABLE_NAME = "APPLICATION";
	
	private User user;
	private Posting posting;
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
//	@OneToMany
//	@JoinColumn(name="application_id")
	public Posting getPosting() {
		return posting;
	}
	public void setPosting(Posting posting) {
		this.posting = posting;
	}
	
}
