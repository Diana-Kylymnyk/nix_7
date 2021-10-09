SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE SCHEMA IF NOT EXISTS `unit_10_db` DEFAULT CHARACTER SET utf8 ;
USE `unit_10_db` ;

CREATE TABLE IF NOT EXISTS `unit_10_db`.`locations` (
                                                        `id` INT NOT NULL,
                                                        `name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `locationscol_UNIQUE` (`name` ASC) VISIBLE)
    ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `unit_10_db`.`routes` (
                                                     `id` INT NOT NULL,
                                                     `from_id` INT NOT NULL,
                                                     `to_id` INT NOT NULL,
                                                     `cost` INT UNSIGNED NULL,
                                                     PRIMARY KEY (`id`),
    INDEX `fk_routes_locations_idx` (`from_id` ASC) VISIBLE,
    INDEX `fk_routes_locations1_idx` (`to_id` ASC) VISIBLE,
    CONSTRAINT `fk_routes_locations`
    FOREIGN KEY (`from_id`)
    REFERENCES `unit_10_db`.`locations` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_routes_locations1`
    FOREIGN KEY (`to_id`)
    REFERENCES `unit_10_db`.`locations` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `unit_10_db`.`problems` (
                                                       `id` INT NOT NULL,
                                                       `from_id` INT NOT NULL,
                                                       `to_id` INT NOT NULL,
                                                       PRIMARY KEY (`id`),
    INDEX `fk_problems_locations1_idx` (`from_id` ASC) VISIBLE,
    INDEX `fk_problems_locations2_idx` (`to_id` ASC) VISIBLE,
    CONSTRAINT `fk_problems_locations1`
    FOREIGN KEY (`from_id`)
    REFERENCES `unit_10_db`.`locations` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_problems_locations2`
    FOREIGN KEY (`to_id`)
    REFERENCES `unit_10_db`.`locations` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `unit_10_db`.`solutions` (
                                                        `problem_id` INT NOT NULL,
                                                        `cost` INT NULL,
                                                        PRIMARY KEY (`problem_id`),
    INDEX `fk_solutions_problems1_idx` (`problem_id` ASC) VISIBLE,
    CONSTRAINT `fk_solutions_problems1`
    FOREIGN KEY (`problem_id`)
    REFERENCES `unit_10_db`.`problems` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO unit_10_db.locations (id, name) VALUES (1, 'Paris');
INSERT INTO unit_10_db.locations (id, name) VALUES (2, 'New York');
INSERT INTO unit_10_db.locations (id, name) VALUES (3, 'Rome');
INSERT INTO unit_10_db.locations (id, name) VALUES (4, 'Berlin');

INSERT INTO unit_10_db.problems (id, from_id, to_id) VALUES (1, 1, 4);
INSERT INTO unit_10_db.problems (id, from_id, to_id) VALUES (2, 2, 1);
INSERT INTO unit_10_db.problems (id, from_id, to_id) VALUES (3, 3, 2);

INSERT INTO unit_10_db.routes (id, from_id, to_id, cost) VALUES (1, 1, 2, 77);
INSERT INTO unit_10_db.routes (id, from_id, to_id, cost) VALUES (2, 1, 3, 50);
INSERT INTO unit_10_db.routes (id, from_id, to_id, cost) VALUES (3, 2, 3, 30);
INSERT INTO unit_10_db.routes (id, from_id, to_id, cost) VALUES (4, 3, 4, 66);
INSERT INTO unit_10_db.routes (id, from_id, to_id, cost) VALUES (5, 1, 4, 90);