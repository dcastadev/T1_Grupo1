package com.cibertec.t1grupo1.service;

import com.cibertec.t1grupo1.dto.DepartamentoDTO;

import java.util.List;

public interface DepartamentoService {
    List<DepartamentoDTO> listarTodos();
    DepartamentoDTO buscarPorId(Long id);
    DepartamentoDTO crear(DepartamentoDTO dto);
    DepartamentoDTO actualizar(Long id, DepartamentoDTO dto);
    void eliminar(Long id);
}