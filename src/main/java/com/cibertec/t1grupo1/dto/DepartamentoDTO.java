package com.cibertec.t1grupo1.dto;

import jakarta.validation.constraints.NotBlank;

public record DepartamentoDTO(
        Long id,

        @NotBlank(message = "El nombre del departamento es obligatorio")
        String nombre
) {
}