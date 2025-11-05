package com.multipedidos.component_a.domain;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ProductoEmbeddable {
    private String nombre;
    private Double precio;
}
