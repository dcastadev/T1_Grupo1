package com.cibertec.t1grupo1.controller;

import com.cibertec.t1grupo1.dto.AsistenciaDTO;
import com.cibertec.t1grupo1.service.AsistenciaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/asistencias")
public class AsistenciaController {

    private final AsistenciaService asistenciaService;

    public AsistenciaController(AsistenciaService asistenciaService) {
        this.asistenciaService = asistenciaService;
    }

    @GetMapping
    public List<AsistenciaDTO> listar() {
        return asistenciaService.listarTodas();
    }

    @GetMapping("/{id}")
    public AsistenciaDTO buscarPorId(@PathVariable Long id) {
        return asistenciaService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public AsistenciaDTO actualizar(@PathVariable Long id, @Valid @RequestBody AsistenciaDTO dto) {
        return asistenciaService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        asistenciaService.eliminar(id);
    }
}