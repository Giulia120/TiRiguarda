DROP SCHEMA IF EXISTS `tiriguardadatabase` ;
CREATE SCHEMA `tiriguardadatabase` ;
USE `tiriguardadatabase`;

DROP TABLE IF EXISTS `tiriguardadatabase`.`Utente`;
CREATE TABLE `tiriguardadatabase`.`Utente`(
	`username` VARCHAR(45) NOT NULL,
    `password` CHAR(32) NOT NULL,
    `sessoBiologico` ENUM('Femminile', 'Maschile') NOT NULL,
    `numeroTelefono` VARCHAR(50) NOT NULL,
    `protocolloAttivo` VARCHAR(45) DEFAULT NULL,
    PRIMARY KEY (`username`))
ENGINE = InnoDB;




