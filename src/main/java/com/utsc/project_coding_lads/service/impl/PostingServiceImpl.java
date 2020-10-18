package com.utsc.project_coding_lads.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.utsc.project_coding_lads.domain.Posting;
import com.utsc.project_coding_lads.exception.BadRequestException;
import com.utsc.project_coding_lads.exception.EntityNotExistException;
import com.utsc.project_coding_lads.exception.MissingInformationException;
import com.utsc.project_coding_lads.exception.ValidationFailedException;
import com.utsc.project_coding_lads.repository.PostingRepository;
import com.utsc.project_coding_lads.repository.UserRepository;
import com.utsc.project_coding_lads.service.PostingService;
import com.utsc.project_coding_lads.validator.PostingValidator;

@Service
@Transactional
public class PostingServiceImpl implements PostingService {

	@Autowired
	PostingRepository postingRepo;
	@Autowired
	UserRepository userRepo;

	@Override
	public Integer savePosting(Posting posting) throws ValidationFailedException {
		if (posting == null)
			throw new MissingInformationException("Posting body is null");
		PostingValidator postingValidator = new PostingValidator(posting.getName(), posting.getPostingDesc(),
				posting.getPostingCreator(), posting.getPostingType(), posting.getPostingDate());
		postingValidator.validate();
		return postingRepo.save(posting).getId();
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
	public Integer updatePosting(Posting posting) throws ValidationFailedException {
		if (posting == null)
			throw new MissingInformationException("Posting body is null");
		PostingValidator postingValidator = new PostingValidator(posting.getName(), posting.getPostingDesc(),
				posting.getPostingCreator(), posting.getPostingType(), posting.getPostingDate(), posting.getId());
		postingValidator.validateExists();
		return postingRepo.save(posting).getId();
	}

	@Override
	public Boolean existsById(Integer postingId) {
		return postingRepo.existsById(postingId);
	}

}
