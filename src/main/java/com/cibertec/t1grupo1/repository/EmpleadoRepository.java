// repository/EmpleadoRepository.java
package com.cibertec.t1grupo1.repository;

import com.cibertec.t1grupo1.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    // TODO (tema: Fetching): agregar aqui las @Query con JOIN
    // 1. SIN JOIN FETCH (El metodo normal que causaría el problema N+1)
    // No necesitas escribirlo si usas findAll() que ya viene por defecto,
    // pero lo ponemos de ejemplo.
    List<Empleado> findAll();

    // 2. CON JOIN FETCH (Solución al N+1)
    // Trae a todos los empleados y sus departamentos en una sola consulta SQL.
    @Query("SELECT e FROM Empleado e JOIN FETCH e.departamento")
    List<Empleado> findAllConDepartamento();

    // 3. CON JOIN FETCH y filtro (Otro ejemplo útil)
    @Query("SELECT e FROM Empleado e JOIN FETCH e.departamento d WHERE d.nombre = :nombreDepto")
    List<Empleado> findByDepartamentoNombreConFetch(String nombreDepto);

}