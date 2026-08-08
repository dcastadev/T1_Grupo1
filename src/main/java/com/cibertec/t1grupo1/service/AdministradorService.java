package com.cibertec.t1grupo1.service;

import com.cibertec.t1grupo1.dto.AdministradorDTO;
import com.cibertec.t1grupo1.dto.LoginDTO;
import com.cibertec.t1grupo1.dto.RegistroDTO;
import com.cibertec.t1grupo1.exception.RecursoNoEncontradoException;
import com.cibertec.t1grupo1.model.Administrador;
import com.cibertec.t1grupo1.repository.AdministradorRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdministradorService {

    private final AdministradorRepository administradorRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AdministradorService(AdministradorRepository administradorRepository) {
        this.administradorRepository = administradorRepository;
    }

    // Metodo original de T1, se mantiene igual para no romper lo ya sustentado
    public boolean login(String usuario, String password) {
        return administradorRepository
                .findByUsuarioAndPassword(usuario, password)
                .isPresent();
    }

    // NUEVO para T2: registro con password encriptado
    @Transactional
    public AdministradorDTO registrar(RegistroDTO dto) {
        if (administradorRepository.findByUsuario(dto.usuario()).isPresent()) {
            throw new IllegalStateException("Ese nombre de usuario ya existe");
        }

        Administrador admin = new Administrador();
        admin.setNombres(dto.nombres());
        admin.setApellidos(dto.apellidos());
        admin.setDni(dto.dni());
        admin.setEmail(dto.email());
        admin.setUsuario(dto.usuario());
        admin.setPassword(passwordEncoder.encode(dto.password()));
        admin.setRol(dto.rol());

        Administrador guardado = administradorRepository.save(admin);
        return toDTO(guardado);
    }

    // NUEVO para T2: login validando el hash
    public AdministradorDTO loginSeguro(LoginDTO dto) {
        Administrador admin = administradorRepository.findByUsuario(dto.usuario())
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario o contraseña incorrectos"));

        if (!passwordEncoder.matches(dto.password(), admin.getPassword())) {
            throw new RecursoNoEncontradoException("Usuario o contraseña incorrectos");
        }

        return toDTO(admin);
    }

    private AdministradorDTO toDTO(Administrador admin) {
        return new AdministradorDTO(
                admin.getId(),
                admin.getNombres(),
                admin.getApellidos(),
                admin.getUsuario(),
                admin.getRol()
        );
    }
}