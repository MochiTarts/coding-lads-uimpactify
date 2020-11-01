package com.utsc.project_coding_lads.service;

import java.util.List;

import com.utsc.project_coding_lads.domain.Invoice;
import com.utsc.project_coding_lads.exception.ValidationFailedException;

public interface InvoiceService {

	public List<Invoice> getInvoice(int userId);
	
	public List<Invoice> getAllInvoicesByUserId(Integer userId) throws ValidationFailedException;

	public Invoice findInvoiceByUserIdAndCourseId(Integer userId, Integer CourseId) throws Exception;

	public Integer payInvoicePerCourse(Integer userId, Integer courseId) throws Exception;
	
	public Integer InstructorPayment(Integer userId, Integer courseId) throws Exception; 
}
