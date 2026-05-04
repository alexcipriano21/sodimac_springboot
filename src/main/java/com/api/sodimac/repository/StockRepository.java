package com.api.sodimac.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.api.sodimac.entity.Stock;
import com.api.sodimac.entity.StockId;

public interface StockRepository extends JpaRepository<Stock, StockId> {
    Optional<Stock> findByIdSucursalAndIdProducto(Integer idSucursal, Integer idProducto);
}
