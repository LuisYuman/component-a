package com.multipedidos.component_a.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClienteInput(
        @NotBlank String nombre,
        @NotBlank @Email String correo
) {}
