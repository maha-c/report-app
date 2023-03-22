package com.revature.reportapp.controller;

import com.revature.reportapp.entity.Complaint;
import com.revature.reportapp.entity.User;
import com.revature.reportapp.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
@RestController
@RequestMapping("/complaints")
@CrossOrigin(origins = "http://localhost:3001")


public class ComplaintController {
    @Autowired
    ComplaintService complaintService;

    @PostMapping()
    public ResponseEntity<Complaint> insert(@RequestBody Complaint complaint) {
        Complaint insertedComp = complaintService.insert(complaint);
        return new ResponseEntity<Complaint>(insertedComp, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Complaint> getAll(@RequestParam(required = false, value = "status") String status, @RequestParam(required = false) Long id) {
        if (status == null & id == null) return complaintService.getAll();
        else if (status == null) {return complaintService.getAll();
        } else return complaintService.findByStatus(status);
    }


    @GetMapping("/{compIdentifier}")
    public ResponseEntity<?> getById(@PathVariable("compIdentifier") String identifier) {
        try {
            Long id = Long.parseLong(identifier);
            Complaint complaint = complaintService.getById(id);
            return ResponseEntity.ok(complaint);
        } catch (NumberFormatException e) {
            List<Complaint> complaints = complaintService.findByStatus(identifier);
            if (complaints.isEmpty()) {
                String errorMessage = "Complaint not found with identifier: " + identifier;
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage); // return 404 with custom error message
            } else {
                return ResponseEntity.ok(complaints.get(0));
            }
        } catch (NoSuchElementException e) {
            String errorMessage = "Complaint not found with identifier: " + identifier;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage); // return 404 with custom error message
        } catch (Exception e) {
            String errorMessage = "Internal Server Error";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage); // return 500 with custom error message
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

    @DeleteMapping("/{compIdentifier}")
    public boolean delete(@PathVariable("compIdentifier") String identifier) {
        Long user_id = Long.parseLong(identifier);
        return complaintService.delete(user_id);
    }

}
    

