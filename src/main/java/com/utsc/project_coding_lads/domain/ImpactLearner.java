package com.utsc.project_coding_lads.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = ImpactLearner.TABLE_NAME)
public class ImpactLearner extends BaseDataEntity {

	public static final String TABLE_NAME = "IMPACT_LEARNER";
	
	private User user;
	private List<ImpactLearnerCourse> courses = new ArrayList<>();

	@OneToOne(optional = false)
	@JoinColumn(name = "id")
	@MapsId
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@JsonIgnore
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<ImpactLearnerCourse> getCourses() {
		return courses;
	}
	public void setCourses(List<ImpactLearnerCourse> courses) {
		this.courses = courses;
	}
	

	
	
	
	
	
}
