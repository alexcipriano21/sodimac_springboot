package com.api.sodimac.entity;

import java.io.Serializable;
import lombok.Data;

@Data
public class StockId implements Serializable {
    private Integer idSucursal;
    private Integer idProducto;
}
