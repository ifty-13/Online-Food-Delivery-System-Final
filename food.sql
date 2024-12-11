-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema food
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema food
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `food` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `food` ;

-- -----------------------------------------------------
-- Table `food`.`american`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `food`.`american` (
  `AR_id` INT NOT NULL AUTO_INCREMENT,
  `AR_name` VARCHAR(50) NOT NULL,
  `AR_price` DECIMAL(15,2) NOT NULL,
  PRIMARY KEY (`AR_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO food.american (AR_name, AR_price) VALUES
('Apple Pie', 16.00),
('Cheeseburger', 7.25),
('Grilled Salmon and Brown Butter Couscous', 50.25),
('Lobster Rolls', 119.25),
('Buffalo Wings 12 pcs', 35.97),
('Chicago Deep Dish Pizza', 20.00),
('Chicken Fried Steak with Milk Gravy', 50.25),
('Macroni and Cheese', 4.25),
('Peanut Butter Sandwich', 10.14),
('Barbecue Ribs 4 pcs', 80.00);


-- -----------------------------------------------------
-- Table `food`.`card_details`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `food`.`card_details` (
  `holder` VARCHAR(30) NOT NULL,
  `card_no` VARCHAR(20) NOT NULL,
  `card_type` VARCHAR(20) NOT NULL,
  `cvv` CHAR(4) NOT NULL,
  `exp` VARCHAR(20) NOT NULL,
  `country` VARCHAR(20) NULL DEFAULT NULL,
  `balance` FLOAT NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `food`.`details`
-- -----------------------------------------------------

-- DROP TABLE details;

CREATE TABLE IF NOT EXISTS `food`.`details` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(20) NOT NULL UNIQUE,
  `passwd` VARCHAR(20) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `firstname` VARCHAR(20) NULL DEFAULT NULL,
  `lastname` VARCHAR(20) NULL DEFAULT NULL,
  `phone` VARCHAR(10) NULL DEFAULT NULL,
  `state` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO `food`.`details` (user_id, username, passwd, email) VALUES (1, 'admin', 'admin', 'admin@gmail.com');
INSERT INTO `food`.`details` (user_id, username, passwd, email) VALUES (2, 'manager', 'manager', 'manager@gmail.com');
INSERT INTO `food`.`details` VALUES (3, 'rishijoshi25', 'rishijoshi', 'rishijoshi25@outlook.com', 'Rishi', 'Joshi', '8654789070', 'NY');
-- -----------------------------------------------------
-- Table `food`.`french`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `food`.`french` (
  `FR_id` INT NOT NULL AUTO_INCREMENT,
  `FR_name` VARCHAR(50) NOT NULL,
  `FR_price` DECIMAL(15,2) NOT NULL,
  PRIMARY KEY (`FR_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO `food`.`french` (FR_name, FR_price) VALUES
('Soupe à l’oignon ', 10.25),
('Butter Croissant', 1.75),
('Coq au vin', 25.85),
('Cassoulet', 18.99),
('Boeuf bourguignon', 45.95),
('Chocolate soufflé', 12.99),
('Flamiche', 13.25),
('Confit de canard', 55.75),
('Salade Niçoise', 15.25),
('Ratatouille', 20.25),
('Tarte Tatin', 15.75);

-- -----------------------------------------------------
-- Table `food`.`indian`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `food`.`indian` (
  `IND_id` INT NOT NULL AUTO_INCREMENT,
  `IND_name` VARCHAR(50) NOT NULL,
  `IND_price` DECIMAL(15,2) NOT NULL,
  PRIMARY KEY (`IND_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO `food`.`indian` (IND_name, IND_price) VALUES
('Murg Makhni', 20.55),
('Tandoori Chicken', 20.45),
('Malai Kofta', 18.10),
('Veg Kolhapuri', 15.10),
('Cheese Vada Pav', 2.20),
('Biryani', 15.25),
('Paneer Tikka Masala', 20.25),
('Butter Naan', 1.15),
('Masala Papad', 0.30),
('Chai', 2.0);

-- -----------------------------------------------------
-- Table `food`.`italian`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `food`.`italian` (
  `ITA_id` INT NOT NULL AUTO_INCREMENT,
  `ITA_name` VARCHAR(100) NOT NULL,
  `ITA_price` DECIMAL(15,2) NOT NULL,
  PRIMARY KEY (`ITA_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO `food`.`italian` (ITA_name, ITA_price) VALUES
('Caprese Salad with Pesto Sauce', 12.95),
('Panzenella', 15.95),
('Bruschetta', 3.25),
('Focaccia Bread', 6.25),
('Pasta Carbonara', 12.00),
('Margherita Pizza', 15.75),
('Mushroom Risotto', 30.00),
('Pasta Con Pomodoro E Basilico', 25.65),
('Tiramisu', 20.00),
('Lasagna', 55.75),
('Pistachio Panna Cotta', 10.15),
('Panettone', 7.95);

-- -----------------------------------------------------
-- Table `food`.`cart`
-- -----------------------------------------------------

-- DROP TABLE food.cart;

CREATE TABLE IF NOT EXISTS `food`.`cart`(
	`item_id` INT NOT NULL AUTO_INCREMENT,
    `item_name` VARCHAR(40),
    `item_price` DECIMAL(15,2),
    `item_quantity` INT,
    PRIMARY KEY (`item_id`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;
;

-- -----------------------------------------------------
-- Table `food`.`orders`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `food`.`orders` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(40) NOT NULL,
  `total_items` INT NOT NULL DEFAULT '1',
  `amount` DECIMAL(15,3) NOT NULL,
  `order_status` ENUM('Processed', 'Delivered') NULL DEFAULT NULL,
  `time_stamp` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`order_id`),
  INDEX `username` (`username` ASC) VISIBLE,
  CONSTRAINT `orders_ibfk_1`
    FOREIGN KEY (`username`)
    REFERENCES `food`.`details` (`username`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `food`.`payment`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `food`.`payment` (
  `payment_id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(20) NOT NULL,
  `card_type` VARCHAR(20) NOT NULL,
  `amount_spent` DECIMAL(15,3) NOT NULL,
  `time_stamp` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`payment_id`),
  INDEX `username` (`username` ASC) VISIBLE,
  CONSTRAINT `payment_ibfk_1`
    FOREIGN KEY (`username`)
    REFERENCES `food`.`details` (`username`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO `food`.`payment` (user_id, amount) VALUES (7, 1000);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
