package com.revature.reportapp.service;
import com.revature.reportapp.entity.Complaint;
import java.util.List;

public interface ComplaintService {

    Complaint insert(Complaint complaint);
    Complaint getById(Long id);
    Complaint update(Complaint complaint);
    List<Complaint> getAll();
    List<Complaint> getAll(String status);
    List<Complaint> findByStatus(String status);
    boolean delete (Long id);

}
