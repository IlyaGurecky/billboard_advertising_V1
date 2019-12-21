CREATE TABLE IF NOT EXISTS `users`
(
    `id`       INT          NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(255) NOT NULL UNIQUE,
    `role`     VARCHAR(20) check (`role` in ('ADMIN', 'DEVICE_OWNER')),
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `log`
(
    `id`      INT      NOT NULL AUTO_INCREMENT,
    `content` VARCHAR(255),
    `user_id` INT,
    FOREIGN KEY (`user_id`) REFERENCES users (`id`) ON DELETE CASCADE,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `device_group`
(
    `id`      INT          NOT NULL AUTO_INCREMENT,
    `name`    VARCHAR(255) NOT NULL,
    `user_id` INT          NOT NULL,
    UNIQUE (`name`, `user_id`),
    FOREIGN KEY (`user_id`) REFERENCES users (`id`) ON DELETE CASCADE,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `schedule`
(
    `id`        INT          NOT NULL AUTO_INCREMENT,
    `name`      VARCHAR(255) NOT NULL,
    `frequency` INT          NOT NULL,
    `user_id`   INT          NOT NULL,
    FOREIGN KEY (`user_id`) REFERENCES users (`id`) ON DELETE CASCADE,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `device`
(
    `id`              INT          NOT NULL AUTO_INCREMENT,
    `name`            VARCHAR(255) NOT NULL,
    `device_group_id` INT,
    `user_id`         INT          NOT NULL,
    `schedule_id`     INT,
    FOREIGN KEY (`device_group_id`) REFERENCES device_group (`id`),
    FOREIGN KEY (`user_id`) REFERENCES users (`id`) ON DELETE CASCADE,
    FOREIGN KEY (`schedule_id`) REFERENCES schedule (`id`),
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `advertising`
(
    `id`           INT          NOT NULL AUTO_INCREMENT,
    `name`         VARCHAR(255) NOT NULL,
    `content_path` VARCHAR(255) NOT NULL,
    `user_id`      INT,
    UNIQUE (`name`, `user_id`),
    FOREIGN KEY (`user_id`) REFERENCES users (`id`) ON DELETE CASCADE,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `advertising_statistic`
(
    `id`             INT NOT NULL AUTO_INCREMENT,
    `time`           DATETIME,
    `content`        VARCHAR(255),
    `advertising_id` INT NOT NULL,
    FOREIGN KEY (`advertising_id`) REFERENCES advertising (`id`) ON DELETE CASCADE,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `schedule_ad`
(
    `id`             INT NOT NULL AUTO_INCREMENT,
    `schedule_id`    INT NOT NULL,
    `advertising_id` INT NOT NULL,
    FOREIGN KEY (`schedule_id`) REFERENCES schedule (`id`) ON DELETE CASCADE,
    FOREIGN KEY (`advertising_id`) REFERENCES advertising (`id`) ON DELETE CASCADE,
    PRIMARY KEY (`id`)
);
