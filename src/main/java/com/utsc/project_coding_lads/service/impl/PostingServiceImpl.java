package com.utsc.project_coding_lads.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.utsc.project_coding_lads.domain.Posting;
import com.utsc.project_coding_lads.domain.User;
import com.utsc.project_coding_lads.exception.EntityNotExistException;
import com.utsc.project_coding_lads.exception.MissingInformationException;
import com.utsc.project_coding_lads.exception.ValidationFailedException;
import com.utsc.project_coding_lads.repository.PostingRepository;
import com.utsc.project_coding_lads.service.PostingService;
import com.utsc.project_coding_lads.service.UserService;
import com.utsc.project_coding_lads.validator.PostingValidator;

@Service
@Transactional
public class PostingServiceImpl implements PostingService {

	@Autowired
	PostingRepository postingRepo;
	@Autowired
	UserService userService;

	@Override
	public Posting savePosting(Posting posting) throws ValidationFailedException {
		if (posting == null)
			throw new MissingInformationException("Posting body is null");
		PostingValidator postingValidator = new PostingValidator(posting.getName(), posting.getPostingDesc(),
				posting.getPostingCreator(), posting.getPostingType(), posting.getPostingDate());
		postingValidator.validate();
		return postingRepo.save(posting);
	}

	@Override
	public Posting findPostingById(Integer postingId) throws ValidationFailedException {
		if (!existsById(postingId)) {
			throw new EntityNotExistException("That posting does not exist.");
		}
		return postingRepo.getOne(postingId);
	}

	@Override
	public void deletePostingById(Integer postingId) throws Exception {
		postingRepo.deleteById(postingId);
	}

	@Override
	public Posting updatePosting(Posting posting) throws ValidationFailedException {
		if (posting == null)
			throw new MissingInformationException("Posting body is null");
		PostingValidator postingValidator = new PostingValidator(posting.getName(), posting.getPostingDesc(),
				posting.getPostingCreator(), posting.getPostingType(), posting.getPostingDate(), posting.getId());
		postingValidator.validateExists();
		return postingRepo.save(posting);
	}

	@Override
	public Boolean existsById(Integer postingId) {
		return postingRepo.existsById(postingId);
	}

	@Override
	public List<Posting> findAllPostingsByUserId(Integer userId) throws ValidationFailedException {
		User user = userService.findUserById(userId);
		return user.getPostings();
	}

}
