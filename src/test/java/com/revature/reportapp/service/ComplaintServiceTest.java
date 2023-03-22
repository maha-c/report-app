package com.revature.reportapp.service;
import com.revature.reportapp.entity.Complaint;
import com.revature.reportapp.repository.ComplaintRepo;
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
public class ComplaintServiceTest {
    @MockBean(ComplaintRepo.class)
    private ComplaintRepo complaintRepo;
    @Autowired
    ComplaintService complaintService;

    @Test
    public void testInsert(){
        Complaint comp1 = new Complaint(1l,"Missing Vehicle","REVIEWED",1l);
        Complaint comp2 = new Complaint(5l,"Missing Packages","UN-REVIEWED",5l);
        Mockito.when(complaintRepo.save(comp1)).thenReturn(comp2);
        Assertions.assertEquals(comp2,complaintService.insert(comp1));
    }

    @Test
    public void testGetById(){
        Complaint comp1 = new Complaint(6l,"Missing Vehicle","REVIEWED",3l);
        Mockito.when(complaintRepo.findById(6l)).thenReturn(Optional.of(comp1));
        Assertions.assertEquals(comp1,complaintService.getById(6l));
    }

    @Test
    public void testGetAll(){
        List<Complaint> complaints = new ArrayList<>(){{
            Complaint comp1 = new Complaint(3l,"Missing Vehicle","REVIEWED",1l);
            Complaint comp2 = new Complaint(2l,"Missing Packages","UN-REVIEWED",7l);
           
        }};
        Mockito.when(complaintRepo.findAll()).thenReturn(complaints);
        Assertions.assertEquals(complaints,complaintService.getAll());
    }

    @Test
    public void testUpdate(){
        Complaint comp1 = new Complaint(5l,"Missing Vehicle","UN-REVIEWED",1l);
        Complaint comp2 = new Complaint(5l,"Missing Vehicles","REVIEWED",1l);
        Mockito.when(complaintRepo.save(comp1)).thenReturn(comp2);
        Assertions.assertEquals(comp2,complaintService.update(comp1));
    }

    @Test
    public void testDelete(){
        Mockito.when(complaintRepo.existsById((long)2)).thenReturn(true);
        Mockito.when(complaintRepo.existsById((long)3)).thenReturn(false);
        Assertions.assertTrue(complaintService.delete((long)2));
        Assertions.assertFalse(complaintService.delete((long)3));
    }

    @Test
    public void testGetByStatus(){
        List<Complaint> complaints = new ArrayList<>(){{
            Complaint comp1 = new Complaint(2l,"Missing Vehicle","REVIEWED",1l);
            Complaint comp2 = new Complaint(3l,"Missing Packages","IGNORED",4l);
            Complaint comp3 = new Complaint(5l,"Missing Lawnmower","UN-REVIEWED",6l);
        }};
        List<Complaint> regcomplaints = new ArrayList<>(){{
            Complaint comp1 = new Complaint(2l,"Missing Vehicle","REVIEWED",1l);
            Complaint comp3 = new Complaint(5l,"Missing Lawnmower","UN-REVIEWED",2l);
        }};
        Mockito.when(complaintRepo.findByStatus("UN-REVIEWED")).thenReturn(regcomplaints);
        Assertions.assertEquals(regcomplaints,complaintService.getAll("UN-REVIEWED"));
    }

}


