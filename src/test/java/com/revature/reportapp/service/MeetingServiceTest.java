package com.revature.reportapp.service;
import com.revature.reportapp.entity.Meeting;
import com.revature.reportapp.repository.MeetingRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@SpringBootTest
public class MeetingServiceTest {
    @MockBean(MeetingRepo.class)
    private MeetingRepo meetingRepo;
    @Autowired
    MeetingService meetingService;

    @Test
    public void testInsert(){
        Meeting meet = new Meeting(5l, "NorthMain", 1234567l,"Missing Packages");
        Meeting meet1 = new Meeting(10l, "SouthMain", 654321l,"Missing Vehicle");
        Mockito.when(meetingRepo.save(meet)).thenReturn(meet1);
        Assertions.assertEquals(meet1,meetingService.insert(meet));
    }
    @Test
    public void testGetById(){
        Meeting meet = new Meeting(2l,"NorthMain",86876879l,"Missing Packages");
        Mockito.when(meetingRepo.findById(2l)).thenReturn(Optional.of(meet));
        Assertions.assertEquals(meet,meetingService.getById(2l));
    }

    @Test
    public void testGetAll(){
        List<Meeting> meetings = new ArrayList<>(){
            Meeting meet = new Meeting(11l,"NorthMain",2452345l,"Missing Vehicle");
            Meeting meet1 = new Meeting(7l,"SouthMain",2424525l,"Missing Packages");
        };
        Mockito.when(meetingRepo.findAll()).thenReturn(meetings);
        Assertions.assertEquals(meetings,meetingService.getAll());
    }

    @Test
    public void testUpdate(){
        Meeting meet = new Meeting(1l,"NorthMain",367481364l,"Missing Vehicles");
        Meeting meet1 = new Meeting(1l,"SouthMain",367481364l,"Missing Packages");
        Mockito.when(meetingRepo.save(meet)).thenReturn(meet1);
        Assertions.assertEquals(meet1,meetingService.update(meet));
    }

    @Test
    public void testDelete(){
        Mockito.when(meetingRepo.existsById((long)3)).thenReturn(true);
        Mockito.when(meetingRepo.existsById((long)4)).thenReturn(false);
        Assertions.assertTrue(meetingService.delete((long)3));
        Assertions.assertFalse(meetingService.delete((long)4));

    }


}
