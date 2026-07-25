package com.cibertec.t1grupo1.service;

import com.cibertec.t1grupo1.model.Asistencia;
import com.cibertec.t1grupo1.repository.AsistenciaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AsistenciaService {

    private final AsistenciaRepository asistenciaRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public AsistenciaService(AsistenciaRepository asistenciaRepository) {
        this.asistenciaRepository = asistenciaRepository;
    }


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
}