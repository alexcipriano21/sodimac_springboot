package com.api.sodimac.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.sodimac.entity.CarritoItem;
import com.api.sodimac.entity.Pedido;
import com.api.sodimac.entity.Producto;
import com.api.sodimac.repository.CarritoItemRepository;
import com.api.sodimac.repository.PedidoRepository;
import com.api.sodimac.repository.ProductoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private CarritoItemRepository carritoItemRepository;

    @Autowired
    private ProductoRepository productoRepository;
    public Pedido checkout(Long usuarioId) throws Exception {
        List<CarritoItem> items = carritoItemRepository.findByUsuarioId(usuarioId);
        if (items.isEmpty()) {
            throw new Exception("El carrito está vacío.");
        }
        double total = 0;
        for (CarritoItem item : items) {
            Optional<Producto> producto = productoRepository.findById(item.getProductoId());
            if (producto.isPresent()) {
                total += producto.get().getPrecio() * item.getCantidad();
            }
        }
        Pedido pedido = new Pedido();
        pedido.setIdUsuario(usuarioId.intValue());
        pedido.setTotal(total);
        Pedido pedidoGuardado = pedidoRepository.save(pedido);
        carritoItemRepository.deleteAll(items);

        return pedidoGuardado;
    }

    public List<Pedido> misCompras(Integer idUsuario) {
        return pedidoRepository.findByIdUsuario(idUsuario);
    }

    public Optional<Pedido> tracking(Integer id) {
        return pedidoRepository.findById(id);
    }

    public Pedido cancelar(Integer id) throws Exception {
        Optional<Pedido> pedidoOpt = pedidoRepository.findById(id);
        if (pedidoOpt.isEmpty()) {
            throw new Exception("Pedido no encontrado.");
        }
        Pedido pedido = pedidoOpt.get();
        if (!pedido.getEstado().equals("PREPARACION")) {
            throw new Exception("El pedido ya fue despachado y no puede cancelarse.");
        }
        pedido.setEstado("CANCELADO");
        return pedidoRepository.save(pedido);
    }
}
