package com.revature.reportapp.repository;
import com.revature.reportapp.entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ComplaintRepo extends JpaRepository<Complaint, Long> {
    List<Complaint> findByStatus(String status);

}
