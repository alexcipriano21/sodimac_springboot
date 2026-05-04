package com.api.sodimac.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Inventario")
@Data
@IdClass(StockId.class)
public class Stock {

    @Id
    @Column(name = "idSucursal", nullable = false)
    private Integer idSucursal;

    @Id
    @Column(name = "idProducto", nullable = false)
    private Integer idProducto;

    @Column(name = "stock", nullable = false)
    private Integer stock;
}
