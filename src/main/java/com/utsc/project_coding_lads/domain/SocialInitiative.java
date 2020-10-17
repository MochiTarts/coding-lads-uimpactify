package com.utsc.project_coding_lads.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = SocialInitiative.TABLE_NAME)
public class SocialInitiative extends BaseDataEntity {

	public static final String TABLE_NAME = "SOCIAL_INITIATIVE";
	
	private String name;
	private List<Posting> postings;

	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@OneToMany
	@JoinColumn(name="posting_id")
	public List<Posting> getPostings() {
		return postings;
	}
	public void setPostings(List<Posting> postings) {
		this.postings = postings;
	}
	
	
}
