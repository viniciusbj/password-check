package com.netdeal.passwordcheck.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record EmployeeRecordDto(@NotBlank String name, @NotNull String password, UUID manager) {
}
