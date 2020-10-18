package com.utsc.project_coding_lads.service;

import com.utsc.project_coding_lads.domain.Posting;
import com.utsc.project_coding_lads.exception.ValidationFailedException;

public interface PostingService {

	public Integer savePosting(Posting posting) throws ValidationFailedException;
	
	public Posting findPostingById(Integer postingId) throws ValidationFailedException;
	
	public Boolean existsById(Integer postingId);
	
	public void deletePostingById(Integer postingId) throws Exception;
	
	public Integer updatePosting(Posting posting) throws ValidationFailedException;
}