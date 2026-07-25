package com.cibertec.t1grupo1;

import com.cibertec.t1grupo1.model.*;
import com.cibertec.t1grupo1.repository.DepartamentoRepository;
import com.cibertec.t1grupo1.repository.EmpleadoRepository;
import com.cibertec.t1grupo1.service.AsistenciaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class FlushDemoRunner {

    @Bean
    CommandLineRunner demoFlush(DepartamentoRepository departamentoRepo,
                                EmpleadoRepository empleadoRepo,
                                AsistenciaService asistenciaService) {
        return args -> {

            Departamento depto = new Departamento();
            depto.setNombre("Sistemas");
            departamentoRepo.save(depto);

            Empleado empleado = new Empleado();
            empleado.setNombres("Juan");
            empleado.setApellidos("Perez");
            empleado.setDni("12345678");
            empleado.setEmail("juan.perez@empresa.com");
            empleado.setFechaIngreso(LocalDate.now());
            empleado.setDepartamento(depto);
            empleadoRepo.save(empleado);


            List<Asistencia> asistencias = new ArrayList<>();
            for (int i = 0; i < 25; i++) {
                Asistencia a = new Asistencia();
                a.setEmpleado(empleado);
                a.setFecha(LocalDate.now().minusDays(i));
                a.setHoraEntrada(LocalTime.of(8, 0));
                a.setHoraSalida(LocalTime.of(17, 0));
                a.setEstado(EstadoAsistencia.PRESENTE);
                asistencias.add(a);
            }

            System.out.println("=== INICIANDO REGISTRO CON FLUSH MANUAL (lotes de 20) ===");
            asistenciaService.registrarAsistenciasConFlushManual(asistencias);
            System.out.println("=== FIN DEL REGISTRO ===");
        };
    }
}