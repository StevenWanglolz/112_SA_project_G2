-- MySQL Script generated by MySQL Workbench
-- Fri Dec  8 22:21:07 2023
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Member`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Member` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Member` (
  `member_id` INT NOT NULL AUTO_INCREMENT,
  `member_name` VARCHAR(45) NOT NULL,
  `member_account` VARCHAR(45) NOT NULL,
  `salt` VARCHAR(45) NULL,
  `hash_pwd` VARCHAR(100) NOT NULL,
  `member_bio` VARCHAR(400) NULL,
  `is_admin` INT NOT NULL,
  `member_img_path` VARCHAR(100) NULL,
  PRIMARY KEY (`member_id`),
  UNIQUE INDEX `會員id_UNIQUE` (`member_id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `mydb`.`Member`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`Member` (`member_id`, `member_name`, `member_account`, `salt`, `hash_pwd`, `member_bio`, `is_admin`, `member_img_path`) VALUES (1, 'alan', 'a', 'a', 'a', NULL, 0, NULL);
INSERT INTO `mydb`.`Member` (`member_id`, `member_name`, `member_account`, `salt`, `hash_pwd`, `member_bio`, `is_admin`, `member_img_path`) VALUES (2, 'ben', 'b', 'b', 'b', NULL, 0, NULL);

COMMIT;

