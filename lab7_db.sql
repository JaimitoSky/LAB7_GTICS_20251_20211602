DROP DATABASE IF EXISTS lab7_db;


CREATE DATABASE IF NOT EXISTS lab7_db
    DEFAULT CHARACTER SET utf8mb4
    DEFAULT COLLATE utf8mb4_general_ci;

USE lab7_db;

-- Tabla estudiante
CREATE TABLE IF NOT EXISTS estudiante (
                                          id INT AUTO_INCREMENT PRIMARY KEY,
                                          nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    dni CHAR(8) NOT NULL UNIQUE,
    codigo_pucp CHAR(8) NOT NULL UNIQUE,
    fecha_nacimiento DATE NOT NULL,
    sexo CHAR(1) NOT NULL CHECK (sexo IN ('M', 'F')),
    correo_institucional VARCHAR(100) NOT NULL CHECK (correo_institucional LIKE '%@pucp.edu.pe'),
    correo_personal VARCHAR(100) NOT NULL,
    telefono CHAR(9) NOT NULL CHECK (telefono REGEXP '^9[0-9]{8}$'),
    direccion VARCHAR(100),
    departamento VARCHAR(100),
    provincia VARCHAR(100),
    carrera VARCHAR(100),
    fecha_registro DATETIME,
    ultima_actualizacion DATETIME,
    estado BOOLEAN
    );

