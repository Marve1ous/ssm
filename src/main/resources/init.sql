create database ssm;
use ssm;

CREATE TABLE `User`
(
    `uuid` varchar(36) CHARACTER SET latin1 NOT NULL,
    `id`   int(11)                          NOT NULL,
    `name` varchar(20) CHARACTER SET latin1 NOT NULL,
    `pwd`  varchar(20) CHARACTER SET latin1 NOT NULL,
    PRIMARY KEY (`uuid`),
    KEY `User_id_index` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `Role`
(
    `id`       int(11)                          NOT NULL,
    `rolename` varchar(20) CHARACTER SET latin1 NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `UserRole`
(
    `user_id` int(11) NOT NULL,
    `role_id` int(11) NOT NULL,
    KEY `UserRole_Role_id_fk` (`role_id`),
    KEY `UserRole_User_id_fk` (`user_id`),
    CONSTRAINT `UserRole_Role_id_fk` FOREIGN KEY (`role_id`) REFERENCES `Role` (`id`),
    CONSTRAINT `UserRole_User_id_fk` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `Permission`
(
    `id`   int(11)     NOT NULL,
    `name` varchar(20) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `PermissionRole`
(
    `permission_id` int(11) NOT NULL,
    `role_id`       int(11) NOT NULL,
    KEY `PermissionRole_Permission_id_fk` (`permission_id`),
    KEY `PermissionRole_Role_id_fk` (`role_id`),
    CONSTRAINT `PermissionRole_Permission_id_fk` FOREIGN KEY (`permission_id`) REFERENCES `Permission` (`id`),
    CONSTRAINT `PermissionRole_Role_id_fk` FOREIGN KEY (`role_id`) REFERENCES `Role` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

