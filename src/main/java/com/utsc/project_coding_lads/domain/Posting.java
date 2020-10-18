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
	private String desc;
	private User postingCreator;
	private PostingEnum postingType;
	private LocalDateTime date;
	
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
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public User getPostingCreator() {
		return postingCreator;
	}
	public void setPostingCreator(User postingCreator) {
		this.postingCreator = postingCreator;
	}
	@Column(name = "posting_date", length = 12)
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	@Column(name = "posting_type", length = 32)
	public PostingEnum getPostingType() {
		return postingType;
	}
	public void setPostingType(PostingEnum postingType) {
		this.postingType = postingType;
	}
	
	
	
	
}
