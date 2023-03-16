package com.revature.reportapp;

import com.revature.reportapp.entity.Complaint;
import com.revature.reportapp.repository.ComplaintRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReportAppApplicationTests {
	@Autowired
	ComplaintRepo complaintRepo;

	@Test
	public void testCreate() {
		Complaint comp = new Complaint(0l, "aaaaa", "UNDER-REVIEW", 2l);
		complaintRepo.save(comp);
	}

	@Test
	public void getAllComplaints(){
		System.out.println(complaintRepo.findAll());
	}



}
