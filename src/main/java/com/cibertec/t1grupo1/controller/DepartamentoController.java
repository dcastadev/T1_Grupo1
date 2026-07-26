package com.cibertec.t1grupo1.controller;

import com.cibertec.t1grupo1.dto.DepartamentoDTO;
import com.cibertec.t1grupo1.service.DepartamentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {

    private final DepartamentoService departamentoService;

    public DepartamentoController(DepartamentoService departamentoService) {
        this.departamentoService = departamentoService;
    }

    @GetMapping
    public List<DepartamentoDTO> listar() {
        return departamentoService.listarTodos();
    }

    @GetMapping("/{id}")
    public DepartamentoDTO buscarPorId(@PathVariable Long id) {
        return departamentoService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DepartamentoDTO crear(@Valid @RequestBody DepartamentoDTO dto) {
        return departamentoService.crear(dto);
    }

    @PutMapping("/{id}")
    public DepartamentoDTO actualizar(@PathVariable Long id, @Valid @RequestBody DepartamentoDTO dto) {
        return departamentoService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        departamentoService.eliminar(id);
    }
}