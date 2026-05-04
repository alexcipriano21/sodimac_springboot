package com.api.sodimac.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.api.sodimac.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByIdUsuario(Integer idUsuario);
}
