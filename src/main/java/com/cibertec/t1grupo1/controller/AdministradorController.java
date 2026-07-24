package com.cibertec.t1grupo1.controller;

import com.cibertec.t1grupo1.service.AdministradorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/administradores")
public class AdministradorController {

    private final AdministradorService administradorService;

    public AdministradorController(
            AdministradorService administradorService
    ) {
        this.administradorService = administradorService;
    }

    @GetMapping("/login")
    public String login(
            @RequestParam String usuario,
            @RequestParam String password
    ) {

        boolean accesoPermitido =
                administradorService.login(usuario, password);

        if (accesoPermitido) {
            return "Acceso permitido";
        }

        return "Usuario o contraseña incorrectos";
    }
}