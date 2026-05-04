package com.api.sodimac.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.sodimac.dto.CarritoItemRequest;
import com.api.sodimac.entity.CarritoItem;
import com.api.sodimac.repository.CarritoItemRepository;

@Service
public class CarritoService {
    @Autowired
    private CarritoItemRepository carritoItemRepository;
    
    public CarritoItem agregarOActualizar(CarritoItemRequest request){
        Optional<CarritoItem> existente = carritoItemRepository
            .findByUsuarioIdAndProductoId(request.getUsuarioId(), request.getProductoId());
        if(existente.isPresent()){
            CarritoItem item = existente.get();
            item.setCantidad(item.getCantidad() + request.getCantidad());
            return carritoItemRepository.save(item);
        }
        CarritoItem nuevoItem = new CarritoItem();
        nuevoItem.setUsuarioId(request.getUsuarioId());
        nuevoItem.setProductoId(request.getProductoId());
        nuevoItem.setCantidad(request.getCantidad());
        return carritoItemRepository.save(nuevoItem);
    }
    public boolean eliminarItem(Long id){
        if(carritoItemRepository.existsById(id)){
            carritoItemRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
