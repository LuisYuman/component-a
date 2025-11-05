package com.multipedidos.component_a.web.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

public record PedidoInput(
    @NotNull Long clienteId,
    @Size(min = 1) List<ProductoDTO> productos
) {}
