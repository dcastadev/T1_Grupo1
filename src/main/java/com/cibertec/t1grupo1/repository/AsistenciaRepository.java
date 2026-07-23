// repository/AsistenciaRepository.java
package com.cibertec.t1grupo1.repository;

import com.cibertec.t1grupo1.model.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {
    // TODO (tema: Flush): metodos de guardado batch se usan desde el service
}