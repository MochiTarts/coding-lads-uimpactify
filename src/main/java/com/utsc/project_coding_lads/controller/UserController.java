package com.utsc.project_coding_lads.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utsc.project_coding_lads.domain.Posting;
import com.utsc.project_coding_lads.domain.User;
import com.utsc.project_coding_lads.exception.BadRequestException;
import com.utsc.project_coding_lads.exception.ValidationFailedException;
import com.utsc.project_coding_lads.security.PasswordHash;
import com.utsc.project_coding_lads.service.PostingService;
import com.utsc.project_coding_lads.service.UserService;

@RestController
@RequestMapping("/" + UserService.SERVICE_NAME)
public class UserController extends BaseController {

	@Autowired
	UserService userService;
	@Autowired 
	PostingService postingService;
	final static Logger log = LoggerFactory.getLogger(UserController.class);
	
	@PostMapping(path="/signup")
	public Integer storeUser(@RequestBody User user) throws Exception {
		try {
			PasswordHash encoder = new PasswordHash();
			user.setHashedPassword(encoder.passwordEncoder(user.getHashedPassword()));
			Integer id = userService.storeUser(user);
			log.info("userid: " + id);
			return id;
		} catch(NullPointerException e) {
			log.info("Could not store user: ", e);
			throw new BadRequestException("Request cannot be null");
		}
	}
	
	@PostMapping(path = "/createPosting")
	public Integer createPosting(@RequestBody Posting posting) {
		Integer id = null;
		try {
			id = postingService.savePosting(posting);
		} catch (ValidationFailedException e) {
			log.info("Could not create posting: ", e);
		}
		return id;
	}
	
	@PostMapping(path = "/updatePosting")
	public Integer updatePosting(@RequestBody Posting posting) throws Exception {
		return postingService.updatePosting(posting);
	}
	
	@PostMapping(path = "/deletePosting/{id}")
	public Boolean deletePosting(@PathVariable("id") Integer id) {
		Boolean ok = true;
		try {
			postingService.deletePostingById(id);
		} catch (Exception e) {
			ok = false;
			log.info("Could not delete posting: ", e.getMessage());
		}
		return ok;
	}
	
	
	@GetMapping(path = "/getPosting/{id}")
	public Posting getPosting(@PathVariable("id") Integer id) {
		Posting posting = null;
		try {
			posting = postingService.findPostingById(id);
		} catch (Exception e) {
			log.info("Could not get posting with id: " + id + ", ", e.getMessage());
		}
		return posting;
	}
	
	@GetMapping(path = "/getPostings/{id}")
	public List<Posting> getPostings(@PathVariable("id") Integer userId) {
		List<Posting> postings = null;
		try {
			postings = postingService.findAllPostingsByUserId(userId);
		} catch (Exception e) {
			log.info("Could not get postings with id: " + userId + ", ", e.getMessage());
		}
		return postings;
	}
	
}