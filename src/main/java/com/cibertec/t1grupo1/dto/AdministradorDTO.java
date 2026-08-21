package com.cibertec.t1grupo1.dto;

import com.cibertec.t1grupo1.model.Rol;

public record AdministradorDTO(
        Long id,
        String nombres,
        String apellidos,
        String usuario,
        Rol rol
) {
}