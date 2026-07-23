// repository/AdministradorRepository.java
package com.cibertec.t1grupo1.repository;

import com.cibertec.t1grupo1.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
}