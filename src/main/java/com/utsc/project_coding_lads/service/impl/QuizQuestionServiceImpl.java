package com.utsc.project_coding_lads.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utsc.project_coding_lads.domain.Quiz;
import com.utsc.project_coding_lads.domain.QuizQuestion;
import com.utsc.project_coding_lads.exception.ValidationFailedException;
import com.utsc.project_coding_lads.repository.QuizQuestionRepository;
import com.utsc.project_coding_lads.service.QuizQuestionService;
import com.utsc.project_coding_lads.service.QuizService;
import com.utsc.project_coding_lads.validator.QuizQuestionValidator;

@Service
@Transactional
public class QuizQuestionServiceImpl implements QuizQuestionService {

	@Autowired
	QuizQuestionRepository quizQnRepo;
	@Autowired
	QuizQuestionValidator questionValidator;
	@Autowired
	QuizService quizService;
	
	@Override
	public Integer createQuizQuestion(QuizQuestion question) throws ValidationFailedException {
		questionValidator.init(question.getQuestionType(), question.getQuestionOptions());
		questionValidator.validate();
		return quizQnRepo.save(question).getId();
	}

	@Override
	public QuizQuestion findQuizQuestionById(Integer id) throws ValidationFailedException {
		return quizQnRepo.findById(id).get();
	}

	@Override
	public Integer updateQuizQuestion(QuizQuestion question) throws ValidationFailedException {
		return quizQnRepo.save(question).getId();
	}

	@Override
	public void deleteQuizQuestionById(Integer id) throws ValidationFailedException {
		quizQnRepo.deleteById(id);
	}

	@Override
	public List<QuizQuestion> findQuestionsByQuizId(Integer id) throws ValidationFailedException {
		Quiz savedQuiz = quizService.findQuizById(id);
		savedQuiz.getQuizQuestions().size();
		for (QuizQuestion qn : savedQuiz.getQuizQuestions()) {
			qn.getQuestionOptions().size();
			qn.getStudentAnswers().size();
		}
		return savedQuiz.getQuizQuestions();
	}

}
