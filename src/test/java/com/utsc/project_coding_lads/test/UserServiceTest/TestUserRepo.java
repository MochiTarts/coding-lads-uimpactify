package com.utsc.project_coding_lads.test.UserServiceTest;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.utsc.project_coding_lads.Application;
import com.utsc.project_coding_lads.domain.User;
import com.utsc.project_coding_lads.repository.UserRepository;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class TestUserRepo {

	@Autowired
	UserRepository userRepo;
	
	@Test
	public void testCRUD() {
		
		User user = new User();
		user.setAge(90);
		user.setFirstName("firstName");
		user.setLastName("lastname");
		user.setUsername("username");
		user.setHashedPassword("pw");

//		user.setRole(new Role("impact_learner"));
//		user.setSocialInitiative(null);
		
		User savedUser = userRepo.save(user);
		Assert.assertNotNull(savedUser.getId());
		User getUser = userRepo.getOne(savedUser.getId());
		Assert.assertNotNull(getUser.getId());
	}

}
