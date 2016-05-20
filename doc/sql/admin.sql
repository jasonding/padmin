DROP TABLE IF EXISTS `user_role`;
DROP TABLE IF EXISTS `role_privilege`;
DROP TABLE IF EXISTS `privilege_resource`;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `role`;
DROP TABLE IF EXISTS `privilege`;
DROP TABLE IF EXISTS `menu`;
DROP TABLE IF EXISTS `resource`;

-- ----------------------------
-- Table structure for user
-- ----------------------------
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `login_time` datetime DEFAULT NULL,
  `password` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `user_name` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `enabled_ip` bit(1) NOT NULL,
  `remote_ip` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


-- ----------------------------
-- Table structure for role
-- ----------------------------

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for privilege
-- ----------------------------
CREATE TABLE `privilege` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for resource
-- ----------------------------
CREATE TABLE `resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `display_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `http_method` int(1) DEFAULT NULL,
  `resource_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `url_partten` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


-- ----------------------------
-- Table structure for menu
-- ----------------------------

CREATE TABLE `menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `orders` int(4) DEFAULT NULL,
  `url` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `parent_menu_id` bigint(20) DEFAULT NULL,
  `resource_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_MENU_PARENT_MENU_ID` (`parent_menu_id`),
  KEY `FK_MENU_RESOURCE_ID` (`resource_id`),
  CONSTRAINT `FK_MENU_PARENT_MENU_ID` FOREIGN KEY (`parent_menu_id`) REFERENCES `menu` (`id`),
  CONSTRAINT `FK_MENU_RESOURCE_ID` FOREIGN KEY (`resource_id`) REFERENCES `resource` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK_USER_ROLE_ROLE_ID` (`role_id`),
  CONSTRAINT `FK_USER_ROLE_USER_ID` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_USER_ROLE_ROLE_ID` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for role_privilege
-- ----------------------------
CREATE TABLE `role_privilege` (
  `role_id` bigint(20) NOT NULL,
  `priviege_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`priviege_id`),
  KEY `FK_ROLE_PRIVILEGE_PRIVIEGE_ID` (`priviege_id`),
  CONSTRAINT `FK_ROLE_PRIVILEGE_ROLE_ID` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FK_ROLE_PRIVILEGE_PRIVIEGE_ID` FOREIGN KEY (`priviege_id`) REFERENCES `privilege` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for privilege_resource
-- ----------------------------
CREATE TABLE `privilege_resource` (
  `priviege_id` bigint(20) NOT NULL,
  `resource_id` bigint(20) NOT NULL,
  PRIMARY KEY (`priviege_id`,`resource_id`),
  KEY `FK_PRIVILEGE_RESOURCE_RESOURCE_ID` (`resource_id`),
  CONSTRAINT `FK_PRIVILEGE_RESOURCE_PRIVIEGE_ID` FOREIGN KEY (`priviege_id`) REFERENCES `privilege` (`id`),
  CONSTRAINT `FK_PRIVILEGE_RESOURCE_RESOURCE_ID` FOREIGN KEY (`resource_id`) REFERENCES `resource` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
