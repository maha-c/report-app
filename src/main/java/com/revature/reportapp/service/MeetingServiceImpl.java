package com.revature.reportapp.service;

import com.revature.reportapp.entity.Meeting;
import com.revature.reportapp.repository.MeetingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MeetingServiceImpl implements MeetingService{

    @Autowired
    MeetingRepo meetingRepo;
    @Override
    public List<Meeting> getAll() {
        return meetingRepo.findAll();
    }

    @Override
    public Meeting getById(Long id) {
        return meetingRepo.findById(id).get();
    }

    @Override
    public Meeting insert(Meeting meeting) {
        return meetingRepo.save(meeting);
    }

    @Override
    public Meeting update(Meeting meeting) {
        return meetingRepo.save(meeting);
    }

    @Override
    public boolean delete(Long id) {
        boolean found = meetingRepo.existsById(id);
        if(found) meetingRepo.deleteById(id);
        return found;
    }
}
