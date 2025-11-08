package com.multipedidos.component_a.service;

import com.multipedidos.component_a.domain.Pedido;
import com.multipedidos.component_a.domain.ProductoEmbeddable;
import com.multipedidos.component_a.repository.PedidoRepository;
import com.multipedidos.component_a.web.dto.PedidoDTO;
import com.multipedidos.component_a.web.dto.PedidoInput;
import com.multipedidos.component_a.web.dto.ProductoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.multipedidos.multipedidos_common.OperacionesNegocio;

@Service @RequiredArgsConstructor
public class PedidoService {
    private final PedidoRepository repo;

    // TODO: reemplazar por Librería C (OperacionesNegocio) más adelante
    //private double calcularTotalConIVA(double subtotal) { return subtotal * 1.12; }
    //private double aplicarDescuento(double total, double porcentaje) { return total - (total * (porcentaje/100.0)); }

    @Transactional
    public PedidoDTO crear(PedidoInput in) {
        double subtotal = in.productos().stream()
                .mapToDouble(ProductoDTO::precio).sum();

        double total = OperacionesNegocio.calcularTotalConIVA(subtotal);

        // Regla ejemplo: si subtotal > 500, 5% de descuento
        if (subtotal > 500) {
            total = OperacionesNegocio.aplicarDescuento(total, 5.0);
        }

        Pedido p = Pedido.builder()
                .clienteId(in.clienteId())
                .productos(in.productos().stream()
                        .map(d -> ProductoEmbeddable.builder().nombre(d.nombre()).precio(d.precio()).build())
                        .toList())
                .total(total)
                .build();

        p = repo.save(p);
        return toDTO(p);
    }

    @Transactional(readOnly = true)
    public List<PedidoDTO> listar() {
        return repo.findAll().stream().map(this::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public PedidoDTO obtener(Long id) {
        Pedido p = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("No existe pedido"));
        return toDTO(p);
    }

    private PedidoDTO toDTO(Pedido p) {
        List<ProductoDTO> productos = p.getProductos().stream()
                .map(pe -> new ProductoDTO(pe.getNombre(), pe.getPrecio()))
                .toList();
        return new PedidoDTO(p.getId(), p.getClienteId(), productos, p.getTotal());
    }
}
