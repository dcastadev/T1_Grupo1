package com.cibertec.t1grupo1.service;

import com.cibertec.t1grupo1.model.Empleado;
import com.cibertec.t1grupo1.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepo;

    public EmpleadoService(EmpleadoRepository empleadoRepo) {
        this.empleadoRepo = empleadoRepo;
    }

    @Transactional(readOnly = true)
    public void listarSinFetch() {
        List<Empleado> empleados = empleadoRepo.findAll(); // 1 consulta
        for (Empleado e : empleados) {
            // esta linea dispara una consulta EXTRA por cada empleado,
            // porque "departamento" es LAZY y todavia no esta cargado
            System.out.println(e.getNombres() + " -> " + e.getDepartamento().getNombre());
        }
    }


    @Transactional(readOnly = true)
    public void listarConFetch() {
        List<Empleado> empleados = empleadoRepo.findAllConDepartamento(); // 1 sola consulta con JOIN
        for (Empleado e : empleados) {
            // aqui "departamento" ya vino incluido, no dispara ninguna consulta nueva
            System.out.println(e.getNombres() + " -> " + e.getDepartamento().getNombre());
        }
    }
}