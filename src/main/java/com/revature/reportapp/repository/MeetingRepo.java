package com.revature.reportapp.repository;

import com.revature.reportapp.entity.Complaint;
import com.revature.reportapp.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface MeetingRepo extends JpaRepository<Meeting, Long> {

}
