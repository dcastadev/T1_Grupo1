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

-- DEPARTAMENTOS
INSERT INTO departamento (nombre) VALUES
('Recursos Humanos'),
('Tecnologías de la Información'),
('Contabilidad'),
('Ventas'),
('Logística');

-- PERSONAS (EMPLEADOS)
INSERT INTO persona (nombres, apellidos, dni, email) VALUES
('Juan','Pérez Gómez','71234561','juan.perez@empresa.com'),
('María','Torres Díaz','71234562','maria.torres@empresa.com'),
('Luis','Ramírez Soto','71234563','luis.ramirez@empresa.com'),
('Andrea','Vargas Ruiz','71234564','andrea.vargas@empresa.com'),
('Carlos','Flores Medina','71234565','carlos.flores@empresa.com');

-- EMPLEADOS
INSERT INTO empleado (id, fecha_ingreso, departamento_id) VALUES
(1,'2023-01-10',1),
(2,'2022-05-18',2),
(3,'2024-02-01',3),
(4,'2021-08-15',4),
(5,'2020-11-20',5);

-- PERSONAS (ADMINISTRADORES)
INSERT INTO persona (nombres, apellidos, dni, email) VALUES
('José','Martínez Rojas','71234566','jose.admin@empresa.com'),
('Patricia','Salazar León','71234567','patricia.admin@empresa.com');

-- ADMINISTRADORES
INSERT INTO administrador (id, usuario, password, rol) VALUES
(6,'admin','123456','RRHH'),
(7,'supervisor','admin123','SUPERVISOR');

-- ASISTENCIAS
INSERT INTO asistencia
(empleado_id, fecha, hora_entrada, hora_salida, estado)
VALUES
(1,'2026-07-20','08:00:00','17:00:00','PRESENTE'),
(2,'2026-07-20','08:20:00','17:00:00','TARDANZA'),
(3,'2026-07-20',NULL,NULL,'FALTA'),
(4,'2026-07-20','07:58:00','17:02:00','PRESENTE'),
(5,'2026-07-20','08:15:00','17:05:00','TARDANZA'),
(1,'2026-07-21','08:01:00','17:03:00','PRESENTE'),
(2,'2026-07-21','08:00:00','17:01:00','PRESENTE'),
(3,'2026-07-21','08:45:00','17:10:00','TARDANZA'),
(4,'2026-07-21',NULL,NULL,'FALTA'),
(5,'2026-07-21','07:59:00','17:00:00','PRESENTE');

SELECT * FROM persona;
SELECT * FROM departamento;
SELECT * FROM empleado;
SELECT * FROM administrador;
SELECT * FROM asistencia;

SELECT
p.id,
p.nombres,
p.apellidos,
p.dni,
d.nombre AS departamento,
e.fecha_ingreso
FROM empleado e
INNER JOIN persona p
ON p.id = e.id
LEFT JOIN departamento d
ON d.id = e.departamento_id;

SELECT
p.id,
p.nombres,
p.apellidos,
a.usuario,
a.rol
FROM administrador a
INNER JOIN persona p
ON p.id = a.id;

SELECT
a.id,
CONCAT(p.nombres,' ',p.apellidos) AS empleado,
d.nombre AS departamento,
a.fecha,
a.hora_entrada,
a.hora_salida,
a.estado
FROM asistencia a
INNER JOIN empleado e
ON a.empleado_id = e.id
INNER JOIN persona p
ON e.id = p.id
LEFT JOIN departamento d
ON e.departamento_id = d.id
ORDER BY a.fecha, empleado;
