package com.pontoapp.pontoapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pontoapp.pontoapp.dto.TimesheetDTO;
import com.pontoapp.pontoapp.service.TimesheetService;

@RestController
@RequestMapping(value = "/api/timesheet")
@CrossOrigin
public class TimesheetController {

    @Autowired
    private TimesheetService timesheetService;

    @PostMapping(value = "/insert")
    public ResponseEntity<?> insert(@RequestBody TimesheetDTO timesheetsDto) {
        try {
            timesheetService.insert(timesheetsDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Novo usuário criado!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage() + " " + e.getCause());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage() + " " + e.getCause());
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<TimesheetDTO> timesheets = timesheetService.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(timesheets);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage() + " " + e.getCause());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage() + " " + e.getCause());
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findUserById(@PathVariable Long id) {
        try {
            TimesheetDTO timesheetsDto = timesheetService.findById(id);
            return ResponseEntity.status(HttpStatus.OK).body(timesheetsDto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage() + " " + e.getCause());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage() + " " + e.getCause());
        }
    }
}
