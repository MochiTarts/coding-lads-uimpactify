package com.utsc.project_coding_lads.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.utsc.project_coding_lads.domain.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer>{
	
	@Query(value = "SELECT i FROM Invoice i WHERE i.user_id = :userId")
	public List<Invoice> findInvoiceByUserId(@Param("userId") Integer userId);
	
}
