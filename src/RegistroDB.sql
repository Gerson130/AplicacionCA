CREATE DATABASE IF NOT EXISTS CA;
USE CA;
CREATE TABLE IF NOT EXISTS `Usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `rut` VARCHAR(13) NOT NULL,
  `nombre` VARCHAR(50) NOT NULL,
  `cargo` VARCHAR(30) NOT NULL,
  `area` VARCHAR(30) NOT NULL,
  `clave` VARCHAR(10) NOT NULL,
  `correo` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `rut_UNIQUE` ON `Usuario` (`rut` ASC) VISIBLE;

CREATE UNIQUE INDEX `correo_UNIQUE` ON `Usuario` (`correo` ASC) VISIBLE;

CREATE UNIQUE INDEX `id_UNIQUE` ON `Usuario` (`id` ASC) VISIBLE;

-- -----------------------------------------------------
-- Table `mydb`.`Asistencia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Asistencia` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idUsuario` INT NOT NULL,
  `accion` VARCHAR(20) NOT NULL,
  `fecha` DATE NOT NULL,
  `hora` TIME NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `id`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `mydb`.`Usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `id_idx` ON `mydb`.`Asistencia` (`idUsuario` ASC) VISIBLE;

CREATE UNIQUE INDEX `id_UNIQUE` ON `mydb`.`Asistencia` (`id` ASC) VISIBLE;

CREATE UNIQUE INDEX `idUsuario_UNIQUE` ON `mydb`.`Asistencia` (`idUsuario` ASC) VISIBLE;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;