-- Création de la base de données

CREATE DATABASE Catalogue CHARACTER SET 'utf8';
  
  CREATE TABLE `catalogue`.`formation` (
  `code` CHAR(4) NOT NULL,
  `intitule` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`code`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC));
  
  CREATE TABLE `catalogue`.`lieu` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `ville` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `d_UNIQUE` (`id` ASC));
  
  CREATE TABLE `catalogue`.`session` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `debut` DATETIME NOT NULL,
  `fin` DATETIME NOT NULL,
  `capacite` INT NULL,
  `formation_code` CHAR(4) NOT NULL,
  `lieu_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_formation_code_idx` (`formation_code` ASC),
  INDEX `fk_lieu_id_idx` (`lieu_id` ASC),
  CONSTRAINT `fk_formation_code`
    FOREIGN KEY (`formation_code`)
    REFERENCES `catalogue`.`formation` (`code`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_lieu_id`
    FOREIGN KEY (`lieu_id`)
    REFERENCES `catalogue`.`lieu` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
	
	CREATE TABLE `catalogue`.`utilisateur` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nom` VARCHAR(45) NOT NULL,
  `prenom` VARCHAR(45) NOT NULL,
  `adresse` VARCHAR(45) NOT NULL,
  `telephone` VARCHAR(10) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `session_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_session_id_idx` (`session_id` ASC),
  CONSTRAINT `fk_session_id`
    FOREIGN KEY (`session_id`)
    REFERENCES `catalogue`.`session` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


-- Création de l'utilisateur lo54
CREATE USER lo54 IDENTIFIED BY 'lo54';
GRANT SELECT, INSERT, UPDATE, DELETE ON catalogue.* TO lo54;

