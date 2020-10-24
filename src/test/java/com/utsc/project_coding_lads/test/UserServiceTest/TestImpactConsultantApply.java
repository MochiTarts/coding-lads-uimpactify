package com.utsc.project_coding_lads.test.UserServiceTest;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.utsc.project_coding_lads.Application;
import com.utsc.project_coding_lads.repository.ImpactConsultantRepository;
import com.utsc.project_coding_lads.service.ImpactConsultantService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class TestImpactConsultantApply {
	
	@Autowired
	ImpactConsultantRepository impactConsultantRepo;
	@Autowired
	ImpactConsultantService icService;

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
