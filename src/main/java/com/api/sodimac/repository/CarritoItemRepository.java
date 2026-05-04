package com.api.sodimac.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.sodimac.entity.CarritoItem;

public interface CarritoItemRepository extends JpaRepository<CarritoItem, Long> {
    Optional<CarritoItem> findByUsuarioIdAndProductoId(Long usuarioId, Integer productoId);
    List<CarritoItem> findByUsuarioId(Long usuarioId);
}
