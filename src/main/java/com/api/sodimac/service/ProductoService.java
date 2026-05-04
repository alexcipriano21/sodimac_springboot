package com.api.sodimac.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.sodimac.entity.Categoria;
import com.api.sodimac.entity.Producto;
import com.api.sodimac.repository.CategoriaRepository;
import com.api.sodimac.repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Page<Producto> listarProductosPaginados(Pageable pageable) {
        return productoRepository.findAll(pageable);
    }

    public Optional<Producto> buscarPorId(Integer id) {
        return productoRepository.findById(id);
    }

    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    public List<Producto> buscarProductos(String query) {
        return productoRepository.findByNombreContainingIgnoreCaseOrMarcaContainingIgnoreCase(query, query);
    }
}
