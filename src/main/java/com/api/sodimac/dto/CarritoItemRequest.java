package com.api.sodimac.dto;

import lombok.Data;

@Data
public class CarritoItemRequest {
    private Long usuarioId;
    private Integer productoId;
    private Integer cantidad;
}