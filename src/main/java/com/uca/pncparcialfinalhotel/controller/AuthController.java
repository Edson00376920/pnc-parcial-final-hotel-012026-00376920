package com.uca.pncparcialfinalhotel.controller;


import com.uca.pncparcialfinalhotel.model.entities.Usuario;
import com.uca.pncparcialfinalhotel.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/register")
    public ResponseEntity<Usuario> registrar(@RequestBody Usuario usuario) {
        return new ResponseEntity<>(usuarioService.registrarUsuario(usuario), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario loginRequest) {
        Usuario usuario = usuarioService.obtenerPorEmail(loginRequest.getEmail());
        if (usuario.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.ok("Login exitoso. Rol asignado: " + usuario.getRol());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
    }
}