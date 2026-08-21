package com.cibertec.t1grupo1.service;

import com.cibertec.t1grupo1.dto.AdministradorDTO;
import com.cibertec.t1grupo1.dto.LoginDTO;
import com.cibertec.t1grupo1.dto.LoginResponseDTO;
import com.cibertec.t1grupo1.dto.RegistroDTO;
import com.cibertec.t1grupo1.exception.RecursoNoEncontradoException;
import com.cibertec.t1grupo1.model.Administrador;
import com.cibertec.t1grupo1.repository.AdministradorRepository;
import com.cibertec.t1grupo1.security.JwtService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdministradorService {

    private final AdministradorRepository administradorRepository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AdministradorService(AdministradorRepository administradorRepository, JwtService jwtService) {
        this.administradorRepository = administradorRepository;
        this.jwtService = jwtService;
    }

    // Metodo original de T1 - se mantiene para no romper nada anterior
    public boolean login(String usuario, String password) {
        return administradorRepository.findByUsuarioAndPassword(usuario, password).isPresent();
    }

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

        return toDTO(administradorRepository.save(admin));
    }

    // NUEVO: login que genera JWT firmado con HMAC-SHA256
    public LoginResponseDTO loginSeguro(LoginDTO dto) {
        Administrador admin = administradorRepository.findByUsuario(dto.usuario())
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario o contraseña incorrectos"));

        if (!passwordEncoder.matches(dto.password(), admin.getPassword())) {
            throw new RecursoNoEncontradoException("Usuario o contraseña incorrectos");
        }

        String rolConPrefijo = "ROLE_" + admin.getRol().name();
        String token = jwtService.generarToken(admin.getUsuario(), List.of(rolConPrefijo));

        return new LoginResponseDTO(
                token,
                "Bearer",
                jwtService.getExpirationMs(),
                admin.getUsuario(),
                List.of(rolConPrefijo)
        );
    }

    private AdministradorDTO toDTO(Administrador admin) {
        return new AdministradorDTO(
                admin.getId(), admin.getNombres(), admin.getApellidos(),
                admin.getUsuario(), admin.getRol()
        );
    }
}