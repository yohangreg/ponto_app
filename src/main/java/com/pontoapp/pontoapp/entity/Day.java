package com.pontoapp.pontoapp.entity;

import java.util.Date;

import javax.annotation.processing.Generated;

@Entity
@Table(name = "timesheet")
public class Day {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Coloumn(name = "dot", nullable = false)
    @Temporal(TemporalType.DATETIME)
    private Date dot;

    @ManyToOne
    @JoinColoumn(name = "user_id", nullable = false)
    private User user;
 
    
}
