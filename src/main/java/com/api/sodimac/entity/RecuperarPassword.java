package com.api.sodimac.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "RecuperarPassword")
@Data
public class RecuperarPassword {
    @Id
    @Column(length = 60)
    private String correo;
    @Column(nullable = false, length = 255)
    private String token;
    @Column(nullable = false)
    private LocalDateTime expiraAt;
}
