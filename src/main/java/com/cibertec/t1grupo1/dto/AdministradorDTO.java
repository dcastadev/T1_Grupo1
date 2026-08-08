package com.cibertec.t1grupo1.dto;

public record AdministradorDTO(
        Long id,
        String nombres,
        String apellidos,
        String usuario,
        String rol
) {
}