package com.utsc.project_coding_lads.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = Course.TABLE_NAME)
public class Course extends BaseDataEntity {

	public static final String TABLE_NAME = "COURSES";
	
	private String courseName;
	private String courseDesc;
	private ImpactConsultant instructor;
	
	@Column(name = "course_name", length = 64)
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	@Column(name = "course_desc", length = 256)
	public String getCourseDesc() {
		return courseDesc;
	}
	public void setCourseDesc(String courseDesc) {
		this.courseDesc = courseDesc;
	}
	@ManyToOne
	@JoinColumn(name = "impact_consultant_id")
	public ImpactConsultant getInstructor() {
		return instructor;
	}
	public void setInstructor(ImpactConsultant instructor) {
		this.instructor = instructor;
	}
	
	
}
