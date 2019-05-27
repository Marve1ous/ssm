/* Initialize Database */
create database ssm;
use ssm;

CREATE TABLE `User`
(
    `uuid` varchar(36) NOT NULL,
    `id`   int(11)     NOT NULL,
    `name` varchar(20) NOT NULL,
    `pwd`  varchar(20) NOT NULL,
    PRIMARY KEY (`uuid`),
    KEY `User_id_index` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `Role`
(
    `id`       int(11)     NOT NULL,
    `rolename` varchar(20) NOT NULL,
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

INSERT INTO User VALUES ('30709c48-b269-4a6c-a510-800c4101a019',20190001,'张三','123456');
INSERT INTO User VALUES ('f0a24f81-e30b-442e-a46e-089d8b2d4dcc',20190002,'李四','123456');
INSERT INTO Role VALUES (1001,'admin');
INSERT INTO Role VALUES (1002,'customer');
INSERT INTO UserRole VALUES (20190001,1001);
INSERT INTO UserRole VALUES (20190002,1002);
INSERT INTO Permission VALUES (2001,'search');
INSERT INTO Permission VALUES (2002,'searchAll');
INSERT INTO Permission VALUES (2003,'insert');
INSERT INTO Permission VALUES (2004,'update');
INSERT INTO Permission VALUES (2005,'delete');
INSERT INTO PermissionRole VALUES (2001,1001);
INSERT INTO PermissionRole VALUES (2001,1002);
INSERT INTO PermissionRole VALUES (2002,1001);
INSERT INTO PermissionRole VALUES (2003,1001);
INSERT INTO PermissionRole VALUES (2004,1001);
INSERT INTO PermissionRole VALUES (2005,1001);

