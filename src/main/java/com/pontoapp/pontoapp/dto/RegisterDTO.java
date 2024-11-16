package com.pontoapp.pontoapp.dto;

import com.pontoapp.pontoapp.enums.UserRole;

import jakarta.validation.constraints.NotNull;

public record RegisterDTO(@NotNull String email,@NotNull String password, @NotNull UserRole role ) {
    
}
