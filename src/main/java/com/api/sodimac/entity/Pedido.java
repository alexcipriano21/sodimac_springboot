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
@Table(name = "Pedido")
@Data
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "idUsuario", nullable = false)
    private Integer idUsuario;

    @Column(nullable = false)
    private Double total;

    @Column(length = 20)
    private String estado = "PREPARACION";

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @PrePersist
    public void prePersist() {
        this.fecha = LocalDateTime.now();
        if (this.estado == null) this.estado = "PREPARACION";
    }
}
