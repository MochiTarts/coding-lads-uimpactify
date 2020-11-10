package com.utsc.project_coding_lads.test.course;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.utsc.project_coding_lads.Application;
import com.utsc.project_coding_lads.domain.Course;
import com.utsc.project_coding_lads.domain.ImpactConsultant;
import com.utsc.project_coding_lads.domain.Quiz;
import com.utsc.project_coding_lads.domain.QuizQuestion;
import com.utsc.project_coding_lads.domain.QuizQuestionOption;
import com.utsc.project_coding_lads.domain.Role;
import com.utsc.project_coding_lads.domain.Solution;
import com.utsc.project_coding_lads.domain.User;
import com.utsc.project_coding_lads.enums.QuizQuestionTypeEnum;
import com.utsc.project_coding_lads.repository.CourseRepository;
import com.utsc.project_coding_lads.repository.RoleRepository;
import com.utsc.project_coding_lads.service.CourseService;
import com.utsc.project_coding_lads.service.ImpactConsultantService;
import com.utsc.project_coding_lads.service.QuizService;
import com.utsc.project_coding_lads.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@Transactional
public class TestQuizRepo {

	@Autowired
	CourseService courseService;
	@Autowired
	CourseRepository courseRepo;
	@Autowired
	RoleRepository roleRepo;
	@Autowired
	UserService userService;
	@Autowired
	ImpactConsultantService consultantService;
	@Autowired
	QuizService quizService;
	
	@Test
	public void testQuizCRUD() throws Exception {
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
		
		Course course = new Course();
		course.setCost(123);
		course.setCourseDesc("desc");
		course.setCourseName("name");
		course.setInstructor(savedInstructor);
		Integer courseId = courseService.storeCourse(course);
		Course savedCourse = courseService.findCourseById(courseId);
		
		Quiz quiz = new Quiz();
		LocalDateTime start = LocalDateTime.of(2000, 10, 31, 00, 00, 00);
		quiz.setQuizStartDate(start);
		LocalDateTime end = LocalDateTime.of(2000, 10, 31, 00, 10, 00);
		quiz.setQuizEndDate(end);
		quiz.setCourse(savedCourse);
		
		savedCourse.getQuizzes().add(quiz);
		savedCourse = courseRepo.save(savedCourse);
		savedCourse.getQuizzes().size();
		Assert.assertFalse(savedCourse.getQuizzes().isEmpty());
	}
	
	@Test
	public void testQuizServiceCRUD() throws Exception {
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
		
		Course course = new Course();
		course.setCost(123);
		course.setCourseDesc("desc");
		course.setCourseName("name");
		course.setInstructor(savedInstructor);
		Integer courseId = courseService.storeCourse(course);
		Course savedCourse = courseService.findCourseById(courseId);
		
		Quiz quiz = new Quiz();
		LocalDateTime start = LocalDateTime.of(2000, 10, 31, 00, 00, 00);
		quiz.setQuizStartDate(start);
		LocalDateTime end = LocalDateTime.of(2000, 10, 31, 00, 10, 00);
		quiz.setQuizEndDate(end);
		quiz.setCourse(savedCourse);
		
		QuizQuestion question = new QuizQuestion();
		question.setQuestionType(QuizQuestionTypeEnum.MULTIPLE_CHOICE.name());
		QuizQuestionOption option = new QuizQuestionOption();
		option.setQuestionOption("A");
		question.getQuestionOptions().add(option);
		Solution solution = new Solution();
		solution.setAnswer("answer");
		question.setSolution(solution);
		quiz.getQuizQuestions().add(question);
		
		Integer savedQuizId = quizService.createQuiz(quiz);
		Quiz savedQuiz = quizService.findQuizById(savedQuizId);
		savedQuiz.getQuizQuestions().size();
		Assert.assertFalse(savedQuiz.getQuizQuestions().isEmpty());
		
		QuizQuestion qn = savedQuiz.getQuizQuestions().get(0);
		QuizQuestionOption op = qn.getQuestionOptions().get(0);
		Assert.assertEquals(option.getQuestionOption(), op.getQuestionOption());
		
	}

}
