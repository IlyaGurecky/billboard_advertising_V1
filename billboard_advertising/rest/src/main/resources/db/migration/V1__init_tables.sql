CREATE TABLE IF NOT EXISTS `users`
(
    `id`       INT          NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(255) NOT NULL UNIQUE,
    `role`     VARCHAR(10) check (`role` in ('ADMIN', 'DEVICE_OWNER')),
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `log`
(
    `id`      INT      NOT NULL AUTO_INCREMENT,
    `time`    DATETIME NOT NULL,
    `content` VARCHAR(255),
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `device_group`
(
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `schedule`
(
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `frequency` INT NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `device`
(
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)

);

CREATE TABLE IF NOT EXISTS `advertising`
(
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `content_path` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `advertising_statistic`
(
    `id` INT NOT NULL AUTO_INCREMENT,
    `time` DATETIME,
    `content` VARCHAR(255),
    PRIMARY KEY (`id`)
);
