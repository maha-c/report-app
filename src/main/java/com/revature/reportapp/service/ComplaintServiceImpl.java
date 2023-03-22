package com.revature.reportapp.service;
import com.revature.reportapp.entity.Complaint;
import com.revature.reportapp.repository.ComplaintRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    @Autowired
    ComplaintRepo complaintRepo;
    @Override
    public Complaint insert(Complaint complaint) {
        return complaintRepo.save(complaint);
    }

    @Override
    public Complaint getById(Long id) {
        return complaintRepo.findById(id).get();
    }

    @Override
    public Complaint update(Complaint complaint) {
        return complaintRepo.save(complaint);
    }


    @Override
    public List<Complaint> getAll() {
        return complaintRepo.findAll();
    }

    @Override
    public List<Complaint> getAll(String status) {
        switch (status) {
            case "REVIEWED":
                return complaintRepo.findByStatus("REVIEWED");
            case "UNDER-REVIEW":
                return complaintRepo.findByStatus("UN-REVIEWED");
            case "HIGH PRIORITY":
                return complaintRepo.findByStatus("HIGH PRIORITY");
            case "LOW PRIORITY":
                return complaintRepo.findByStatus("LOW PRIORITY");
            case "IGNORED":
                return complaintRepo.findByStatus("IGNORED");
            default:
                return complaintRepo.findAll();
        }
    }

    @Override
    public boolean delete(Long id) {
        boolean found = complaintRepo.existsById(id);
        if(found) complaintRepo.deleteById(id);
        return found;
    }
    public List <Complaint> findByStatus(String status){
        return complaintRepo.findByStatus(status);
    }
}
