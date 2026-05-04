package com.api.sodimac.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.sodimac.dto.CarritoItemRequest;
import com.api.sodimac.entity.CarritoItem;
import com.api.sodimac.service.CarritoService;

@RestController
@RequestMapping("/api/carrito")
public class CarritoController {
    @Autowired
    private CarritoService carritoService;

    @PostMapping("/items")
    public ResponseEntity<?> agregarItem(@RequestBody CarritoItemRequest request){
        try{
            CarritoItem item = carritoService.agregarOActualizar(request);
            return ResponseEntity.ok(item);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(Map.of("mensaje", "Error al agregar al carrito: " + e.getMessage()));
        }
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<?> eliminarItem(@PathVariable Long id){
        boolean eliminado = carritoService.eliminarItem(id);
        if(eliminado){
            return ResponseEntity.ok(Map.of("mensaje", "Item eliminado del carrito"));
        }else{
            return ResponseEntity.badRequest().body(Map.of("mensaje", "Item no encontrado en el carrito"));
        }
    }
}
