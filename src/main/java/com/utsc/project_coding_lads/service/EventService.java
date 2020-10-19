package com.utsc.project_coding_lads.service;

import java.util.List;

import com.utsc.project_coding_lads.domain.Event;
import com.utsc.project_coding_lads.exception.ValidationFailedException;

public interface EventService {

	public Event saveEvent(Event event) throws ValidationFailedException;
	
	public Event findEventById(Integer eventId) throws ValidationFailedException;
	
	public Boolean existsById(Integer eventId);
	
	public void deleteEventById(Integer eventId) throws Exception;
	
	public Event updateEvent(Event event) throws ValidationFailedException;
	
	public List<Event> findAllEventsByUserId(Integer userId) throws ValidationFailedException;
}