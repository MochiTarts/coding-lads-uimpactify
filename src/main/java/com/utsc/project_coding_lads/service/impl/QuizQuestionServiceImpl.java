package com.utsc.project_coding_lads.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utsc.project_coding_lads.domain.QuizQuestion;
import com.utsc.project_coding_lads.exception.ValidationFailedException;
import com.utsc.project_coding_lads.repository.QuizQuestionRepository;
import com.utsc.project_coding_lads.service.QuizQuestionService;
import com.utsc.project_coding_lads.validator.QuizQuestionValidator;

@Service
@Transactional
public class QuizQuestionServiceImpl implements QuizQuestionService {

	@Autowired
	QuizQuestionRepository quizQnRepo;
	@Autowired
	QuizQuestionValidator questionValidator;
	
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

}
