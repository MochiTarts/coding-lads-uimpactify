package com.utsc.project_coding_lads.test.UserServiceTest;

import java.time.LocalDateTime;
import java.util.List;

import org.json.JSONObject;
import org.junit.Assert;
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

import com.utsc.project_coding_lads.Application;
import com.utsc.project_coding_lads.controller.UserController;
import com.utsc.project_coding_lads.domain.Posting;
import com.utsc.project_coding_lads.domain.Role;
import com.utsc.project_coding_lads.domain.SocialInitiative;
import com.utsc.project_coding_lads.domain.User;
import com.utsc.project_coding_lads.enums.PostingEnum;
import com.utsc.project_coding_lads.exception.MissingInformationException;
import com.utsc.project_coding_lads.repository.ApplicationRepository;
import com.utsc.project_coding_lads.repository.RoleRepository;
import com.utsc.project_coding_lads.service.ApplicationService;
import com.utsc.project_coding_lads.service.PostingService;
import com.utsc.project_coding_lads.service.SocialInitService;
import com.utsc.project_coding_lads.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration
@AutoConfigureMockMvc
public class TestImpactLearnerApply {

	@Autowired
	UserService userService;
	@Autowired
	RoleRepository roleRepo;
	@Autowired
	SocialInitService siService;
	@Autowired
	PostingService postingService;
	@Autowired
	ApplicationRepository appRepo;
	@Autowired
	ApplicationService appService;
	@Autowired
	MockMvc mockMvc;
	@Autowired
	WebApplicationContext webApplicationContext;
	@Autowired
	UserController controller;
	
	@Test(expected = MissingInformationException.class)
	public void applyNull() throws Exception {
		controller.apply(null, null);
	}

	@Test
	public void applyToVolunteering() throws Exception {
		User user = new User();
		user.setAge(90);
		user.setFirstName("firstNameCreator");
		user.setLastName("lastname");
		user.setUsername("username1");
		user.setHashedPassword("pw");
		SocialInitiative socialInit = new SocialInitiative();
		socialInit.setName("org a");
		SocialInitiative savedSI = siService.storeSocialInit(socialInit);
		
		user.setSocialInit(savedSI);
		Integer savedUserId = userService.storeUser(user);
		User savedUser = userService.findUserById(savedUserId);
		
		Posting posting = new Posting();
		posting.setPostingDate(LocalDateTime.now());
		posting.setPostingDesc("desc");
		posting.setName("name");
		posting.setPostingType(PostingEnum.VOLUNTEERING.name());
		posting.setPostingCreator(savedUser);
		posting.setSocialInit(savedSI);
		Posting savedPosting = postingService.savePosting(posting);
		Assert.assertNotNull(savedPosting.getId());
		Integer savedPostingId = savedPosting.getId();
		
		User learner = new User();
		learner.setAge(90);
		learner.setFirstName("firstName");
		learner.setLastName("lastname");
		learner.setUsername("usernameLearner1");
		learner.setHashedPassword("pw");
		Role role = new Role("IMPACT_LEARNER");
		Role savedRole = roleRepo.save(role);
		learner.setRole(savedRole);
		
		Integer savedLearnerId = userService.storeUser(learner);
		User savedLearner = userService.findUserById(savedLearnerId);
		Assert.assertNotNull(savedLearner);
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		MvcResult mvc = mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/users/apply")
								.contentType(MediaType.APPLICATION_JSON).content("{\n"
										+ "  \"applicant\": {\n"
										+ "      \"id\": " + savedLearnerId + "\n"
										+ "  },\n"
										+ "  \"posting\": {\n"
										+ "      \"id\": " + savedPostingId + "\n"
										+ "  }\n"
										+ "}"))
								.andReturn();
		
//		System.out.println(mvc.getResponse().getContentAsString());
		JSONObject application = new JSONObject(mvc.getResponse().getContentAsString());
		Boolean found = appRepo.existsById(application.getInt("id"));
		int status = mvc.getResponse().getStatus();
		Assert.assertTrue(found);
		Assert.assertEquals(200, status);
	}
	
	/*@Test
	public void applyToEmployment() throws Exception {
		User user = new User();
		user.setAge(90);
		user.setFirstName("firstName");
		user.setLastName("lastname");
		user.setUsername("username2");
		user.setHashedPassword("pw");
		SocialInitiative socialInit = new SocialInitiative();
		socialInit.setName("org b");
		SocialInitiative savedSI = siService.storeSocialInit(socialInit);
		
		user.setSocialInit(savedSI);
		Integer savedUserId = userService.storeUser(user);
		User savedUser = userService.findUserById(savedUserId);
		
		Posting posting = new Posting();
		posting.setPostingDate(LocalDateTime.now());
		posting.setPostingDesc("desc");
		posting.setName("name");
		posting.setPostingType(PostingEnum.EMPLOYMENT.name());
		posting.setPostingCreator(savedUser);
		posting.setSocialInit(savedSI);
		Posting savedPosting = postingService.savePosting(posting);
		Assert.assertNotNull(savedPosting.getId());
		Integer savedPostingId = savedPosting.getId();
		
		User learner = new User();
		learner.setAge(90);
		learner.setFirstName("firstName");
		learner.setLastName("lastname");
		learner.setUsername("usernameLearner2");
		learner.setHashedPassword("pw");
		Role savedRole = roleRepo.findRoleByName("IMPACT_LEARNER");
		learner.setRole(savedRole);
		
		Integer savedLearnerId = userService.storeUser(learner);
		User savedLearner = userService.findUserById(savedLearnerId);
		Assert.assertNotNull(savedLearner);
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		MvcResult mvc = mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/users/apply")
								.contentType(MediaType.APPLICATION_JSON).content("{\n"
										+ "  \"applicant\": {\n"
										+ "      \"id\": " + savedLearnerId + "\n"
										+ "  },\n"
										+ "  \"posting\": {\n"
										+ "      \"id\": " + savedPostingId + "\n"
										+ "  }\n"
										+ "}"))
								.andReturn();
		
//		System.out.println(mvc.getResponse().getContentAsString());
		JSONObject application = new JSONObject(mvc.getResponse().getContentAsString());
		Boolean found = appRepo.existsById(application.getInt("id"));
		int status = mvc.getResponse().getStatus();
		Assert.assertTrue(found);
		Assert.assertEquals(200, status);
	}
	
	@Test
	public void applyToVolunteeringIneligible() throws Exception {
		User user = new User();
		user.setAge(90);
		user.setFirstName("firstNameCreator");
		user.setLastName("lastname");
		user.setUsername("username3");
		user.setHashedPassword("pw");
		SocialInitiative socialInit = new SocialInitiative();
		socialInit.setName("org c");
		SocialInitiative savedSI = siService.storeSocialInit(socialInit);
		
		user.setSocialInit(savedSI);
		Integer savedUserId = userService.storeUser(user);
		User savedUser = userService.findUserById(savedUserId);
		
		Posting posting = new Posting();
		posting.setPostingDate(LocalDateTime.now());
		posting.setPostingDesc("desc");
		posting.setName("name");
		posting.setPostingType(PostingEnum.VOLUNTEERING.name());
		posting.setPostingCreator(savedUser);
		posting.setSocialInit(savedSI);
		Posting savedPosting = postingService.savePosting(posting);
		Assert.assertNotNull(savedPosting.getId());
		Integer savedPostingId = savedPosting.getId();
		
		User consultant = new User();
		consultant.setAge(90);
		consultant.setFirstName("firstName");
		consultant.setLastName("lastname");
		consultant.setUsername("usernameLearner3");
		consultant.setHashedPassword("pw");
		Role role = new Role("IMPACT_CONSULTANT");
		Role savedRole = roleRepo.save(role);
		consultant.setRole(savedRole);
		
		Integer savedConsultantId = userService.storeUser(consultant);
		User savedConsultant = userService.findUserById(savedConsultantId);
		Assert.assertNotNull(savedConsultant);
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		MvcResult mvc = mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/users/apply")
								.contentType(MediaType.APPLICATION_JSON).content("{\n"
										+ "  \"applicant\": {\n"
										+ "      \"id\": " + savedConsultantId + "\n"
										+ "  },\n"
										+ "  \"posting\": {\n"
										+ "      \"id\": " + savedPostingId + "\n"
										+ "  }\n"
										+ "}"))
								.andReturn();
		
//		System.out.println(mvc.getResponse().getContentAsString());
		Assert.assertTrue(mvc.getResponse().getContentAsString().contains("Impact learners can only apply to employment and/or volunteering opportunities. "
				+ "Impact consultants can only apply to consultant opportunities"));
		int status = mvc.getResponse().getStatus();
		Assert.assertEquals(400, status);
	}
	
	@Test
	public void applyToEmploymentIneligible() throws Exception {
		User user = new User();
		user.setAge(90);
		user.setFirstName("firstNameCreator");
		user.setLastName("lastname");
		user.setUsername("username4");
		user.setHashedPassword("pw");
		SocialInitiative socialInit = new SocialInitiative();
		socialInit.setName("org d");
		SocialInitiative savedSI = siService.storeSocialInit(socialInit);
		
		user.setSocialInit(savedSI);
		Integer savedUserId = userService.storeUser(user);
		User savedUser = userService.findUserById(savedUserId);
		
		Posting posting = new Posting();
		posting.setPostingDate(LocalDateTime.now());
		posting.setPostingDesc("desc");
		posting.setName("name");
		posting.setPostingType(PostingEnum.EMPLOYMENT.name());
		posting.setPostingCreator(savedUser);
		posting.setSocialInit(savedSI);
		Posting savedPosting = postingService.savePosting(posting);
		Assert.assertNotNull(savedPosting.getId());
		Integer savedPostingId = savedPosting.getId();
		
		User consultant = new User();
		consultant.setAge(90);
		consultant.setFirstName("firstName");
		consultant.setLastName("lastname");
		consultant.setUsername("usernameLearner4");
		consultant.setHashedPassword("pw");
		consultant.setRole(roleRepo.findRoleByName("IMPACT_CONSULTANT"));
		
		Integer savedConsultantId = userService.storeUser(consultant);
		User savedConsultant = userService.findUserById(savedConsultantId);
		Assert.assertNotNull(savedConsultant);
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		MvcResult mvc = mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/users/apply")
								.contentType(MediaType.APPLICATION_JSON).content("{\n"
										+ "  \"applicant\": {\n"
										+ "      \"id\": " + savedConsultantId + "\n"
										+ "  },\n"
										+ "  \"posting\": {\n"
										+ "      \"id\": " + savedPostingId + "\n"
										+ "  }\n"
										+ "}"))
								.andReturn();
		
//		System.out.println(mvc.getResponse().getContentAsString());
		Assert.assertTrue(mvc.getResponse().getContentAsString().contains("Impact learners can only apply to employment and/or volunteering opportunities. "
				+ "Impact consultants can only apply to consultant opportunities"));
		int status = mvc.getResponse().getStatus();
		Assert.assertEquals(400, status);
	}*/

}
