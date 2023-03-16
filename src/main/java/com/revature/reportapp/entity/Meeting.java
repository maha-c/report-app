package com.revature.reportapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.*;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "meeting")
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long meeting_id;
    private String address;
    private Long time;
    private String summary;



}
