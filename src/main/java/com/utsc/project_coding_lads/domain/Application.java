package com.utsc.project_coding_lads.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = Application.TABLE_NAME)
public class Application extends BaseDataEntity {

	public static final String TABLE_NAME = "APPLICATION";
	
	private User applicant;
	private Posting posting;
	private byte[] resume;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "applicant_id")
	public User getApplicant() {
		return applicant;
	}
	public void setApplicant(User applicant) {
		this.applicant = applicant;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="posting_id")
	public Posting getPosting() {
		return posting;
	}
	public void setPosting(Posting posting) {
		this.posting = posting;
	}
	@Lob
	@JoinColumn(name="resume")
	public byte[] getResume() {
		return resume;
	}
	public void setResume(byte[] resume) {
		this.resume = resume;
	}
	
}
