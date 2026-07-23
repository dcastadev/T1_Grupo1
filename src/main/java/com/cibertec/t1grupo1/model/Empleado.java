package com.cibertec.t1grupo1.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
public class Empleado extends Persona {

    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;

    // TODO (tema: Relaciones ManyToOne/OneToMany):
    // confirmar fetch type y cascade adecuados aqui
    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL)
    private List<Asistencia> asistencias = new ArrayList<>();
}