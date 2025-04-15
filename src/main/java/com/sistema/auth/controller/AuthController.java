package com.sistema.auth.controller;

import com.sistema.auth.entity.AuthUsuario;
import com.sistema.auth.service.AuthService;
import com.sistema.auth.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthUsuario> registrar(@RequestBody AuthUsuario usuario) {
        AuthUsuario creado = authService.registrar(usuario);
        return ResponseEntity.ok(creado);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> datos) {
        String email = datos.get("email");
        String password = datos.get("password");

        Optional<String> token = authService.login(email, password);

        if (token.isPresent()) {
            return ResponseEntity.ok(Map.of("token", token.get()));
        } else {
            return ResponseEntity.status(401).body(Map.of("mensaje", "Credenciales inválidas"));
        }
    }

    @GetMapping("/validate-token")
    public ResponseEntity<Map<String, String>> validateToken(@RequestHeader("Authorization") String token) {
        System.out.println("🔐 Validando token en microservicio AUTH: " + token);

        try {
            String email = jwtUtil.obtenerEmailDesdeToken(token.replace("Bearer ", ""));
            String rol = jwtUtil.obtenerRolDesdeToken(token.replace("Bearer ", ""));

            System.out.println(" Token válido - Email: " + email + ", Rol: " + rol);

            return ResponseEntity.ok(Map.of(
                    "rol", rol,
                    "email", email));
        } catch (Exception e) {
            System.out.println("Token inválido en AUTH: " + e.getMessage());
            return ResponseEntity.status(401).body(Map.of("error", "Token inválido"));
        }
    }
}
