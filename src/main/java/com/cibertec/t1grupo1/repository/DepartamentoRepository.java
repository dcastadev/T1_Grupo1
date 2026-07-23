// repository/DepartamentoRepository.java
package com.cibertec.t1grupo1.repository;

import com.cibertec.t1grupo1.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
}