package com.api.sodimac.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.api.sodimac.dto.CheckoutRequest;
import com.api.sodimac.entity.Pedido;
import com.api.sodimac.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(@RequestBody CheckoutRequest request) {
        try {
            Pedido pedido = pedidoService.checkout(request.getUsuarioId());
            return ResponseEntity.ok(pedido);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("mensaje", e.getMessage()));
        }
    }

    @GetMapping("/mis-compras")
    public ResponseEntity<?> misCompras(@RequestParam Integer usuarioId) {
        List<Pedido> pedidos = pedidoService.misCompras(usuarioId);
        if (pedidos.isEmpty()) {
            return ResponseEntity.status(404).body(Map.of("mensaje", "No tienes pedidos registrados."));
        }
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}/tracking")
    public ResponseEntity<?> tracking(@PathVariable Integer id) {
        Optional<Pedido> pedido = pedidoService.tracking(id);
        if (pedido.isPresent()) {
            return ResponseEntity.ok(Map.of(
                "id", pedido.get().getId(),
                "estado", pedido.get().getEstado(),
                "fecha", pedido.get().getFecha()
            ));
        }
        return ResponseEntity.status(404).body(Map.of("mensaje", "Pedido no encontrado."));
    }

    @DeleteMapping("/{id}/cancelar")
    public ResponseEntity<?> cancelar(@PathVariable Integer id) {
        try {
            Pedido pedido = pedidoService.cancelar(id);
            return ResponseEntity.ok(Map.of(
                "mensaje", "Pedido cancelado exitosamente.",
                "estado", pedido.getEstado()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("mensaje", e.getMessage()));
        }
    }
}
