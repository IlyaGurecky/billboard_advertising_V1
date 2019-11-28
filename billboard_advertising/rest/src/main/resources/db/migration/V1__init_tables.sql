create database if not exists billboards_ad;

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
    `user_id` INT      NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `device_group`
(
    `id`      INT          NOT NULL AUTO_INCREMENT,
    `name`    VARCHAR(255) NOT NULL,
    `user_id` INT          NOT NULL,
    UNIQUE (`name`, `user_id`),
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `schedule`
(
    `id`        INT          NOT NULL AUTO_INCREMENT,
    `name`      VARCHAR(255) NOT NULL,
    `frequency` INT          NOT NULL,
    `user_id`   INT          NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `device`
(
    `id`              INT          NOT NULL AUTO_INCREMENT,
    `name`            VARCHAR(255) NOT NULL,
    `device_group_id` INT,
    `user_id`         INT          NOT NULL,
    `schedule_id`     INT,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `advertising`
(
    `id`           INT          NOT NULL AUTO_INCREMENT,
    `name`         VARCHAR(255) NOT NULL,
    `content_path` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `advertising_statistic`
(
    `id`             INT NOT NULL AUTO_INCREMENT,
    `time`           DATETIME,
    `content`        VARCHAR(255),
    `advertising_id` INT NOT NULL,
    PRIMARY KEY (`id`)
);

ALTER TABLE `log`
    ADD CONSTRAINT `log_fk0` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `device_group`
    ADD CONSTRAINT `device_group_fk0` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `schedule`
    ADD CONSTRAINT `schedule_fk0` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `device`
    ADD CONSTRAINT `device_fk0` FOREIGN KEY (`device_group_id`) REFERENCES `device_group` (`id`);

ALTER TABLE `device`
    ADD CONSTRAINT `device_fk1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `device`
    ADD CONSTRAINT `device_fk2` FOREIGN KEY (`schedule_id`) REFERENCES `schedule` (`id`);

ALTER TABLE `advertising_statistic`
    ADD CONSTRAINT `advertising_statistic_fk0` FOREIGN KEY (`advertising_id`) REFERENCES `advertising` (`id`);