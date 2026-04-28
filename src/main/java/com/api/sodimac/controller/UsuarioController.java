package com.api.sodimac.controller;

import java.util.Optional;

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
    public ResponseEntity<?> registrar(@RequestBody Usuario usuario){
        try{
            Usuario nuevoUsuario = usuarioService.registrar(usuario);
            return ResponseEntity.ok(nuevoUsuario);
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Error al registrar: " + e.getMessage());
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario loginData){
        Optional<Usuario> user = usuarioService.login(loginData.getCorreo());
        if(user.isPresent() && user.get().getPassword().equals(loginData.getPassword())){
            return ResponseEntity.ok(user.get());
        }else{
            return ResponseEntity.status(401).body("Correo o contraseña incorrectos");
        }
    }
    @PostMapping("/olvidarPassword")
    public ResponseEntity<?> olvidarPassword(@RequestBody String correo){
        try{
            usuarioService.solicitarRecuperacion(correo);
            return ResponseEntity.ok("Si el correo existe, se ha enviado se ha enviado un token de recuperacion.");
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
