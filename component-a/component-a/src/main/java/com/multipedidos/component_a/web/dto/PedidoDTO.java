package com.multipedidos.component_a.web.dto;

import java.util.List;

public record PedidoDTO(
    Long id,
    Long clienteId,
    List<ProductoDTO> productos,
    Double total
) {}
