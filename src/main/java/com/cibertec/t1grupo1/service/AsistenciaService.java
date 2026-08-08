package com.cibertec.t1grupo1.service;

import com.cibertec.t1grupo1.dto.AsistenciaDTO;
import com.cibertec.t1grupo1.exception.RecursoNoEncontradoException;
import com.cibertec.t1grupo1.mapper.AsistenciaMapper;
import com.cibertec.t1grupo1.model.Asistencia;
import com.cibertec.t1grupo1.repository.AsistenciaRepository;
import com.cibertec.t1grupo1.repository.EmpleadoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AsistenciaService {

    private final AsistenciaRepository asistenciaRepository;
    private final AsistenciaMapper asistenciaMapper;
    private final EmpleadoRepository empleadoRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public AsistenciaService(AsistenciaRepository asistenciaRepository,
                             AsistenciaMapper asistenciaMapper,
                             EmpleadoRepository empleadoRepository) {
        this.asistenciaRepository = asistenciaRepository;
        this.asistenciaMapper = asistenciaMapper;
        this.empleadoRepository = empleadoRepository;
    }

    // ==========================================
    // MÉTODOS DE T1 (Demostración de Flush)
    // ==========================================

    @Transactional
    public List<Asistencia> registrarAsistenciasEnLote(List<Asistencia> asistencias) {
        return asistenciaRepository.saveAllAndFlush(asistencias);
    }

    @Transactional
    public void registrarAsistenciasConFlushManual(List<Asistencia> asistencias) {
        int contador = 0;
        for (Asistencia asistencia : asistencias) {
            entityManager.persist(asistencia);
            contador++;

            if (contador % 20 == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }

        entityManager.flush();
        entityManager.clear();
    }

    // ==========================================
    // MÉTODOS DE T2 (CRUD Completo)
    // ==========================================

    @Transactional(readOnly = true)
    public List<AsistenciaDTO> listarTodas() {
        return asistenciaRepository.findAll().stream().map(asistenciaMapper::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AsistenciaDTO buscarPorId(Long id) {
        Asistencia a = asistenciaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe una asistencia con id " + id));
        return asistenciaMapper.toDTO(a);
    }

    @Transactional
    public AsistenciaDTO actualizar(Long id, AsistenciaDTO dto) {
        Asistencia a = asistenciaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe una asistencia con id " + id));
        a.setFecha(dto.fecha());
        a.setHoraEntrada(dto.horaEntrada());
        a.setHoraSalida(dto.horaSalida());
        a.setEstado(dto.estado());
        a.setEmpleado(empleadoRepository.findById(dto.empleadoId())
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe el empleado con id " + dto.empleadoId())));
        return asistenciaMapper.toDTO(asistenciaRepository.save(a));
    }

    @Transactional
    public void eliminar(Long id) {
        if (!asistenciaRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("No existe una asistencia con id " + id);
        }
        asistenciaRepository.deleteById(id);
    }
}