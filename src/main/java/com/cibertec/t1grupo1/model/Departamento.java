package com.cibertec.t1grupo1.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departamento")
@Getter
@Setter
@NoArgsConstructor
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 60)
    private String nombre;

    @OneToMany(
        mappedBy = "departamento",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY
    )
    private List<Empleado> empleados = new ArrayList<>();
}