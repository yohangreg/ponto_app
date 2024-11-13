package com.pontoapp.pontoapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pontoapp.pontoapp.dto.UpdateUserDTO;
import com.pontoapp.pontoapp.dto.UserDTO;
import com.pontoapp.pontoapp.exceptions.UserServiceException;
import com.pontoapp.pontoapp.service.UserService;

@RestController
@RequestMapping(value = "/api/user")
@CrossOrigin
public class UserController {

    @Autowired  
    private UserService userService;
    
    @GetMapping("/findAll")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<UserDTO> users = userService.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(users);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage() + " " + e.getCause());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage() + " " + e.getCause());
        }
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserDTO userDTO) {
        try {
            UserDTO userUpdated = userService.update(userDTO);
            return ResponseEntity.status(HttpStatus.OK).body(userUpdated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage() + " " + e.getCause());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage() + " " + e.getCause());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            userService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado com sucesso");
        } catch (UserServiceException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage() + " " + e.getCause());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage() + " " + e.getCause());
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findUserById(@PathVariable Long id) {
        try {
            UserDTO userDTO = userService.findById(id);
            return ResponseEntity.status(HttpStatus.OK).body(userDTO);
        } catch (UserServiceException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage() + " " + e.getCause());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage() + " " + e.getCause());
        }
    }
}
