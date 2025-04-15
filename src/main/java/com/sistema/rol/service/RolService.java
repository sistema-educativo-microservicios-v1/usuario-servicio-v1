package com.sistema.rol.service;

import com.sistema.rol.entity.Rol;
import com.sistema.rol.repository.RolRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RolService {

    private final RolRepository rolRepository;

    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public Rol obtenerORCrear(String nombreRol) {
        Optional<Rol> rol = rolRepository.findByNombre(nombreRol);

        // Si existe, lo devolvemos. Si no, lo creamos.
        return rol.orElseGet(() -> rolRepository.save(new Rol(null, nombreRol)));
    }
}
