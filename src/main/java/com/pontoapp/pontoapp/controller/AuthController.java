package com.pontoapp.pontoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pontoapp.pontoapp.dto.AcessDTO;
import com.pontoapp.pontoapp.dto.AuthenticationDTO;
import com.pontoapp.pontoapp.dto.NewUserDTO;
import com.pontoapp.pontoapp.service.AuthService;
import com.pontoapp.pontoapp.service.UserService;

@RestController
@RequestMapping(value = "/api/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "singUp")
    public ResponseEntity<?> insertNewUser(@RequestBody NewUserDTO newUser) {
        try {
            userService.insert(newUser);
            return ResponseEntity.status(HttpStatus.CREATED).body("Novo usuário criado!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage() + " " + e.getCause());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage() + " " + e.getCause());
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationDTO authDto) {
        try {
            AcessDTO token = authService.login(authDto);
            return ResponseEntity.status(HttpStatus.OK).body(token);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage() + " " + ex.getCause());
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage() + " " + ex.getCause());
        }
    }
}
