package com.cibertec.t1grupo1.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "empleado")
@Getter
@Setter
@NoArgsConstructor
public class Empleado extends Persona {

    @Column(
            name = "fecha_ingreso",
            nullable = false
    )
    private LocalDate fechaIngreso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

    @OneToMany(
            mappedBy = "empleado",
            cascade = CascadeType.ALL
    )
    private List<Asistencia> asistencias = new ArrayList<>();
}
