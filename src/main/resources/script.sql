CREATE DATABASE IF NOT EXISTS asistencia_personal_db;
USE asistencia_personal_db;

-- Tabla base de la jerarquia (estrategia JOINED)
CREATE TABLE persona (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         nombres VARCHAR(80) NOT NULL,
                         apellidos VARCHAR(80) NOT NULL,
                         dni VARCHAR(8) NOT NULL UNIQUE,
                         email VARCHAR(120)
);

CREATE TABLE departamento (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              nombre VARCHAR(60) NOT NULL
);

CREATE TABLE empleado (
                          id BIGINT PRIMARY KEY,
                          fecha_ingreso DATE NOT NULL,
                          departamento_id BIGINT,
                          CONSTRAINT fk_empleado_persona FOREIGN KEY (id) REFERENCES persona(id),
                          CONSTRAINT fk_empleado_departamento FOREIGN KEY (departamento_id) REFERENCES departamento(id)
);

CREATE TABLE administrador (
                               id BIGINT PRIMARY KEY,
                               usuario VARCHAR(40) NOT NULL UNIQUE,
                               password VARCHAR(120) NOT NULL,
                               rol VARCHAR(30) NOT NULL,
                               CONSTRAINT fk_administrador_persona FOREIGN KEY (id) REFERENCES persona(id)
);

CREATE TABLE asistencia (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            empleado_id BIGINT NOT NULL,
                            fecha DATE NOT NULL,
                            hora_entrada TIME,
                            hora_salida TIME,
                            estado VARCHAR(20) NOT NULL,
                            CONSTRAINT fk_asistencia_empleado FOREIGN KEY (empleado_id) REFERENCES empleado(id)
);