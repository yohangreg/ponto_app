package com.pontoapp.pontoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.pontoapp.pontoapp.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    UserDetails findByLogin(String login);   
    
}
