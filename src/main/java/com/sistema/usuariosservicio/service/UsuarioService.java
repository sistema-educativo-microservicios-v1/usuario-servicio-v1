package com.sistema.usuariosservicio.service;

import com.sistema.auth.entity.AuthUsuario;
import com.sistema.usuariosservicio.model.Usuario;
import com.sistema.usuariosservicio.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import com.sistema.auth.repository.AuthUsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final AuthUsuarioRepository authUsuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, AuthUsuarioRepository authUsuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.authUsuarioRepository = authUsuarioRepository;
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> obtenerPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario guardar(Usuario usuario) {
        // Obtener el AuthUsuario por ID
        AuthUsuario authUsuario = authUsuarioRepository.findById(usuario.getAuthUsuario().getId())
                .orElseThrow(() -> new RuntimeException("AuthUsuario no encontrado"));

        // Asocia el AuthUsuario al nuevo Usuario
        usuario.setAuthUsuario(authUsuario);

        return usuarioRepository.save(usuario);
    }

    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }
}
