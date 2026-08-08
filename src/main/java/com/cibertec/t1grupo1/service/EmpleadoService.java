package com.cibertec.t1grupo1.service;

import com.cibertec.t1grupo1.dto.EmpleadoDTO;
import com.cibertec.t1grupo1.exception.RecursoNoEncontradoException;
import com.cibertec.t1grupo1.mapper.EmpleadoMapper;
import com.cibertec.t1grupo1.model.Empleado;
import com.cibertec.t1grupo1.repository.DepartamentoRepository;
import com.cibertec.t1grupo1.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepo;
    private final EmpleadoMapper empleadoMapper;
    private final DepartamentoRepository departamentoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepo,
                           EmpleadoMapper empleadoMapper,
                           DepartamentoRepository departamentoRepository) {
        this.empleadoRepo = empleadoRepo;
        this.empleadoMapper = empleadoMapper;
        this.departamentoRepository = departamentoRepository;
    }

    // ==========================================
    // MÉTODOS DE T1 (Demostración de Fetching)
    // ==========================================

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

    // ==========================================
    // MÉTODOS DE T2 (CRUD Completo)
    // ==========================================

    @Transactional(readOnly = true)
    public List<EmpleadoDTO> listarTodos() {
        return empleadoRepo.findAll().stream().map(empleadoMapper::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public EmpleadoDTO buscarPorId(Long id) {
        Empleado e = empleadoRepo.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe un empleado con id " + id));
        return empleadoMapper.toDTO(e);
    }

    @Transactional
    public EmpleadoDTO crear(EmpleadoDTO dto) {
        Empleado e = empleadoMapper.toEntity(dto);
        e.setDepartamento(departamentoRepository.findById(dto.departamentoId())
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe el departamento con id " + dto.departamentoId())));
        return empleadoMapper.toDTO(empleadoRepo.save(e));
    }

    @Transactional
    public EmpleadoDTO actualizar(Long id, EmpleadoDTO dto) {
        Empleado e = empleadoRepo.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe un empleado con id " + id));
        e.setNombres(dto.nombres());
        e.setApellidos(dto.apellidos());
        e.setDni(dto.dni());
        e.setEmail(dto.email());
        e.setFechaIngreso(dto.fechaIngreso());
        e.setDepartamento(departamentoRepository.findById(dto.departamentoId())
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe el departamento con id " + dto.departamentoId())));
        return empleadoMapper.toDTO(empleadoRepo.save(e));
    }

    @Transactional
    public void eliminar(Long id) {
        if (!empleadoRepo.existsById(id)) {
            throw new RecursoNoEncontradoException("No existe un empleado con id " + id);
        }
        empleadoRepo.deleteById(id);
    }
}