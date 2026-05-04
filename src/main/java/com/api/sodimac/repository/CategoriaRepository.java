package com.api.sodimac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.sodimac.entity.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
