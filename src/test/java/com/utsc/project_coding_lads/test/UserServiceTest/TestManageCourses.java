package com.utsc.project_coding_lads.test.UserServiceTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.utsc.project_coding_lads.Application;
import com.utsc.project_coding_lads.domain.Course;
import com.utsc.project_coding_lads.domain.ImpactConsultant;
import com.utsc.project_coding_lads.domain.Role;
import com.utsc.project_coding_lads.domain.User;
import com.utsc.project_coding_lads.repository.RoleRepository;
import com.utsc.project_coding_lads.service.CourseService;
import com.utsc.project_coding_lads.service.ImpactConsultantService;
import com.utsc.project_coding_lads.service.PostingService;
import com.utsc.project_coding_lads.service.SocialInitService;
import com.utsc.project_coding_lads.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
class TestManageCourses {
	
	@Autowired
	UserService userService;
	@Autowired
	RoleRepository roleRepo;
	@Autowired
	SocialInitService siService;
	@Autowired
	PostingService postingService;
	@Autowired
	ImpactConsultantService consultantService;
	@Autowired
	CourseService courseService;

	@Test
	void testService() throws Exception {
		User user = new User();
		user.setAge(20);
		user.setFirstName("instructor");
		user.setLastName("lastname");
		user.setUsername("instructor");
		user.setHashedPassword("password");
		Role consultant = new Role();
		consultant.setName("IMPACT_CONSULTANT");
		Role savedConsultant = roleRepo.save(consultant);
		user.setRole(savedConsultant);
		Integer instructorId = userService.storeUser(user);
		ImpactConsultant savedInstructor = consultantService.findImpactConsultantById(instructorId);
		Assert.assertNotNull(savedInstructor);
		
		Course course = new Course();
		course.setCourseName("course");
		course.setCourseDesc("desc");
		course.setInstructor(savedInstructor);
		Integer savedCourseId = courseService.storeCourseService(course);
	}

}
