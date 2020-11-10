package com.utsc.project_coding_lads.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utsc.project_coding_lads.domain.StudentAnswer;
import com.utsc.project_coding_lads.exception.EntityNotExistException;
import com.utsc.project_coding_lads.exception.ValidationFailedException;
import com.utsc.project_coding_lads.repository.StudentAnswerRepository;
import com.utsc.project_coding_lads.service.StudentAnswerService;

@Service
@Transactional
public class StudentAnswerServiceImpl implements StudentAnswerService {

	@Autowired
	StudentAnswerRepository studentAnswerRepo;
	
	@Override
	public Integer createBlankStudentAnswer(StudentAnswer studentAnswer) throws ValidationFailedException {
		studentAnswer.setStudentAnswer("");
		return studentAnswerRepo.save(studentAnswer).getId();
	}

	@Override
	public StudentAnswer findStudentAnswerById(Integer id) throws ValidationFailedException {
		return studentAnswerRepo.findById(id).get();
	}

	@Override
	public Integer updateStudentAnswer(StudentAnswer studentAnswer) throws ValidationFailedException {
		if (!existsById(studentAnswer.getId()))
			throw new EntityNotExistException("That answer does not exist for that student and course.");
		return studentAnswerRepo.save(studentAnswer).getId();
	}

	@Override
	public void deleteStudentAnswerById(Integer id) throws ValidationFailedException {
		if (!existsById(id)) 
			throw new EntityNotExistException("That answer does not exist for that student and course.");
		studentAnswerRepo.deleteById(id);
		
	}

	@Override
	public Boolean existsById(Integer id) {
		return studentAnswerRepo.existsById(id);
	}

	
}
