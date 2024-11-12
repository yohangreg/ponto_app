package com.pontoapp.pontoapp.repository;

import java.util.Optional;

import com.pontoapp.pontoapp.entity.Timesheet;

@Repository
public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {

    Optional<Timesheet> fingByUser(User user);
    
}
