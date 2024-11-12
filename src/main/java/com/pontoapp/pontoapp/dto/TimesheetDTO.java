package com.pontoapp.pontoapp.dto;

import java.util.Date;

import com.pontoapp.pontoapp.entity.Timesheet;

public class TimesheetDTO {

    private Long id;

    @Temporal(TemporalType.TIME)
    private Date dot;
    
    private Long user_id;

    public TimesheetDTO(Timesheet timesheet) {
        BeanUtils.copyProperties(Timesheet, this);
    }

    
}