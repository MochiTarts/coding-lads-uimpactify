package com.utsc.project_coding_lads.service;

import java.util.List;

import com.utsc.project_coding_lads.domain.Invoice;
import com.utsc.project_coding_lads.exception.ValidationFailedException;

public interface InvoiceService {
	
	public Integer payInvoice(Integer invoiceId) throws Exception;

	public List<Invoice> getUnpaidInvoice(int userId);
	
	public List<Invoice> getAllInvoicesByUserId(Integer userId) throws ValidationFailedException;

	public Invoice findInvoiceByUserIdAndCourseId(Integer userId, Integer CourseId) throws Exception;

	public Integer payInvoicePerCourse(Integer userId, Integer courseId) throws Exception;
	
	public Integer InstructorPayment(Integer userId, Integer courseId) throws Exception;

	Boolean existsById(Integer invoiceId);

	Invoice saveInvoice(Invoice inv) throws ValidationFailedException; 
}
