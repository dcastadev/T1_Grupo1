package com.cibertec.t1grupo1.service.impl;

import com.cibertec.t1grupo1.dto.DepartamentoDTO;
import com.cibertec.t1grupo1.exception.RecursoNoEncontradoException;
import com.cibertec.t1grupo1.mapper.DepartamentoMapper;
import com.cibertec.t1grupo1.model.Departamento;
import com.cibertec.t1grupo1.repository.DepartamentoRepository;
import com.cibertec.t1grupo1.service.DepartamentoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

    private final DepartamentoRepository departamentoRepository;
    private final DepartamentoMapper departamentoMapper;

    public DepartamentoServiceImpl(DepartamentoRepository departamentoRepository,
                                   DepartamentoMapper departamentoMapper) {
        this.departamentoRepository = departamentoRepository;
        this.departamentoMapper = departamentoMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DepartamentoDTO> listarTodos() {
        return departamentoRepository.findAll()
                .stream().map(departamentoMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public DepartamentoDTO buscarPorId(Long id) {
        Departamento d = departamentoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe un departamento con id " + id));
        return departamentoMapper.toDTO(d);
    }

    @Override
    @Transactional
    public DepartamentoDTO crear(DepartamentoDTO dto) {
        Departamento d = departamentoMapper.toEntity(dto);
        return departamentoMapper.toDTO(departamentoRepository.save(d));
    }

    @Override
    @Transactional
    public DepartamentoDTO actualizar(Long id, DepartamentoDTO dto) {
        Departamento d = departamentoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe un departamento con id " + id));
        d.setNombre(dto.nombre());
        return departamentoMapper.toDTO(departamentoRepository.save(d));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!departamentoRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("No existe un departamento con id " + id);
        }
        departamentoRepository.deleteById(id);
    }
}