package com.sistema.usuariosservicio.repository;

import com.sistema.usuariosservicio.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}