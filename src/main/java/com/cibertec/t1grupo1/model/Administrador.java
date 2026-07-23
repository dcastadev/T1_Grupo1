package com.cibertec.t1grupo1.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Getter @Setter @NoArgsConstructor
public class Administrador extends Persona {

    private String usuario;
    private String password;
    private String rol; // ej: "SUPERVISOR", "RRHH"
}