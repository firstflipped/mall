/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 5.7.32 : Database - gulimall_admin
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE = ''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS */`gulimall_admin` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `gulimall_admin`;

/*Table structure for table `QRTZ_BLOB_TRIGGERS` */

DROP TABLE IF EXISTS `QRTZ_BLOB_TRIGGERS`;

CREATE TABLE `QRTZ_BLOB_TRIGGERS`
(
    `SCHED_NAME`    varchar(120) NOT NULL,
    `TRIGGER_NAME`  varchar(200) NOT NULL,
    `TRIGGER_GROUP` varchar(200) NOT NULL,
    `BLOB_DATA`     blob,
    PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`),
    KEY `SCHED_NAME` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`),
    CONSTRAINT `QRTZ_BLOB_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*Data for the table `QRTZ_BLOB_TRIGGERS` */

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

/*Table structure for table `QRTZ_CRON_TRIGGERS` */

DROP TABLE IF EXISTS `QRTZ_CRON_TRIGGERS`;

CREATE TABLE `QRTZ_CRON_TRIGGERS`
(
    `SCHED_NAME`      varchar(120) NOT NULL,
    `TRIGGER_NAME`    varchar(200) NOT NULL,
    `TRIGGER_GROUP`   varchar(200) NOT NULL,
    `CRON_EXPRESSION` varchar(120) NOT NULL,
    `TIME_ZONE_ID`    varchar(80) DEFAULT NULL,
    PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`),
    CONSTRAINT `QRTZ_CRON_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*Data for the table `QRTZ_CRON_TRIGGERS` */

insert into `QRTZ_CRON_TRIGGERS`(`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `CRON_EXPRESSION`, `TIME_ZONE_ID`)
values ('RenrenScheduler', 'TASK_1', 'DEFAULT', '0 0/30 * * * ?', 'Asia/Shanghai');

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

insert into `QRTZ_JOB_DETAILS`(`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`, `DESCRIPTION`, `JOB_CLASS_NAME`, `IS_DURABLE`,
                               `IS_NONCONCURRENT`, `IS_UPDATE_DATA`, `REQUESTS_RECOVERY`, `JOB_DATA`)
values ('RenrenScheduler', 'TASK_1', 'DEFAULT', NULL, 'com.laughingather.gulimall.admin.modules.job.utils.ScheduleJob',
        '0', '0', '0', '0',
        '��\0sr\0org.quartz.JobDataMap���迩��\0\0xr\0&org.quartz.utils.StringKeyDirtyFlagMap�����](\0Z\0allowsTransientDataxr\0org.quartz.utils.DirtyFlagMap�.�(v\n�\0Z\0dirtyL\0mapt\0Ljava/util/Map;xpsr\0java.util.HashMap���`�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\rJOB_PARAM_KEYsr\0Ecom.laughingather.gulimall.admin.modules.job.entity.ScheduleJobEntity\0\0\0\0\0\0\0\0L\0beanNamet\0Ljava/lang/String;L\0\ncreateTimet\0Ljava/util/Date;L\0cronExpressionq\0~\0	L\0jobIdt\0Ljava/lang/Long;L\0paramsq\0~\0	L\0remarkq\0~\0	L\0statust\0Ljava/lang/Integer;xpt\0testTasksr\0java.util.Datehj�KYt\0\0xpw\0\0v6+�@xt\00 0/30 * * * ?sr\0java.lang.Long;��̏#�\0J\0valuexr\0java.lang.Number������\0\0xp\0\0\0\0\0\0\0t\0renrent\0参数测试sr\0java.lang.Integer⠤���8\0I\0valuexq\0~\0\0\0\0\0x\0');

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
values ('RenrenScheduler', 'LAPTOP-EBF4ULUI1621678372086', 1621687818565, 15000);

/*Table structure for table `QRTZ_SIMPLE_TRIGGERS` */

DROP TABLE IF EXISTS `QRTZ_SIMPLE_TRIGGERS`;

CREATE TABLE `QRTZ_SIMPLE_TRIGGERS`
(
    `SCHED_NAME`      varchar(120) NOT NULL,
    `TRIGGER_NAME`    varchar(200) NOT NULL,
    `TRIGGER_GROUP`   varchar(200) NOT NULL,
    `REPEAT_COUNT`    bigint(7)    NOT NULL,
    `REPEAT_INTERVAL` bigint(12)   NOT NULL,
    `TIMES_TRIGGERED` bigint(10)   NOT NULL,
    PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`),
    CONSTRAINT `QRTZ_SIMPLE_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*Data for the table `QRTZ_SIMPLE_TRIGGERS` */

/*Table structure for table `QRTZ_SIMPROP_TRIGGERS` */

DROP TABLE IF EXISTS `QRTZ_SIMPROP_TRIGGERS`;

CREATE TABLE `QRTZ_SIMPROP_TRIGGERS`
(
    `SCHED_NAME`    varchar(120) NOT NULL,
    `TRIGGER_NAME`  varchar(200) NOT NULL,
    `TRIGGER_GROUP` varchar(200) NOT NULL,
    `STR_PROP_1`    varchar(512)   DEFAULT NULL,
    `STR_PROP_2`    varchar(512)   DEFAULT NULL,
    `STR_PROP_3`    varchar(512)   DEFAULT NULL,
    `INT_PROP_1`    int(11)        DEFAULT NULL,
    `INT_PROP_2`    int(11)        DEFAULT NULL,
    `LONG_PROP_1`   bigint(20)     DEFAULT NULL,
    `LONG_PROP_2`   bigint(20)     DEFAULT NULL,
    `DEC_PROP_1`    decimal(13, 4) DEFAULT NULL,
    `DEC_PROP_2`    decimal(13, 4) DEFAULT NULL,
    `BOOL_PROP_1`   varchar(1)     DEFAULT NULL,
    `BOOL_PROP_2`   varchar(1)     DEFAULT NULL,
    PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`),
    CONSTRAINT `QRTZ_SIMPROP_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*Data for the table `QRTZ_SIMPROP_TRIGGERS` */

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

insert into `QRTZ_TRIGGERS`(`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `JOB_NAME`, `JOB_GROUP`, `DESCRIPTION`,
                            `NEXT_FIRE_TIME`, `PREV_FIRE_TIME`, `PRIORITY`, `TRIGGER_STATE`, `TRIGGER_TYPE`,
                            `START_TIME`, `END_TIME`, `CALENDAR_NAME`, `MISFIRE_INSTR`, `JOB_DATA`)
values ('RenrenScheduler', 'TASK_1', 'DEFAULT', 'TASK_1', 'DEFAULT', NULL, 1621688400000, 1621686600000, 5, 'WAITING',
        'CRON', 1618119170000, 0, NULL, 2,
        '��\0sr\0org.quartz.JobDataMap���迩��\0\0xr\0&org.quartz.utils.StringKeyDirtyFlagMap�����](\0Z\0allowsTransientDataxr\0org.quartz.utils.DirtyFlagMap�.�(v\n�\0Z\0dirtyL\0mapt\0Ljava/util/Map;xpsr\0java.util.HashMap���`�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\rJOB_PARAM_KEYsr\0Ecom.laughingather.gulimall.admin.modules.job.entity.ScheduleJobEntity\0\0\0\0\0\0\0\0L\0beanNamet\0Ljava/lang/String;L\0\ncreateTimet\0Ljava/util/Date;L\0cronExpressionq\0~\0	L\0jobIdt\0Ljava/lang/Long;L\0paramsq\0~\0	L\0remarkq\0~\0	L\0statust\0Ljava/lang/Integer;xpt\0testTasksr\0java.util.Datehj�KYt\0\0xpw\0\0v6+�@xt\00 0/30 * * * ?sr\0java.lang.Long;��̏#�\0J\0valuexr\0java.lang.Number������\0\0xp\0\0\0\0\0\0\0t\0renrent\0参数测试sr\0java.lang.Integer⠤���8\0I\0valuexq\0~\0\0\0\0\0x\0');

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
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4 COMMENT ='定时任务';

/*Data for the table `schedule_job` */

insert into `schedule_job`(`job_id`, `bean_name`, `params`, `cron_expression`, `status`, `remark`, `create_time`)
values (1, 'testTask', 'renren', '0 0/30 * * * ?', 0, '参数测试', '2020-12-06 11:50:00');

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
  AUTO_INCREMENT = 419
  DEFAULT CHARSET = utf8mb4 COMMENT ='定时任务日志';

/*Data for the table `schedule_job_log` */

insert into `schedule_job_log`(`log_id`, `job_id`, `bean_name`, `params`, `status`, `error`, `times`, `create_time`)
values (1, 1, 'testTask', 'renren', 0, NULL, 0, '2020-12-06 22:00:00'),
       (2, 1, 'testTask', 'renren', 0, NULL, 1, '2020-12-06 23:00:00'),
       (3, 1, 'testTask', 'renren', 0, NULL, 1, '2020-12-06 23:30:00'),
       (4, 1, 'testTask', 'renren', 0, NULL, 5, '2020-12-07 00:00:00'),
       (5, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-11 11:00:00'),
       (6, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-11 11:30:00'),
       (7, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-11 12:00:00'),
       (8, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-11 12:30:00'),
       (9, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-11 14:30:01'),
       (10, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-11 15:00:00'),
       (11, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-11 15:30:00'),
       (12, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-11 16:00:00'),
       (13, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-11 16:30:00'),
       (14, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-11 17:00:00'),
       (15, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-11 17:30:00'),
       (16, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-11 18:00:00'),
       (17, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-11 18:30:00'),
       (18, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-11 19:00:00'),
       (19, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-11 19:30:00'),
       (20, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-11 20:00:00'),
       (21, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-11 20:30:00'),
       (22, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-11 21:00:00'),
       (23, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-11 21:30:00'),
       (24, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-11 22:00:00'),
       (25, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-11 22:30:00'),
       (26, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-11 23:00:00'),
       (27, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-11 23:30:00'),
       (28, 1, 'testTask', 'renren', 0, NULL, 4, '2021-04-12 00:00:00'),
       (29, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-12 00:30:00'),
       (30, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-12 01:00:00'),
       (31, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-12 01:30:00'),
       (32, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-12 02:00:00'),
       (33, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-12 02:30:00'),
       (34, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-12 03:00:00'),
       (35, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-12 03:30:00'),
       (36, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-12 04:00:00'),
       (37, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-12 04:30:00'),
       (38, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-12 05:00:00'),
       (39, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-12 05:30:00'),
       (40, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-12 06:00:00'),
       (41, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-12 06:30:00'),
       (42, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-12 07:00:00'),
       (43, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-12 07:30:00'),
       (44, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-12 08:00:00'),
       (45, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-12 08:30:00'),
       (46, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-12 09:00:00'),
       (47, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-12 09:30:00'),
       (48, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-12 10:00:00'),
       (49, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-12 10:30:00'),
       (50, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-12 11:00:00'),
       (51, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-12 11:30:00'),
       (52, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-12 12:00:00'),
       (53, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-12 12:30:00'),
       (54, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-12 13:00:00'),
       (55, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-12 18:30:00'),
       (56, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-12 19:00:00'),
       (57, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-12 19:30:00'),
       (58, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-12 20:00:00'),
       (59, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-12 20:30:00'),
       (60, 1, 'testTask', 'renren', 0, NULL, 3, '2021-04-12 21:00:00'),
       (61, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-12 21:30:00'),
       (62, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-12 22:00:00'),
       (63, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-12 22:30:00'),
       (64, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-12 23:00:00'),
       (65, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-12 23:30:00'),
       (66, 1, 'testTask', 'renren', 0, NULL, 4, '2021-04-13 00:00:00'),
       (67, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-13 00:30:00'),
       (68, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-16 20:00:00'),
       (69, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-16 20:30:00'),
       (70, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-16 21:00:00'),
       (71, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-16 21:30:00'),
       (72, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-16 22:00:00'),
       (73, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-16 22:30:00'),
       (74, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-16 23:00:00'),
       (75, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-16 23:30:00'),
       (76, 1, 'testTask', 'renren', 0, NULL, 5, '2021-04-17 00:00:00'),
       (77, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-17 00:30:00'),
       (78, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-17 01:00:01'),
       (79, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-17 01:30:00'),
       (80, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-17 11:30:00'),
       (81, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-17 12:00:00'),
       (82, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-17 12:30:00'),
       (83, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-17 13:00:00'),
       (84, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-17 13:30:00'),
       (85, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-17 14:00:00'),
       (86, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-17 14:30:00'),
       (87, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-17 15:00:00'),
       (88, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-17 15:30:01'),
       (89, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-17 16:00:00'),
       (90, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-17 16:30:00'),
       (91, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-17 17:00:00'),
       (92, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-17 17:30:00'),
       (93, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-17 18:00:00'),
       (94, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-17 18:30:00'),
       (95, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-17 19:00:00'),
       (96, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-17 19:30:00'),
       (97, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-17 20:00:00'),
       (98, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-17 20:30:00'),
       (99, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-17 21:00:00'),
       (100, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-17 21:30:00'),
       (101, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-17 22:00:00'),
       (102, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-17 22:30:01'),
       (103, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-17 23:00:00'),
       (104, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-17 23:30:00'),
       (105, 1, 'testTask', 'renren', 0, NULL, 6, '2021-04-18 00:00:00'),
       (106, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-18 00:30:00'),
       (107, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-18 11:00:00'),
       (108, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-18 11:30:00'),
       (109, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-18 12:00:00'),
       (110, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-18 12:30:00'),
       (111, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-18 13:00:00'),
       (112, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-18 13:30:00'),
       (113, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-18 14:00:00'),
       (114, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-18 14:30:00'),
       (115, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-18 15:00:01'),
       (116, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-18 15:30:01'),
       (117, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-18 16:00:00'),
       (118, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-18 16:30:00'),
       (119, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-18 17:00:00'),
       (120, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-18 17:30:00'),
       (121, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-18 18:00:00'),
       (122, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-18 18:30:00'),
       (123, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-18 19:00:00'),
       (124, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-18 19:30:00'),
       (125, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-18 20:00:00'),
       (126, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-18 20:30:00'),
       (127, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-18 21:00:00'),
       (128, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-18 21:30:01'),
       (129, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-18 22:00:00'),
       (130, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-18 22:30:00'),
       (131, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-18 23:00:00'),
       (132, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-18 23:30:00'),
       (133, 1, 'testTask', 'renren', 0, NULL, 4, '2021-04-19 00:00:00'),
       (134, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-19 10:00:00'),
       (135, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-19 10:30:00'),
       (136, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-19 11:00:00'),
       (137, 1, 'testTask', 'renren', 0, NULL, 2, '2021-04-19 11:30:00'),
       (138, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-19 12:00:00'),
       (139, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-19 12:30:00'),
       (140, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-19 13:00:00'),
       (141, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-19 13:30:00'),
       (142, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-19 14:00:00'),
       (143, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-19 14:30:00'),
       (144, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-19 15:00:00'),
       (145, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-19 15:30:00'),
       (146, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-19 16:00:00'),
       (147, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-19 16:30:00'),
       (148, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-19 17:00:00'),
       (149, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-19 17:30:00'),
       (150, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-19 18:00:00'),
       (151, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-19 18:30:00'),
       (152, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-19 19:00:00'),
       (153, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-19 19:30:00'),
       (154, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-19 20:00:00'),
       (155, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-19 20:30:00'),
       (156, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-19 21:00:00'),
       (157, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-19 21:30:00'),
       (158, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-19 22:00:00'),
       (159, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-19 22:30:00'),
       (160, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-19 23:00:01'),
       (161, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-19 23:30:00'),
       (162, 1, 'testTask', 'renren', 0, NULL, 4, '2021-04-20 00:00:00'),
       (163, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-20 10:30:00'),
       (164, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-20 11:00:00'),
       (165, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-20 11:30:00'),
       (166, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-20 12:00:00'),
       (167, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-20 12:30:01'),
       (168, 1, 'testTask', 'renren', 0, NULL, 2, '2021-04-20 13:00:00'),
       (169, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-20 13:30:00'),
       (170, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-20 14:00:00'),
       (171, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-20 14:30:00'),
       (172, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-20 15:00:00'),
       (173, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-20 15:30:00'),
       (174, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-20 16:00:00'),
       (175, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-20 16:30:00'),
       (176, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-20 17:00:00'),
       (177, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-20 17:30:00'),
       (178, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-20 18:00:00'),
       (179, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-20 18:30:00'),
       (180, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-20 19:00:00'),
       (181, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-20 19:30:00'),
       (182, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-20 20:00:01'),
       (183, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-20 20:30:01'),
       (184, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-20 21:00:00'),
       (185, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-20 21:30:00'),
       (186, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-20 22:00:00'),
       (187, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-20 22:30:00'),
       (188, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-20 23:00:00'),
       (189, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-20 23:30:00'),
       (190, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-21 00:00:01'),
       (191, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-21 00:30:00'),
       (192, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-21 01:00:00'),
       (193, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-21 01:30:00'),
       (194, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-21 11:30:00'),
       (195, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-21 12:00:00'),
       (196, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-21 12:30:00'),
       (197, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-21 13:00:00'),
       (198, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-21 13:30:00'),
       (199, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-21 14:00:00'),
       (200, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-21 14:30:00'),
       (201, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-21 15:00:00'),
       (202, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-21 19:00:00'),
       (203, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-21 19:30:00'),
       (204, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-21 20:00:01'),
       (205, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-21 20:30:00'),
       (206, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-21 21:00:00'),
       (207, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-21 21:30:00'),
       (208, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-21 22:00:00'),
       (209, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-21 22:30:00'),
       (210, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-21 23:00:00'),
       (211, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-21 23:30:00'),
       (212, 1, 'testTask', 'renren', 0, NULL, 5, '2021-04-22 00:00:01'),
       (213, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-22 00:30:00'),
       (214, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-22 11:00:00'),
       (215, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-22 11:30:00'),
       (216, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-22 12:00:00'),
       (217, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-22 12:30:00'),
       (218, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-22 13:00:00'),
       (219, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-22 13:30:00'),
       (220, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-22 14:00:00'),
       (221, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-22 14:30:00'),
       (222, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-22 15:00:00'),
       (223, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-22 15:30:00'),
       (224, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-22 16:00:00'),
       (225, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-22 16:30:00'),
       (226, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-22 17:00:00'),
       (227, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-22 17:30:00'),
       (228, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-22 18:00:00'),
       (229, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-22 18:30:00'),
       (230, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-22 19:00:00'),
       (231, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-22 19:30:00'),
       (232, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-22 20:00:00'),
       (233, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-22 20:30:00'),
       (234, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-22 21:00:00'),
       (235, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-22 21:30:00'),
       (236, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-22 22:00:00'),
       (237, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-22 22:30:00'),
       (238, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-22 23:00:00'),
       (239, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-22 23:30:00'),
       (240, 1, 'testTask', 'renren', 0, NULL, 4, '2021-04-23 00:00:00'),
       (241, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-23 00:30:00'),
       (242, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-23 10:00:00'),
       (243, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-23 10:30:00'),
       (244, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-23 11:00:00'),
       (245, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-23 11:30:00'),
       (246, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-23 12:00:00'),
       (247, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-23 12:30:00'),
       (248, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-23 13:00:00'),
       (249, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-23 13:30:00'),
       (250, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-23 14:00:00'),
       (251, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-23 14:30:00'),
       (252, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-23 15:00:00'),
       (253, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-23 15:30:00'),
       (254, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-23 16:00:00'),
       (255, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-23 16:30:00'),
       (256, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-23 17:00:00'),
       (257, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-24 17:00:02'),
       (258, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-24 17:30:00'),
       (259, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-24 18:00:00'),
       (260, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-24 18:30:00'),
       (261, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-24 19:00:00'),
       (262, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-24 19:30:00'),
       (263, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-24 20:00:00'),
       (264, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-24 20:30:00'),
       (265, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-24 21:00:00'),
       (266, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-24 21:30:00'),
       (267, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-24 22:00:00'),
       (268, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-24 22:30:00'),
       (269, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-24 23:00:00'),
       (270, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-24 23:30:00'),
       (271, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-25 10:30:00'),
       (272, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-25 11:00:00'),
       (273, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-25 11:30:00'),
       (274, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-25 12:00:00'),
       (275, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-25 12:30:00'),
       (276, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-25 13:00:00'),
       (277, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-25 13:30:00'),
       (278, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-25 14:00:00'),
       (279, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-25 14:30:00'),
       (280, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-25 15:00:00'),
       (281, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-25 15:30:00'),
       (282, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-25 16:00:00'),
       (283, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-25 16:30:00'),
       (284, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-25 17:00:00'),
       (285, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-25 17:30:00'),
       (286, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-25 18:00:00'),
       (287, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-25 18:30:00'),
       (288, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-25 19:00:00'),
       (289, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-25 19:30:00'),
       (290, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-25 20:00:00'),
       (291, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-25 20:30:00'),
       (292, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-25 21:00:00'),
       (293, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-25 21:30:00'),
       (294, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-25 22:00:00'),
       (295, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-25 22:30:00'),
       (296, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-25 23:00:00'),
       (297, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-25 23:30:00'),
       (298, 1, 'testTask', 'renren', 0, NULL, 3, '2021-04-26 00:00:00'),
       (299, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-26 00:30:00'),
       (300, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-26 01:00:00'),
       (301, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-26 01:30:00'),
       (302, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-26 02:00:00'),
       (303, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-26 02:30:00'),
       (304, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-26 03:00:00'),
       (305, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-26 03:30:00'),
       (306, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-26 04:00:00'),
       (307, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-26 04:30:00'),
       (308, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-26 05:00:00'),
       (309, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-26 05:30:00'),
       (310, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-26 06:00:00'),
       (311, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-26 06:30:00'),
       (312, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-26 07:00:00'),
       (313, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-26 07:30:00'),
       (314, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-26 08:00:00'),
       (315, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-26 08:30:00'),
       (316, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-26 09:00:00'),
       (317, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-26 09:30:00'),
       (318, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-26 10:00:00'),
       (319, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-26 22:30:00'),
       (320, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-26 23:00:00'),
       (321, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-26 23:30:00'),
       (322, 1, 'testTask', 'renren', 0, NULL, 4, '2021-04-27 00:00:00'),
       (323, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-27 00:30:00'),
       (324, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-27 01:00:00'),
       (325, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-27 01:30:00'),
       (326, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-27 02:00:00'),
       (327, 1, 'testTask', 'renren', 0, NULL, 2, '2021-04-27 02:30:00'),
       (328, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-27 03:00:00'),
       (329, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-27 16:00:00'),
       (330, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-27 16:30:00'),
       (331, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-27 17:00:00'),
       (332, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-27 17:30:00'),
       (333, 1, 'testTask', 'renren', 0, NULL, 2, '2021-04-27 18:00:00'),
       (334, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-27 18:30:00'),
       (335, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-27 19:00:00'),
       (336, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-27 19:30:00'),
       (337, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-27 20:00:00'),
       (338, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-27 20:30:00'),
       (339, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-27 21:00:01'),
       (340, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-27 21:30:00'),
       (341, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-27 22:00:00'),
       (342, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-27 22:30:00'),
       (343, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-27 23:00:00'),
       (344, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-27 23:30:00'),
       (345, 1, 'testTask', 'renren', 0, NULL, 4, '2021-04-28 00:00:00'),
       (346, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-28 00:30:00'),
       (347, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-28 09:30:00'),
       (348, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-28 11:00:00'),
       (349, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-28 11:30:00'),
       (350, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-28 12:00:00'),
       (351, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-28 12:30:00'),
       (352, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-28 13:00:00'),
       (353, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-28 13:30:00'),
       (354, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-28 14:00:00'),
       (355, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-28 14:30:00'),
       (356, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-28 15:00:00'),
       (357, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-28 15:30:00'),
       (358, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-28 16:00:00'),
       (359, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-28 16:30:00'),
       (360, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-28 17:00:00'),
       (361, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-28 17:30:00'),
       (362, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-28 18:00:00'),
       (363, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-28 18:30:00'),
       (364, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-28 19:00:00'),
       (365, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-28 19:30:00'),
       (366, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-28 20:00:00'),
       (367, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-28 20:30:00'),
       (368, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-28 21:00:00'),
       (369, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-28 21:30:00'),
       (370, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-28 22:00:00'),
       (371, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-28 22:30:00'),
       (372, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-28 23:30:00'),
       (373, 1, 'testTask', 'renren', 0, NULL, 4, '2021-04-29 00:00:00'),
       (374, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-29 00:30:01'),
       (375, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-29 01:00:01'),
       (376, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-29 01:30:00'),
       (377, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-29 11:30:00'),
       (378, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-29 12:00:00'),
       (379, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-29 12:30:00'),
       (380, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-29 13:00:00'),
       (381, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-29 13:30:00'),
       (382, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-29 14:00:00'),
       (383, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-29 14:30:00'),
       (384, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-29 15:00:00'),
       (385, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-29 15:30:00'),
       (386, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-29 16:00:00'),
       (387, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-29 16:30:00'),
       (388, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-29 17:00:00'),
       (389, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-29 17:30:00'),
       (390, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-29 18:00:00'),
       (391, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-29 18:30:00'),
       (392, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-29 19:00:00'),
       (393, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-29 19:30:00'),
       (394, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-29 20:00:00'),
       (395, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-29 20:30:00'),
       (396, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-29 21:00:00'),
       (397, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-29 21:30:00'),
       (398, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-29 22:00:00'),
       (399, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-29 22:30:00'),
       (400, 1, 'testTask', 'renren', 0, NULL, 1, '2021-04-29 23:00:00'),
       (401, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-29 23:30:00'),
       (402, 1, 'testTask', 'renren', 0, NULL, 3, '2021-04-30 00:00:00'),
       (403, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-30 00:30:00'),
       (404, 1, 'testTask', 'renren', 0, NULL, 0, '2021-04-30 01:00:00'),
       (405, 1, 'testTask', 'renren', 0, NULL, 1, '2021-05-11 21:00:00'),
       (406, 1, 'testTask', 'renren', 0, NULL, 0, '2021-05-11 21:30:00'),
       (407, 1, 'testTask', 'renren', 0, NULL, 1, '2021-05-11 22:00:00'),
       (408, 1, 'testTask', 'renren', 0, NULL, 1, '2021-05-11 22:30:00'),
       (409, 1, 'testTask', 'renren', 0, NULL, 0, '2021-05-11 23:00:00'),
       (410, 1, 'testTask', 'renren', 0, NULL, 1, '2021-05-11 23:30:00'),
       (411, 1, 'testTask', 'renren', 0, NULL, 5, '2021-05-12 00:00:00'),
       (412, 1, 'testTask', 'renren', 0, NULL, 0, '2021-05-12 00:30:00'),
       (413, 1, 'testTask', 'renren', 0, NULL, 1, '2021-05-17 17:30:00'),
       (414, 1, 'testTask', 'renren', 0, NULL, 1, '2021-05-22 18:30:00'),
       (415, 1, 'testTask', 'renren', 0, NULL, 1, '2021-05-22 19:00:00'),
       (416, 1, 'testTask', 'renren', 0, NULL, 1, '2021-05-22 19:30:00'),
       (417, 1, 'testTask', 'renren', 0, NULL, 1, '2021-05-22 20:00:00'),
       (418, 1, 'testTask', 'renren', 0, NULL, 2, '2021-05-22 20:30:00');

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
values ('05cb872d-66f9-4321-86ad-f9a5951b6d60', '28e26', '2021-04-11 14:06:39'),
       ('10eb5651-6f88-4587-84fa-c158bbd1c0c3', 'f4yne', '2021-04-17 01:24:28'),
       ('20655e2f-33c4-45fa-84bc-03683f0b5311', '6pfnd', '2021-05-17 17:10:56'),
       ('274dd613-5b49-4e43-87e7-f581b9f19626', 'ncw4n', '2021-05-17 17:14:09'),
       ('33019dba-98f5-4ddd-8f98-ce63e49cdd8c', 'ae2e8', '2021-04-19 12:06:50'),
       ('5aec07af-b893-4426-8f87-31b3458af7d8', 'fxw63', '2021-04-17 23:27:48'),
       ('72583f71-16ae-4189-865b-966061064b32', 'p58mw', '2021-04-26 21:10:45'),
       ('92a2d0d6-89b9-4863-8280-10e42a3dcc86', 'yemf5', '2021-04-11 14:06:41'),
       ('949c4ab1-eca1-4a65-887e-06d41f1ac03a', '24g68', '2021-04-28 10:58:00'),
       ('9a74b453-4fb1-434e-87e7-0a4910b40604', '4b3da', '2021-04-11 14:11:30'),
       ('a4aae7a0-5419-4ce9-879e-7dcbec4cc2fb', 'g2cbf', '2021-04-28 10:58:09'),
       ('c4386cbf-722f-4feb-8015-504e71bc69fd', 'aw5e6', '2021-04-28 10:57:35'),
       ('c62792ce-7062-4f4a-808f-be4c579d5d18', '5b6xc', '2021-04-16 20:26:13'),
       ('cb3b7be2-e6ff-4ebd-89a8-3344760cfcf1', '4ff5p', '2021-04-17 00:34:08'),
       ('e51d84d9-d8d1-4773-80a3-193692aee5e1', 'bnc54', '2021-04-17 00:49:20'),
       ('e6c07ba4-000a-4a25-8d5e-efc7166aa75e', 'we2p4', '2020-12-06 22:02:43'),
       ('e91db218-7bc4-4d48-8a2a-571347130884', 'em58b', '2021-04-26 22:53:40'),
       ('eadd4134-d58c-441e-8e91-e00c3758ceca', 'm64wm', '2021-04-19 10:29:30'),
       ('ee9a1263-25cb-4f70-8996-0324037459a5', 'ddnw3', '2021-04-11 14:11:22'),
       ('ef88cd3c-0867-4ed4-8587-70db002d4632', 'd2wdn', '2021-04-11 14:06:41'),
       ('f6802e9e-5904-4983-8859-a31d99b40f85', 'aaa6w', '2021-04-17 01:24:19');

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
       (5, 1, 'SQL监控', 'http://localhost:8080/renren-fast/druid/sql.html', NULL, 1, 'sql', 4),
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
values (1, '0f0ba823147cd44577f878632c5e3824', '2021-05-23 06:20:22', '2021-05-22 18:20:22');

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

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;
