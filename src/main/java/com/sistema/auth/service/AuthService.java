package com.sistema.auth.service;

import com.sistema.auth.entity.AuthUsuario;
import com.sistema.auth.repository.AuthUsuarioRepository;
import com.sistema.auth.security.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.Optional;

import com.sistema.rol.service.RolService;

@Service
public class AuthService {

    private final AuthUsuarioRepository authRepo;
    private final JwtUtil jwtUtil;
    private final RolService rolService;

    public AuthService(AuthUsuarioRepository authRepo, JwtUtil jwtUtil, RolService rolService) {
        this.authRepo = authRepo;
        this.jwtUtil = jwtUtil;
        this.rolService = rolService;
    }

    public AuthUsuario registrar(AuthUsuario usuario) {
        if ("PROFESOR".equalsIgnoreCase(usuario.getTipo())) {
            usuario.setRol(rolService.obtenerORCrear("ADMIN"));
        } else {
            usuario.setTipo("ESTUDIANTE"); // por defecto
            usuario.setRol(rolService.obtenerORCrear("USER"));
        }

        return authRepo.save(usuario);
    }

    public Optional<String> login(String email, String password) {
        return authRepo.findByEmail(email)
                .filter(user -> user.getPassword().equals(password))
                .map(user -> jwtUtil.generarToken(user.getEmail(), user.getRol().getNombre()));
    }
}
