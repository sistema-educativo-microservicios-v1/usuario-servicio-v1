package com.sistema.auth.entity;

import jakarta.persistence.*;
import lombok.*;
import com.sistema.rol.entity.Rol;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;

    private String tipo; // ESTUDIANTE o PROFESOR
}
