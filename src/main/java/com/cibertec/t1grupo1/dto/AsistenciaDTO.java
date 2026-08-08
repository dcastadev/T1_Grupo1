package com.cibertec.t1grupo1.dto;

import com.cibertec.t1grupo1.model.EstadoAsistencia;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record AsistenciaDTO(
        Long id,
        @NotNull(message = "El id del empleado es obligatorio") Long empleadoId,
        @NotNull(message = "La fecha es obligatoria") LocalDate fecha,
        LocalTime horaEntrada,
        LocalTime horaSalida,
        @NotNull(message = "El estado es obligatorio") EstadoAsistencia estado
) {
}