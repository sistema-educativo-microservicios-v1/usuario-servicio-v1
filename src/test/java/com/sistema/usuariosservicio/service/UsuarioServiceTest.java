package com.sistema.usuariosservicio.service;

import com.sistema.auth.entity.AuthUsuario;
import com.sistema.auth.repository.AuthUsuarioRepository;
import com.sistema.usuariosservicio.model.Usuario;
import com.sistema.usuariosservicio.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private AuthUsuarioRepository authUsuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuario;
    private AuthUsuario authUsuario;

    @BeforeEach
    void setup() {
        authUsuario = new AuthUsuario();
        authUsuario.setId(1L);

        usuario = new Usuario();
        usuario.setId(100L);
        usuario.setAuthUsuario(authUsuario);
    }

    @Test
    void testListar() {
        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));

        List<Usuario> resultado = usuarioService.listar();

        assertEquals(1, resultado.size());
        verify(usuarioRepository).findAll();
    }

    @Test
    void testObtenerPorId() {
        when(usuarioRepository.findById(100L)).thenReturn(Optional.of(usuario));

        Optional<Usuario> resultado = usuarioService.obtenerPorId(100L);

        assertTrue(resultado.isPresent());
        assertEquals(100L, resultado.get().getId());
        verify(usuarioRepository).findById(100L);
    }

    @Test
    void testGuardarUsuarioConAuthValido() {
        when(authUsuarioRepository.findById(1L)).thenReturn(Optional.of(authUsuario));
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario resultado = usuarioService.guardar(usuario);

        assertNotNull(resultado);
        assertEquals(authUsuario, resultado.getAuthUsuario());
        verify(usuarioRepository).save(usuario);
    }

    @Test
    void testGuardarUsuarioConAuthInexistenteDebeFallar() {
        when(authUsuarioRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            usuarioService.guardar(usuario);
        });

        assertEquals("AuthUsuario no encontrado", exception.getMessage());
    }

    @Test
    void testEliminar() {
        usuarioService.eliminar(100L);

        verify(usuarioRepository).deleteById(100L);
    }
}
