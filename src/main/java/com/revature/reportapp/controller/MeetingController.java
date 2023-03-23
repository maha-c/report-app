package com.revature.reportapp.controller;

import com.revature.reportapp.entity.Complaint;
import com.revature.reportapp.entity.Meeting;
import com.revature.reportapp.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/meetings")
@CrossOrigin(origins = "*")
public class MeetingController {
    @Autowired
    MeetingService meetingService;
    @GetMapping()
    public List<Meeting> getAll() {
       return meetingService.getAll();
    }

    @PostMapping()
    public ResponseEntity<Meeting> insert(@RequestBody Meeting meeting) {
        Meeting insertedMeeting = meetingService.insert(meeting);
        return new ResponseEntity<Meeting>(insertedMeeting, HttpStatus.CREATED);

    }
//
//    @GetMapping("/{meetingIdentifier}")
//    public Meeting getById(@PathVariable("meetingIdentifier") String identifier) {
//        Long id = Long.parseLong(identifier);
//        return meetingService.getById(id);
//    }

    @GetMapping("/{meetingIdentifier}")
    public ResponseEntity<Meeting> getById(@PathVariable("meetingIdentifier") String identifier) {
        try {
            Long id = Long.parseLong(identifier);
            Meeting meeting = meetingService.getById(id);
            return ResponseEntity.ok(meeting);
        } catch (NumberFormatException e) {
            String errorMessage = "Invalid meeting identifier: " + identifier;
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // return 400 with null body
        } catch (NoSuchElementException e) {
            String errorMessage = "Meeting not found with identifier: " + identifier;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // return 404 with null body
        } catch (Exception e) {
            String errorMessage = "Internal Server Error";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // return 500 with null body
        }
    }


    @PutMapping()
    public Meeting update(@RequestBody Meeting meeting) {
        return meetingService.update(meeting);
    }

    @DeleteMapping("/{meetingIdentifier}")
    public boolean delete(@PathVariable("meetingIdentifier") String identifier) {
        Long id = Long.parseLong(identifier);
        return meetingService.delete(id);
    }

    //    @GetMapping("/meeting/{id}")
//    public Meeting getById(@PathVariable("id") Long id) {
//        return meetingService.getById(id);
//    }
//@DeleteMapping("/meeting/{id}")
//public boolean delete(@PathVariable("id") Long id){
//    return meetingService.delete(id);
//}

}
