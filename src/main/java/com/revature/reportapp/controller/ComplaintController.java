package com.revature.reportapp.controller;

import com.revature.reportapp.entity.Complaint;
import com.revature.reportapp.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/complaints")
public class ComplaintController {
    @Autowired
    ComplaintService complaintService;

    @PostMapping()
    public Complaint insert(@RequestBody Complaint complaint) {
        return complaintService.insert(complaint);
    }

    @GetMapping
    public List<Complaint> getAll(@RequestParam(required = false, value = "status") String status, @RequestParam(required = false) Long id) {
        if (status == null & id == null) return complaintService.getAll();
        else if (status == null) {return complaintService.getAll();

        } else return complaintService.findByStatus(status);
    }

    @GetMapping("/{compIdentifier}")
    public Complaint getById(@PathVariable("compIdentifier") String identifier) {
        try {
            Long id = Long.parseLong(identifier);
            return complaintService.getById(id);
        } catch (Exception e) {
            return complaintService.findByStatus(identifier).get(0);
        }
    }

//    @GetMapping("/complaint/{id}")
//    public Complaint getById(@PathVariable("id") Long compliant_id) {
//        return complaintService.getById(compliant_id);
//    }


    @PutMapping()
    public Complaint update(@RequestBody Complaint complaint) {
        return complaintService.update(complaint);
    }

//    @DeleteMapping("/complaints/{id}")
//    public boolean delete(@PathVariable("id") Long id) {
//        return complaintService.delete(id);
//    }

    @DeleteMapping("/{userIdentifier}")
    public boolean delete(@PathVariable("userIdentifier") String identifier) {
        Long user_id = Long.parseLong(identifier);
        return complaintService.delete(user_id);
    }

}
    

