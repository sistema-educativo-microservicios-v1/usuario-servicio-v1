package com.sistema.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @GetMapping("/admin/secret")
    public String soloAdminPuedeVerEsto() {
        return "Bienvenido ADMIN: ¡acceso autorizado!";
    }
}