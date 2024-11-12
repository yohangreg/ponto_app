package com.pontoapp.pontoapp.dto;

import java.util.Date;

import com.pontoapp.pontoapp.entity.Timesheet;

public class TimesheetDTO {

    private Long id;

    private Date dot;

    private Integer timeflag;
    
    private Long user_id;

    public TimesheetDTO(Timesheet timesheet) {
        this.id = timesheet.getId();
        this.dot = timesheet.getDot();
        this.timeflag = timesheet.getTimeflag();
        this.user_id = timesheet.getUser().getId();
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

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Integer getTimeflag() {
        return timeflag;
    }

    public void setTimeflag(Integer timeflag) {
        this.timeflag = timeflag;
    }
    
}