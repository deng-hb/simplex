# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.6.46)
# Database: simplex
# Generation Time: 2019-11-18 13:59:33 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table tb_sys_access_log
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tb_sys_access_log`;

CREATE TABLE `tb_sys_access_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `ip` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT 'IP',
  `method` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '请求方式',
  `url` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '路径',
  `user_agent` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户代理',
  `access_token` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '访问令牌',
  `start_time` datetime(3) NOT NULL COMMENT '开始时间',
  `end_time` datetime(3) DEFAULT NULL COMMENT '结束时间',
  `operator` int(11) NOT NULL DEFAULT '0' COMMENT '操作人',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`),
  KEY `idx_created_time` (`created_time`),
  KEY `idx_updated_time` (`updated_time`),
  KEY `idx_deleted` (`deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='访问记录';



# Dump of table tb_sys_config
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tb_sys_config`;

CREATE TABLE `tb_sys_config` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '配置代码',
  `value` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '配置值',
  `description` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '配置描述',
  `operator` int(11) DEFAULT NULL COMMENT '操作人',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`),
  KEY `idx_created_time` (`created_time`),
  KEY `idx_updated_time` (`updated_time`),
  KEY `idx_deleted` (`deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统配置';



# Dump of table tb_sys_resource
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tb_sys_resource`;

CREATE TABLE `tb_sys_resource` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `type` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '类型',
  `title` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '标题',
  `method` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `uri` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '路径',
  `icon` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图标',
  `parent_id` int(11) DEFAULT NULL COMMENT '父ID',
  `opened` int(11) NOT NULL DEFAULT '0' COMMENT '公开',
  `seq` int(11) NOT NULL DEFAULT '0' COMMENT '排序,升序',
  `operator` int(11) NOT NULL DEFAULT '0' COMMENT '操作人',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`),
  KEY `idx_created_time` (`created_time`),
  KEY `idx_updated_time` (`updated_time`),
  KEY `idx_deleted` (`deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统资源';

LOCK TABLES `tb_sys_resource` WRITE;
/*!40000 ALTER TABLE `tb_sys_resource` DISABLE KEYS */;

INSERT INTO `tb_sys_resource` (`id`, `type`, `title`, `method`, `uri`, `icon`, `parent_id`, `opened`, `seq`, `operator`, `created_time`, `updated_time`, `deleted`, `version`)
VALUES
	(1,'MENU','首页',NULL,'/','h-icon-home',NULL,0,0,100001,'2019-04-13 22:38:51','2019-04-16 23:19:03',0,0),
	(2,'API','ui','GET','/swagger-resources/configuration/ui',NULL,39,1,0,100001,'2019-04-22 21:40:58','2019-10-26 10:13:39',0,0),
	(3,'API','security','GET','/swagger-resources/configuration/security',NULL,39,1,0,100001,'2019-04-22 21:41:22','2019-10-26 10:13:29',0,0),
	(4,'API','swagger-resources','GET','/swagger-resources',NULL,39,1,0,100001,'2019-04-22 21:41:51','2019-10-26 10:13:17',0,0),
	(5,'MENU','商品',NULL,'test','h-icon-task',1,0,0,100001,'2019-04-13 22:44:44','2019-04-16 23:41:01',0,0),
	(6,'API','api-docs','GET','/v2/api-docs',NULL,39,1,0,100001,'2019-04-22 21:42:52','2019-10-26 10:13:53',0,0),
	(8,'MENU','系统管理',NULL,'sys','h-icon-task',NULL,0,0,100001,'2019-04-16 23:29:29','2019-04-16 23:41:04',0,0),
	(9,'MENU','系统资源',NULL,'/sys/resource','h-icon-task',8,0,3,100001,'2019-04-16 23:33:31','2019-04-17 23:30:48',0,0),
	(10,'API','系统资源列表','POST','/sys/resource/list','h-icon-link',9,0,0,100001,'2019-04-16 23:34:05','2019-10-26 06:42:57',0,0),
	(11,'API','保存系统资源','POST','/sys/resource/save','h-icon-link',9,0,0,100001,'2019-04-16 23:39:33','2019-10-26 06:20:46',0,0),
	(12,'API','系统资源类型','GET','/sys/resource/type','h-icon-link',9,0,0,0,'2019-04-17 00:02:20','2019-10-26 06:20:48',0,0),
	(13,'API','删除系统资源','POST','/sys/resource/del','h-icon-link',9,0,4,100001,'2019-04-17 00:03:13','2019-10-26 06:20:52',0,0),
	(14,'API','我的菜单','GET','/sys/user/menu','h-icon-link',1,0,0,0,'2019-04-17 00:05:26','2019-10-26 06:20:55',0,0),
	(15,'API','登录','POST','/sys/user/signIn','',1,1,0,100001,'2019-04-17 00:13:53','2019-10-26 06:20:57',0,0),
	(16,'API','获取验证码','GET','/captcha','',1,1,0,100001,'2019-04-17 00:21:20','2019-04-17 00:21:31',0,0),
	(17,'MENU','系统角色',NULL,'/sys/role','h-icon-task',8,0,2,100001,'2019-04-17 22:07:53','2019-04-17 23:30:38',0,0),
	(18,'API','系统角色列表','POST','/sys/role/list','',17,0,0,100001,'2019-04-17 22:28:56','2019-04-17 22:28:56',0,0),
	(19,'API','保存系统角色','POST','/sys/role/save','',17,0,0,100001,'2019-04-17 22:29:24','2019-04-17 22:29:24',0,0),
	(22,'MENU','系统用户',NULL,'/sys/user','h-icon-task',8,0,0,0,'2019-04-17 22:07:53','2019-04-17 22:09:38',0,0),
	(23,'API','系统用户列表','POST','/sys/user/list','',22,0,0,100001,'2019-04-17 23:23:00','2019-04-17 23:23:00',0,0),
	(24,'API','系统角色列表','GET','/sys/role/list','',22,0,0,100001,'2019-04-18 23:26:22','2019-04-18 23:26:22',0,0),
	(25,'API','保存系统用户','POST','/sys/user/save','',22,0,0,100001,'2019-04-18 23:26:53','2019-04-18 23:26:53',0,0),
	(26,'API','我的接口','GET','/sys/user/api','',1,0,0,100001,'2019-04-19 00:32:16','2019-04-19 00:32:16',0,0),
	(27,'API','删除系统角色','POST','/sys/role/del','',17,0,5,100001,'2019-04-19 01:14:52','2019-04-19 01:15:11',0,0),
	(28,'API','安全退出','GET','/sys/user/signOut','',1,0,0,100001,'2019-04-19 22:57:23','2019-04-19 23:04:33',0,0),
	(29,'API','重置密码','POST','/sys/user/resetPassword','',22,0,0,100001,'2019-04-19 22:58:14','2019-04-19 23:04:38',0,0),
	(30,'API','修改密码','POST','/sys/user/updatePassword','',1,0,0,100001,'2019-04-19 22:58:27','2019-04-19 23:04:44',0,0),
	(31,'API','禁用用户','POST','/sys/user/disabled','',22,0,0,100001,'2019-04-19 23:00:27','2019-04-19 23:04:48',0,0),
	(32,'API','启用用户','POST','/sys/user/enabled','',22,0,0,100001,'2019-04-19 23:01:03','2019-04-19 23:04:53',0,0),
	(33,'API','删除用户','POST','/sys/user/del','',22,0,0,100001,'2019-04-19 23:01:50','2019-04-19 23:04:56',0,0),
	(34,'API','解锁用户','POST','/sys/user/unlockSignError',NULL,22,0,0,0,'2019-04-19 23:06:46','2019-04-19 23:06:46',0,0),
	(36,'MENU','访问日志',NULL,'/sys/access-log','',8,0,0,100001,'2019-04-19 23:35:03','2019-10-26 08:19:08',0,0),
	(37,'API','访问日志列表','POST','/sys/access/log/list','',36,0,0,100001,'2019-04-19 23:35:32','2019-04-19 23:35:32',0,0),
	(38,'API','首页','GET','/',NULL,NULL,1,0,100001,'2019-04-22 21:47:24','2019-04-22 23:35:21',0,0),
	(39,'PAGE','Swagger2','GET','/swagger-ui.html',NULL,NULL,1,0,0,'2019-04-22 21:51:39','2019-04-22 21:55:59',0,0),
	(40,'API','csrf','GET','/csrf',NULL,39,1,0,100001,'2019-04-22 21:51:09','2019-10-26 10:14:09',0,0),
	(41,'API','系统信息','GET','/system/info',NULL,NULL,1,0,0,'2019-04-22 22:58:36','2019-04-22 22:58:46',0,0);

/*!40000 ALTER TABLE `tb_sys_resource` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table tb_sys_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tb_sys_role`;

CREATE TABLE `tb_sys_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '代码（唯一）',
  `name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色名称',
  `description` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  `operator` int(11) NOT NULL DEFAULT '0' COMMENT '操作人',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`),
  KEY `idx_created_time` (`created_time`),
  KEY `idx_updated_time` (`updated_time`),
  KEY `idx_deleted` (`deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统角色';

LOCK TABLES `tb_sys_role` WRITE;
/*!40000 ALTER TABLE `tb_sys_role` DISABLE KEYS */;

INSERT INTO `tb_sys_role` (`id`, `code`, `name`, `description`, `operator`, `created_time`, `updated_time`, `deleted`, `version`)
VALUES
	(1,NULL,'管理员','addddddddddd',100001,'2019-04-13 23:24:58','2019-11-18 13:48:06',0,0),
	(2,NULL,'xxx',NULL,0,'2019-04-13 23:58:16','2019-04-13 23:58:16',0,0),
	(3,NULL,'11','11177',100001,'2019-04-17 22:38:29','2019-04-17 23:10:40',0,0),
	(4,NULL,'123','123',100001,'2019-10-26 09:18:16','2019-10-26 09:18:16',0,0);

/*!40000 ALTER TABLE `tb_sys_role` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table tb_sys_role_resource
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tb_sys_role_resource`;

CREATE TABLE `tb_sys_role_resource` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `sys_role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `sys_resource_id` int(11) DEFAULT NULL COMMENT '资源ID',
  `operator` int(11) NOT NULL DEFAULT '0' COMMENT '操作人',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`),
  KEY `idx_created_time` (`created_time`),
  KEY `idx_updated_time` (`updated_time`),
  KEY `idx_deleted` (`deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统角色资源';

LOCK TABLES `tb_sys_role_resource` WRITE;
/*!40000 ALTER TABLE `tb_sys_role_resource` DISABLE KEYS */;

INSERT INTO `tb_sys_role_resource` (`id`, `sys_role_id`, `sys_resource_id`, `operator`, `created_time`, `updated_time`, `deleted`, `version`)
VALUES
	(237,1,1,100001,'2019-04-19 23:35:43','2019-04-19 23:35:43',0,0),
	(238,1,5,100001,'2019-04-19 23:35:43','2019-04-19 23:35:43',0,0),
	(239,1,8,100001,'2019-04-19 23:35:43','2019-04-19 23:35:43',0,0),
	(240,1,9,100001,'2019-04-19 23:35:43','2019-04-19 23:35:43',0,0),
	(242,1,11,100001,'2019-04-19 23:35:43','2019-04-19 23:35:43',0,0),
	(243,1,12,100001,'2019-04-19 23:35:43','2019-04-19 23:35:43',0,0),
	(244,1,14,100001,'2019-04-19 23:35:43','2019-04-19 23:35:43',0,0),
	(245,1,17,100001,'2019-04-19 23:35:43','2019-04-19 23:35:43',0,0),
	(246,1,18,100001,'2019-04-19 23:35:43','2019-04-19 23:35:43',0,0),
	(247,1,19,100001,'2019-04-19 23:35:43','2019-04-19 23:35:43',0,0),
	(248,1,20,100001,'2019-04-19 23:35:43','2019-04-19 23:35:43',0,0),
	(249,1,21,100001,'2019-04-19 23:35:43','2019-04-19 23:35:43',0,0),
	(250,1,22,100001,'2019-04-19 23:35:43','2019-04-19 23:35:43',0,0),
	(251,1,23,100001,'2019-04-19 23:35:43','2019-04-19 23:35:43',0,0),
	(252,1,24,100001,'2019-04-19 23:35:43','2019-04-19 23:35:43',0,0),
	(253,1,25,100001,'2019-04-19 23:35:43','2019-04-19 23:35:43',0,0),
	(254,1,26,100001,'2019-04-19 23:35:43','2019-04-19 23:35:43',0,0),
	(255,1,27,100001,'2019-04-19 23:35:43','2019-04-19 23:35:43',0,0),
	(256,1,28,100001,'2019-04-19 23:35:43','2019-04-19 23:35:43',0,0),
	(257,1,29,100001,'2019-04-19 23:35:43','2019-04-19 23:35:43',0,0),
	(258,1,30,100001,'2019-04-19 23:35:43','2019-04-19 23:35:43',0,0),
	(259,1,31,100001,'2019-04-19 23:35:43','2019-04-19 23:35:43',0,0),
	(260,1,32,100001,'2019-04-19 23:35:43','2019-04-19 23:35:43',0,0),
	(261,1,33,100001,'2019-04-19 23:35:43','2019-04-19 23:35:43',0,0),
	(262,1,34,100001,'2019-04-19 23:35:43','2019-04-19 23:35:43',0,0),
	(263,1,36,100001,'2019-04-19 23:35:43','2019-04-19 23:35:43',0,0),
	(264,1,37,100001,'2019-04-19 23:35:43','2019-04-19 23:35:43',0,0),
	(265,1,13,100001,'2019-10-26 10:09:47','2019-10-26 10:09:47',0,0),
	(266,1,10,0,'2019-10-26 10:12:05','2019-10-26 10:12:05',0,0);

/*!40000 ALTER TABLE `tb_sys_role_resource` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table tb_sys_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tb_sys_user`;

CREATE TABLE `tb_sys_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '账号',
  `name` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '姓名',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `email` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮箱',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态1正常，2:冻结',
  `sign_error_size` int(11) NOT NULL DEFAULT '0' COMMENT '登录错误次数',
  `sign_error_limit` int(11) NOT NULL DEFAULT '5' COMMENT '登录错误上限',
  `sys_role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `operator` int(11) NOT NULL DEFAULT '0' COMMENT '操作人',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`),
  KEY `idx_created_time` (`created_time`),
  KEY `idx_updated_time` (`updated_time`),
  KEY `idx_deleted` (`deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统用户';

LOCK TABLES `tb_sys_user` WRITE;
/*!40000 ALTER TABLE `tb_sys_user` DISABLE KEYS */;

INSERT INTO `tb_sys_user` (`id`, `username`, `name`, `birthday`, `email`, `status`, `sign_error_size`, `sign_error_limit`, `sys_role_id`, `operator`, `created_time`, `updated_time`, `deleted`, `version`)
VALUES
	(100001,'su','超级管理员P2V49fAp','2019-04-18','1231@x.x',1,0,5,1,0,'2019-04-13 18:08:07','2019-04-19 01:23:24',0,0),
	(100002,'123','123-4bLLD0cK','2019-04-24','123@12.cc',3,0,5,3,100001,'2019-04-19 01:23:45','2019-04-19 23:16:48',0,0);

/*!40000 ALTER TABLE `tb_sys_user` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table tb_sys_user_pwd
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tb_sys_user_pwd`;

CREATE TABLE `tb_sys_user_pwd` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `sys_user_id` int(11) NOT NULL COMMENT '用户ID',
  `pwd` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '密码',
  `salt` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '盐',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`),
  KEY `idx_created_time` (`created_time`),
  KEY `idx_updated_time` (`updated_time`),
  KEY `idx_deleted` (`deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统用户密码';

LOCK TABLES `tb_sys_user_pwd` WRITE;
/*!40000 ALTER TABLE `tb_sys_user_pwd` DISABLE KEYS */;

INSERT INTO `tb_sys_user_pwd` (`id`, `sys_user_id`, `pwd`, `salt`, `created_time`, `updated_time`, `deleted`, `version`)
VALUES
	(1,100003,'4e8f0d446422cae13580ef98606786d6','6vjYCpVp1a','2019-04-13 16:43:26','2019-04-19 23:22:41',1,0),
	(2,100001,'60741c0b82ffeae5626ef841dfe69d26','aHACXpafoI','2019-04-13 18:08:07','2019-04-13 18:08:07',0,0),
	(3,100002,'f17d5549c7bbb9cb2b0896efca523d80','SHnszzUwi7','2019-04-19 01:23:45','2019-04-19 01:23:45',0,0);

/*!40000 ALTER TABLE `tb_sys_user_pwd` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table tb_sys_user_sign_log
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tb_sys_user_sign_log`;

CREATE TABLE `tb_sys_user_sign_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `ip` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT 'IP',
  `username` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `user_agent` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '浏览器代理',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '1成功,0失败',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`),
  KEY `idx_created_time` (`created_time`),
  KEY `idx_updated_time` (`updated_time`),
  KEY `idx_deleted` (`deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户登录日志';



# Dump of table tb_sys_user_token
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tb_sys_user_token`;

CREATE TABLE `tb_sys_user_token` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `sys_user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `ip` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'IP',
  `access_token` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '令牌（唯一）',
  `user_agent` text COLLATE utf8mb4_unicode_ci COMMENT '用户代理',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_access_token` (`access_token`),
  KEY `idx_created_time` (`created_time`),
  KEY `idx_updated_time` (`updated_time`),
  KEY `idx_deleted` (`deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统用户令牌';




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
