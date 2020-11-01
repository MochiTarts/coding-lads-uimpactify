package com.utsc.project_coding_lads.test.UserServiceTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.utsc.project_coding_lads.Application;
import com.utsc.project_coding_lads.controller.UserController;
import com.utsc.project_coding_lads.domain.ClassSession;
import com.utsc.project_coding_lads.domain.Course;
import com.utsc.project_coding_lads.domain.ImpactConsultant;
import com.utsc.project_coding_lads.domain.Role;
import com.utsc.project_coding_lads.domain.User;
import com.utsc.project_coding_lads.repository.ApplicationRepository;
import com.utsc.project_coding_lads.repository.ImpactLearnerCourseRepository;
import com.utsc.project_coding_lads.repository.RoleRepository;
import com.utsc.project_coding_lads.service.ApplicationService;
import com.utsc.project_coding_lads.service.CourseService;
import com.utsc.project_coding_lads.service.ImpactConsultantService;
import com.utsc.project_coding_lads.service.ImpactLearnerService;
import com.utsc.project_coding_lads.service.PostingService;
import com.utsc.project_coding_lads.service.SocialInitService;
import com.utsc.project_coding_lads.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
class TestManageCourseService {

	@Autowired
	UserService userService;
	@Autowired
	RoleRepository roleRepo;
	@Autowired
	SocialInitService socialInitService;
	@Autowired
	PostingService postingService;
	@Autowired
	CourseService courseService;
	@Autowired
	ImpactLearnerService learnerService;
	@Autowired
	ImpactConsultantService consultantService;
	
	@Test
	void testManageCourse() throws Exception {
		User instructor = new User();
		instructor.setFirstName("instructor");
		instructor.setLastName("lastname");
		instructor.setAge(20);
		instructor.setUsername("instructor");
		instructor.setHashedPassword("password");
		Role consultant = new Role();
		consultant.setName("IMPACT_CONSULTANT");
		Role savedConsultant = roleRepo.save(consultant);
		instructor.setRole(savedConsultant);
		Integer savedInstructorId = userService.storeUser(instructor);
		ImpactConsultant savedInstructor = consultantService.findImpactConsultantById(savedInstructorId);
		Assert.assertNotNull(savedInstructor);
		
		Course course1 = new Course();
		course1.setCourseName("course 1");
		course1.setCourseDesc("course 1 desc");
		course1.setInstructor(savedInstructor);
		course1.setSessions(null);
		Integer savedCourseId = courseService.storeCourseService(course1);
		Course savedCourse = courseService.findCourseById(savedCourseId);
		Assert.assertNotNull(savedCourse);
		
		
	}

}
