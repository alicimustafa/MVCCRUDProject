-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema partydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `partydb` ;

-- -----------------------------------------------------
-- Schema partydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `partydb` DEFAULT CHARACTER SET utf8 ;
USE `partydb` ;

-- -----------------------------------------------------
-- Table `class_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `class_type` ;

CREATE TABLE IF NOT EXISTS `class_type` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `item_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `item_type` ;

CREATE TABLE IF NOT EXISTS `item_type` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `items`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `items` ;

CREATE TABLE IF NOT EXISTS `items` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `type` INT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_items_item_type_idx` (`type` ASC),
  CONSTRAINT `fk_items_item_type`
    FOREIGN KEY (`type`)
    REFERENCES `item_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `adventurer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `adventurer` ;

CREATE TABLE IF NOT EXISTS `adventurer` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `class_type` INT UNSIGNED NULL,
  `main_hand` INT UNSIGNED NULL,
  `off_hand` INT UNSIGNED NULL,
  `armor` INT UNSIGNED NULL,
  INDEX `fk_adventurere_main_hand_idx` (`main_hand` ASC),
  INDEX `fk_addventurer_class_type_idx` (`class_type` ASC),
  INDEX `fk__idx` (`off_hand` ASC),
  INDEX `fk_adventurer_item_armor_idx` (`armor` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_adventurer_item`
    FOREIGN KEY (`main_hand`)
    REFERENCES `items` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_adventurer_class_type`
    FOREIGN KEY (`class_type`)
    REFERENCES `class_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_adventurer_item_off`
    FOREIGN KEY (`off_hand`)
    REFERENCES `items` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_adventurer_item_armor`
    FOREIGN KEY (`armor`)
    REFERENCES `items` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `backpack`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `backpack` ;

CREATE TABLE IF NOT EXISTS `backpack` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `item_id` INT UNSIGNED NULL,
  `addventurer_id` INT UNSIGNED NULL,
  INDEX `fk_bakcpack_adventurer_idx` (`addventurer_id` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_bakcpack_adventurer`
    FOREIGN KEY (`addventurer_id`)
    REFERENCES `adventurer` (`class_type`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_backpack_item`
    FOREIGN KEY (`item_id`)
    REFERENCES `items` (`type`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `groups`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `groups` ;

CREATE TABLE IF NOT EXISTS `groups` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `adventurer_group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `adventurer_group` ;

CREATE TABLE IF NOT EXISTS `adventurer_group` (
  `adventurer_id` INT UNSIGNED NOT NULL,
  `group_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`adventurer_id`, `group_id`),
  INDEX `fk_adventurer_group_group_idx` (`group_id` ASC),
  CONSTRAINT `fk_adventurer_group_adventurer`
    FOREIGN KEY (`adventurer_id`)
    REFERENCES `adventurer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_adventurer_group_group`
    FOREIGN KEY (`group_id`)
    REFERENCES `groups` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
GRANT USAGE ON *.* TO gamemaster@localhost;
 DROP USER gamemaster@localhost;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'gamemaster'@'localhost' IDENTIFIED BY 'gameOn66';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'gamemaster'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `class_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `partydb`;
INSERT INTO `class_type` (`id`, `name`) VALUES (1, 'MAGE');
INSERT INTO `class_type` (`id`, `name`) VALUES (2, 'ARCHER');
INSERT INTO `class_type` (`id`, `name`) VALUES (3, 'FIGHTHER');
INSERT INTO `class_type` (`id`, `name`) VALUES (4, 'ROGUE');
INSERT INTO `class_type` (`id`, `name`) VALUES (5, 'CLERIC');

COMMIT;


-- -----------------------------------------------------
-- Data for table `item_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `partydb`;
INSERT INTO `item_type` (`id`, `name`) VALUES (1, 'Main Hand');
INSERT INTO `item_type` (`id`, `name`) VALUES (2, 'Off Hand');
INSERT INTO `item_type` (`id`, `name`) VALUES (3, 'Armor');
INSERT INTO `item_type` (`id`, `name`) VALUES (4, 'Other');

COMMIT;


-- -----------------------------------------------------
-- Data for table `items`
-- -----------------------------------------------------
START TRANSACTION;
USE `partydb`;
INSERT INTO `items` (`id`, `name`, `type`) VALUES (1, 'Sword', 1);
INSERT INTO `items` (`id`, `name`, `type`) VALUES (2, 'Axe', 1);
INSERT INTO `items` (`id`, `name`, `type`) VALUES (3, 'Spear', 1);
INSERT INTO `items` (`id`, `name`, `type`) VALUES (4, 'Mace', 1);
INSERT INTO `items` (`id`, `name`, `type`) VALUES (5, 'Bow', 1);
INSERT INTO `items` (`id`, `name`, `type`) VALUES (6, 'Staff', 1);
INSERT INTO `items` (`id`, `name`, `type`) VALUES (7, 'Dagger', 1);
INSERT INTO `items` (`id`, `name`, `type`) VALUES (8, 'Shield', 2);
INSERT INTO `items` (`id`, `name`, `type`) VALUES (9, 'Spell Book', 2);
INSERT INTO `items` (`id`, `name`, `type`) VALUES (10, 'Holy symbol', 2);
INSERT INTO `items` (`id`, `name`, `type`) VALUES (11, 'Gambeson', 3);
INSERT INTO `items` (`id`, `name`, `type`) VALUES (12, 'Leather', 3);
INSERT INTO `items` (`id`, `name`, `type`) VALUES (13, 'Chain Mail', 3);
INSERT INTO `items` (`id`, `name`, `type`) VALUES (14, 'Plate Mail', 3);
INSERT INTO `items` (`id`, `name`, `type`) VALUES (15, 'Rope', 4);
INSERT INTO `items` (`id`, `name`, `type`) VALUES (16, 'Flint and Steel', 4);
INSERT INTO `items` (`id`, `name`, `type`) VALUES (17, 'Bedding', 4);
INSERT INTO `items` (`id`, `name`, `type`) VALUES (18, 'Dry Rations', 4);
INSERT INTO `items` (`id`, `name`, `type`) VALUES (19, 'Water Skin', 4);
INSERT INTO `items` (`id`, `name`, `type`) VALUES (20, 'Dagger', 2);
INSERT INTO `items` (`id`, `name`, `type`) VALUES (21, 'Robes', 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `adventurer`
-- -----------------------------------------------------
START TRANSACTION;
USE `partydb`;
INSERT INTO `adventurer` (`id`, `name`, `class_type`, `main_hand`, `off_hand`, `armor`) VALUES (1, 'Gunther', 3, 2, 8, 13);
INSERT INTO `adventurer` (`id`, `name`, `class_type`, `main_hand`, `off_hand`, `armor`) VALUES (2, 'Lycia', 1, 6, 9, 21);
INSERT INTO `adventurer` (`id`, `name`, `class_type`, `main_hand`, `off_hand`, `armor`) VALUES (3, 'Dormus', 2, 5, 20, 11);
INSERT INTO `adventurer` (`id`, `name`, `class_type`, `main_hand`, `off_hand`, `armor`) VALUES (4, 'Talafane', 4, 7, 20, 12);
INSERT INTO `adventurer` (`id`, `name`, `class_type`, `main_hand`, `off_hand`, `armor`) VALUES (5, 'Loral', 5, 4, 10, 13);
INSERT INTO `adventurer` (`id`, `name`, `class_type`, `main_hand`, `off_hand`, `armor`) VALUES (6, 'Valcon', 3, 1, 8, 14);
INSERT INTO `adventurer` (`id`, `name`, `class_type`, `main_hand`, `off_hand`, `armor`) VALUES (7, 'Fhaga', 1, 6, 9, 21);
INSERT INTO `adventurer` (`id`, `name`, `class_type`, `main_hand`, `off_hand`, `armor`) VALUES (8, 'Marris', 2, 5, 20, 12);
INSERT INTO `adventurer` (`id`, `name`, `class_type`, `main_hand`, `off_hand`, `armor`) VALUES (9, 'Bairel', 4, 7, 20, 11);
INSERT INTO `adventurer` (`id`, `name`, `class_type`, `main_hand`, `off_hand`, `armor`) VALUES (10, 'Thormund', 5, 1, 10, 14);

COMMIT;


-- -----------------------------------------------------
-- Data for table `groups`
-- -----------------------------------------------------
START TRANSACTION;
USE `partydb`;
INSERT INTO `groups` (`id`, `name`) VALUES (1, 'current');
INSERT INTO `groups` (`id`, `name`) VALUES (2, 'pool');

COMMIT;


-- -----------------------------------------------------
-- Data for table `adventurer_group`
-- -----------------------------------------------------
START TRANSACTION;
USE `partydb`;
INSERT INTO `adventurer_group` (`adventurer_id`, `group_id`) VALUES (1, 1);
INSERT INTO `adventurer_group` (`adventurer_id`, `group_id`) VALUES (2, 1);
INSERT INTO `adventurer_group` (`adventurer_id`, `group_id`) VALUES (3, 1);
INSERT INTO `adventurer_group` (`adventurer_id`, `group_id`) VALUES (4, 1);
INSERT INTO `adventurer_group` (`adventurer_id`, `group_id`) VALUES (5, 1);
INSERT INTO `adventurer_group` (`adventurer_id`, `group_id`) VALUES (6, 2);
INSERT INTO `adventurer_group` (`adventurer_id`, `group_id`) VALUES (7, 2);
INSERT INTO `adventurer_group` (`adventurer_id`, `group_id`) VALUES (8, 2);
INSERT INTO `adventurer_group` (`adventurer_id`, `group_id`) VALUES (9, 2);
INSERT INTO `adventurer_group` (`adventurer_id`, `group_id`) VALUES (10, 2);

COMMIT;

