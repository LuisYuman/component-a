package com.multipedidos.component_a.service;

import com.multipedidos.component_a.domain.Cliente;
import com.multipedidos.component_a.repository.ClienteRepository;
import com.multipedidos.component_a.web.dto.ClienteDTO;
import com.multipedidos.component_a.web.dto.ClienteInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository repo;

    @Transactional
    public ClienteDTO crear(ClienteInput in) {
        if (repo.existByCorreo(in.correo())) {
            throw new IllegalArgumentException("El correo ya está registrado");
        }
        Cliente c = repo.save(Cliente.builder()
                .nombre(in.nombre())
                .correo(in.correo())
                .build());
        return new ClienteDTO(c.getId(), c.getNombre(), c.getCorreo());
    }

    @Transactional(readOnly = true)
    public ClienteDTO obtener(Long id) {
        Cliente c = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
        return new ClienteDTO(c.getId(), c.getNombre(), c.getCorreo());
    }

    @Transactional(readOnly = true)
    public List<ClienteDTO> listar() {
        return repo.findAll().stream()
                .map(c -> new ClienteDTO(c.getId(), c.getNombre(), c.getCorreo()))
                .toList();
    }
}
