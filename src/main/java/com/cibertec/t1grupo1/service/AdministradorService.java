package com.cibertec.t1grupo1.service;

import com.cibertec.t1grupo1.repository.AdministradorRepository;
import org.springframework.stereotype.Service;

@Service
public class AdministradorService {

    private final AdministradorRepository administradorRepository;

    public AdministradorService(
            AdministradorRepository administradorRepository
    ) {
        this.administradorRepository = administradorRepository;
    }

    public boolean login(
            String usuario,
            String password
    ) {
        return administradorRepository
                .findByUsuarioAndPassword(usuario, password)
                .isPresent();
    }
}