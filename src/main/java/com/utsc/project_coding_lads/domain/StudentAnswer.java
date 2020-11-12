package com.utsc.project_coding_lads.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = StudentAnswer.TABLE_NAME)
public class StudentAnswer extends BaseDataEntity {

	public static final String TABLE_NAME = "STUDENT_ANSWER";
	
	private ImpactLearner student;
	private QuizQuestion question;
	private String studentAnswer;
	
	@ManyToOne
	@JoinColumn(name = "student_id")
	public ImpactLearner getStudent() {
		return student;
	}
	public void setStudent(ImpactLearner student) {
		this.student = student;
	}
	@ManyToOne
	@JoinColumn(name = "question_id")
	public QuizQuestion getQuestion() {
		return question;
	}
	public void setQuestion(QuizQuestion question) {
		this.question = question;
	}
	@Column(name = "student_answer")
	public String getStudentAnswer() {
		return studentAnswer;
	}
	public void setStudentAnswer(String studentAnswer) {
		this.studentAnswer = studentAnswer;
	}
	
	
}
