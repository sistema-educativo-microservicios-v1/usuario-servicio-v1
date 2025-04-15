package com.sistema.usuariosservicio.model;

import jakarta.persistence.*;
import lombok.*;
import com.sistema.auth.entity.AuthUsuario;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String telefono;
    private String tipo; // "ESTUDIANTE" o "PROFESOR"

    @OneToOne
    @JoinColumn(name = "auth_usuario_id", referencedColumnName = "id")
    private AuthUsuario authUsuario;
}
