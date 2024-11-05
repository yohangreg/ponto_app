package com.pontoapp.pontoapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.pontoapp.pontoapp.dto.AcessDTO;
import com.pontoapp.pontoapp.dto.AuthenticationDTO;
import com.pontoapp.pontoapp.entity.UserDetailsImpl;
import com.pontoapp.pontoapp.security.jwt.JwtUtils;

@Service
public class AuthService {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AcessDTO login(AuthenticationDTO authDto) {
        try {
            //Cria Mecanismo de credencial para o spring 
            UsernamePasswordAuthenticationToken userAuth = new UsernamePasswordAuthenticationToken(authDto.getLogin(), authDto.getPassword());

            //Prepara mecanismo para autenticacao
            Authentication authentication = authenticationManager.authenticate(userAuth);

            //Busca usuario logado
            UserDetailsImpl userAuthenticate = (UserDetailsImpl) authentication.getPrincipal();

            String token = jwtUtils.generateTokenFromUserDetailsImpl(userAuthenticate);

            AcessDTO acessDTO = new AcessDTO(token);

            return acessDTO;
        } catch (BadCredentialsException e) {
            //TODO: LOGIN OU SENHA INVALIDO
        }
        return new AcessDTO(null);
    }
}
