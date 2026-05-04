package com.api.sodimac.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.sodimac.entity.Categoria;
import com.api.sodimac.entity.Producto;
import com.api.sodimac.service.ProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<Page<Producto>> listarProductos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Page<Producto> productos = productoService.listarProductosPaginados(PageRequest.of(page, size));
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerProductoPorId(@PathVariable Integer id) {
        Optional<Producto> producto = productoService.buscarPorId(id);
        if (producto.isPresent()) {
            return ResponseEntity.ok(producto.get());
        } else {
            return ResponseEntity.status(404).body(Map.of("mensaje", "Producto no encontrado"));
        }
    }

    @GetMapping("/categorias")
    public ResponseEntity<List<Categoria>> listarCategorias() {
        List<Categoria> categorias = productoService.listarCategorias();
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/busqueda")
    public ResponseEntity<?> buscarProductos(@RequestParam String q) {
        List<Producto> resultados = productoService.buscarProductos(q);
        if(resultados.isEmpty()){
            return ResponseEntity.status(404).body(Map.of("mensaje", "No se encontraron productos con: " + q));
        }
        return ResponseEntity.ok(resultados);
    }
}
