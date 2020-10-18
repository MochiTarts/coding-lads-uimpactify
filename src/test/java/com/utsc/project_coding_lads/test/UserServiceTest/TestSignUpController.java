package com.utsc.project_coding_lads.test.UserServiceTest;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
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
import com.utsc.project_coding_lads.domain.Role;
import com.utsc.project_coding_lads.domain.User;
import com.utsc.project_coding_lads.exception.BadRequestException;
import com.utsc.project_coding_lads.repository.RoleRepository;
import com.utsc.project_coding_lads.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration
@AutoConfigureMockMvc
public class TestSignUpController {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	RoleRepository roleRepo;

	@Autowired
	UserController controller;

	@Autowired
	MockMvc mockMvc;

	@Autowired
	WebApplicationContext webApplicationContext;
	
	@Before
	public void setUp() {
		Role impactLearner = new Role("impact_learner");
		Role impactConsultant = new Role("impact_consultant");
	
		if (roleRepo.findRoleIdByName("impact_learner") == null && roleRepo.findRoleIdByName("impact_consultant") == null) {
			roleRepo.save(impactLearner);
			roleRepo.save(impactConsultant);
		}
	}
	
	@Test
	public void addSocialOrgImpactLearner() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		MvcResult mvc = mockMvc.perform(MockMvcRequestBuilders.post("/signup")
								.contentType(MediaType.APPLICATION_JSON).content("{\n"
										+ "  \"firstName\" : \"first\",\n"
										+ "  \"lastName\" : \"last\",\n"
										+ "  \"username\" : \"1\",\n"
										+ "  \"password\" : \"asdf\",\n"
										+ "  \"age\" : 18,\n"
										+ "  \"role\": \"\",\n"
										+ "  \"socialInit\": {\"name\": \"Org B\"}\n"
										+ "}"))
								.andReturn();

		boolean found = userRepo.existsById(Integer.parseInt(mvc.getResponse().getContentAsString()));
		int status = mvc.getResponse().getStatus();

		Assert.assertTrue(found);
		Assert.assertEquals(200, status);
	}
	
	@Test
	public void addImpactLearnerNoSocialOrg() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		MvcResult mvc = mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/users/signup")
				.contentType(MediaType.APPLICATION_JSON).content("{\n"
						+ "  \"firstName\" : \"first\",\n"
						+ "  \"lastName\" : \"last\",\n"
						+ "  \"username\" : \"2\",\n"
						+ "  \"password\" : \"asdf\",\n"
						+ "  \"age\" : 18,\n"
						+ "  \"role\": {\"name\": \"impact_learner\"},\n"
						+ "  \"socialInit\": null\n"
						+ "}"))
				.andReturn();
		String id = mvc.getResponse().getContentAsString();
		boolean found = false;
		if (id != null && !id.isEmpty()) {
			found = userRepo.existsById(Integer.parseInt(id));
		}
		int status = mvc.getResponse().getStatus();
		Assert.assertTrue(found);
		Assert.assertEquals(200, status);
	}
	
	/*@Test
	public void addManyImpactLearners() throws Exception {
		Role impactLearner = new Role("impact_learner");
		Role impactConsultant = new Role("impact_consultant");
	
		roleRepo.save(impactLearner);
		roleRepo.save(impactConsultant);
		
		Map<String, Object> request1 = new HashMap<>();
		request1.put("firstName", "first");
		request1.put("lastName", "last");
		request1.put("username", "username3");
		request1.put("hashedPassword", "password");
		request1.put("age", 18);
		request1.put("userType", "impact_learners");
		request1.put("userSocialInit", null);
		
		Map<String, Object> request2 = new HashMap<>();
		request2.put("firstName", "first");
		request2.put("lastName", "last");
		request2.put("username", "username4");
		request2.put("hashedPassword", "password");
		request2.put("age", 18);
		request2.put("userType", "impact_consultant");
		request2.put("userSocialInit", null);
		
		ObjectMapper mapper = new ObjectMapper();
		String json1 = mapper.writeValueAsString(request1);
		String json2 = mapper.writeValueAsString(request2);
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		MvcResult mvc1 = mockMvc.perform(MockMvcRequestBuilders.post("/signup")
				.contentType(MediaType.APPLICATION_JSON).content(json1))
				.andReturn();
		MvcResult mvc2 = mockMvc.perform(MockMvcRequestBuilders.post("/signup")
				.contentType(MediaType.APPLICATION_JSON).content(json2))
				.andReturn();
		
		boolean found1 = userRepo.existsById(Integer.parseInt(mvc1.getResponse().getContentAsString()));
		int status1 = mvc1.getResponse().getStatus();
		boolean found2 = userRepo.existsById(Integer.parseInt(mvc2.getResponse().getContentAsString()));
		int status2 = mvc2.getResponse().getStatus();
		
		Assert.assertTrue(found1);
		Assert.assertEquals(200, status1);
		Assert.assertTrue(found2);
		Assert.assertEquals(200, status2);
	}
	
	@Test
	public void addManyImpactConsultants() throws Exception {
		Map<String, Object> request1 = new HashMap<>();
		request1.put("id", null);
		request1.put("firstName", "first");
		request1.put("lastName", "last");
		request1.put("username", "username");
		request1.put("hashedPassword", "password");
		request1.put("socialInit", null);
		request1.put("role", null);
		request1.put("age", 18);
		request1.put("events", null);
		request1.put("userType", "impact_consultant");
		
		Map<String, Object> request2 = new HashMap<>();
		request2.put("id", null);
		request2.put("firstName", "first");
		request2.put("lastName", "last");
		request2.put("username", "username");
		request2.put("hashedPassword", "password");
		request2.put("socialInit", null);
		request2.put("role", null);
		request2.put("age", 18);
		request2.put("events", null);
		request2.put("userType", "impact_consultant");
		
		ObjectMapper mapper = new ObjectMapper();
		String json1 = mapper.writeValueAsString(request1);
		String json2 = mapper.writeValueAsString(request2);
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		MvcResult mvc1 = mockMvc.perform(MockMvcRequestBuilders.post("/signup")
				.contentType(MediaType.APPLICATION_JSON).content(json1))
				.andReturn();
		MvcResult mvc2 = mockMvc.perform(MockMvcRequestBuilders.post("/signup")
				.contentType(MediaType.APPLICATION_JSON).content(json2))
				.andReturn();
		
		boolean found1 = userRepo.existsById(Integer.parseInt(mvc1.getResponse().getContentAsString()));
		int status1 = mvc1.getResponse().getStatus();
		boolean found2 = userRepo.existsById(Integer.parseInt(mvc2.getResponse().getContentAsString()));
		int status2 = mvc2.getResponse().getStatus();
		
		Assert.assertTrue(found1);
		Assert.assertEquals(200, status1);
		Assert.assertTrue(found2);
		Assert.assertEquals(200, status2);
	}
	@Test
	public void missingInfo() throws Exception {
		Map<String, Object> request = new HashMap<>();
		request.put("id", null);
		//request.put("firstName", "first");
		request.put("lastName", "last");
		request.put("username", "username");
		request.put("hashedPassword", "password");
		request.put("socialInit", null);
		request.put("role", null);
		request.put("age", 18);
		request.put("events", null);
		//request.put("userType", "impact_learner");
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(request);
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		MvcResult mvc = mockMvc.perform(MockMvcRequestBuilders.post("/signup")
				.contentType(MediaType.APPLICATION_JSON).content(json))
				.andReturn();
		int status = mvc.getResponse().getStatus();
		String message = mvc.getResponse().getContentAsString();
		Assert.assertTrue(message.contains("Request is either improperly formatted or missing info"));
		Assert.assertEquals(400, status);
	}
	@Test
	public void emptyJSONRequest() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		MvcResult mvc = mockMvc.perform(MockMvcRequestBuilders.post("/signup")
								.contentType(MediaType.APPLICATION_JSON).content("{}"))
								.andReturn();
		int status = mvc.getResponse().getStatus();
		String message = mvc.getResponse().getContentAsString();
		Assert.assertTrue(message.contains("Request is either improperly formatted or missing info"));
		Assert.assertEquals(400, status);
	}
	@Test
	public void improperRequest() throws Exception {
		Map<String, Object> request = new HashMap<>();
		request.put("id", null);
		request.put("firstname", "first"); //misspelled firstName as firstname
		request.put("lastName", "last");
		request.put("username", "username");
		request.put("hashedPassword", "password");
		request.put("socialInit", null);
		request.put("role", null);
		request.put("age", 18);
		request.put("events", null);
		request.put("userType", "impact_learner");
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(request);
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		MvcResult mvc = mockMvc.perform(MockMvcRequestBuilders.post("/signup")
				.contentType(MediaType.APPLICATION_JSON).content(json))
				.andReturn();
		int status = mvc.getResponse().getStatus();
		String message = mvc.getResponse().getContentAsString();
		Assert.assertTrue(message.contains("Request is either improperly formatted or missing info"));
		Assert.assertEquals(400, status);
	}
	@Test
	public void nullFieldsRequest() throws Exception {
		Map<String, Object> request = new HashMap<>();
		request.put("id", null);
		request.put("firstName", null);
		request.put("lastName", null);
		request.put("username", null);
		request.put("hashedPassword", null);
		request.put("socialInit", null);
		request.put("role", null);
		request.put("age", 18);
		request.put("events", null);
		request.put("userType", null);
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(request);
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		MvcResult mvc = mockMvc.perform(MockMvcRequestBuilders.post("/signup")
								.contentType(MediaType.APPLICATION_JSON).content(json))
								.andReturn();
		int status = mvc.getResponse().getStatus();
		String message = mvc.getResponse().getContentAsString();
		
		Assert.assertTrue(message.contains("Request is either improperly formatted or missing info"));
		Assert.assertEquals(400, status);
	}*/

	@Test(expected = BadRequestException.class)
	public void nullRequest() throws Exception {
		controller.storeUser(null);
	}

}