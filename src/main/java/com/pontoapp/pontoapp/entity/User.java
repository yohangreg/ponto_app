package com.pontoapp.pontoapp.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.pontoapp.pontoapp.dto.NewUserDTO;
import com.pontoapp.pontoapp.dto.UpdateUserDTO;
import com.pontoapp.pontoapp.dto.UserDTO;
import com.pontoapp.pontoapp.enums.UserRole;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password_hash", nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Timesheet> timesheet;

    @Enumerated
    private UserRole userRole;

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

    public User(Long id, String login, String password, UserRole userRole, List<Timesheet> timesheet) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.userRole = userRole;
        this.timesheet = timesheet;
    }

    public User(String login, String password, UserRole userRole) {
        this.login = login;
        this.password = password;
        this.userRole = userRole;
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

    @Override
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

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.userRole == userRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getUsername() {
        return getLogin();
    }
}
