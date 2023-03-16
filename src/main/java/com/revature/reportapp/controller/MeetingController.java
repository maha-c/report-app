package com.revature.reportapp.controller;

import com.revature.reportapp.entity.Complaint;
import com.revature.reportapp.entity.Meeting;
import com.revature.reportapp.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meetings")
public class MeetingController {
    @Autowired
    MeetingService meetingService;
    @GetMapping()
    public List<Meeting> getAll() {
       return meetingService.getAll();
    }

    @PostMapping()
    public Meeting insert(@RequestBody Meeting meeting) {
        return meetingService.insert(meeting);
    }

    @GetMapping("/{meetingIdentifier}")
    public Meeting getById(@PathVariable("meetingIdentifier") String identifier) {
        Long id = Long.parseLong(identifier);
        return meetingService.getById(id);
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
