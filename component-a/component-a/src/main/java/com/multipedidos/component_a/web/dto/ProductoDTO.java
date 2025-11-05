package com.multipedidos.component_a.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductoDTO(
    @NotBlank String nombre,
    @NotNull Double precio
) {}
