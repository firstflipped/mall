/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 5.7.34 : Database - gulimall_admin_new
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE = ''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS */`gulimall_admin_new` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin */;

USE `gulimall_admin_new`;

/*Table structure for table `sys_permission` */

DROP TABLE IF EXISTS `sys_permission`;

CREATE TABLE `sys_permission`
(
    `id`              bigint(20) NOT NULL COMMENT '主键id',
    `parent_id`       bigint(20)   DEFAULT NULL COMMENT '父id',
    `permission_name` varchar(100) DEFAULT NULL COMMENT '菜单标题',
    `url`             varchar(255) DEFAULT NULL COMMENT '路径',
    `menu_type`       tinyint(1)   DEFAULT NULL COMMENT '菜单类型(0:一级菜单; 1:子菜单:2:按钮权限)',
    `sort_no`         int(11)      DEFAULT NULL COMMENT '菜单排序',
    `icon`            varchar(100) DEFAULT NULL COMMENT '菜单图标',
    `description`     varchar(255) DEFAULT NULL COMMENT '描述',
    `create_by`       varchar(32)  DEFAULT NULL COMMENT '创建人',
    `create_time`     datetime     DEFAULT NULL COMMENT '创建时间',
    `update_by`       varchar(32)  DEFAULT NULL COMMENT '更新人',
    `update_time`     datetime     DEFAULT NULL COMMENT '更新时间',
    `delete`          tinyint(1)   DEFAULT '0' COMMENT '删除状态 0正常 1已删除',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_sp_parent_id` (`parent_id`) USING BTREE,
    KEY `idx_sp_sort_no` (`sort_no`) USING BTREE,
    KEY `idx_sp_menu_type` (`menu_type`) USING BTREE,
    KEY `index_sp_pid` (`parent_id`),
    KEY `index_sp_sort_no` (`sort_no`),
    KEY `index_sp_menu_type` (`menu_type`),
    KEY `idx_sp_del_flag` (`delete`),
    KEY `index_sp_is_delete` (`delete`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC COMMENT ='菜单权限表';

/*Data for the table `sys_permission` */

insert into `sys_permission`(`id`, `parent_id`, `permission_name`, `url`, `menu_type`, `sort_no`, `icon`, `description`,
                             `create_by`, `create_time`, `update_by`, `update_time`, `delete`)
values (1466209998387417088, 0, '用户管理', 'www.baidu.com', NULL, 1, NULL, NULL, NULL, '2021-12-02 08:57:59', NULL, NULL,
        NULL),
       (1466211572253855744, 0, '角色管理', 'www.baidu.com', NULL, 1, NULL, NULL, NULL, '2021-12-02 09:04:14', NULL, NULL,
        NULL),
       (1466211672942317568, 0, '权限管理', 'www.baidu.com', NULL, 1, NULL, NULL, NULL, '2021-12-02 09:04:38', NULL, NULL,
        NULL),
       (1466212316054949888, 1466209998387417088, '用户添加', 'www.baidu.com', NULL, 1, NULL, NULL, NULL,
        '2021-12-02 09:07:12', NULL, NULL, NULL),
       (1466212464789164032, 1466211572253855744, '角色添加', 'www.baidu.com', NULL, 1, NULL, NULL, NULL,
        '2021-12-02 09:07:47', NULL, NULL, NULL);

/*Table structure for table `sys_permission_data_rule` */

DROP TABLE IF EXISTS `sys_permission_data_rule`;

CREATE TABLE `sys_permission_data_rule`
(
    `id`              bigint(20) NOT NULL COMMENT 'ID',
    `permission_id`   bigint(20)   DEFAULT NULL COMMENT '菜单ID',
    `rule_name`       varchar(50)  DEFAULT NULL COMMENT '规则名称',
    `rule_column`     varchar(50)  DEFAULT NULL COMMENT '字段',
    `rule_conditions` varchar(50)  DEFAULT NULL COMMENT '条件',
    `rule_value`      varchar(300) DEFAULT NULL COMMENT '规则值',
    `status`          tinyint(4)   DEFAULT NULL COMMENT '权限有效状态1有0否',
    `create_time`     datetime     DEFAULT NULL COMMENT '创建时间',
    `create_by`       varchar(32)  DEFAULT NULL,
    `update_time`     datetime     DEFAULT NULL COMMENT '修改时间',
    `update_by`       varchar(32)  DEFAULT NULL COMMENT '修改人',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `index_fucntionid` (`permission_id`) USING BTREE,
    KEY `idx_spdr_permission_id` (`permission_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC;

/*Data for the table `sys_permission_data_rule` */

/*Table structure for table `sys_platform_log` */

DROP TABLE IF EXISTS `sys_platform_log`;

CREATE TABLE `sys_platform_log`
(
    `id`                 bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `user_id`            bigint(20)               DEFAULT NULL COMMENT '操作用户账号',
    `username`           varchar(100)             DEFAULT NULL COMMENT '操作用户名称',
    `uri`                varchar(255)             DEFAULT NULL COMMENT 'uri',
    `url`                varchar(255)             DEFAULT NULL COMMENT 'url',
    `class_name`         varchar(255)             DEFAULT NULL COMMENT '请求类',
    `method_name`        varchar(255)             DEFAULT NULL COMMENT '请求方法',
    `method_type`        tinyint(4)               DEFAULT NULL COMMENT '请求类型 增1删2改3查4',
    `method_params`      varchar(511)             DEFAULT NULL COMMENT '请求参数',
    `method_description` varchar(255)             DEFAULT NULL COMMENT '操作描述',
    `server_ip`          varchar(55)              DEFAULT NULL COMMENT '服务器地址',
    `client_ip`          varchar(55)              DEFAULT NULL COMMENT '客户端地址',
    `is_success`         tinyint(4)               DEFAULT NULL COMMENT '是否成功',
    `spend_time`         bigint(20)               DEFAULT NULL COMMENT '耗时',
    `is_login`           tinyint(4)               DEFAULT NULL COMMENT '是否为登录请求',
    `create_time`        timestamp           NULL DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_sl_userid` (`user_id`)
) ENGINE = MyISAM
  AUTO_INCREMENT = 1493492984043851778
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC COMMENT ='系统日志表';

/*Data for the table `sys_platform_log` */

insert into `sys_platform_log`(`id`, `user_id`, `username`, `uri`, `url`, `class_name`, `method_name`, `method_type`,
                               `method_params`, `method_description`, `server_ip`, `client_ip`, `is_success`,
                               `spend_time`, `is_login`, `create_time`)
values (1481828902563745794, NULL, NULL, '/gulimall-admin-new/user/list',
        'http://172.20.218.95:9090/gulimall-admin-new/user/list',
        'com.laughingather.gulimall.adminnew.controller.SysUserController', 'listUsers()', 4, NULL, '获取用户列表',
        '172.20.218.95', '0:0:0:0:0:0:0:1', 1, 34, 0, '2022-01-14 11:21:56'),
       (1481823668651167745, NULL, NULL, '/gulimall-admin-new/user/list',
        'http://172.20.218.95:9090/gulimall-admin-new/user/list',
        'com.laughingather.gulimall.adminnew.controller.SysUserController', 'listUsers()', 4, NULL, '获取用户列表',
        '172.20.218.95', '0:0:0:0:0:0:0:1', 1, 38, 0, '2022-01-14 11:01:08'),
       (1481820723092131842, NULL, NULL, '/gulimall-admin-new/user/list',
        'http://localhost:9090/gulimall-admin-new/user/list',
        'com.laughingather.gulimall.adminnew.controller.SysUserController', 'listUsers()', 4, NULL, '获取用户列表',
        '172.20.218.95', '172.20.218.95', 1, 121, 0, '2022-01-14 10:49:26'),
       (1493396426157760514, NULL, NULL, '/gulimall-admin-new/user/list',
        'http://172.20.202.93:9090/gulimall-admin-new/user/list',
        'com.laughingather.gulimall.adminnew.controller.SysUserController', 'listUsers()', 4, NULL, '获取用户列表',
        '172.20.202.93', '127.0.0.1', 1, 53, 0, '2022-02-15 09:27:09'),
       (1493396858942824450, NULL, NULL, '/gulimall-admin-new/user/list',
        'http://172.20.202.93:9090/gulimall-admin-new/user/list',
        'com.laughingather.gulimall.adminnew.controller.SysUserController', 'listUsers()', 4, NULL, '获取用户列表',
        '172.20.202.93', '127.0.0.1', 1, 3, 0, '2022-02-15 09:28:52'),
       (1493396875942338562, NULL, NULL, '/gulimall-admin-new/user/list',
        'http://172.20.202.93:9090/gulimall-admin-new/user/list',
        'com.laughingather.gulimall.adminnew.controller.SysUserController', 'listUsers()', 4, NULL, '获取用户列表',
        '172.20.202.93', '127.0.0.1', 1, 4, 0, '2022-02-15 09:28:56'),
       (1493402519374176257, NULL, NULL, '/gulimall-admin-new/user/list',
        'http://172.20.202.93:9090/gulimall-admin-new/user/list',
        'com.laughingather.gulimall.adminnew.controller.SysUserController', 'listUsers()', 4, NULL, '获取用户列表',
        '172.20.202.93', '127.0.0.1', 1, 38, 0, '2022-02-15 09:51:21'),
       (1493402857216974850, NULL, NULL, '/gulimall-admin-new/user/list',
        'http://172.20.202.93:9090/gulimall-admin-new/user/list',
        'com.laughingather.gulimall.adminnew.controller.SysUserController', 'listUsers()', 4, NULL, '获取用户列表',
        '172.20.202.93', '127.0.0.1', 1, 36, 0, '2022-02-15 09:52:42'),
       (1493403238449848321, NULL, NULL, '/gulimall-admin-new/user/list',
        'http://172.20.202.93:9090/gulimall-admin-new/user/list',
        'com.laughingather.gulimall.adminnew.controller.SysUserController', 'listUsers()', 4, NULL, '获取用户列表',
        '172.20.202.93', '127.0.0.1', 1, 31, 0, '2022-02-15 09:54:13'),
       (1493403913644711938, NULL, NULL, '/gulimall-admin-new/user/list',
        'http://172.20.202.93:9090/gulimall-admin-new/user/list',
        'com.laughingather.gulimall.adminnew.controller.SysUserController', 'listUsers()', 4, NULL, '获取用户列表',
        '172.20.202.93', '127.0.0.1', 1, 40, 0, '2022-02-15 09:56:54'),
       (1493404828036538369, NULL, NULL, '/gulimall-admin-new/user/list',
        'http://172.20.202.93:9090/gulimall-admin-new/user/list',
        'com.laughingather.gulimall.adminnew.controller.SysUserController', 'listUsers()', 4, NULL, '获取用户列表',
        '172.20.202.93', '127.0.0.1', 1, 34, 0, '2022-02-15 10:00:32'),
       (1493405815535083521, NULL, NULL, '/gulimall-admin-new/user/list',
        'http://172.20.202.93:9090/gulimall-admin-new/user/list',
        'com.laughingather.gulimall.adminnew.controller.SysUserController', 'listUsers()', 4, NULL, '获取用户列表',
        '172.20.202.93', '127.0.0.1', 1, 34, 0, '2022-02-15 10:04:27'),
       (1493407428282720257, NULL, NULL, '/gulimall-admin-new/user/list',
        'http://172.20.202.93:9090/gulimall-admin-new/user/list',
        'com.laughingather.gulimall.adminnew.controller.SysUserController', 'listUsers()', 4, NULL, '获取用户列表',
        '172.20.202.93', '127.0.0.1', 1, 33, 0, '2022-02-15 10:10:52'),
       (1493407794445459457, NULL, NULL, '/gulimall-admin-new/user/list',
        'http://172.20.202.93:9090/gulimall-admin-new/user/list',
        'com.laughingather.gulimall.adminnew.controller.SysUserController', 'listUsers()', 4, NULL, '获取用户列表',
        '172.20.202.93', '127.0.0.1', 1, 4, 0, '2022-02-15 10:12:19'),
       (1493407895805009921, NULL, NULL, '/gulimall-admin-new/user/list',
        'http://172.20.202.93:9090/gulimall-admin-new/user/list',
        'com.laughingather.gulimall.adminnew.controller.SysUserController', 'listUsers()', 4, NULL, '获取用户列表',
        '172.20.202.93', '127.0.0.1', 1, 3, 0, '2022-02-15 10:12:43'),
       (1493407976113348609, NULL, NULL, '/gulimall-admin-new/user/list',
        'http://172.20.202.93:9090/gulimall-admin-new/user/list',
        'com.laughingather.gulimall.adminnew.controller.SysUserController', 'listUsers()', 4, NULL, '获取用户列表',
        '172.20.202.93', '127.0.0.1', 1, 4, 0, '2022-02-15 10:13:02'),
       (1493409186912444417, NULL, NULL, '/gulimall-admin-new/user/list',
        'http://172.20.202.93:9090/gulimall-admin-new/user/list',
        'com.laughingather.gulimall.adminnew.controller.SysUserController', 'listUsers()', 4, NULL, '获取用户列表',
        '172.20.202.93', '127.0.0.1', 1, 35, 0, '2022-02-15 10:17:51'),
       (1493413364904796161, NULL, NULL, '/gulimall-admin-new/user/list',
        'http://172.20.202.93:9090/gulimall-admin-new/user/list',
        'com.laughingather.gulimall.adminnew.controller.SysUserController', 'listUsers()', 4, NULL, '获取用户列表',
        '172.20.202.93', '127.0.0.1', 1, 114, 0, '2022-02-15 10:34:27'),
       (1493413513525764098, NULL, NULL, '/gulimall-admin-new/user/list',
        'http://172.20.202.93:9090/gulimall-admin-new/user/list',
        'com.laughingather.gulimall.adminnew.controller.SysUserController', 'listUsers()', 4, NULL, '获取用户列表',
        '172.20.202.93', '127.0.0.1', 1, 4, 0, '2022-02-15 10:35:03'),
       (1493413804962783234, NULL, NULL, '/gulimall-admin-new/user/list',
        'http://172.20.202.93:9090/gulimall-admin-new/user/list',
        'com.laughingather.gulimall.adminnew.controller.SysUserController', 'listUsers()', 4, NULL, '获取用户列表',
        '172.20.202.93', '127.0.0.1', 1, 43, 0, '2022-02-15 10:36:12'),
       (1493414055098490881, NULL, NULL, '/gulimall-admin-new/user/list',
        'http://172.20.202.93:9090/gulimall-admin-new/user/list',
        'com.laughingather.gulimall.adminnew.controller.SysUserController', 'listUsers()', 4, NULL, '获取用户列表',
        '172.20.202.93', '127.0.0.1', 1, 5, 0, '2022-02-15 10:37:12'),
       (1493415125203853314, NULL, NULL, '/gulimall-admin-new/user/list',
        'http://172.20.202.93:9090/gulimall-admin-new/user/list',
        'com.laughingather.gulimall.adminnew.controller.SysUserController', 'listUsers()', 4, NULL, '获取用户列表',
        '172.20.202.93', '127.0.0.1', 1, 38, 0, '2022-02-15 10:41:27'),
       (1493417430720487426, NULL, NULL, '/gulimall-admin-new/user/list',
        'http://172.20.202.93:9090/gulimall-admin-new/user/list',
        'com.laughingather.gulimall.adminnew.controller.SysUserController', 'listUsers()', 4, NULL, '获取用户列表',
        '172.20.202.93', '127.0.0.1', 1, 38, 0, '2022-02-15 10:50:37'),
       (1493417441550180354, NULL, NULL, '/gulimall-admin-new/user/list',
        'http://172.20.202.93:9090/gulimall-admin-new/user/list',
        'com.laughingather.gulimall.adminnew.controller.SysUserController', 'listUsers()', 4, NULL, '获取用户列表',
        '172.20.202.93', '127.0.0.1', 1, 5, 0, '2022-02-15 10:50:39'),
       (1493421325354049537, NULL, NULL, '/gulimall-admin-new/user/list',
        'http://172.20.202.93:9090/gulimall-admin-new/user/list',
        'com.laughingather.gulimall.adminnew.controller.SysUserController', 'listUsers()', 4, NULL, '获取用户列表',
        '172.20.202.93', '127.0.0.1', 1, 36, 0, '2022-02-15 11:06:05'),
       (1493421335357464577, NULL, NULL, '/gulimall-admin-new/user/list',
        'http://172.20.202.93:9090/gulimall-admin-new/user/list',
        'com.laughingather.gulimall.adminnew.controller.SysUserController', 'listUsers()', 4, NULL, '获取用户列表',
        '172.20.202.93', '127.0.0.1', 1, 3, 0, '2022-02-15 11:06:07'),
       (1493421847783972865, NULL, NULL, '/gulimall-admin-new/user/list',
        'http://172.20.202.93:9090/gulimall-admin-new/user/list',
        'com.laughingather.gulimall.adminnew.controller.SysUserController', 'listUsers()', 4, NULL, '获取用户列表',
        '172.20.202.93', '127.0.0.1', 1, 3, 0, '2022-02-15 11:08:10'),
       (1493422497653628929, NULL, NULL, '/gulimall-admin-new/user/list',
        'http://172.20.202.93:9090/gulimall-admin-new/user/list',
        'com.laughingather.gulimall.adminnew.controller.SysUserController', 'listUsers()', 4, NULL, '获取用户列表',
        '172.20.202.93', '127.0.0.1', 1, 38, 0, '2022-02-15 11:10:45'),
       (1493422802147516417, NULL, NULL, '/gulimall-admin-new/user/list',
        'http://172.20.202.93:9090/gulimall-admin-new/user/list',
        'com.laughingather.gulimall.adminnew.controller.SysUserController', 'listUsers()', 4, NULL, '获取用户列表',
        '172.20.202.93', '127.0.0.1', 1, 35, 0, '2022-02-15 11:11:57'),
       (1493424494037475329, NULL, NULL, '/gulimall-admin-new/user/list',
        'http://172.20.202.93:9090/gulimall-admin-new/user/list',
        'com.laughingather.gulimall.adminnew.controller.SysUserController', 'listUsers()', 4, NULL, '获取用户列表',
        '172.20.202.93', '127.0.0.1', 1, 49, 0, '2022-02-15 11:18:41'),
       (1493424656176685057, NULL, NULL, '/gulimall-admin-new/user/list',
        'http://172.20.202.93:9090/gulimall-admin-new/user/list',
        'com.laughingather.gulimall.adminnew.controller.SysUserController', 'listUsers()', 4, NULL, '获取用户列表',
        '172.20.202.93', '127.0.0.1', 1, 3, 0, '2022-02-15 11:19:19'),
       (1493425046519586818, NULL, NULL, '/gulimall-admin-new/user/list',
        'http://172.20.202.93:9090/gulimall-admin-new/user/list',
        'com.laughingather.gulimall.adminnew.controller.SysUserController', 'listUsers()', 4, NULL, '获取用户列表',
        '172.20.202.93', '127.0.0.1', 1, 39, 0, '2022-02-15 11:20:52'),
       (1493464253988380673, NULL, NULL, '/gulimall-admin-new/user/list',
        'http://172.20.202.93:9090/gulimall-admin-new/user/list',
        'com.laughingather.gulimall.adminnew.controller.SysUserController', 'listUsers()', 4, NULL, '获取用户列表',
        '172.20.202.93', '127.0.0.1', 1, 60, 0, '2022-02-15 13:56:40'),
       (1493471641982246913, NULL, NULL, '/gulimall-admin-new/user/list',
        'http://172.20.202.93:9090/gulimall-admin-new/user/list',
        'com.laughingather.gulimall.adminnew.controller.SysUserController', 'listUsers()', 4, NULL, '获取用户列表',
        '172.20.202.93', '127.0.0.1', 1, 4, 0, '2022-02-15 14:26:01'),
       (1493471975030956034, NULL, NULL, '/gulimall-admin-new/user/list',
        'http://172.20.202.93:9090/gulimall-admin-new/user/list',
        'com.laughingather.gulimall.adminnew.controller.SysUserController', 'listUsers()', 4, NULL, '获取用户列表',
        '172.20.202.93', '127.0.0.1', 1, 4, 0, '2022-02-15 14:27:21'),
       (1493472572656361474, NULL, NULL, '/gulimall-admin-new/user/list',
        'http://172.20.202.93:9090/gulimall-admin-new/user/list',
        'com.laughingather.gulimall.adminnew.controller.SysUserController', 'listUsers()', 4, NULL, '获取用户列表',
        '172.20.202.93', '127.0.0.1', 1, 3, 0, '2022-02-15 14:29:43'),
       (1493485355020111874, NULL, NULL, '/gulimall-admin-new/user/list',
        'http://172.20.202.93:9090/gulimall-admin-new/user/list',
        'com.laughingather.gulimall.adminnew.controller.SysUserController', 'listUsers()', 4, NULL, '获取用户列表',
        '172.20.202.93', '127.0.0.1', 1, 21, 0, '2022-02-15 15:20:31'),
       (1493492259972763650, NULL, NULL, '/gulimall-admin-new/user/list',
        'http://172.20.202.93:9090/gulimall-admin-new/user/list',
        'com.laughingather.gulimall.adminnew.controller.SysUserController', 'listUsers()', 4, NULL, '获取用户列表',
        '172.20.202.93', '127.0.0.1', 1, 5, 0, '2022-02-15 15:47:57'),
       (1493492340985745410, NULL, NULL, '/gulimall-admin-new/user/list',
        'http://172.20.202.93:9090/gulimall-admin-new/user/list',
        'com.laughingather.gulimall.adminnew.controller.SysUserController', 'listUsers()', 4, NULL, '获取用户列表',
        '172.20.202.93', '127.0.0.1', 1, 6, 0, '2022-02-15 15:48:16'),
       (1493492638059909122, NULL, NULL, '/gulimall-admin-new/user/list',
        'http://172.20.202.93:9090/gulimall-admin-new/user/list',
        'com.laughingather.gulimall.adminnew.controller.SysUserController', 'listUsers()', 4, NULL, '获取用户列表',
        '172.20.202.93', '127.0.0.1', 1, 44, 0, '2022-02-15 15:49:27'),
       (1493492984043851777, NULL, NULL, '/gulimall-admin-new/user/list',
        'http://172.20.202.93:9090/gulimall-admin-new/user/list',
        'com.laughingather.gulimall.adminnew.controller.SysUserController', 'listUsers()', 4, NULL, '获取用户列表',
        '172.20.202.93', '127.0.0.1', 1, 57, 0, '2022-02-15 15:50:50');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role`
(
    `id`          bigint(20) NOT NULL COMMENT '主键id',
    `role_name`   varchar(200) DEFAULT NULL COMMENT '角色名称',
    `role_code`   varchar(100) DEFAULT NULL COMMENT '角色编码',
    `description` varchar(255) DEFAULT NULL COMMENT '描述',
    `create_by`   varchar(32)  DEFAULT NULL COMMENT '创建人',
    `create_time` datetime     DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(32)  DEFAULT NULL COMMENT '更新人',
    `update_time` datetime     DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uniq_sr_role_code` (`role_code`),
    KEY `idx_sr_role_code` (`role_code`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC COMMENT ='角色表';

/*Data for the table `sys_role` */

insert into `sys_role`(`id`, `role_name`, `role_code`, `description`, `create_by`, `create_time`, `update_by`,
                       `update_time`)
values (1468855748988637184, '库存管理员', 'ware_manager', '库存服务管理', 'admin', '2021-12-09 16:11:15', NULL, NULL),
       (1468855856593506304, '订单管理员', 'order_manager', '订单服务管理', 'admin', '2021-12-09 16:11:41', NULL, NULL);

/*Table structure for table `sys_role_permission` */

DROP TABLE IF EXISTS `sys_role_permission`;

CREATE TABLE `sys_role_permission`
(
    `id`            bigint(20) NOT NULL,
    `role_id`       bigint(20)    DEFAULT NULL COMMENT '角色id',
    `permission_id` bigint(20)    DEFAULT NULL COMMENT '权限id',
    `data_rule_ids` varchar(1000) DEFAULT NULL COMMENT '数据权限ids',
    `operate_date`  datetime      DEFAULT NULL COMMENT '操作时间',
    `operate_ip`    int(11)       DEFAULT NULL COMMENT '操作ip',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `index_group_role_per_id` (`role_id`, `permission_id`) USING BTREE,
    KEY `index_group_role_id` (`role_id`) USING BTREE,
    KEY `index_group_per_id` (`permission_id`) USING BTREE,
    KEY `idx_srp_role_per_id` (`role_id`, `permission_id`) USING BTREE,
    KEY `idx_srp_role_id` (`role_id`) USING BTREE,
    KEY `idx_srp_permission_id` (`permission_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC COMMENT ='角色权限表';

/*Data for the table `sys_role_permission` */

/*Table structure for table `sys_third_account` */

DROP TABLE IF EXISTS `sys_third_account`;

CREATE TABLE `sys_third_account`
(
    `id`              bigint(20) NOT NULL COMMENT '编号',
    `user_id`         bigint(20)   DEFAULT NULL COMMENT '第三方登录id',
    `avatar`          varchar(255) DEFAULT NULL COMMENT '头像',
    `status`          tinyint(1)   DEFAULT NULL COMMENT '状态(1-正常,2-冻结)',
    `is_del`          tinyint(1)   DEFAULT NULL COMMENT '删除状态(0-正常,1-已删除)',
    `realname`        varchar(100) DEFAULT NULL COMMENT '真实姓名',
    `third_user_uuid` varchar(100) DEFAULT NULL COMMENT '第三方账号',
    `third_user_id`   varchar(100) DEFAULT NULL COMMENT '第三方app用户账号',
    `third_type`      varchar(50)  DEFAULT NULL COMMENT '登录来源',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uniq_sys_third_account_third_type_third_user_id` (`third_type`, `third_user_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC;

/*Data for the table `sys_third_account` */

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user`
(
    `userid`      varchar(32) NOT NULL COMMENT '主键id',
    `username`    varchar(100) DEFAULT NULL COMMENT '登录账号',
    `real_name`   varchar(100) DEFAULT NULL COMMENT '真实姓名',
    `password`    varchar(255) DEFAULT NULL COMMENT '密码',
    `avatar`      varchar(511) DEFAULT NULL COMMENT '头像',
    `birthday`    date         DEFAULT NULL COMMENT '生日',
    `gender`      tinyint(1)   DEFAULT NULL COMMENT '性别(0-默认保密,1-男,2-女)',
    `email`       varchar(45)  DEFAULT NULL COMMENT '电子邮件',
    `phone`       varchar(45)  DEFAULT NULL COMMENT '电话',
    `status`      tinyint(1)   DEFAULT NULL COMMENT '状态(1-正常,2-冻结)',
    `delete`      tinyint(1)   DEFAULT NULL COMMENT '删除状态(0-正常,1-已删除)',
    `create_by`   varchar(32)  DEFAULT NULL COMMENT '创建人',
    `create_time` datetime     DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(32)  DEFAULT NULL COMMENT '更新人',
    `update_time` datetime     DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`userid`),
    UNIQUE KEY `uniq_su_email` (`email`),
    UNIQUE KEY `uniq_su_username` (`username`),
    UNIQUE KEY `uniq_su_phone` (`phone`),
    KEY `idx_su_username` (`username`),
    KEY `idx_su_status` (`status`),
    KEY `idx_su_is_delete` (`delete`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC COMMENT ='用户表';

/*Data for the table `sys_user` */

insert into `sys_user`(`userid`, `username`, `real_name`, `password`, `avatar`, `birthday`, `gender`, `email`, `phone`,
                       `status`, `delete`, `create_by`, `create_time`, `update_by`, `update_time`)
values ('1463452828315029504', 'wangjie', '王杰', '$2a$10$pabNigivrJNadv7.CGus8.tfqnZ.jpWeYp/C2UoR2Y8.isnRHrykS',
        'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg9.doubanio.com%2Fview%2Fgroup_topic%2Fl%2Fpublic%2Fp192244425.jpg&refer=http%3A%2F%2Fimg9.doubanio.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1647422947&t=56358da60f52c59b03eec3abc7dea6bf',
        '1998-08-30', 0, '18763096838@163.com', '18763096838', 1, 0, 'admin', '2021-11-24 18:21:59', NULL, NULL),
       ('1493468819329519616', 'admin', '管理员', '$2a$10$KN7XTwFtpUwVgoVJrq2VZOthDHxfSnf0de5kVx7cwSi.Y/uPP8Ty.',
        'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fup.enterdesk.com%2Fedpic%2F69%2F5f%2Fa7%2F695fa728c162c2cb073d7e0079dfdee5.jpeg&refer=http%3A%2F%2Fup.enterdesk.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1647497570&t=ec87f37033839d93bfc8b10be8031e31',
        '1998-08-20', 0, '123456789@163.com', '13390908080', 1, 0, 'root', '2022-02-15 14:14:49', NULL, NULL),
       ('1493469684618629120', 'zhangsan', '张三', '$2a$10$OAQQ3a6daUk8.cngekzUquQUO6DYCg9egyB2U3TqsMeXHqaGyaxHK',
        'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fup.enterdesk.com%2Fedpic%2F69%2F5f%2Fa7%2F695fa728c162c2cb073d7e0079dfdee5.jpeg&refer=http%3A%2F%2Fup.enterdesk.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1647497570&t=ec87f37033839d93bfc8b10be8031e31',
        '1998-08-20', 0, '784217549@163.com', '13390907070', 1, 0, 'admin', '2022-02-15 14:18:15', NULL, NULL),
       ('1493470195535187968', 'root', '超级管理员', '$2a$10$ozeJUCSnnQZmx5/LPbqO7ueKtXz2cORvm4NiZ2Y38t3i33s/gQmDm',
        'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fup.enterdesk.com%2Fedpic%2F69%2F5f%2Fa7%2F695fa728c162c2cb073d7e0079dfdee5.jpeg&refer=http%3A%2F%2Fup.enterdesk.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1647497570&t=ec87f37033839d93bfc8b10be8031e31',
        '1998-08-20', 0, '0000000@163.com', '13312345678', 1, 0, 'admin', '2022-02-15 14:20:17', NULL, NULL),
       ('1493470641809133568', 'lisi', '李四', '$2a$10$.3ViS1pYsPZO5Pz6g83R0OTGEa6SmdEqlFZUisvCETvV5hIISn0/q',
        'https://img0.baidu.com/it/u=4044314804,383808458&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500', '1996-09-20', 0,
        '000123141@163.com', '13333335678', 1, 0, 'admin', '2022-02-15 14:22:03', NULL, NULL),
       ('1493471308288233472', 'wangwu', '王五', '$2a$10$ddYGFWXrGy5sCuqaO4qM7eVSqEagGbxAPfibx3QFCGToWswwe2AbW',
        'https://img0.baidu.com/it/u=4044314804,383808458&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500', '1996-09-20', 0,
        '9999999@qq.com', '13377777777', 1, 0, 'admin', '2022-02-15 14:24:42', NULL, NULL);

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role`
(
    `id`      bigint(20) NOT NULL COMMENT '主键id',
    `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
    `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_sur_user_id` (`user_id`) USING BTREE,
    KEY `idx_sur_role_id` (`role_id`) USING BTREE,
    KEY `idx_sur_user_role_id` (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC COMMENT ='用户角色表';

/*Data for the table `sys_user_role` */

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;
