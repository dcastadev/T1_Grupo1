package com.cibertec.t1grupo1.repository;

import com.cibertec.t1grupo1.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdministradorRepository extends JpaRepository<Administrador, Long> {

    Optional<Administrador> findByUsuarioAndPassword(String usuario, String password);

    Optional<Administrador> findByUsuario(String usuario);
}