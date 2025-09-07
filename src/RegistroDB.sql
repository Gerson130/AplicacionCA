DROP DATABASE IF EXISTS CA;

CREATE DATABASE IF NOT EXISTS CA;
USE CA;

-- Tabla Usuario
CREATE TABLE IF NOT EXISTS `Usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `rut` VARCHAR(13) NOT NULL,
  `nombre` VARCHAR(50) NOT NULL,
  `cargo` VARCHAR(30) NOT NULL,
  `area` VARCHAR(30) NOT NULL,
  `clave` VARCHAR(10) NOT NULL,
  `correo` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `rut_UNIQUE` (`rut` ASC),
  UNIQUE INDEX `correo_UNIQUE` (`correo` ASC)
) ENGINE = InnoDB;

-- Tabla Asistencia
CREATE TABLE IF NOT EXISTS `Asistencia` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idUsuario` INT NOT NULL,
  `accion` VARCHAR(20) NOT NULL,
  `fecha` DATE NOT NULL,
  `hora` TIME NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_asistencia_usuario`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `Usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  INDEX `idUsuario_idx` (`idUsuario` ASC)
) ENGINE = InnoDB;

-- Usuario de prueba
INSERT INTO Usuario (rut, nombre, cargo, area, clave, correo) 
VALUES ('12.345.678-9', 'Erwin Huilcapan', 'Administrador', 'TI', '123456', 'e.huilcapan@ca.com');

-- Consultas de verificación
SELECT * FROM Usuario;
SELECT * FROM Asistencia;