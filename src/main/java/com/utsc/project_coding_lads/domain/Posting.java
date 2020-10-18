package com.utsc.project_coding_lads.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.utsc.project_coding_lads.enums.PostingEnum;

@Entity
@Table(name = Posting.TABLE_NAME)
public class Posting extends BaseDataEntity {

	public static final String TABLE_NAME = "POSTING";
	
//	private List<Application> application;
	private String name;
	private String postingDesc;
	private User postingCreator;
	private String postingType;
	private LocalDateTime postingDate;
	
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
	@Column(name = "posting_desc", length = 32)
	public String getPostingDesc() {
		return postingDesc;
	}
	public void setPostingDesc(String postingDesc) {
		this.postingDesc = postingDesc;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	public User getPostingCreator() {
		return postingCreator;
	}
	public void setPostingCreator(User postingCreator) {
		this.postingCreator = postingCreator;
	}
	@Column(name = "posting_date", length = 12)
	public LocalDateTime getPostingDate() {
		return postingDate;
	}
	public void setPostingDate(LocalDateTime postingDate) {
		this.postingDate = postingDate;
	}
	@Column(name = "posting_type", length = 32)
	public String getPostingType() {
		return postingType;
	}
	public void setPostingType(String postingType) {
		this.postingType = postingType;
	}
	
	
	
	
}
