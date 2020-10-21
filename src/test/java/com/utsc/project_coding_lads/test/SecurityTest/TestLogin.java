package com.utsc.project_coding_lads.test.SecurityTest;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.utsc.project_coding_lads.Application;
import com.utsc.project_coding_lads.domain.User;
import com.utsc.project_coding_lads.exception.MissingInformationException;
import com.utsc.project_coding_lads.exception.UsernameNotFoundException;
import com.utsc.project_coding_lads.exception.WrongPasswordException;
import com.utsc.project_coding_lads.repository.RoleRepository;
import com.utsc.project_coding_lads.repository.UserRepository;
import com.utsc.project_coding_lads.security.SecurityConfig;
import com.utsc.project_coding_lads.service.SocialInitService;
import com.utsc.project_coding_lads.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class TestLogin {


	@Autowired
	SecurityConfig security;
		@Autowired
		UserRepository userRepo;
		@Autowired
		RoleRepository roleRepo;
		@Autowired
		UserService userService;
		@Autowired
		SocialInitService socialInitService;
		
		
		@Test
		public void testCorrectInfo() throws Exception {
			User user = new User();
			user.setAge(90);
			user.setFirstName("firstName");
			user.setLastName("lastname");
			user.setUsername("username");
			user.setHashedPassword("pw");

			User test = new User();
			test.setUsername("firstName");
			test.setHashedPassword("pw");
			User savedUser = userRepo.save(user);
			Integer result = security.authentication(test);
			User getUser = userRepo.getOne(savedUser.getId());
			Assert.assertEquals(getUser.getId(), result);
		}

		@Test
		public void testWrongUsername() throws Exception {
			
			User user = new User();
			user.setAge(90);
			user.setFirstName("firstName");
			user.setLastName("lastname");
			user.setUsername("username");
			user.setHashedPassword("pw");

			User test = new User();
			test.setUsername("wrong");
			test.setHashedPassword("pw");
			userRepo.save(user);
			
			
			Assert.assertThrows(UsernameNotFoundException.class, () -> {
				security.authentication(test);
			});
			
			
		}
		
		@Test
		public void testWrongPassword() throws Exception {
			
			User user = new User();
			user.setAge(90);
			user.setFirstName("firstName");
			user.setLastName("lastname");
			user.setUsername("username");
			user.setHashedPassword("pw");

			User test = new User();
			test.setUsername("username");
			test.setHashedPassword("wrong");
			userRepo.save(user);
			
			Assert.assertThrows(WrongPasswordException.class, () -> {
				security.authentication(test);
			});
			
		}
		
		@Test
		public void testEmptyUsername() throws Exception {
			

			User test = new User();
			test.setUsername("");
			test.setHashedPassword("wrong");
			
			Assert.assertThrows(MissingInformationException.class, () -> {
				security.authentication(test);
			});
			
			
		}
		
		@Test
		public void testEmptyPassword() throws Exception {
			
			

			User test = new User();
			test.setUsername("username");
			test.setHashedPassword("");
			
			Assert.assertThrows(MissingInformationException.class, () -> {
				security.authentication(test);
			});
			
			
		}
	}

