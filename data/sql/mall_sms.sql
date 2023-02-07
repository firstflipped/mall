/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : mall_sms

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 31/10/2022 15:45:09
*/
CREATE DATABASE IF NOT EXISTS `mall_sms` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;
USE `mall_sms`;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sms_coupon
-- ----------------------------
DROP TABLE IF EXISTS `sms_coupon`;
CREATE TABLE `sms_coupon`
(
    `id`                bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    `coupon_type`       tinyint(1)     DEFAULT NULL COMMENT '优惠卷类型[0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券]',
    `coupon_img`        varchar(2000)  DEFAULT NULL COMMENT '优惠券图片',
    `coupon_name`       varchar(100)   DEFAULT NULL COMMENT '优惠券名字',
    `num`               int            DEFAULT NULL COMMENT '数量',
    `amount`            decimal(18, 4) DEFAULT NULL COMMENT '金额',
    `per_limit`         int            DEFAULT NULL COMMENT '每人限领张数',
    `min_point`         decimal(18, 4) DEFAULT NULL COMMENT '使用门槛',
    `start_time`        datetime       DEFAULT NULL COMMENT '开始时间',
    `end_time`          datetime       DEFAULT NULL COMMENT '结束时间',
    `use_type`          tinyint(1)     DEFAULT NULL COMMENT '使用类型[0->全场通用；1->指定分类；2->指定商品]',
    `note`              varchar(200)   DEFAULT NULL COMMENT '备注',
    `publish_count`     int            DEFAULT NULL COMMENT '发行数量',
    `use_count`         int            DEFAULT NULL COMMENT '已使用数量',
    `receive_count`     int            DEFAULT NULL COMMENT '领取数量',
    `enable_start_time` datetime       DEFAULT NULL COMMENT '可以领取的开始日期',
    `enable_end_time`   datetime       DEFAULT NULL COMMENT '可以领取的结束日期',
    `code`              varchar(64)    DEFAULT NULL COMMENT '优惠码',
    `member_level`      tinyint(1)     DEFAULT NULL COMMENT '可以领取的会员等级[0->不限等级，其他-对应等级]',
    `publish`           tinyint(1)     DEFAULT NULL COMMENT '发布状态[0-未发布，1-已发布]',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='优惠券信息';

-- ----------------------------
-- Records of sms_coupon
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sms_coupon_history
-- ----------------------------
DROP TABLE IF EXISTS `sms_coupon_history`;
CREATE TABLE `sms_coupon_history`
(
    `id`               bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    `coupon_id`        bigint      DEFAULT NULL COMMENT '优惠券id',
    `member_id`        bigint      DEFAULT NULL COMMENT '会员id',
    `member_nick_name` varchar(64) DEFAULT NULL COMMENT '会员名字',
    `get_type`         tinyint(1)  DEFAULT NULL COMMENT '获取方式[0->后台赠送；1->主动领取]',
    `use_type`         tinyint(1)  DEFAULT NULL COMMENT '使用状态[0->未使用；1->已使用；2->已过期]',
    `create_time`      datetime    DEFAULT NULL COMMENT '创建时间',
    `use_time`         datetime    DEFAULT NULL COMMENT '使用时间',
    `order_id`         bigint      DEFAULT NULL COMMENT '订单id',
    `order_sn`         bigint      DEFAULT NULL COMMENT '订单号',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='优惠券领取历史记录';

-- ----------------------------
-- Records of sms_coupon_history
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sms_coupon_spu_category_relation
-- ----------------------------
DROP TABLE IF EXISTS `sms_coupon_spu_category_relation`;
CREATE TABLE `sms_coupon_spu_category_relation`
(
    `id`            bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    `coupon_id`     bigint      DEFAULT NULL COMMENT '优惠券id',
    `category_id`   bigint      DEFAULT NULL COMMENT '产品分类id',
    `category_name` varchar(64) DEFAULT NULL COMMENT '产品分类名称',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='优惠券分类关联';

-- ----------------------------
-- Records of sms_coupon_spu_category_relation
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sms_coupon_spu_relation
-- ----------------------------
DROP TABLE IF EXISTS `sms_coupon_spu_relation`;
CREATE TABLE `sms_coupon_spu_relation`
(
    `id`        bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    `coupon_id` bigint       DEFAULT NULL COMMENT '优惠券id',
    `spu_id`    bigint       DEFAULT NULL COMMENT 'spu_id',
    `spu_name`  varchar(255) DEFAULT NULL COMMENT 'spu_name',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='优惠券与产品关联';

-- ----------------------------
-- Records of sms_coupon_spu_relation
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sms_home_figure
-- ----------------------------
DROP TABLE IF EXISTS `sms_home_figure`;
CREATE TABLE `sms_home_figure`
(
    `id`           bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name`         varchar(100) DEFAULT NULL COMMENT '名字',
    `pic`          varchar(500) DEFAULT NULL COMMENT '图片地址',
    `start_time`   datetime     DEFAULT NULL COMMENT '开始时间',
    `end_time`     datetime     DEFAULT NULL COMMENT '结束时间',
    `status`       tinyint(1)   DEFAULT NULL COMMENT '状态',
    `click_count`  int          DEFAULT NULL COMMENT '点击数',
    `url`          varchar(500) DEFAULT NULL COMMENT '广告详情连接地址',
    `note`         varchar(500) DEFAULT NULL COMMENT '备注',
    `sort`         int          DEFAULT NULL COMMENT '排序',
    `publisher_id` bigint       DEFAULT NULL COMMENT '发布者',
    `auth_id`      bigint       DEFAULT NULL COMMENT '审核者',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='首页轮播广告';

-- ----------------------------
-- Records of sms_home_figure
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sms_home_subject
-- ----------------------------
DROP TABLE IF EXISTS `sms_home_subject`;
CREATE TABLE `sms_home_subject`
(
    `id`        bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name`      varchar(200) DEFAULT NULL COMMENT '专题名字',
    `title`     varchar(255) DEFAULT NULL COMMENT '专题标题',
    `sub_title` varchar(255) DEFAULT NULL COMMENT '专题副标题',
    `status`    tinyint(1)   DEFAULT NULL COMMENT '显示状态',
    `url`       varchar(500) DEFAULT NULL COMMENT '详情连接',
    `sort`      int          DEFAULT NULL COMMENT '排序',
    `img`       varchar(500) DEFAULT NULL COMMENT '专题图片地址',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】';

-- ----------------------------
-- Records of sms_home_subject
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sms_home_subject_spu
-- ----------------------------
DROP TABLE IF EXISTS `sms_home_subject_spu`;
CREATE TABLE `sms_home_subject_spu`
(
    `id`         bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name`       varchar(200) DEFAULT NULL COMMENT '专题名字',
    `subject_id` bigint       DEFAULT NULL COMMENT '专题id',
    `spu_id`     bigint       DEFAULT NULL COMMENT 'spu_id',
    `sort`       int          DEFAULT NULL COMMENT '排序',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='专题商品';

-- ----------------------------
-- Records of sms_home_subject_spu
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sms_member_price
-- ----------------------------
DROP TABLE IF EXISTS `sms_member_price`;
CREATE TABLE `sms_member_price`
(
    `id`                bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    `sku_id`            bigint         DEFAULT NULL COMMENT 'sku_id',
    `member_level_id`   bigint         DEFAULT NULL COMMENT '会员等级id',
    `member_level_name` varchar(100)   DEFAULT NULL COMMENT '会员等级名',
    `member_price`      decimal(18, 4) DEFAULT NULL COMMENT '会员对应价格',
    `add_other`         tinyint(1)     DEFAULT NULL COMMENT '可否叠加其他优惠[0-不可叠加优惠，1-可叠加]',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='商品会员价格';

-- ----------------------------
-- Records of sms_member_price
-- ----------------------------
BEGIN;
INSERT INTO `sms_member_price` (`id`, `sku_id`, `member_level_id`, `member_level_name`, `member_price`, `add_other`)
VALUES (1, 1, 2, '白银会员', 5699.0000, NULL);
INSERT INTO `sms_member_price` (`id`, `sku_id`, `member_level_id`, `member_level_name`, `member_price`, `add_other`)
VALUES (2, 1, 3, '黄金会员', 5599.0000, NULL);
INSERT INTO `sms_member_price` (`id`, `sku_id`, `member_level_id`, `member_level_name`, `member_price`, `add_other`)
VALUES (3, 1, 4, '钻石会员', 5499.0000, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sms_sec_kill_promotion
-- ----------------------------
DROP TABLE IF EXISTS `sms_sec_kill_promotion`;
CREATE TABLE `sms_sec_kill_promotion`
(
    `id`          bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    `title`       varchar(255) DEFAULT NULL COMMENT '活动标题',
    `start_time`  datetime     DEFAULT NULL COMMENT '开始日期',
    `end_time`    datetime     DEFAULT NULL COMMENT '结束日期',
    `status`      tinyint      DEFAULT NULL COMMENT '上下线状态',
    `create_time` datetime     DEFAULT NULL COMMENT '创建时间',
    `user_id`     bigint       DEFAULT NULL COMMENT '创建人',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='秒杀活动';

-- ----------------------------
-- Records of sms_sec_kill_promotion
-- ----------------------------
BEGIN;
INSERT INTO `sms_sec_kill_promotion` (`id`, `title`, `start_time`, `end_time`, `status`, `create_time`, `user_id`)
VALUES (3, 'ceshi', '2021-11-11 00:00:00', '2021-12-01 00:00:00', 1, '2021-11-10 10:55:34', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sms_sec_kill_session
-- ----------------------------
DROP TABLE IF EXISTS `sms_sec_kill_session`;
CREATE TABLE `sms_sec_kill_session`
(
    `id`          bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name`        varchar(200) DEFAULT NULL COMMENT '场次名称',
    `start_time`  datetime     DEFAULT NULL COMMENT '每日开始时间',
    `end_time`    datetime     DEFAULT NULL COMMENT '每日结束时间',
    `status`      tinyint(1)   DEFAULT NULL COMMENT '启用状态',
    `create_time` datetime     DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='秒杀活动场次';

-- ----------------------------
-- Records of sms_sec_kill_session
-- ----------------------------
BEGIN;
INSERT INTO `sms_sec_kill_session` (`id`, `name`, `start_time`, `end_time`, `status`, `create_time`)
VALUES (1, 'xxx', '2021-11-16 16:00:00', '2021-11-16 18:00:00', 1, '2021-11-10 16:10:25');
INSERT INTO `sms_sec_kill_session` (`id`, `name`, `start_time`, `end_time`, `status`, `create_time`)
VALUES (2, 'yyy', '2021-11-17 03:30:00', '2021-11-17 08:00:00', 1, '2021-11-16 09:35:40');
INSERT INTO `sms_sec_kill_session` (`id`, `name`, `start_time`, `end_time`, `status`, `create_time`)
VALUES (3, 'jjj', '2021-11-22 08:30:00', '2021-11-22 23:00:00', 1, '2021-11-16 10:52:43');
COMMIT;

-- ----------------------------
-- Table structure for sms_sec_kill_sku_notice
-- ----------------------------
DROP TABLE IF EXISTS `sms_sec_kill_sku_notice`;
CREATE TABLE `sms_sec_kill_sku_notice`
(
    `id`             bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    `member_id`      bigint     DEFAULT NULL COMMENT 'member_id',
    `sku_id`         bigint     DEFAULT NULL COMMENT 'sku_id',
    `session_id`     bigint     DEFAULT NULL COMMENT '活动场次id',
    `subscribe_time` datetime   DEFAULT NULL COMMENT '订阅时间',
    `send_time`      datetime   DEFAULT NULL COMMENT '发送时间',
    `notice_type`    tinyint(1) DEFAULT NULL COMMENT '通知方式[0-短信，1-邮件]',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='秒杀商品通知订阅';

-- ----------------------------
-- Records of sms_sec_kill_sku_notice
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sms_sec_kill_sku_relation
-- ----------------------------
DROP TABLE IF EXISTS `sms_sec_kill_sku_relation`;
CREATE TABLE `sms_sec_kill_sku_relation`
(
    `id`                   bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    `promotion_id`         bigint         DEFAULT NULL COMMENT '活动id',
    `promotion_session_id` bigint         DEFAULT NULL COMMENT '活动场次id',
    `sku_id`               bigint         DEFAULT NULL COMMENT '商品id',
    `sec_kill_price`       decimal(10, 0) DEFAULT NULL COMMENT '秒杀价格',
    `sec_kill_count`       int            DEFAULT NULL COMMENT '秒杀总量',
    `sec_kill_limit`       int            DEFAULT NULL COMMENT '每人限购数量',
    `sec_kill_sort`        int            DEFAULT NULL COMMENT '排序',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='秒杀活动商品关联';

-- ----------------------------
-- Records of sms_sec_kill_sku_relation
-- ----------------------------
BEGIN;
INSERT INTO `sms_sec_kill_sku_relation` (`id`, `promotion_id`, `promotion_session_id`, `sku_id`, `sec_kill_price`,
                                         `sec_kill_count`, `sec_kill_limit`, `sec_kill_sort`)
VALUES (2, NULL, 1, 41, 3999, 1, 1, 0);
INSERT INTO `sms_sec_kill_sku_relation` (`id`, `promotion_id`, `promotion_session_id`, `sku_id`, `sec_kill_price`,
                                         `sec_kill_count`, `sec_kill_limit`, `sec_kill_sort`)
VALUES (3, NULL, 2, 41, 4999, 10, 1, 0);
INSERT INTO `sms_sec_kill_sku_relation` (`id`, `promotion_id`, `promotion_session_id`, `sku_id`, `sec_kill_price`,
                                         `sec_kill_count`, `sec_kill_limit`, `sec_kill_sort`)
VALUES (4, NULL, 3, 41, 4299, 100, 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for sms_sku_full_reduction
-- ----------------------------
DROP TABLE IF EXISTS `sms_sku_full_reduction`;
CREATE TABLE `sms_sku_full_reduction`
(
    `id`           bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    `sku_id`       bigint         DEFAULT NULL COMMENT 'spu_id',
    `full_price`   decimal(18, 4) DEFAULT NULL COMMENT '满多少',
    `reduce_price` decimal(18, 4) DEFAULT NULL COMMENT '减多少',
    `add_other`    tinyint(1)     DEFAULT NULL COMMENT '是否参与其他优惠',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='商品满减信息';

-- ----------------------------
-- Records of sms_sku_full_reduction
-- ----------------------------
BEGIN;
INSERT INTO `sms_sku_full_reduction` (`id`, `sku_id`, `full_price`, `reduce_price`, `add_other`)
VALUES (1, 1, 1000.0000, 20.0000, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sms_sku_ladder
-- ----------------------------
DROP TABLE IF EXISTS `sms_sku_ladder`;
CREATE TABLE `sms_sku_ladder`
(
    `id`         bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    `sku_id`     bigint         DEFAULT NULL COMMENT 'spu_id',
    `full_count` int            DEFAULT NULL COMMENT '满几件',
    `discount`   decimal(4, 2)  DEFAULT NULL COMMENT '打几折',
    `price`      decimal(18, 4) DEFAULT NULL COMMENT '折后价',
    `add_other`  tinyint(1)     DEFAULT NULL COMMENT '是否叠加其他优惠[0-不可叠加，1-可叠加]',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='商品阶梯价格';

-- ----------------------------
-- Records of sms_sku_ladder
-- ----------------------------
BEGIN;
INSERT INTO `sms_sku_ladder` (`id`, `sku_id`, `full_count`, `discount`, `price`, `add_other`)
VALUES (1, 1, 2, 1.00, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for sms_spu_bounds
-- ----------------------------
DROP TABLE IF EXISTS `sms_spu_bounds`;
CREATE TABLE `sms_spu_bounds`
(
    `id`          bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    `spu_id`      bigint         DEFAULT NULL,
    `grow_bounds` decimal(18, 4) DEFAULT NULL COMMENT '成长积分',
    `buy_bounds`  decimal(18, 4) DEFAULT NULL COMMENT '购物积分',
    `work`        tinyint(1)     DEFAULT NULL COMMENT '优惠生效情况[1111（四个状态位，从右到左）;0 - 无优惠，成长积分是否赠送;1 - 无优惠，购物积分是否赠送;2 - 有优惠，成长积分是否赠送;3 - 有优惠，购物积分是否赠送【状态位0：不赠送，1：赠送】]',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 13
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='商品spu积分设置';

-- ----------------------------
-- Records of sms_spu_bounds
-- ----------------------------
BEGIN;
INSERT INTO `sms_spu_bounds` (`id`, `spu_id`, `grow_bounds`, `buy_bounds`, `work`)
VALUES (4, 7, 5000.0000, 5000.0000, NULL);
INSERT INTO `sms_spu_bounds` (`id`, `spu_id`, `grow_bounds`, `buy_bounds`, `work`)
VALUES (5, 8, 9.0000, 9.0000, NULL);
INSERT INTO `sms_spu_bounds` (`id`, `spu_id`, `grow_bounds`, `buy_bounds`, `work`)
VALUES (6, 12, 9.0000, 9.0000, NULL);
INSERT INTO `sms_spu_bounds` (`id`, `spu_id`, `grow_bounds`, `buy_bounds`, `work`)
VALUES (7, 13, 9.0000, 9.0000, NULL);
INSERT INTO `sms_spu_bounds` (`id`, `spu_id`, `grow_bounds`, `buy_bounds`, `work`)
VALUES (8, 14, 10.0000, 10.0000, NULL);
INSERT INTO `sms_spu_bounds` (`id`, `spu_id`, `grow_bounds`, `buy_bounds`, `work`)
VALUES (9, 15, 10.0000, 10.0000, NULL);
INSERT INTO `sms_spu_bounds` (`id`, `spu_id`, `grow_bounds`, `buy_bounds`, `work`)
VALUES (10, 16, 1.0000, 1.0000, NULL);
INSERT INTO `sms_spu_bounds` (`id`, `spu_id`, `grow_bounds`, `buy_bounds`, `work`)
VALUES (11, 17, 1000.0000, 1000.0000, NULL);
INSERT INTO `sms_spu_bounds` (`id`, `spu_id`, `grow_bounds`, `buy_bounds`, `work`)
VALUES (12, 18, 1000.0000, 1000.0000, NULL);
COMMIT;

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log`
(
    `id`            bigint       NOT NULL AUTO_INCREMENT,
    `branch_id`     bigint       NOT NULL,
    `xid`           varchar(100) NOT NULL,
    `context`       varchar(128) NOT NULL,
    `rollback_info` longblob     NOT NULL,
    `log_status`    int          NOT NULL,
    `log_created`   datetime     NOT NULL,
    `log_modified`  datetime     NOT NULL,
    `ext`           varchar(100) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `ux_undo_log` (`xid`, `branch_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb3;

-- ----------------------------
-- Records of undo_log
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
