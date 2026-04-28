package com.api.sodimac.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.sodimac.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    Optional<Usuario> findByCorreo(String correo);
    boolean existsByDni(String dni);
    boolean existsByCorreo(String correo);
}
