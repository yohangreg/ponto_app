package com.pontoapp.pontoapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pontoapp.pontoapp.entity.Timesheet;
import com.pontoapp.pontoapp.entity.User;

@Repository
public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {

    List<Timesheet> findByUser(User user);
    
}
