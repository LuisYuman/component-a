package com.multipedidos.component_a.web.controller;

import com.multipedidos.component_a.service.PedidoService;
import com.multipedidos.component_a.web.dto.PedidoDTO;
import com.multipedidos.component_a.web.dto.PedidoInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
@Tag(name = "Pedidos")
public class PedidoController {

    private final PedidoService service;

    @PostMapping
    @Operation(summary = "Crear un pedido")
    public ResponseEntity<PedidoDTO> crear(@Valid @RequestBody PedidoInput in) {
        PedidoDTO dto = service.crear(in);
        return ResponseEntity.created(URI.create("/pedidos/" + dto.id())).body(dto);
    }

    @GetMapping
    @Operation(summary = "Listar todos los pedidos")
    public ResponseEntity<List<PedidoDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un pedido por ID")
    public ResponseEntity<PedidoDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtener(id));
    }
}
