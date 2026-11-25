-- 1. Creación de base de datos y usuario
CREATE DATABASE gimnasiodb;
CREATE USER gimnasio WITH PASSWORD 'clave123';
GRANT ALL PRIVILEGES ON DATABASE gimnasiodb TO gimnasio;
GRANT ALL ON SCHEMA public TO gimnasio;

-- Conectarse a gimnasiodb antes de ejecutar las tablas

-- 2. Creación de tablas
CREATE TABLE IF NOT EXISTS socio (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255),
    plan VARCHAR(255),
    mensualidad DOUBLE PRECISION,
    activo BOOLEAN
);

CREATE TABLE IF NOT EXISTS clase_grupal (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255),
    instructor VARCHAR(255),
    horario VARCHAR(255),
    cupos INTEGER
);

CREATE TABLE IF NOT EXISTS maquina (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255),
    marca VARCHAR(255),
    anio_compra INTEGER,
    en_mantenimiento BOOLEAN
);

-- 3. Datos de prueba (2 registros por tabla)
INSERT INTO socio (nombre, plan, mensualidad, activo) VALUES
('Carlos Mena', 'mensual', 30.0, true),
('Valeria Ortiz', 'trimestral', 75.0, true);

INSERT INTO clase_grupal (nombre, instructor, horario, cupos) VALUES
('Spinning', 'Andrés Paz', '07:00', 15),
('Yoga', 'Sofía Vela', '18:30', 12);

INSERT INTO maquina (nombre, marca, anio_compra, en_mantenimiento) VALUES
('Caminadora Pro', 'Life Fitness', 2018, false),
('Prensa de Piernas', 'Matrix', 2023, false);