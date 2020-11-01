package com.utsc.project_coding_lads.test.UserServiceTest;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.utsc.project_coding_lads.Application;
import com.utsc.project_coding_lads.domain.Course;
import com.utsc.project_coding_lads.domain.ImpactConsultant;
import com.utsc.project_coding_lads.domain.ImpactLearner;
import com.utsc.project_coding_lads.domain.ImpactLearnerCourse;
import com.utsc.project_coding_lads.domain.Role;
import com.utsc.project_coding_lads.domain.User;
import com.utsc.project_coding_lads.repository.RoleRepository;
import com.utsc.project_coding_lads.service.CourseService;
import com.utsc.project_coding_lads.service.ImpactConsultantService;
import com.utsc.project_coding_lads.service.ImpactLearnerService;
import com.utsc.project_coding_lads.service.PostingService;
import com.utsc.project_coding_lads.service.SocialInitService;
import com.utsc.project_coding_lads.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class TestManageCourses {
	
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
	ImpactLearnerService learnerService;
	@Autowired
	CourseService courseService;

	@Test
	public void testCRUDService() throws Exception {
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
		Integer savedCourseId = courseService.storeCourse(course);
		Course savedCourse = courseService.findCourseById(savedCourseId);
		Assert.assertNotNull(savedCourse);
		
		User user2 = new User();
		user2.setAge(90);
		user2.setFirstName("student");
		user2.setLastName("lastname");
		user2.setUsername("student");
		user2.setHashedPassword("password");
		Role learner = new Role();
		learner.setName("IMPACT_LEARNER");
		Role savedLearner = roleRepo.save(learner);
		user2.setRole(savedLearner);
		Integer studentId = userService.storeUser(user2);
		ImpactLearner savedStudent = learnerService.findLearnerById(studentId);
		Assert.assertNotNull(savedStudent);
		
		learnerService.addCourseToLearner(savedStudent, savedCourse);
		List<ImpactLearnerCourse> courses = learnerService.findCoursesByLearnerId(savedStudent.getId());
		
//		savedStudent = learnerService.findLearnerById(savedStudent.getId());
//		savedStudent.getCourses().size();
//		courses = savedStudent.getCourses();
		for (ImpactLearnerCourse impactLearnerCourse : courses) {
			System.out.println(impactLearnerCourse.getCourse().getCourseName());
		}
	}

}
