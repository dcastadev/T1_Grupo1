// repository/EmpleadoRepository.java
package com.cibertec.t1grupo1.repository;

import com.cibertec.t1grupo1.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    // TODO (tema: Fetching): agregar aqui las @Query con JOIN FETCH
}