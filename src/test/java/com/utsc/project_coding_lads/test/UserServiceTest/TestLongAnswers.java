package com.utsc.project_coding_lads.test.UserServiceTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.utsc.project_coding_lads.Application;
import com.utsc.project_coding_lads.domain.Course;
import com.utsc.project_coding_lads.domain.ImpactConsultant;
import com.utsc.project_coding_lads.domain.ImpactLearner;
import com.utsc.project_coding_lads.domain.Invoice;
import com.utsc.project_coding_lads.domain.Quiz;
import com.utsc.project_coding_lads.domain.QuizQuestion;
import com.utsc.project_coding_lads.domain.QuizQuestionOption;
import com.utsc.project_coding_lads.domain.Role;
import com.utsc.project_coding_lads.domain.Solution;
import com.utsc.project_coding_lads.domain.StudentAnswer;
import com.utsc.project_coding_lads.domain.User;
import com.utsc.project_coding_lads.enums.QuizQuestionTypeEnum;
import com.utsc.project_coding_lads.repository.CourseRepository;
import com.utsc.project_coding_lads.repository.RoleRepository;
import com.utsc.project_coding_lads.service.CourseService;
import com.utsc.project_coding_lads.service.ImpactConsultantService;
import com.utsc.project_coding_lads.service.ImpactLearnerService;
import com.utsc.project_coding_lads.service.QuizService;
import com.utsc.project_coding_lads.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@Transactional
class TestLongAnswers {

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
	ImpactLearnerService learnerService;
	@Autowired
	QuizService quizService;
	
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
		
		User user2 = new User();
		user2.setAge(90);
		user2.setFirstName("student");
		user2.setLastName("lastname");
		user2.setUsername("student");
		user2.setHashedPassword("password");
		user2.setInvoices(new ArrayList<Invoice>());
		Role learner = new Role();
		learner.setName("IMPACT_LEARNER");
		Role savedLearner = roleRepo.save(learner);
		user2.setRole(savedLearner);
		Integer studentId = userService.storeUser(user2);
		ImpactLearner savedStudent = learnerService.findLearnerById(studentId);
		Assert.assertNotNull(savedStudent);
		Assert.assertNotNull(savedStudent.getUser().getInvoices());
		
		Course course = new Course();
		course.setCost(123);
		course.setCourseDesc("desc");
		course.setCourseName("name");
		course.setInstructor(savedInstructor);
		Integer courseId = courseService.storeCourse(course);
		Course savedCourse = courseService.findCourseById(courseId);
		learnerService.addCourseToLearner(savedStudent, savedCourse);
		
		Quiz quiz = new Quiz();
		LocalDateTime start = LocalDateTime.of(2000, 10, 31, 00, 00, 00);
		quiz.setQuizStartDate(start);
		LocalDateTime end = LocalDateTime.of(2000, 10, 31, 00, 10, 00);
		quiz.setQuizEndDate(end);
		quiz.setCourse(savedCourse);
		
		QuizQuestion question = new QuizQuestion();
		question.setQuestionType(QuizQuestionTypeEnum.SHORT_ANSWER.name());
		Solution solution = new Solution();
		solution.setAnswer("answer");
		question.setSolution(solution);
		quiz.getQuizQuestions().add(question);
		
		Integer savedQuizId = quizService.createQuiz(quiz);
		Quiz savedQuiz = quizService.findQuizById(savedQuizId);
		savedQuiz.getQuizQuestions().size();
		savedQuiz.getQuizQuestions().get(0).getStudentAnswers().size();
		Assert.assertFalse(savedQuiz.getQuizQuestions().isEmpty());
		Assert.assertFalse(savedQuiz.getQuizQuestions().get(0).getStudentAnswers().isEmpty());
		
		QuizQuestion qn = savedQuiz.getQuizQuestions().get(0);
		Solution soln = qn.getSolution();
		Assert.assertNotNull(soln);
		Assert.assertEquals("answer", soln.getAnswer());
		
		StudentAnswer answer = learnerService.longAnswerQuizQuestion(qn, savedStudent, "answer");
	}

}
