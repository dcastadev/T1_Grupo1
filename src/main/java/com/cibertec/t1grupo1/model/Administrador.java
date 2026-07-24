package com.cibertec.t1grupo1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "administrador")
@Getter
@Setter
@NoArgsConstructor
public class Administrador extends Persona {

    @Column(nullable = false, unique = true, length = 40)
    private String usuario;

    @Column(nullable = false, length = 120)
    private String password;

    @Column(nullable = false, length = 30)
    private String rol;
}
