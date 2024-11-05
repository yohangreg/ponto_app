package com.pontoapp.pontoapp.dto;

import org.springframework.beans.BeanUtils;

import com.pontoapp.pontoapp.entity.User;

public class NewUserDTO {

    private String login;

    private String password;
    
    private String confirmPassword;

    public NewUserDTO(User user) {
        BeanUtils.copyProperties(user, this);
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public NewUserDTO() {}

}
