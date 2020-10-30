package com.utsc.project_coding_lads.domain;

import java.util.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = Event.TABLE_NAME)
public class Event extends BaseDataEntity {

	public static final String TABLE_NAME = "EVENT";
	
	private String eventName;
	private String eventDesc;
	private User eventCreator;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date eventDate;
	
	@Column(name = "event_name", length = 32)
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	@Column(name = "event_desc", length = 256)
	public String getEventDesc() {
		return eventDesc;
	}
	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}
	@Column(name = "event_date", length = 12)
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public User getEventCreator() {
		return eventCreator;
	}
	public void setEventCreator(User eventCreator) {
		this.eventCreator = eventCreator;
	}
	
	
	
	
	
}
