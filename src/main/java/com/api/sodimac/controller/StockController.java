package com.api.sodimac.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.sodimac.entity.Stock;
import com.api.sodimac.service.StockService;

@RestController
@RequestMapping("/api/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/{sucursalId}/{productoId}")
    public ResponseEntity<?> consultarStock(
            @PathVariable Integer sucursalId,
            @PathVariable Integer productoId) {

        Optional<Stock> stock = stockService.consultarStock(sucursalId, productoId);

        if (stock.isPresent()) {
            return ResponseEntity.ok(stock.get());
        } else {
            return ResponseEntity.status(404).body(Map.of("mensaje", "No se encontró stock para esa sucursal y producto"));
        }
    }
}
