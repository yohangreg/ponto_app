package com.pontoapp.pontoapp.dto;

import org.springframework.beans.BeanUtils;

import com.pontoapp.pontoapp.entity.User;

public class UpdateUserDTO {

    private Long id;

    private String login;

    private String password;

    public UpdateUserDTO(User user) {
        BeanUtils.copyProperties(user, this);
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UpdateUserDTO(Long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }
}
