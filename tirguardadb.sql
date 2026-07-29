DROP SCHEMA IF EXISTS `tiriguardadatabase` ;
CREATE SCHEMA `tiriguardadatabase` ;
USE `tiriguardadatabase`;

DROP TABLE IF EXISTS `tiriguardadatabase`.`Utente`;
CREATE TABLE `tiriguardadatabase`.`Utente`(
	`username` VARCHAR(45) NOT NULL,
    `password` CHAR(64) NOT NULL,
    `sessoBiologico` ENUM('FEMMINILE', 'MASCHILE') NOT NULL,
    `numeroTelefono` VARCHAR(50) NOT NULL,
    `protocolloAttivo` VARCHAR(45) DEFAULT NULL,
    PRIMARY KEY (`username`))
ENGINE = InnoDB;

DROP TABLE IF EXISTS `tiriguardadatabase`.`ProtocolloPrEP`;
CREATE TABLE `tiriguardadatabase`.`ProtocolloPrEP`(
	`idProtocollo` VARCHAR(45) NOT NULL,
    `utente` VARCHAR(45) NOT NULL,
    `tipoPrEP` ENUM('DAILY', 'ON_DEMAND') NOT NULL,
    `dataInizio` DATE NOT NULL,
    `statoPrEP` BOOLEAN NOT NULL DEFAULT 1,
    `dataFine` DATE,
    `ora` TIME NOT NULL,
	PRIMARY KEY (`idProtocollo`),

    CONSTRAINT `protocollo_utente`
    FOREIGN KEY (`utente`)
    REFERENCES `Utente` (`username`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

DROP TABLE IF EXISTS `tiriguardadatabase`.`Rapporto`;
CREATE TABLE `tiriguardadatabase`.`Rapporto`(
    `utente` VARCHAR(45) NOT NULL,
    `idRapporto` VARCHAR(45) NOT NULL,
    `data` DATE NOT NULL,
    `rischio` ENUM('NULLO', 'BASSO', 'ALTO') NOT NULL,
    `dataFinePeriodoFinestra` DATE,
    PRIMARY KEY (`idRapporto`),

    CONSTRAINT `rapporto_utente`
    FOREIGN KEY (`utente`)
    REFERENCES `Utente` (`username`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

DROP TABLE IF EXISTS `tiriguardadatabase`.`Test`;
CREATE TABLE `tiriguardadatabase`.`Test`(
    `utente` VARCHAR(45) NOT NULL,
    `idTest` VARCHAR(45) NOT NULL,
    `tipoTest` ENUM('PRELIEVO', 'RAPIDO') NOT NULL,
    `data` DATE NOT NULL,
    PRIMARY KEY (`idTest`),

    CONSTRAINT `test_utente`
    FOREIGN KEY (`utente`)
    REFERENCES `Utente` (`username`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

DROP TABLE IF EXISTS `tiriguardadatabase`.`Sms`;
CREATE TABLE `tiriguardadatabase`.`Sms`(
	`utente` VARCHAR(45) NOT NULL,
    `idSms` VARCHAR(45) NOT NULL,
    `testo` VARCHAR(45) NOT NULL,
    `dataSpedizione` TIMESTAMP NOT NULL,
    `stato` ENUM('DA_INVIARE', 'INVIATO','ERRORE') NOT NULL,
    `tipo` ENUM('PREP_ON', 'PREP_DAILY', 'TEST') NOT NULL,
    PRIMARY KEY (`idSms`),
    
    CONSTRAINT `sms_utente`
    FOREIGN KEY (`utente`)
    REFERENCES `Utente` (`username`)
)
ENGINE = InnoDB;


DROP USER IF EXISTS tiriguarda;
CREATE USER 'tiriguarda' IDENTIFIED BY 'password123';
GRANT ALL PRIVILEGES ON tiriguardadatabase.* TO 'tiriguarda';