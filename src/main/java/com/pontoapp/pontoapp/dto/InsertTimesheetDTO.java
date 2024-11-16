package com.pontoapp.pontoapp.dto;

import java.sql.Date;

import jakarta.validation.constraints.NotNull;

public record  InsertTimesheetDTO(@NotNull Date dot, @NotNull Integer timeflag, @NotNull Long userId) {

}
