package com.pontoapp.pontoapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pontoapp.pontoapp.dto.NewUserDTO;
import com.pontoapp.pontoapp.entity.User;
import com.pontoapp.pontoapp.exceptions.UserServiceException;
import com.pontoapp.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void insert(NewUserDTO userDTO) {
        try {
            validateUser(userDTO);
            validatePassword(userDTO);
            User user = new User(userDTO);
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            user.setId(null);
            userRepository.save(user);
        } catch (Exception e) {
            throw new UserServiceException("Erro ao criar novo usuário", e);
        }
    }

    private void validateUser(NewUserDTO user) {
        if (userRepository.findByLogin(user.getLogin()).isPresent()) {
            throw new UserServiceException("Usuário já cadastrado: " + user.getLogin());
        }
    }

    private static void validatePassword(NewUserDTO userDTO) {
        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            throw new UserServiceException("As senhas devem ser iguais");
        }
    }
}
