CREATE TABLE IF NOT EXISTS `password-check`.`tb_employee` (
    `id` BINARY(16) NOT NULL,
    `name` VARCHAR(45) NOT NULL,
    `password` VARCHAR(45) NOT NULL,
    `score` INT NOT NULL,
    `level` INT NOT NULL,
    `manager_id` BINARY(16) NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    INDEX `score_index` (`score` ASC) VISIBLE,
    INDEX `fk_tb_employee_tb_employee` (`manager_id` ASC) VISIBLE,
    CONSTRAINT `fk_tb_employee_tb_employee`
    FOREIGN KEY (`manager_id`)
    REFERENCES `teste`.`tb_employee` (`id`)
    ON DELETE CASCADE)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci