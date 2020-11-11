package com.utsc.project_coding_lads.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utsc.project_coding_lads.domain.Course;
import com.utsc.project_coding_lads.service.CourseService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/" + CourseService.SERVICE_NAME)
public class CourseController extends BaseController {

	@Autowired
	CourseService courseService;
	
	final static Logger log = LoggerFactory.getLogger(PostingController.class);
	
	@GetMapping(path = "/getAllCourses")
	@ApiOperation(value = "get all courses", response = Course.class, responseContainer = "List")
	public List<Course> getAllPostings() {
		List<Course> courses = new ArrayList<>();
		try {
			courses = courseService.getAllCourses();
		} catch (Exception e) {
			log.info("Could not get all courses", e.getMessage());
		}
		return courses;
	}
	
	
}