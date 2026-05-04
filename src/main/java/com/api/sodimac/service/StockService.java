package com.api.sodimac.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.sodimac.entity.Stock;
import com.api.sodimac.repository.StockRepository;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public Optional<Stock> consultarStock(Integer idSucursal, Integer idProducto) {
        return stockRepository.findByIdSucursalAndIdProducto(idSucursal, idProducto);
    }
}
