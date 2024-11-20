package com.pontoapp.pontoapp.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pontoapp.pontoapp.dto.InsertTimesheetDTO;
import com.pontoapp.pontoapp.dto.TimesheetDTO;
import com.pontoapp.pontoapp.entity.Timesheet;
import com.pontoapp.pontoapp.entity.User;
import com.pontoapp.pontoapp.exceptions.UserServiceException;
import com.pontoapp.pontoapp.repository.TimesheetRepository;
import com.pontoapp.pontoapp.repository.UserRepository;

@Service
public class TimesheetService {

    @Autowired
    private TimesheetRepository timesheetRepository;

    @Autowired
    private UserRepository userRepository;

    public void insert(InsertTimesheetDTO dto) {
        try {
            Optional<User> userOp = userRepository.findById(dto.userId());
            if(!userOp.isPresent()) {
                throw new UserServiceException("Usuário não encontrado para o id fornecido: " + dto.userId());
            }
            Timesheet timesheet = new Timesheet(dto);
            timesheet.setUser(userOp.get());
            timesheetRepository.save(timesheet);
        } catch (RuntimeException e) {
            throw new RuntimeException("Failed to create timesheet", e);
        }
    }

    public List<TimesheetDTO> findAll() {
        try {
            List<Timesheet> timesheetList = timesheetRepository.findAll();
            return timesheetList.stream().map(TimesheetDTO::new).collect(Collectors.toList());
        } catch (RuntimeException e) {
            throw new RuntimeException("Failed to find timesheet", e);
        }
    }

    public TimesheetDTO findById(Long id) {
        try {
            Timesheet timesheet = timesheetRepository.findById(id).orElseThrow(() -> new RuntimeException("timesheet não encontrado para o ID: " + id));
            return new TimesheetDTO(timesheet);
        } catch (RuntimeException e) {
            throw new RuntimeException("Failed to find timesheet", e);
        }
    }

    public List<TimesheetDTO> findByUser(Long userId) {
        try {
            Optional<User> userOp = userRepository.findById(userId);
            if(!userOp.isPresent()) {
                throw new UserServiceException("Usuário não encontrado para o id fornecido: " + userId);
            }
            List<Timesheet> timesheets = timesheetRepository.findByUser(userOp.get());
            if(timesheets.isEmpty()||timesheets==null) {
                throw new RuntimeException("Nenhum ponto encontrado para o usuário");
            }
            return timesheets.stream().map(TimesheetDTO::new).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to find timesheet for this user", e);
        }
    }

}
