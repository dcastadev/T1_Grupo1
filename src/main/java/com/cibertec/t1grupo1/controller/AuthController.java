package com.cibertec.t1grupo1.controller;

import com.cibertec.t1grupo1.dto.AdministradorDTO;
import com.cibertec.t1grupo1.dto.LoginDTO;
import com.cibertec.t1grupo1.dto.LoginResponseDTO;
import com.cibertec.t1grupo1.dto.RegistroDTO;
import com.cibertec.t1grupo1.service.AdministradorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AdministradorService administradorService;

    public AuthController(AdministradorService administradorService) {
        this.administradorService = administradorService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AdministradorDTO register(@Valid @RequestBody RegistroDTO dto) {
        return administradorService.registrar(dto);
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@Valid @RequestBody LoginDTO dto) {
        return administradorService.loginSeguro(dto);
    }
}