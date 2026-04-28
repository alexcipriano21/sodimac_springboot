package com.api.sodimac.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.api.sodimac.entity.RecuperarPassword;
import com.api.sodimac.entity.Usuario;
import com.api.sodimac.repository.RecuperarPasswordRepository;
import com.api.sodimac.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RecuperarPasswordRepository recuperarRepository;
    @Autowired
    private JavaMailSender mailSender;

    public Usuario registrar(Usuario usuario){
        return usuarioRepository.save(usuario);
    }
    public Optional<Usuario> login(String correo){
        return usuarioRepository.findByCorreo(correo);
    }
    public void enviarEmailRecuperacion(String correoDestino, String token){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("alexander.cipriano2019@gmail.com");
        message.setTo(correoDestino);
        message.setSubject("Recuperacion de Contraseña - Sodimac");
        message.setText("Usa este token para restablecer tu clave: " + token + "\nExpira en 15 minutos.");
        mailSender.send(message);
    }
    public void solicitarRecuperacion(String correo){
        Optional<Usuario> usuarioOpt = usuarioRepository.findByCorreo(correo);
        if (usuarioOpt.isPresent()){
            String token = java.util.UUID.randomUUID().toString();
            RecuperarPassword rp = new RecuperarPassword();
            rp.setCorreo(correo);
            rp.setToken(token);
            rp.setExpiraAt(java.time.LocalDateTime.now().plusMinutes(15));
            recuperarRepository.save(rp);
            enviarEmailRecuperacion(correo, token);
        }
    }
}
