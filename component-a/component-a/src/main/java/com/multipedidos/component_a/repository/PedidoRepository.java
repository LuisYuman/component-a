package com.multipedidos.component_a.repository;

import com.multipedidos.component_a.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
