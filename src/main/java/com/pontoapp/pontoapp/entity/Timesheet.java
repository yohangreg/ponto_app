package com.pontoapp.pontoapp.entity;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.pontoapp.pontoapp.dto.InsertTimesheetDTO;
import com.pontoapp.pontoapp.dto.TimesheetDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "timesheet")
public class Timesheet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "dot", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date dot;

    @Column(name = "Timeflag", nullable = false)
    private Integer timeflag;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Timesheet(TimesheetDTO TimesheetDTO) {
        BeanUtils.copyProperties(TimesheetDTO, this);
    }
    
    public Timesheet() {
    }

    public Timesheet(InsertTimesheetDTO dto) {
        this.dot = dto.dot();
        this.timeflag = dto.timeflag();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDot() {
        return dot;
    }

    public void setDot(Date dot) {
        this.dot = dot;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getTimeflag() {
        return timeflag;
    }

    public void setTimeflag(Integer timeflag) {
        this.timeflag = timeflag;
    }
}
