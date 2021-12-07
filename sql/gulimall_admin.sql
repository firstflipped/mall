/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 5.7.34 : Database - gulimall_admin
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE = ''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS */`gulimall_admin` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `gulimall_admin`;

/*Table structure for table `QRTZ_CALENDARS` */

DROP TABLE IF EXISTS `QRTZ_CALENDARS`;

CREATE TABLE `QRTZ_CALENDARS`
(
    `SCHED_NAME`    varchar(120) NOT NULL,
    `CALENDAR_NAME` varchar(200) NOT NULL,
    `CALENDAR`      blob         NOT NULL,
    PRIMARY KEY (`SCHED_NAME`, `CALENDAR_NAME`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*Data for the table `QRTZ_CALENDARS` */

/*Table structure for table `QRTZ_FIRED_TRIGGERS` */

DROP TABLE IF EXISTS `QRTZ_FIRED_TRIGGERS`;

CREATE TABLE `QRTZ_FIRED_TRIGGERS`
(
    `SCHED_NAME`        varchar(120) NOT NULL,
    `ENTRY_ID`          varchar(95)  NOT NULL,
    `TRIGGER_NAME`      varchar(200) NOT NULL,
    `TRIGGER_GROUP`     varchar(200) NOT NULL,
    `INSTANCE_NAME`     varchar(200) NOT NULL,
    `FIRED_TIME`        bigint(13)   NOT NULL,
    `SCHED_TIME`        bigint(13)   NOT NULL,
    `PRIORITY`          int(11)      NOT NULL,
    `STATE`             varchar(16)  NOT NULL,
    `JOB_NAME`          varchar(200) DEFAULT NULL,
    `JOB_GROUP`         varchar(200) DEFAULT NULL,
    `IS_NONCONCURRENT`  varchar(1)   DEFAULT NULL,
    `REQUESTS_RECOVERY` varchar(1)   DEFAULT NULL,
    PRIMARY KEY (`SCHED_NAME`, `ENTRY_ID`),
    KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`, `INSTANCE_NAME`),
    KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`, `INSTANCE_NAME`, `REQUESTS_RECOVERY`),
    KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`),
    KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`, `JOB_GROUP`),
    KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`),
    KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`, `TRIGGER_GROUP`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*Data for the table `QRTZ_FIRED_TRIGGERS` */

/*Table structure for table `QRTZ_JOB_DETAILS` */

DROP TABLE IF EXISTS `QRTZ_JOB_DETAILS`;

CREATE TABLE `QRTZ_JOB_DETAILS`
(
    `SCHED_NAME`        varchar(120) NOT NULL,
    `JOB_NAME`          varchar(200) NOT NULL,
    `JOB_GROUP`         varchar(200) NOT NULL,
    `DESCRIPTION`       varchar(250) DEFAULT NULL,
    `JOB_CLASS_NAME`    varchar(250) NOT NULL,
    `IS_DURABLE`        varchar(1)   NOT NULL,
    `IS_NONCONCURRENT`  varchar(1)   NOT NULL,
    `IS_UPDATE_DATA`    varchar(1)   NOT NULL,
    `REQUESTS_RECOVERY` varchar(1)   NOT NULL,
    `JOB_DATA`          blob,
    PRIMARY KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`),
    KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`, `REQUESTS_RECOVERY`),
    KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`, `JOB_GROUP`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*Data for the table `QRTZ_JOB_DETAILS` */

/*Table structure for table `QRTZ_LOCKS` */

DROP TABLE IF EXISTS `QRTZ_LOCKS`;

CREATE TABLE `QRTZ_LOCKS`
(
    `SCHED_NAME` varchar(120) NOT NULL,
    `LOCK_NAME`  varchar(40)  NOT NULL,
    PRIMARY KEY (`SCHED_NAME`, `LOCK_NAME`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*Data for the table `QRTZ_LOCKS` */

insert into `QRTZ_LOCKS`(`SCHED_NAME`, `LOCK_NAME`)
values ('RenrenScheduler', 'STATE_ACCESS'),
       ('RenrenScheduler', 'TRIGGER_ACCESS');

/*Table structure for table `QRTZ_PAUSED_TRIGGER_GRPS` */

DROP TABLE IF EXISTS `QRTZ_PAUSED_TRIGGER_GRPS`;

CREATE TABLE `QRTZ_PAUSED_TRIGGER_GRPS`
(
    `SCHED_NAME`    varchar(120) NOT NULL,
    `TRIGGER_GROUP` varchar(200) NOT NULL,
    PRIMARY KEY (`SCHED_NAME`, `TRIGGER_GROUP`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*Data for the table `QRTZ_PAUSED_TRIGGER_GRPS` */

/*Table structure for table `QRTZ_SCHEDULER_STATE` */

DROP TABLE IF EXISTS `QRTZ_SCHEDULER_STATE`;

CREATE TABLE `QRTZ_SCHEDULER_STATE`
(
    `SCHED_NAME`        varchar(120) NOT NULL,
    `INSTANCE_NAME`     varchar(200) NOT NULL,
    `LAST_CHECKIN_TIME` bigint(13)   NOT NULL,
    `CHECKIN_INTERVAL`  bigint(13)   NOT NULL,
    PRIMARY KEY (`SCHED_NAME`, `INSTANCE_NAME`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*Data for the table `QRTZ_SCHEDULER_STATE` */

insert into `QRTZ_SCHEDULER_STATE`(`SCHED_NAME`, `INSTANCE_NAME`, `LAST_CHECKIN_TIME`, `CHECKIN_INTERVAL`)
values ('RenrenScheduler', 'Flipped1638427707853', 1638438099174, 15000);

/*Table structure for table `QRTZ_TRIGGERS` */

DROP TABLE IF EXISTS `QRTZ_TRIGGERS`;

CREATE TABLE `QRTZ_TRIGGERS`
(
    `SCHED_NAME`     varchar(120) NOT NULL,
    `TRIGGER_NAME`   varchar(200) NOT NULL,
    `TRIGGER_GROUP`  varchar(200) NOT NULL,
    `JOB_NAME`       varchar(200) NOT NULL,
    `JOB_GROUP`      varchar(200) NOT NULL,
    `DESCRIPTION`    varchar(250) DEFAULT NULL,
    `NEXT_FIRE_TIME` bigint(13)   DEFAULT NULL,
    `PREV_FIRE_TIME` bigint(13)   DEFAULT NULL,
    `PRIORITY`       int(11)      DEFAULT NULL,
    `TRIGGER_STATE`  varchar(16)  NOT NULL,
    `TRIGGER_TYPE`   varchar(8)   NOT NULL,
    `START_TIME`     bigint(13)   NOT NULL,
    `END_TIME`       bigint(13)   DEFAULT NULL,
    `CALENDAR_NAME`  varchar(200) DEFAULT NULL,
    `MISFIRE_INSTR`  smallint(2)  DEFAULT NULL,
    `JOB_DATA`       blob,
    PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`),
    KEY `IDX_QRTZ_T_J` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`),
    KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`, `JOB_GROUP`),
    KEY `IDX_QRTZ_T_C` (`SCHED_NAME`, `CALENDAR_NAME`),
    KEY `IDX_QRTZ_T_G` (`SCHED_NAME`, `TRIGGER_GROUP`),
    KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`, `TRIGGER_STATE`),
    KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `TRIGGER_STATE`),
    KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`, `TRIGGER_GROUP`, `TRIGGER_STATE`),
    KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`, `NEXT_FIRE_TIME`),
    KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`, `TRIGGER_STATE`, `NEXT_FIRE_TIME`),
    KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`),
    KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`, `TRIGGER_STATE`),
    KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`, `TRIGGER_GROUP`,
                                         `TRIGGER_STATE`),
    CONSTRAINT `QRTZ_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `QRTZ_JOB_DETAILS` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*Data for the table `QRTZ_TRIGGERS` */

/*Table structure for table `mq_message` */

DROP TABLE IF EXISTS `mq_message`;

CREATE TABLE `mq_message`
(
    `message_id`     bigint(20) NOT NULL AUTO_INCREMENT,
    `content`        text,
    `to_exchange`    varchar(255) DEFAULT NULL,
    `routing_key`    varchar(255) DEFAULT NULL,
    `class_type`     varchar(255) DEFAULT NULL,
    `message_status` tinyint(4)   DEFAULT '0' COMMENT '0-新建 1-已发送 2-错误抵达 3-已抵达',
    `create_time`    datetime     DEFAULT NULL,
    `update_time`    datetime     DEFAULT NULL,
    PRIMARY KEY (`message_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

/*Data for the table `mq_message` */

/*Table structure for table `schedule_job` */

DROP TABLE IF EXISTS `schedule_job`;

CREATE TABLE `schedule_job`
(
    `job_id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务id',
    `bean_name`       varchar(200)  DEFAULT NULL COMMENT 'spring bean名称',
    `params`          varchar(2000) DEFAULT NULL COMMENT '参数',
    `cron_expression` varchar(100)  DEFAULT NULL COMMENT 'cron表达式',
    `status`          tinyint(4)    DEFAULT NULL COMMENT '任务状态  0：正常  1：暂停',
    `remark`          varchar(255)  DEFAULT NULL COMMENT '备注',
    `create_time`     datetime      DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`job_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='定时任务';

/*Data for the table `schedule_job` */

/*Table structure for table `schedule_job_log` */

DROP TABLE IF EXISTS `schedule_job_log`;

CREATE TABLE `schedule_job_log`
(
    `log_id`      bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志id',
    `job_id`      bigint(20) NOT NULL COMMENT '任务id',
    `bean_name`   varchar(200)  DEFAULT NULL COMMENT 'spring bean名称',
    `params`      varchar(2000) DEFAULT NULL COMMENT '参数',
    `status`      tinyint(4) NOT NULL COMMENT '任务状态    0：成功    1：失败',
    `error`       varchar(2000) DEFAULT NULL COMMENT '失败信息',
    `times`       int(11)    NOT NULL COMMENT '耗时(单位：毫秒)',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`log_id`),
    KEY `job_id` (`job_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='定时任务日志';

/*Data for the table `schedule_job_log` */

/*Table structure for table `sys_captcha` */

DROP TABLE IF EXISTS `sys_captcha`;

CREATE TABLE `sys_captcha`
(
    `uuid`        char(36)   NOT NULL COMMENT 'uuid',
    `code`        varchar(6) NOT NULL COMMENT '验证码',
    `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
    PRIMARY KEY (`uuid`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='系统验证码';

/*Data for the table `sys_captcha` */

insert into `sys_captcha`(`uuid`, `code`, `expire_time`)
values ('5ef59ed5-7455-4f7e-8820-f951c1ada3ee', 'fw7cw', '2021-09-13 09:09:18'),
       ('70103c8c-9a18-47f0-8db7-03050ba26e5d', 'mxgy8', '2021-08-23 13:55:39'),
       ('82fd01ce-c71a-461d-81b1-0a518c0dcb3b', '34ncw', '2021-12-02 14:54:11'),
       ('ce329760-105a-494a-8b52-58d906793dbf', 'a2eax', '2021-08-23 13:55:26');

/*Table structure for table `sys_config` */

DROP TABLE IF EXISTS `sys_config`;

CREATE TABLE `sys_config`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `param_key`   varchar(50)   DEFAULT NULL COMMENT 'key',
    `param_value` varchar(2000) DEFAULT NULL COMMENT 'value',
    `status`      tinyint(4)    DEFAULT '1' COMMENT '状态   0：隐藏   1：显示',
    `remark`      varchar(500)  DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`),
    UNIQUE KEY `param_key` (`param_key`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4 COMMENT ='系统配置信息表';

/*Data for the table `sys_config` */

insert into `sys_config`(`id`, `param_key`, `param_value`, `status`, `remark`)
values (1, 'CLOUD_STORAGE_CONFIG_KEY',
        '{\"aliyunAccessKeyId\":\"\",\"aliyunAccessKeySecret\":\"\",\"aliyunBucketName\":\"\",\"aliyunDomain\":\"\",\"aliyunEndPoint\":\"\",\"aliyunPrefix\":\"\",\"qcloudBucketName\":\"\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qiniuAccessKey\":\"NrgMfABZxWLo5B-YYSjoE8-AZ1EISdi1Z3ubLOeZ\",\"qiniuBucketName\":\"ios-app\",\"qiniuDomain\":\"http://7xqbwh.dl1.z0.glb.clouddn.com\",\"qiniuPrefix\":\"upload\",\"qiniuSecretKey\":\"uIwJHevMRWU0VLxFvgy0tAcOdGqasdtVlJkdy6vV\",\"type\":1}',
        0, '云存储配置信息');

/*Table structure for table `sys_log` */

DROP TABLE IF EXISTS `sys_log`;

CREATE TABLE `sys_log`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `username`    varchar(50)   DEFAULT NULL COMMENT '用户名',
    `operation`   varchar(50)   DEFAULT NULL COMMENT '用户操作',
    `method`      varchar(200)  DEFAULT NULL COMMENT '请求方法',
    `params`      varchar(5000) DEFAULT NULL COMMENT '请求参数',
    `time`        bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
    `ip`          varchar(64)   DEFAULT NULL COMMENT 'IP地址',
    `create_date` datetime      DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 6
  DEFAULT CHARSET = utf8mb4 COMMENT ='系统日志';

/*Data for the table `sys_log` */

insert into `sys_log`(`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`)
values (1, 'admin', '保存菜单', 'com.laughingather.gulimall.admin.modules.sys.controller.SysMenuController.save()',
        '[{\"menuId\":31,\"parentId\":0,\"name\":\"商品系统\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"shoucang\",\"orderNum\":0}]',
        88, '0:0:0:0:0:0:0:1', '2021-04-16 20:32:14'),
       (2, 'admin', '保存菜单', 'com.laughingather.gulimall.admin.modules.sys.controller.SysMenuController.save()',
        '[{\"menuId\":32,\"parentId\":31,\"name\":\"分类管理\",\"url\":\"product/category\",\"type\":1,\"icon\":\"menu\",\"orderNum\":0}]',
        84, '0:0:0:0:0:0:0:1', '2021-04-16 20:34:53'),
       (3, 'admin', '保存菜单', 'com.laughingather.gulimall.admin.modules.sys.controller.SysMenuController.save()',
        '[{\"menuId\":33,\"parentId\":0,\"name\":\"品牌管理\",\"url\":\"product/brand\",\"perms\":\"\",\"type\":1,\"icon\":\"xiangqufill\",\"orderNum\":0}]',
        60, '0:0:0:0:0:0:0:1', '2021-04-19 14:45:50'),
       (4, 'admin', '修改菜单', 'com.laughingather.gulimall.admin.modules.sys.controller.SysMenuController.update()',
        '[{\"menuId\":33,\"parentId\":31,\"name\":\"品牌管理\",\"url\":\"product/brand\",\"perms\":\"\",\"type\":1,\"icon\":\"xiangqufill\",\"orderNum\":0}]',
        136, '0:0:0:0:0:0:0:1', '2021-04-19 14:46:07'),
       (5, 'admin', '保存用户', 'com.laughingather.gulimall.admin.modules.sys.controller.SysUserController.save()',
        '[{\"userId\":2,\"username\":\"wangjie\",\"password\":\"35c477810fa3806106dc0c11c6e462a8200f70aebe347925f1aae2384bd453e0\",\"salt\":\"cpqYT49Hw8VHjGSfLYp5\",\"email\":\"001@163.com\",\"mobile\":\"17515236789\",\"status\":1,\"roleIdList\":[],\"createUserId\":1,\"createTime\":\"Apr 29, 2021 12:50:44 PM\"}]',
        251, '0:0:0:0:0:0:0:1', '2021-04-29 12:50:45');

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu`
(
    `menu_id`   bigint(20) NOT NULL AUTO_INCREMENT,
    `parent_id` bigint(20)   DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
    `name`      varchar(50)  DEFAULT NULL COMMENT '菜单名称',
    `url`       varchar(200) DEFAULT NULL COMMENT '菜单URL',
    `perms`     varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
    `type`      int(11)      DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
    `icon`      varchar(50)  DEFAULT NULL COMMENT '菜单图标',
    `order_num` int(11)      DEFAULT NULL COMMENT '排序',
    PRIMARY KEY (`menu_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 76
  DEFAULT CHARSET = utf8mb4 COMMENT ='菜单管理';

/*Data for the table `sys_menu` */

insert into `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
values (1, 0, '系统管理', NULL, NULL, 0, 'system', 0),
       (2, 1, '管理员列表', 'sys/user', NULL, 1, 'admin', 1),
       (3, 1, '角色管理', 'sys/role', NULL, 1, 'role', 2),
       (4, 1, '菜单管理', 'sys/menu', NULL, 1, 'menu', 3),
       (5, 1, 'SQL监控', 'http://localhost:8080/gulimall-admin/druid/sql.html', NULL, 1, 'sql', 4),
       (6, 1, '定时任务', 'job/schedule', NULL, 1, 'job', 5),
       (7, 6, '查看', NULL, 'sys:schedule:list,sys:schedule:info', 2, NULL, 0),
       (8, 6, '新增', NULL, 'sys:schedule:save', 2, NULL, 0),
       (9, 6, '修改', NULL, 'sys:schedule:update', 2, NULL, 0),
       (10, 6, '删除', NULL, 'sys:schedule:delete', 2, NULL, 0),
       (11, 6, '暂停', NULL, 'sys:schedule:pause', 2, NULL, 0),
       (12, 6, '恢复', NULL, 'sys:schedule:resume', 2, NULL, 0),
       (13, 6, '立即执行', NULL, 'sys:schedule:run', 2, NULL, 0),
       (14, 6, '日志列表', NULL, 'sys:schedule:log', 2, NULL, 0),
       (15, 2, '查看', NULL, 'sys:user:list,sys:user:info', 2, NULL, 0),
       (16, 2, '新增', NULL, 'sys:user:save,sys:role:select', 2, NULL, 0),
       (17, 2, '修改', NULL, 'sys:user:update,sys:role:select', 2, NULL, 0),
       (18, 2, '删除', NULL, 'sys:user:delete', 2, NULL, 0),
       (19, 3, '查看', NULL, 'sys:role:list,sys:role:info', 2, NULL, 0),
       (20, 3, '新增', NULL, 'sys:role:save,sys:menu:list', 2, NULL, 0),
       (21, 3, '修改', NULL, 'sys:role:update,sys:menu:list', 2, NULL, 0),
       (22, 3, '删除', NULL, 'sys:role:delete', 2, NULL, 0),
       (23, 4, '查看', NULL, 'sys:menu:list,sys:menu:info', 2, NULL, 0),
       (24, 4, '新增', NULL, 'sys:menu:save,sys:menu:select', 2, NULL, 0),
       (25, 4, '修改', NULL, 'sys:menu:update,sys:menu:select', 2, NULL, 0),
       (26, 4, '删除', NULL, 'sys:menu:delete', 2, NULL, 0),
       (27, 1, '参数管理', 'sys/config',
        'sys:config:list,sys:config:info,sys:config:save,sys:config:update,sys:config:delete', 1, 'config', 6),
       (29, 1, '系统日志', 'sys/log', 'sys:log:list', 1, 'log', 7),
       (30, 1, '文件上传', 'oss/oss', 'sys:oss:all', 1, 'oss', 6),
       (31, 0, '商品系统', '', '', 0, 'editor', 0),
       (32, 31, '分类管理', 'product/category', '', 1, 'menu', 0),
       (34, 31, '品牌管理', 'product/brand', '', 1, 'editor', 0),
       (37, 31, '属性管理', '', '', 0, 'system', 0),
       (38, 37, '属性分组', 'product/attrgroup', '', 1, 'tubiao', 0),
       (39, 37, '规格参数', 'product/baseattr', '', 1, 'log', 0),
       (40, 37, '销售属性', 'product/saleattr', '', 1, 'zonghe', 0),
       (41, 31, '商品维护', 'product/spu', '', 0, 'zonghe', 0),
       (42, 0, '优惠营销', '', '', 0, 'mudedi', 0),
       (43, 0, '库存系统', '', '', 0, 'shouye', 0),
       (44, 0, '订单系统', '', '', 0, 'config', 0),
       (45, 0, '用户系统', '', '', 0, 'admin', 0),
       (46, 0, '内容管理', '', '', 0, 'sousuo', 0),
       (47, 42, '优惠券管理', 'coupon/coupon', '', 1, 'zhedie', 0),
       (48, 42, '发放记录', 'coupon/history', '', 1, 'sql', 0),
       (49, 42, '专题活动', 'coupon/subject', '', 1, 'tixing', 0),
       (50, 42, '秒杀活动', 'coupon/seckill', '', 1, 'daohang', 0),
       (51, 42, '积分维护', 'coupon/bounds', '', 1, 'geren', 0),
       (52, 42, '满减折扣', 'coupon/full', '', 1, 'shoucang', 0),
       (53, 43, '仓库维护', 'ware/wareinfo', '', 1, 'shouye', 0),
       (54, 43, '库存工作单', 'ware/task', '', 1, 'log', 0),
       (55, 43, '商品库存', 'ware/sku', '', 1, 'jiesuo', 0),
       (56, 44, '订单查询', 'order/order', '', 1, 'zhedie', 0),
       (57, 44, '退货单处理', 'order/return', '', 1, 'shanchu', 0),
       (58, 44, '等级规则', 'order/settings', '', 1, 'system', 0),
       (59, 44, '支付流水查询', 'order/payment', '', 1, 'job', 0),
       (60, 44, '退款流水查询', 'order/refund', '', 1, 'mudedi', 0),
       (61, 45, '会员列表', 'member/member', '', 1, 'geren', 0),
       (62, 45, '会员等级', 'member/level', '', 1, 'tubiao', 0),
       (63, 45, '积分变化', 'member/growth', '', 1, 'bianji', 0),
       (64, 45, '统计信息', 'member/statistics', '', 1, 'sql', 0),
       (65, 46, '首页推荐', 'content/index', '', 1, 'shouye', 0),
       (66, 46, '分类热门', 'content/category', '', 1, 'zhedie', 0),
       (67, 46, '评论管理', 'content/comments', '', 1, 'pinglun', 0),
       (68, 41, 'spu管理', 'product/spu', '', 1, 'config', 0),
       (69, 41, '发布商品', 'product/spuadd', '', 1, 'bianji', 0),
       (70, 43, '采购单维护', '', '', 0, 'tubiao', 0),
       (71, 70, '采购需求', 'ware/purchaseitem', '', 1, 'editor', 0),
       (72, 70, '采购单', 'ware/purchase', '', 1, 'menu', 0),
       (73, 41, '商品管理', 'product/manager', '', 1, 'zonghe', 0),
       (74, 42, '会员价格', 'coupon/memberprice', '', 1, 'admin', 0),
       (75, 42, '每日秒杀', 'coupon/seckillsession', '', 1, 'job', 0);

/*Table structure for table `sys_oss` */

DROP TABLE IF EXISTS `sys_oss`;

CREATE TABLE `sys_oss`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `url`         varchar(200) DEFAULT NULL COMMENT 'URL地址',
    `create_date` datetime     DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='文件上传';

/*Data for the table `sys_oss` */

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role`
(
    `role_id`        bigint(20) NOT NULL AUTO_INCREMENT,
    `role_name`      varchar(100) DEFAULT NULL COMMENT '角色名称',
    `remark`         varchar(100) DEFAULT NULL COMMENT '备注',
    `create_user_id` bigint(20)   DEFAULT NULL COMMENT '创建者ID',
    `create_time`    datetime     DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`role_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='角色';

/*Data for the table `sys_role` */

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu`
(
    `id`      bigint(20) NOT NULL AUTO_INCREMENT,
    `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
    `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='角色与菜单对应关系';

/*Data for the table `sys_role_menu` */

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user`
(
    `user_id`        bigint(20)  NOT NULL AUTO_INCREMENT,
    `username`       varchar(50) NOT NULL COMMENT '用户名',
    `password`       varchar(100) DEFAULT NULL COMMENT '密码',
    `salt`           varchar(20)  DEFAULT NULL COMMENT '盐',
    `email`          varchar(100) DEFAULT NULL COMMENT '邮箱',
    `mobile`         varchar(100) DEFAULT NULL COMMENT '手机号',
    `status`         tinyint(4)   DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
    `create_user_id` bigint(20)   DEFAULT NULL COMMENT '创建者ID',
    `create_time`    datetime     DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`user_id`),
    UNIQUE KEY `username` (`username`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4 COMMENT ='系统用户';

/*Data for the table `sys_user` */

insert into `sys_user`(`user_id`, `username`, `password`, `salt`, `email`, `mobile`, `status`, `create_user_id`,
                       `create_time`)
values (1, 'admin', '9ec9750e709431dad22365cabc5c625482e574c74adaebba7dd02f1129e4ce1d', 'YzcmCZNvbXocrsz9dm8e',
        'root@renren.io', '13612345678', 1, 1, '2016-11-11 11:11:11'),
       (2, 'wangjie', '35c477810fa3806106dc0c11c6e462a8200f70aebe347925f1aae2384bd453e0', 'cpqYT49Hw8VHjGSfLYp5',
        '001@163.com', '17515236789', 1, 1, '2021-04-29 12:50:45');

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role`
(
    `id`      bigint(20) NOT NULL AUTO_INCREMENT,
    `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
    `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户与角色对应关系';

/*Data for the table `sys_user_role` */

/*Table structure for table `sys_user_token` */

DROP TABLE IF EXISTS `sys_user_token`;

CREATE TABLE `sys_user_token`
(
    `user_id`     bigint(20)   NOT NULL,
    `token`       varchar(100) NOT NULL COMMENT 'token',
    `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`user_id`),
    UNIQUE KEY `token` (`token`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='系统用户Token';

/*Data for the table `sys_user_token` */

insert into `sys_user_token`(`user_id`, `token`, `expire_time`, `update_time`)
values (1, 'dd080f7b8defaba4f3ead2e210d01762', '2021-12-03 02:49:33', '2021-12-02 14:49:33');

/*Table structure for table `tb_user` */

DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user`
(
    `user_id`     bigint(20)  NOT NULL AUTO_INCREMENT,
    `username`    varchar(50) NOT NULL COMMENT '用户名',
    `mobile`      varchar(20) NOT NULL COMMENT '手机号',
    `password`    varchar(64) DEFAULT NULL COMMENT '密码',
    `create_time` datetime    DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`user_id`),
    UNIQUE KEY `username` (`username`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户';

/*Data for the table `tb_user` */

insert into `tb_user`(`user_id`, `username`, `mobile`, `password`, `create_time`)
values (1, 'mark', '13612345678', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918',
        '2017-03-23 22:37:41');

/*Table structure for table `undo_log` */

DROP TABLE IF EXISTS `undo_log`;

CREATE TABLE `undo_log`
(
    `id`            bigint(20)   NOT NULL AUTO_INCREMENT,
    `branch_id`     bigint(20)   NOT NULL,
    `xid`           varchar(100) NOT NULL,
    `context`       varchar(128) NOT NULL,
    `rollback_info` longblob     NOT NULL,
    `log_status`    int(11)      NOT NULL,
    `log_created`   datetime     NOT NULL,
    `log_modified`  datetime     NOT NULL,
    `ext`           varchar(100) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `ux_undo_log` (`xid`, `branch_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*Data for the table `undo_log` */

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;
