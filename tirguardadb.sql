DROP SCHEMA IF EXISTS `tiriguardadatabase` ;
CREATE SCHEMA `tiriguardadatabase` ;
USE `tiriguardadatabase`;

DROP TABLE IF EXISTS `tiriguardadatabase`.`Utente`;
CREATE TABLE `tiriguardadatabase`.`Utente`(
	`username` VARCHAR(45) NOT NULL,
    `password` CHAR(64) NOT NULL,
    `sessoBiologico` ENUM('Femminile', 'Maschile') NOT NULL,
    `numeroTelefono` VARCHAR(50) NOT NULL,
    `protocolloAttivo` VARCHAR(45) DEFAULT NULL,
    PRIMARY KEY (`username`))
ENGINE = InnoDB;

DROP TABLE IF EXISTS `tiriguardadatabase`.`ProtocolloPrEP`;
CREATE TABLE `tiriguardadatabase`.`ProtocolloPrEP`(
	`idProtocollo` VARCHAR(45) NOT NULL,
    `utente` VARCHAR(45) NOT NULL,
    `tipoPrEP` ENUM('Daily', 'On_Demand') NOT NULL,
    `dataInizio` DATE NOT NULL,
    `statoPrEP` BOOLEAN NOT NULL DEFAULT 1,
    `dataFine` DATE,
    PRIMARY KEY (`idProtocollo`))
ENGINE = InnoDB;

DROP TABLE IF EXISTS `tiriguardadatabase`.`Rapporto`;
CREATE TABLE `tiriguardadatabase`.`Rapporto`(
    `utente` VARCHAR(45) NOT NULL,
    `idRapporto` VARCHAR(45) NOT NULL,
    `data` DATE NOT NULL,
    `rischio` ENUM('Nullo', 'Basso', 'Alto') NOT NULL,
    `dataFinePeriodoFinestra` DATE,
    PRIMARY KEY (`idRapporto`))
ENGINE = InnoDB;

DROP TABLE IF EXISTS `tiriguardadatabase`.`Test`;
CREATE TABLE `tiriguardadatabase`.`Test`(
    `utente` VARCHAR(45) NOT NULL,
    `idTest` VARCHAR(45) NOT NULL,
    `tipoTest` ENUM('Prelievo', 'Rapido') NOT NULL,
    `data` DATE NOT NULL,
    PRIMARY KEY (`idTest`))
ENGINE = InnoDB;


DROP USER IF EXISTS tiriguarda;
CREATE USER 'tiriguarda' IDENTIFIED BY 'password123';
GRANT ALL PRIVILEGES ON tiriguardadatabase.* TO 'tiriguarda';
FLUSH PRIVILEGES;



