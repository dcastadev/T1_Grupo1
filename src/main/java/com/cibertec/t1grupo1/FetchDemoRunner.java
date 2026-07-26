package com.cibertec.t1grupo1;

import com.cibertec.t1grupo1.service.EmpleadoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FetchDemoRunner {

    @Bean
    CommandLineRunner demoFetch(EmpleadoService empleadoService) {
        return args -> {
            System.out.println("\n=== SIN JOIN FETCH (aqui deberian aparecer varias consultas SELECT) ===");
            empleadoService.listarSinFetch();

            System.out.println("\n=== CON JOIN FETCH (aqui deberia aparecer solo UNA consulta SELECT) ===");
            empleadoService.listarConFetch();
        };
    }
}


