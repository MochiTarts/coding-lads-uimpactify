package com.utsc.project_coding_lads.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.utsc.project_coding_lads.domain.Invoice;
import com.utsc.project_coding_lads.domain.User;
import com.utsc.project_coding_lads.exception.EntityNotExistException;
import com.utsc.project_coding_lads.exception.ValidationFailedException;
import com.utsc.project_coding_lads.repository.InvoiceRepository;
import com.utsc.project_coding_lads.repository.UserRepository;
import com.utsc.project_coding_lads.service.InvoiceService;
import com.utsc.project_coding_lads.service.UserService;
import com.utsc.project_coding_lads.validator.UserValidator;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService{
	
	@Autowired
	InvoiceRepository invoiceRepo;
	@Autowired
	UserService userService;
	@Autowired
	UserValidator userValidator;

	@Override
	@SuppressWarnings("null")
	public List<Invoice> getInvoice(int userId){
		List<Invoice> result = null;
		try {
			List<Invoice> invoices = getAllInvoicesByUserId(userId);
			
			int i = invoices.size();
			if(i==0) {
				return null;
			}
			while(i!=0) {
				if(invoices.get(i).getCost() != 0) {
					result.add(invoices.get(i));
				}
				i--;
			}
		} catch (ValidationFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public List<Invoice> getAllInvoicesByUserId(Integer userId) throws ValidationFailedException{
		User user = userService.findUserById(userId);
		userValidator.init(user);
		userValidator.validate();
		user.getInvoices().size();
		List<Invoice> invoices = user.getInvoices();
		return invoices;
	}
	
	@SuppressWarnings("null")
	public Invoice findInvoiceByUserIdAndCourseId(Integer userId, Integer CourseId) throws Exception {
		List<Invoice> invoices = getAllInvoicesByUserId(userId);
		
		if(invoices.size()==0) {
			throw new Exception();
		}
		int i = invoices.size();
		while (i!=0) {
			if(invoices.get(i).getCourse().getId() == CourseId) {
				return invoices.get(i);
			}
		}
		
		
		return null;
		
	}


	@Override
	public Integer payInvoicePerCourse(Integer userId, Integer courseId) throws Exception {
		Invoice i = findInvoiceByUserIdAndCourseId(userId, courseId);
		i.setCost(0);
		return i.getCost();
		
	}
	
	public Integer InstructorPayment(Integer userId, Integer courseId) throws Exception {
		Invoice i = findInvoiceByUserIdAndCourseId(userId, courseId);
		Integer result = i.getCost(); 
		i.setCost(0);
		return result;
		
	}
}
