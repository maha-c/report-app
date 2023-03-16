package com.revature.reportapp.service;

import com.revature.reportapp.entity.Meeting;

import java.util.List;

public interface MeetingService {

    List<Meeting> getAll();
    Meeting getById(Long id);
    Meeting insert(Meeting meeting);
    Meeting update(Meeting meeting);

    boolean delete (Long id);
}
