package com.pontoapp.pontoapp.entity;

import java.util.List;

import org.springframework.beans.BeanUtils;

import com.pontoapp.pontoapp.dto.NewUserDTO;
import com.pontoapp.pontoapp.dto.UpdateUserDTO;
import com.pontoapp.pontoapp.dto.UserDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password_hash", nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Timesheet> timesheet;

    public User(UserDTO userDto) {
        BeanUtils.copyProperties(userDto, this);
    }
    
    public User(NewUserDTO userDto) {
        BeanUtils.copyProperties(userDto, this);
    }

    public User(UpdateUserDTO userDto) {
        BeanUtils.copyProperties(userDto, this);
    }

    public User() {
    }

    public User(Long id, String login, String password, List<Timesheet> timesheet) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.timesheet = timesheet;
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

    public List<Timesheet> getTimesheet() {
        return timesheet;
    }

    public void setTimesheet(List<Timesheet> timesheet) {
        this.timesheet = timesheet;
    }

}
