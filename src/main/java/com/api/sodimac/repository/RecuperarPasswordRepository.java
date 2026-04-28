package com.api.sodimac.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.sodimac.entity.RecuperarPassword;

@Repository
public interface RecuperarPasswordRepository extends JpaRepository<RecuperarPassword, String>{
    Optional<RecuperarPassword> findByToken(String token);
}
