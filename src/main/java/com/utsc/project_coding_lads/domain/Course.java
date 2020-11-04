package com.utsc.project_coding_lads.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = Course.TABLE_NAME)
public class Course extends BaseDataEntity {

	public static final String TABLE_NAME = "COURSE";
	
	private String courseName;
	private String courseDesc;
	private ImpactConsultant instructor;
	private List<ImpactLearnerCourse> students = new ArrayList<>();
	private List<ClassSession> sessions = new ArrayList<>();
	private Integer cost;
	
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
	@JsonIgnore
	@OneToMany(mappedBy = "course")
	public List<ClassSession> getSessions() {
		return sessions;
	}
	public void setSessions(List<ClassSession> sessions) {
		this.sessions = sessions;
	}
	@JsonIgnore
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<ImpactLearnerCourse> getStudents() {
		return students;
	}
	public void setStudents(List<ImpactLearnerCourse> students) {
		this.students = students;
	}
	@OneToOne
	@JoinColumn(name = "cost")
	public Integer getCost() {
		return cost;
	}
	public void setInvoice(Integer cost) {
		this.cost = cost;
	}
	
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Course))
			return false;
		Course course = (Course) object;
		return course.getId() == this.getId();
	}
	
}
