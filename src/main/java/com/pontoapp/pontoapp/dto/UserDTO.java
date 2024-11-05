package com.pontoapp.pontoapp.dto;

import org.springframework.beans.BeanUtils;

import com.pontoapp.pontoapp.entity.User;

public class UserDTO {

    private Long id;

    private String login;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public UserDTO(User user) {
        BeanUtils.copyProperties(user, this);
    }
        
}