package com.utsc.project_coding_lads.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = ImpactConsultant.TABLE_NAME)
public class ImpactConsultant extends BaseDataEntity {

	public static final String TABLE_NAME = "IMPACT_CONSULTANT";
	
	private User user;
	private List<Course> courses;

	@OneToOne(optional = false)
	@MapsId
	@JoinColumn(name = "id")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@OneToMany
	@JoinColumn(name = "course_id")
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
	
	
}
