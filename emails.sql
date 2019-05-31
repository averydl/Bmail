DROP TABLE `users`;
DROP TABLE `emails`;

CREATE SCHEMA IF NOT EXISTS `email_database`;

CREATE TABLE IF NOT EXISTS `email_database`.`users` (
	`username` VARCHAR(100),
    `password` VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS `email_database`.`emails` (
	`ownerId` VARCHAR(100),
    `senderId` VARCHAR(100),
    `subject` VARCHAR(100),
    `body` TEXT
);