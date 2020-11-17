package com.utsc.project_coding_lads.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.utsc.project_coding_lads.domain.Event;
import com.utsc.project_coding_lads.domain.Invoice;
import com.utsc.project_coding_lads.domain.User;
import com.utsc.project_coding_lads.exception.EntityNotExistException;
import com.utsc.project_coding_lads.exception.MissingInformationException;
import com.utsc.project_coding_lads.exception.ValidationFailedException;
import com.utsc.project_coding_lads.repository.InvoiceRepository;
import com.utsc.project_coding_lads.repository.UserRepository;
import com.utsc.project_coding_lads.service.InvoiceService;
import com.utsc.project_coding_lads.service.UserService;
import com.utsc.project_coding_lads.validator.InvoiceValidator;
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
	@Autowired
	InvoiceValidator invoiceValidator;

	@Override
	public List<Invoice> getUnpaidInvoice(int userId){
		List<Invoice> result = new ArrayList<Invoice>();
		try {
			List<Invoice> invoices = getAllInvoicesByUserId(userId);
			
			int i = invoices.size();
			if(i==0) {
				return null;
			}
			for(int x = 0; x<i; x++) {
				if(invoices.get(x).getCost() != 0) {
					result.add(invoices.get(x));
				}
				
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
		userValidator.validateExists();
		user.getInvoices().size();
		List<Invoice> invoices = user.getInvoices();
		return invoices;
	}
	
	 
	
	@Override
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
	public Integer payInvoice(Integer invoiceId) throws Exception{
		Invoice inv = invoiceRepo.getOne(invoiceId);
		inv.setCost(0);
		return inv.getCost();
	}

	@Override
	public Integer payInvoicePerCourse(Integer userId, Integer courseId) throws Exception {
		Invoice i = findInvoiceByUserIdAndCourseId(userId, courseId);
		i.setCost(0);
		return i.getCost();
		
	}
	
	@Override
	public Integer InstructorPayment(Integer userId, Integer courseId) throws Exception {
		Invoice i = findInvoiceByUserIdAndCourseId(userId, courseId);
		Integer result = i.getCost(); 
		i.setCost(0);
		return result;
		
	}
	
	@Override
	public Boolean existsById(Integer invoiceId) {
		return invoiceRepo.existsById(invoiceId);
	}
	
	
	@Override
	public Invoice saveInvoice(Invoice inv) throws ValidationFailedException {
		if (inv == null)
			throw new MissingInformationException("Invoice body is null");
		invoiceValidator.init(inv.getCost(), inv.getUser(), inv.getCourse());
		invoiceValidator.validate();
		User user = userService.findUserById(inv.getUser().getId());
		inv.setUser(user);
		user.getInvoices().add(inv);
		User savedUser = userService.updateUser(user);
		Invoice saved = savedUser.getInvoices().get(user.getInvoices().size() - 1);
		return saved;
	}
	
	public Invoice updateInvoice(Invoice invoice) throws ValidationFailedException {
		if(invoice == null) 
			throw new MissingInformationException("Invoice body is null");
		invoiceValidator.init(invoice.getCost(), invoice.getUser(), invoice.getCourse(), invoice.getId());
		invoiceValidator.validateExists();
		User user = userService.findUserById(invoice.getUser().getId());
		invoice.setUser(user);
		return invoiceRepo.save(invoice);
	}
	
	public void deleteInvoiceById(Integer id) throws Exception{
		invoiceRepo.deleteById(id);
	}
}
