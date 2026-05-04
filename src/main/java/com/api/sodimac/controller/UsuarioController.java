package com.api.sodimac.controller;

import java.util.Optional;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.sodimac.entity.Usuario;
import com.api.sodimac.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody Usuario usuario) {
        try {
            Usuario nuevoUsuario = usuarioService.registrar(usuario);
            return ResponseEntity.ok(nuevoUsuario);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("mensaje", "Error al registrar: " + e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario loginData) {
        Optional<Usuario> user = usuarioService.login(loginData.getCorreo());
        if (user.isPresent() && user.get().getPassword().equals(loginData.getPassword())) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(401).body(Map.of("mensaje", "Correo o contraseña incorrectos"));
        }
    }

    @PostMapping("/olvidarPassword")
    public ResponseEntity<?> olvidarPassword(@RequestBody String correo) {
        try {
            usuarioService.solicitarRecuperacion(correo);
            return ResponseEntity.ok(Map.of("mensaje", "Si el correo existe, se ha enviado un token de recuperacion."));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("mensaje", "Error: " + e.getMessage()));
        }
    }

    @PostMapping("/restablecerPassword")
    public ResponseEntity<?> restablecerPassword(@RequestBody Map<String, String> data) {
        try {
            String token = data.get("token");
            String nuevaPassword = data.get("nuevaPassword");
            usuarioService.restablecerPassword(token, nuevaPassword);
            return ResponseEntity.ok(Map.of("mensaje", "Contraseña actualizada exitosamente."));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("mensaje", "Error: " + e.getMessage()));
        }
    }
}
