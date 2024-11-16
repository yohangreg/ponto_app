package com.pontoapp.pontoapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.pontoapp.pontoapp.dto.AuthetinticationDTO;
import com.pontoapp.pontoapp.dto.LoginResponseDTO;
import com.pontoapp.pontoapp.dto.RegisterDTO;
import com.pontoapp.pontoapp.entity.User;
import com.pontoapp.pontoapp.repository.UserRepository;
import com.pontoapp.pontoapp.security.TokenService;

import jakarta.validation.Valid;

@Service
public class AuthorizationService implements UserDetailsService{
    @Autowired
    private ApplicationContext context;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    private AuthenticationManager authenticationManager;
    
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userRepository.findByLogin(login);
    } 

    public ResponseEntity<Object> login(@RequestBody @Valid AuthetinticationDTO data){
        authenticationManager = context.getBean(AuthenticationManager.class);

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }


    public ResponseEntity<Object> register (@RequestBody RegisterDTO registerDto){
        if (this.userRepository.findByLogin(registerDto.login()) != null ) return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDto.password());
        
        User newUser = new User(registerDto.login(), encryptedPassword, registerDto.role());
        this.userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }

}