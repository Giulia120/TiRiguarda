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

DROP TABLE IF EXISTS `tiriguardadatabase`.`ProtocolloPrEP`;
CREATE TABLE `tiriguardadatabase`.`ProtocolloPrEP`(
	`idProtocollo` VARCHAR(45) NOT NULL,
    `username` VARCHAR(45) NOT NULL,
    `tipoPrEP` ENUM('Daily', 'On_Demand') NOT NULL,
    `dataInizio` DATE NOT NULL,
    `statoPrEP` BOOLEAN NOT NULL DEFAULT 1,
    `dataFine` TIME,
    PRIMARY KEY (`idProtocollo`))
ENGINE = InnoDB;

DROP TABLE IF EXISTS `tiriguardadatabase`.`Rappporto`;
CREATE TABLE `tiriguardadatabase`.`ProtocolloPrEP`(
	`idProtocollo` VARCHAR(45) NOT NULL,
    `username` VARCHAR(45) NOT NULL,
    `tipoPrEP` ENUM('Daily', 'On_Demand') NOT NULL,
    `dataInizio` DATE NOT NULL,
    `statoPrEP` BOOLEAN NOT NULL DEFAULT 1,
    `dataFine` TIME,
    PRIMARY KEY (`idProtocollo`))
ENGINE = InnoDB;




