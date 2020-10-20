package com.utsc.project_coding_lads.validator;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.utsc.project_coding_lads.domain.User;
import com.utsc.project_coding_lads.exception.EntityNotExistException;
import com.utsc.project_coding_lads.exception.MissingInformationException;
import com.utsc.project_coding_lads.exception.ValidationFailedException;
import com.utsc.project_coding_lads.service.EventService;
import com.utsc.project_coding_lads.service.UserService;

@Component
@Transactional
public class EventValidator implements Validator {

	private String eventName;
	private String eventDesc;
	private User eventCreator;
	private LocalDateTime eventDate;
	private Integer eventId;
	
	@Autowired
	UserService userService;
	@Autowired
	EventService eventService;
	
	public void init(String eventName, String eventDesc, User eventCreator, LocalDateTime eventDate) {
		this.eventName = eventName;
		this.eventDesc = eventDesc;
		this.eventCreator = eventCreator;
		this.eventDate = eventDate;
	}
	
	public void init(String eventName, String eventDesc, User eventCreator, LocalDateTime eventDate, Integer eventId) {
		this.eventName = eventName;
		this.eventDesc = eventDesc;
		this.eventCreator = eventCreator;
		this.eventDate = eventDate;
		this.eventId = eventId;
	}

	@Override
	public void validate() throws ValidationFailedException {
		if (eventName == null || eventDesc == null || eventDate == null)
			throw new MissingInformationException("Required fields are missing.");
		if (eventCreator == null)
			throw new EntityNotExistException("The Event creator cannot be null.");
		if (eventCreator.getId() == null)
			throw new EntityNotExistException("The event creator Id cannot be null");
		if (!userService.existsById(eventCreator.getId()))
			throw new EntityNotExistException("That user does not exist in the db.");
	}
	
	public void validateExists() throws ValidationFailedException {
		validate();
		if (!eventService.existsById(eventId))
			throw new EntityNotExistException("That event does not exist.");
	}
}
