
-- Schema employee_database
-- -----------------------------------------------------

USE `employee_database` ;

-- -----------------------------------------------------
-- Table `employee_database`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `employee_database`.`employee` (
  `emp_id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(50) NOT NULL,
  `emp_no` INT(11) NOT NULL,
  `mgr_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`emp_id`));



-- -----------------------------------------------------
-- Table `employee_database`.`manager`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `employee_database`.`manager` (
  `mgr_id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`mgr_id`));



