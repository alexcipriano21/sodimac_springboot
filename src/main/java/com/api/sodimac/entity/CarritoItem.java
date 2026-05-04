package com.api.sodimac.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "carrito_items")
@Data
public class CarritoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;
    @Column(name = "producto_id", nullable = false)
    private Integer productoId;
    @Column(nullable = false)
    private Integer cantidad;
    @Column(name = "fecha_agregado")
    private LocalDateTime fechaAgregado;
    @PrePersist
    public void prePersist() {
        this.fechaAgregado = LocalDateTime.now();
    }
}
