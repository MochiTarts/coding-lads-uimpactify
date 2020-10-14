package com.utsc.project_coding_lads.test.UserServiceTest;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.hibernate.Hibernate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.utsc.project_coding_lads.Application;
import com.utsc.project_coding_lads.controller.UserController;
import com.utsc.project_coding_lads.domain.SocialInitiative;
import com.utsc.project_coding_lads.domain.User;
import com.utsc.project_coding_lads.repository.UserRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration
public class TestSignUpController {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	UserController controller;
	
	MockMvc mockMvc;
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	@Test
	public void addOneUser() throws Exception {
		
		SocialInitiative socialInit = new SocialInitiative();
		socialInit.setName("Heart");
		
		User newUser = new User();
		
		newUser.setId(1);
		newUser.setFirstName("first");
		newUser.setLastName("last");
		newUser.setUsername("username");
		newUser.setHashedPassword("password");
		newUser.setAge(18);
		newUser.setSocialInitiative(socialInit);
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		String json = ow.writeValueAsString(newUser);
		MvcResult mvc = mockMvc.perform(MockMvcRequestBuilders.post("/signup").contentType(MediaType.APPLICATION_JSON).content(json)).andReturn();
		
		boolean found = userRepo.existsById(1);
		
		int status = mvc.getResponse().getStatus();
		
		assertEquals(200, status);
		assertTrue(found);
		
	}
	
	@Test
	public void addNullUser() throws Exception {
	    
	    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		String json = ow.writeValueAsString(null);
		MvcResult mvc = mockMvc.perform(MockMvcRequestBuilders.post("/signup").contentType(MediaType.APPLICATION_JSON).content(json)).andReturn();
		
		int status = mvc.getResponse().getStatus();
		
		assertEquals(400, status);
	    
	}
	
	@Test
	public void addManyUsers() throws Exception {
		
		User user1 = new User();
		User user2 = new User();
		
		user1.setFirstName("first");
		user1.setLastName("last");
		user1.setUsername("username");
		user1.setHashedPassword("password");
		user1.setAge(18);
		
		user2.setFirstName("first");
		user2.setLastName("last");
		user2.setUsername("username");
		user2.setHashedPassword("password");
		user2.setAge(18);
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		String json1 = ow.writeValueAsString(user1);
		MvcResult mvc1 = mockMvc.perform(MockMvcRequestBuilders.post("/signup").contentType(MediaType.APPLICATION_JSON).content(json1)).andReturn();
		
		int status1 = mvc1.getResponse().getStatus();
		
		assertEquals(200, status1);
		
		String json2 = ow.writeValueAsString(user1);
		MvcResult mvc2 = mockMvc.perform(MockMvcRequestBuilders.post("/signup").contentType(MediaType.APPLICATION_JSON).content(json2)).andReturn();
		
		int status2 = mvc2.getResponse().getStatus();
		
		assertEquals(200, status2);
		
	}

}
