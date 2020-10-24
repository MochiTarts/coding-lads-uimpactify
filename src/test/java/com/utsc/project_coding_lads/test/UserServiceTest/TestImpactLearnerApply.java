package com.utsc.project_coding_lads.test.UserServiceTest;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.utsc.project_coding_lads.Application;
import com.utsc.project_coding_lads.domain.Posting;
import com.utsc.project_coding_lads.domain.Role;
import com.utsc.project_coding_lads.domain.SocialInitiative;
import com.utsc.project_coding_lads.domain.User;
import com.utsc.project_coding_lads.enums.PostingEnum;
import com.utsc.project_coding_lads.repository.RoleRepository;
import com.utsc.project_coding_lads.service.PostingService;
import com.utsc.project_coding_lads.service.SocialInitService;
import com.utsc.project_coding_lads.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class TestImpactLearnerApply {
	
	@Autowired
	UserService userService;
	@Autowired
	RoleRepository roleRepo;
	@Autowired
	SocialInitService siService;
	@Autowired
	PostingService postingService;

	@Test
	public void applyToVolunteering() throws Exception {
		User user = new User();
		user.setAge(90);
		user.setFirstName("firstName");
		user.setLastName("lastname");
		user.setUsername("username1");
		user.setHashedPassword("pw");
		SocialInitiative socialInit = new SocialInitiative();
		socialInit.setName("org");
		SocialInitiative saved = siService.storeSocialInit(socialInit);
		
		user.setSocialInit(saved);
		Integer id = userService.storeUser(user);
		User savedUser = userService.findUserById(id);
		
		Posting posting = new Posting();
		posting.setPostingDate(LocalDateTime.now());
		posting.setPostingDesc("desc");
		posting.setName("name");
		posting.setPostingType(PostingEnum.VOLUNTEERING.name());
		posting.setPostingCreator(savedUser);
		posting.setSocialInit(saved);
		Posting savedPosting = postingService.savePosting(posting);
		Assert.assertNotNull(savedPosting.getId());
		
		User learner = new User();
		learner.setAge(90);
		learner.setFirstName("firstName");
		learner.setLastName("lastname");
		learner.setUsername("usernameLearner1");
		learner.setHashedPassword("pw");
		Role role = new Role("IMPACT_LEARNER");
		Role savedRole = roleRepo.save(role);
		learner.setRole(savedRole);
		
		Integer learnerId = userService.storeUser(learner);
		User savedLearner = userService.findUserById(id);
	}
	
	@Test
	public void applyToEmployment() throws Exception {
		User user = new User();
		user.setAge(90);
		user.setFirstName("firstName");
		user.setLastName("lastname");
		user.setUsername("username2");
		user.setHashedPassword("pw");
		SocialInitiative socialInit = new SocialInitiative();
		socialInit.setName("org b");
		SocialInitiative saved = siService.storeSocialInit(socialInit);
		
		user.setSocialInit(saved);
		Integer id = userService.storeUser(user);
		User savedUser = userService.findUserById(id);
		
		Posting posting = new Posting();
		posting.setPostingDate(LocalDateTime.now());
		posting.setPostingDesc("desc");
		posting.setName("name");
		posting.setPostingType(PostingEnum.EMPLOYMENT.name());
		posting.setPostingCreator(savedUser);
		posting.setSocialInit(saved);
		Posting savedPosting = postingService.savePosting(posting);
		Assert.assertNotNull(savedPosting.getId());
	}

}
