package com.sistema.auth.repository;

import com.sistema.auth.entity.AuthUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthUsuarioRepository extends JpaRepository<AuthUsuario, Long> {
    Optional<AuthUsuario> findByEmail(String email);
}
