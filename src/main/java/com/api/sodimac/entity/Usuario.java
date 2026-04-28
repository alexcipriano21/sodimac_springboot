package com.api.sodimac.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Usuario")
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 50)
    private String nombres;
    @Column(nullable = false, length = 50)
    private String apellidos;
    @Column(nullable = false, unique = true, length = 8)
    private String dni;
    @Column(nullable = false, length = 255)
    private String password;
    @Column(nullable = false, length = 9)
    private String telefono;
    @Column(nullable = false, unique = true, length = 60)
    private String correo;
    @Column(length = 20)
    private String rol = "Cliente";
}
