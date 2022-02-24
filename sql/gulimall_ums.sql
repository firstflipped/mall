/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 5.7.34 : Database - gulimall_ums
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE = ''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS */`gulimall_ums` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `gulimall_ums`;

/*Table structure for table `ums_growth_change_history` */

DROP TABLE IF EXISTS `ums_growth_change_history`;

CREATE TABLE `ums_growth_change_history`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `member_id`    bigint(20) DEFAULT NULL COMMENT 'member_id',
    `change_count` int(11)    DEFAULT NULL COMMENT '改变的值（正负计数）',
    `note`         varchar(0) DEFAULT NULL COMMENT '备注',
    `source_type`  tinyint(1) DEFAULT NULL COMMENT '积分来源[0-购物，1-管理员修改]',
    `create_time`  datetime   DEFAULT NULL COMMENT 'create_time',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='成长值变化历史记录';

/*Data for the table `ums_growth_change_history` */

/*Table structure for table `ums_integration_change_history` */

DROP TABLE IF EXISTS `ums_integration_change_history`;

CREATE TABLE `ums_integration_change_history`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `member_id`    bigint(20)   DEFAULT NULL COMMENT 'member_id',
    `change_count` int(11)      DEFAULT NULL COMMENT '变化的值',
    `note`         varchar(255) DEFAULT NULL COMMENT '备注',
    `source_type`  tinyint(1)   DEFAULT NULL COMMENT '来源[0->购物；1->管理员修改;2->活动]',
    `create_time`  datetime     DEFAULT NULL COMMENT 'create_time',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='积分变化历史记录';

/*Data for the table `ums_integration_change_history` */

/*Table structure for table `ums_member` */

DROP TABLE IF EXISTS `ums_member`;

CREATE TABLE `ums_member`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `level_id`     bigint(20)   DEFAULT NULL COMMENT '会员等级id',
    `username`     char(64)     DEFAULT NULL COMMENT '用户名',
    `password`     varchar(64)  DEFAULT NULL COMMENT '密码',
    `nickname`     varchar(64)  DEFAULT NULL COMMENT '昵称',
    `mobile`       varchar(20)  DEFAULT NULL COMMENT '手机号码',
    `email`        varchar(64)  DEFAULT NULL COMMENT '邮箱',
    `header`       varchar(500) DEFAULT NULL COMMENT '头像',
    `gender`       tinyint(4)   DEFAULT NULL COMMENT '性别',
    `birth`        date         DEFAULT NULL COMMENT '生日',
    `city`         varchar(500) DEFAULT NULL COMMENT '所在城市',
    `job`          varchar(255) DEFAULT NULL COMMENT '职业',
    `sign`         varchar(255) DEFAULT NULL COMMENT '个性签名',
    `source_type`  tinyint(4)   DEFAULT NULL COMMENT '用户来源',
    `integration`  int(11)      DEFAULT NULL COMMENT '积分',
    `growth`       int(11)      DEFAULT NULL COMMENT '成长值',
    `status`       tinyint(4)   DEFAULT NULL COMMENT '启用状态',
    `social_uid`   bigint(20)   DEFAULT NULL COMMENT '社交用户唯一id',
    `access_token` varchar(255) DEFAULT NULL COMMENT '访问令牌',
    `expires_in`   bigint(20)   DEFAULT NULL COMMENT '访问令牌的时间',
    `create_time`  datetime     DEFAULT NULL COMMENT '注册时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 6
  DEFAULT CHARSET = utf8mb4 COMMENT ='会员';

/*Data for the table `ums_member` */

insert into `ums_member`(`id`, `level_id`, `username`, `password`, `nickname`, `mobile`, `email`, `header`, `gender`,
                         `birth`, `city`, `job`, `sign`, `source_type`, `integration`, `growth`, `status`, `social_uid`,
                         `access_token`, `expires_in`, `create_time`)
values (2, 1, 'wangjie', '$2a$10$6gKu6vmpTFQUtEczpX5bdOItBTKuLLOnFpjc53sX1Zps7lCJ0.n82', NULL, '18763096838', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-05-27 23:01:35'),
       (4, 1, 'admin', '$2a$10$ekdVeZ8K5cQg1dKSbaUyNudomVMex8KkPA8ztUAm8iNMNZxyxGVkm', NULL, '17515235623', NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-05-27 23:41:09'),
       (5, 1, NULL, '$2a$10$WHcNFkpxhOQer.UHE4FT6uyyUZxmDFP895BnJjy8w7.tJ0dRgjkB.', '心有猛虎的杰爷', NULL, NULL,
        'https://tva4.sinaimg.cn/crop.0.0.1080.1080.50/006evlUgjw8fbuphxbojoj30u00u0jrs.jpg?KID=imgbed,tva&Expires=1629309063&ssig=JnKnMHkqmm',
        1, NULL, '16', NULL, NULL, NULL, NULL, NULL, NULL, 5711138076, '2.00Qu2VOGppkbeC8f7ac62209LyPwxD', 157679999,
        '2021-08-18 22:51:04');

/*Table structure for table `ums_member_collect_spu` */

DROP TABLE IF EXISTS `ums_member_collect_spu`;

CREATE TABLE `ums_member_collect_spu`
(
    `id`          bigint(20) NOT NULL COMMENT 'id',
    `member_id`   bigint(20)   DEFAULT NULL COMMENT '会员id',
    `spu_id`      bigint(20)   DEFAULT NULL COMMENT 'spu_id',
    `spu_name`    varchar(500) DEFAULT NULL COMMENT 'spu_name',
    `spu_img`     varchar(500) DEFAULT NULL COMMENT 'spu_img',
    `create_time` datetime     DEFAULT NULL COMMENT 'create_time',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='会员收藏的商品';

/*Data for the table `ums_member_collect_spu` */

/*Table structure for table `ums_member_collect_subject` */

DROP TABLE IF EXISTS `ums_member_collect_subject`;

CREATE TABLE `ums_member_collect_subject`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `subject_id`   bigint(20)   DEFAULT NULL COMMENT 'subject_id',
    `subject_name` varchar(255) DEFAULT NULL COMMENT 'subject_name',
    `subject_img`  varchar(500) DEFAULT NULL COMMENT 'subject_img',
    `subject_url`  varchar(500) DEFAULT NULL COMMENT '活动url',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='会员收藏的专题活动';

/*Data for the table `ums_member_collect_subject` */

/*Table structure for table `ums_member_level` */

DROP TABLE IF EXISTS `ums_member_level`;

CREATE TABLE `ums_member_level`
(
    `id`                     bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name`                   varchar(100)   DEFAULT NULL COMMENT '等级名称',
    `growth_point`           int(11)        DEFAULT NULL COMMENT '等级需要的成长值',
    `default_status`         tinyint(1)     DEFAULT NULL COMMENT '是否为默认等级[0->不是；1->是]',
    `free_freight_point`     decimal(18, 4) DEFAULT NULL COMMENT '免运费标准',
    `comment_growth_point`   int(11)        DEFAULT NULL COMMENT '每次评价获取的成长值',
    `free_freight_privilege` tinyint(1)     DEFAULT NULL COMMENT '是否有免邮特权',
    `member_price_privilege` tinyint(1)     DEFAULT NULL COMMENT '是否有会员价格特权',
    `birthday_privilege`     tinyint(1)     DEFAULT NULL COMMENT '是否有生日特权',
    `note`                   varchar(255)   DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8mb4 COMMENT ='会员等级';

/*Data for the table `ums_member_level` */

insert into `ums_member_level`(`id`, `name`, `growth_point`, `default_status`, `free_freight_point`,
                               `comment_growth_point`, `free_freight_privilege`, `member_price_privilege`,
                               `birthday_privilege`, `note`)
values (1, '普通会员', 0, 1, 1000.0000, 10, 0, 0, 0, '普通会员'),
       (2, '白银会员', 5000, 0, 1000.0000, 20, 0, 1, 1, '白银会员'),
       (3, '黄金会员', 10000, 0, 500.0000, 30, 0, 1, 1, '黄金会员'),
       (4, '钻石会员', 100000, 0, 500.0000, 50, 1, 1, 1, '钻石会员');

/*Table structure for table `ums_member_login_log` */

DROP TABLE IF EXISTS `ums_member_login_log`;

CREATE TABLE `ums_member_login_log`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `member_id`   bigint(20)  DEFAULT NULL COMMENT 'member_id',
    `create_time` datetime    DEFAULT NULL COMMENT '创建时间',
    `ip`          varchar(64) DEFAULT NULL COMMENT 'ip',
    `city`        varchar(64) DEFAULT NULL COMMENT 'city',
    `login_type`  tinyint(1)  DEFAULT NULL COMMENT '登录类型[1-web，2-app]',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='会员登录记录';

/*Data for the table `ums_member_login_log` */

/*Table structure for table `ums_member_receive_address` */

DROP TABLE IF EXISTS `ums_member_receive_address`;

CREATE TABLE `ums_member_receive_address`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `member_id`      bigint(20)   DEFAULT NULL COMMENT 'member_id',
    `name`           varchar(255) DEFAULT NULL COMMENT '收货人姓名',
    `phone`          varchar(64)  DEFAULT NULL COMMENT '电话',
    `post_code`      varchar(64)  DEFAULT NULL COMMENT '邮政编码',
    `province`       varchar(100) DEFAULT NULL COMMENT '省份/直辖市',
    `city`           varchar(100) DEFAULT NULL COMMENT '城市',
    `region`         varchar(100) DEFAULT NULL COMMENT '区',
    `detail_address` varchar(255) DEFAULT NULL COMMENT '详细地址(街道)',
    `area_code`      varchar(15)  DEFAULT NULL COMMENT '省市区代码',
    `default_status` tinyint(1)   DEFAULT NULL COMMENT '是否默认',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8mb4 COMMENT ='会员收货地址';

/*Data for the table `ums_member_receive_address` */

insert into `ums_member_receive_address`(`id`, `member_id`, `name`, `phone`, `post_code`, `province`, `city`, `region`,
                                         `detail_address`, `area_code`, `default_status`)
values (1, 5, '山东舞王小阿靠', '17515235623', '0000', '山东省', '济南市', '历下区', '舜华路街道草山岭小区', NULL, 1),
       (2, 5, '山东舞王小阿靠', '17515235623', '9999', '北京市', '北京市', '东城区', '长安街1号', NULL, 0),
       (3, 5, '山东舞王小阿靠', '17515235623', '2222', '山东省', '青岛市', '市南区', '济南路66号', NULL, 0);

/*Table structure for table `ums_member_statistics_info` */

DROP TABLE IF EXISTS `ums_member_statistics_info`;

CREATE TABLE `ums_member_statistics_info`
(
    `id`                    bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `member_id`             bigint(20)     DEFAULT NULL COMMENT '会员id',
    `consume_amount`        decimal(18, 4) DEFAULT NULL COMMENT '累计消费金额',
    `coupon_amount`         decimal(18, 4) DEFAULT NULL COMMENT '累计优惠金额',
    `order_count`           int(11)        DEFAULT NULL COMMENT '订单数量',
    `coupon_count`          int(11)        DEFAULT NULL COMMENT '优惠券数量',
    `comment_count`         int(11)        DEFAULT NULL COMMENT '评价数',
    `return_order_count`    int(11)        DEFAULT NULL COMMENT '退货数量',
    `login_count`           int(11)        DEFAULT NULL COMMENT '登录次数',
    `attend_count`          int(11)        DEFAULT NULL COMMENT '关注数量',
    `fans_count`            int(11)        DEFAULT NULL COMMENT '粉丝数量',
    `collect_product_count` int(11)        DEFAULT NULL COMMENT '收藏的商品数量',
    `collect_subject_count` int(11)        DEFAULT NULL COMMENT '收藏的专题活动数量',
    `collect_comment_count` int(11)        DEFAULT NULL COMMENT '收藏的评论数量',
    `invite_friend_count`   int(11)        DEFAULT NULL COMMENT '邀请的朋友数量',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='会员统计信息';

/*Data for the table `ums_member_statistics_info` */

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
