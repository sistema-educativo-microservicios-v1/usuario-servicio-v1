package com.sistema.usuariosservicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = { "com.sistema" })
@EnableJpaRepositories(basePackages = {
		"com.sistema.usuariosservicio.repository",
		"com.sistema.auth.repository",
		"com.sistema.rol.repository"
})
@EntityScan(basePackages = {
		"com.sistema.usuariosservicio.model",
		"com.sistema.auth.entity",
		"com.sistema.rol.entity"
})
public class UsuariosServicioApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsuariosServicioApplication.class, args);
	}
}
