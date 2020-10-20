package com.utsc.project_coding_lads.test.EventTest;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.utsc.project_coding_lads.Application;
import com.utsc.project_coding_lads.domain.Event;
import com.utsc.project_coding_lads.domain.Posting;
import com.utsc.project_coding_lads.domain.Role;
import com.utsc.project_coding_lads.domain.SocialInitiative;
import com.utsc.project_coding_lads.domain.User;
import com.utsc.project_coding_lads.enums.PostingEnum;
import com.utsc.project_coding_lads.exception.EntityNotExistException;
import com.utsc.project_coding_lads.repository.RoleRepository;
import com.utsc.project_coding_lads.service.EventService;
import com.utsc.project_coding_lads.service.SocialInitService;
import com.utsc.project_coding_lads.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class TestEventService {

	@Autowired
	EventService eventService;
	@Autowired
	UserService userService;
	@Autowired
	RoleRepository roleRepo;
	@Autowired
	SocialInitService socialInitService;
	
	@Test
	public void testCRUD() throws Exception {
		User user = new User();
		user.setAge(90);
		user.setFirstName("firstName");
		user.setLastName("lastname");
		user.setUsername("username");
		user.setHashedPassword("pw");
		Role role1 = new Role("IMPACT_LEARNER");
		Role role_1 = roleRepo.save(role1);
		user.setRole(role_1);
		SocialInitiative socialInit = new SocialInitiative();
		socialInit.setName("org");
		SocialInitiative saved = socialInitService.storeSocialInit(socialInit);
		
		user.setSocialInit(saved);
		Integer id = userService.storeUser(user);
		User savedUser = userService.findUserById(id);
		
		Event event = new Event();
		event.setEventName("name");
		event.setEventDesc("desc");
		event.setEventDate(LocalDateTime.now());
		event.setEventCreator(savedUser);
		
		Event savedEvent = eventService.saveEvent(event);
		Assert.assertNotNull(savedEvent.getId());
		
		Event getEvent = eventService.findEventById(savedEvent.getId());
		Assert.assertNotNull(getEvent);
		Assert.assertEquals(savedEvent.getId(), getEvent.getId());
		
		savedEvent.setEventName("new name");
		savedEvent = eventService.updateEvent(savedEvent);
		Assert.assertEquals("new name", savedEvent.getEventName());
		eventService.deleteEventById(savedEvent.getId());
		
		Boolean exist = eventService.existsById(id);
		Assert.assertFalse(exist);
	}
	
}
