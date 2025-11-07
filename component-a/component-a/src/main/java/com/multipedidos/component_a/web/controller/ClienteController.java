package com.multipedidos.component_a.web.controller;

import com.multipedidos.component_a.service.ClienteService;
import com.multipedidos.component_a.web.dto.ClienteDTO;
import com.multipedidos.component_a.web.dto.ClienteInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;



@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
@Tag(name = "Clientes")
public class ClienteController {
    private final ClienteService service;

    @PostMapping
    @Operation(summary = "Crear un cliente")
    public ResponseEntity<ClienteDTO> crear(@Valid @RequestBody ClienteInput in) {
        ClienteDTO dto = service.crear(in);
        return ResponseEntity.created(URI.create("/clientes/" + dto.id())).body(dto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un cliente por ID")
    public ResponseEntity<ClienteDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @GetMapping
    @Operation(summary = "Listar todos los clientes")
    public ResponseEntity<List<ClienteDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }
}
