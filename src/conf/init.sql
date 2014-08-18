SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`account`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`account` ;

CREATE TABLE IF NOT EXISTS `mydb`.`account` (
  `idaccount` INT NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idaccount`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`state`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`state` ;

CREATE TABLE IF NOT EXISTS `mydb`.`state` (
  `idstate` INT NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idstate`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`method`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`method` ;

CREATE TABLE IF NOT EXISTS `mydb`.`method` (
  `idmethod` INT NOT NULL,
  `title` VARCHAR(45) NULL,
  PRIMARY KEY (`idmethod`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`invoice`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`invoice` ;

CREATE TABLE IF NOT EXISTS `mydb`.`invoice` (
  `idinvoice` INT NOT NULL,
  `account_idaccount` INT NOT NULL,
  `state_idstate` INT NOT NULL,
  `created` DATE NOT NULL,
  `method_idmethod` INT NOT NULL,
  `due` DATE NOT NULL,
  `duzp` DATE NULL,
  `variablesymbol` INT NULL,
  `constantsymbol` INT NULL,
  `specificsymbol` VARCHAR(45) NULL,
  PRIMARY KEY (`idinvoice`, `account_idaccount`, `state_idstate`, `method_idmethod`),
  INDEX `fk_invoice_account1_idx` (`account_idaccount` ASC),
  INDEX `fk_invoice_state1_idx` (`state_idstate` ASC),
  INDEX `fk_invoice_method1_idx` (`method_idmethod` ASC),
  CONSTRAINT `fk_invoice_account1`
    FOREIGN KEY (`account_idaccount`)
    REFERENCES `mydb`.`account` (`idaccount`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_invoice_state1`
    FOREIGN KEY (`state_idstate`)
    REFERENCES `mydb`.`state` (`idstate`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_invoice_method1`
    FOREIGN KEY (`method_idmethod`)
    REFERENCES `mydb`.`method` (`idmethod`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`person`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`person` ;

CREATE TABLE IF NOT EXISTS `mydb`.`person` (
  `idperson` INT NOT NULL,
  `account_idaccount` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `street` VARCHAR(45) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `pcode` INT NOT NULL,
  `state` VARCHAR(45) NOT NULL,
  `owner` VARCHAR(45) NOT NULL,
  `telefon` INT NULL,
  `email` VARCHAR(45) NULL,
  `fax` INT NULL,
  `www` VARCHAR(45) NULL,
  `bankaccount` VARCHAR(45) NULL,
  `ico` VARCHAR(45) NULL,
  `dic` VARCHAR(45) NULL,
  PRIMARY KEY (`idperson`, `account_idaccount`),
  INDEX `fk_person_account_idx` (`account_idaccount` ASC),
  CONSTRAINT `fk_person_account`
    FOREIGN KEY (`account_idaccount`)
    REFERENCES `mydb`.`account` (`idaccount`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `mydb`.`rate`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`rate` ;

CREATE TABLE IF NOT EXISTS `mydb`.`rate` (
  `idrate` INT NOT NULL,
  `title` VARCHAR(45) NULL,
  `value` VARCHAR(45) NULL,
  PRIMARY KEY (`idrate`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`item` ;

CREATE TABLE IF NOT EXISTS `mydb`.`item` (
  `iditem` INT NOT NULL,
  `account_idaccount` INT NOT NULL,
  `rate_idrate` INT NOT NULL,
  `title` VARCHAR(45) NULL,
  `price` INT NULL,
  PRIMARY KEY (`iditem`, `account_idaccount`, `rate_idrate`),
  INDEX `fk_item_account1_idx` (`account_idaccount` ASC),
  INDEX `fk_item_rate1_idx` (`rate_idrate` ASC),
  CONSTRAINT `fk_item_account1`
    FOREIGN KEY (`account_idaccount`)
    REFERENCES `mydb`.`account` (`idaccount`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_item_rate1`
    FOREIGN KEY (`rate_idrate`)
    REFERENCES `mydb`.`rate` (`idrate`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`invoice_has_person`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`invoice_has_person` ;

CREATE TABLE IF NOT EXISTS `mydb`.`invoice_has_person` (
  `invoice_idinvoice` INT NOT NULL,
  `person_idperson` INT NOT NULL,
  `payer` VARCHAR(45) NULL,
  PRIMARY KEY (`invoice_idinvoice`, `person_idperson`),
  INDEX `fk_invoice_has_person1_person1_idx` (`person_idperson` ASC),
  INDEX `fk_invoice_has_person1_invoice1_idx` (`invoice_idinvoice` ASC),
  CONSTRAINT `fk_invoice_has_person1_invoice1`
    FOREIGN KEY (`invoice_idinvoice`)
    REFERENCES `mydb`.`invoice` (`idinvoice`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_invoice_has_person1_person1`
    FOREIGN KEY (`person_idperson`)
    REFERENCES `mydb`.`person` (`idperson`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`invoice_has_item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`invoice_has_item` ;

CREATE TABLE IF NOT EXISTS `mydb`.`invoice_has_item` (
  `invoice_idinvoice` INT NOT NULL,
  `item_iditem` INT NOT NULL,
  `count` VARCHAR(45) NOT NULL,
  `code` VARCHAR(45) NULL,
  PRIMARY KEY (`invoice_idinvoice`, `item_iditem`),
  INDEX `fk_invoice_has_item_item1_idx` (`item_iditem` ASC),
  INDEX `fk_invoice_has_item_invoice1_idx` (`invoice_idinvoice` ASC),
  CONSTRAINT `fk_invoice_has_item_invoice1`
    FOREIGN KEY (`invoice_idinvoice`)
    REFERENCES `mydb`.`invoice` (`idinvoice`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_invoice_has_item_item1`
    FOREIGN KEY (`item_iditem`)
    REFERENCES `mydb`.`item` (`iditem`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
