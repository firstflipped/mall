/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 5.7.34 : Database - gulimall_wms
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE = ''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS */`gulimall_wms` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `gulimall_wms`;

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

/*Table structure for table `wms_purchase` */

DROP TABLE IF EXISTS `wms_purchase`;

CREATE TABLE `wms_purchase`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT '采购单id',
    `assignee_id`   bigint(20)     DEFAULT NULL COMMENT '采购人id',
    `assignee_name` varchar(255)   DEFAULT NULL COMMENT '采购人名',
    `phone`         char(13)       DEFAULT NULL COMMENT '联系方式',
    `priority`      int(4)         DEFAULT NULL COMMENT '优先级',
    `status`        int(4)         DEFAULT NULL COMMENT '状态 0新建 1已分配 2已领取 3已完成 4有异常',
    `ware_id`       bigint(20)     DEFAULT NULL COMMENT '仓库id',
    `amount`        decimal(18, 4) DEFAULT NULL COMMENT '总金额',
    `create_time`   datetime       DEFAULT NULL COMMENT '创建日期',
    `update_time`   datetime       DEFAULT NULL COMMENT '更新日期',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4 COMMENT ='采购信息';

/*Data for the table `wms_purchase` */

insert into `wms_purchase`(`id`, `assignee_id`, `assignee_name`, `phone`, `priority`, `status`, `ware_id`, `amount`,
                           `create_time`, `update_time`)
values (1, 2, 'wangjie', '17515236789', 1, 4, NULL, NULL, NULL, '2021-04-29 23:43:33'),
       (2, 1, 'admin', '13612345678', NULL, 3, NULL, NULL, '2021-04-29 23:48:33', '2021-04-29 23:50:23');

/*Table structure for table `wms_purchase_detail` */

DROP TABLE IF EXISTS `wms_purchase_detail`;

CREATE TABLE `wms_purchase_detail`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `purchase_id` bigint(20)     DEFAULT NULL COMMENT '采购单id',
    `sku_id`      bigint(20)     DEFAULT NULL COMMENT '采购商品id',
    `sku_num`     int(11)        DEFAULT NULL COMMENT '采购数量',
    `sku_price`   decimal(18, 4) DEFAULT NULL COMMENT '采购金额',
    `ware_id`     bigint(20)     DEFAULT NULL COMMENT '仓库id',
    `status`      int(11)        DEFAULT NULL COMMENT '状态[0新建，1已分配，2正在采购，3已完成，4采购失败]',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8mb4;

/*Data for the table `wms_purchase_detail` */

insert into `wms_purchase_detail`(`id`, `purchase_id`, `sku_id`, `sku_num`, `sku_price`, `ware_id`, `status`)
values (1, 1, 1, 100, NULL, 3, 3),
       (2, 1, 2, 100, NULL, 4, 4),
       (3, 2, 3, 100, NULL, 3, 3),
       (4, 2, 4, 100, NULL, 3, 3);

/*Table structure for table `wms_ware_info` */

DROP TABLE IF EXISTS `wms_ware_info`;

CREATE TABLE `wms_ware_info`
(
    `id`       bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name`     varchar(255) DEFAULT NULL COMMENT '仓库名',
    `address`  varchar(255) DEFAULT NULL COMMENT '仓库地址',
    `areacode` varchar(20)  DEFAULT NULL COMMENT '区域编码',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8mb4 COMMENT ='仓库信息';

/*Data for the table `wms_ware_info` */

insert into `wms_ware_info`(`id`, `name`, `address`, `areacode`)
values (3, '华北二仓', '山东省青岛市济南路09号', '099009'),
       (4, '东北一仓', '吉林省大连市北京北路', '119120');

/*Table structure for table `wms_ware_order_task` */

DROP TABLE IF EXISTS `wms_ware_order_task`;

CREATE TABLE `wms_ware_order_task`
(
    `id`               bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `order_id`         bigint(20)   DEFAULT NULL COMMENT 'order_id',
    `order_sn`         varchar(255) DEFAULT NULL COMMENT 'order_sn',
    `consignee`        varchar(100) DEFAULT NULL COMMENT '收货人',
    `consignee_tel`    char(15)     DEFAULT NULL COMMENT '收货人电话',
    `delivery_address` varchar(500) DEFAULT NULL COMMENT '配送地址',
    `order_comment`    varchar(200) DEFAULT NULL COMMENT '订单备注',
    `payment_way`      tinyint(1)   DEFAULT NULL COMMENT '付款方式【 1:在线付款 2:货到付款】',
    `task_status`      tinyint(2)   DEFAULT NULL COMMENT '任务状态',
    `order_body`       varchar(255) DEFAULT NULL COMMENT '订单描述',
    `tracking_no`      char(30)     DEFAULT NULL COMMENT '物流单号',
    `create_time`      datetime     DEFAULT NULL COMMENT 'create_time',
    `ware_id`          bigint(20)   DEFAULT NULL COMMENT '仓库id',
    `task_comment`     varchar(500) DEFAULT NULL COMMENT '工作单备注',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='库存工作单';

/*Data for the table `wms_ware_order_task` */

/*Table structure for table `wms_ware_order_task_detail` */

DROP TABLE IF EXISTS `wms_ware_order_task_detail`;

CREATE TABLE `wms_ware_order_task_detail`
(
    `id`       bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `sku_id`   bigint(20)   DEFAULT NULL COMMENT 'sku_id',
    `sku_name` varchar(255) DEFAULT NULL COMMENT 'sku_name',
    `sku_num`  int(11)      DEFAULT NULL COMMENT '购买个数',
    `task_id`  bigint(20)   DEFAULT NULL COMMENT '工作单id',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='库存工作单';

/*Data for the table `wms_ware_order_task_detail` */

/*Table structure for table `wms_ware_sku` */

DROP TABLE IF EXISTS `wms_ware_sku`;

CREATE TABLE `wms_ware_sku`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `sku_id`       bigint(20)   DEFAULT NULL COMMENT 'sku_id',
    `ware_id`      bigint(20)   DEFAULT NULL COMMENT '仓库id',
    `stock`        int(11)      DEFAULT NULL COMMENT '库存数',
    `sku_name`     varchar(200) DEFAULT NULL COMMENT 'sku_name',
    `stock_locked` int(11)      DEFAULT '0' COMMENT '锁定库存',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8mb4 COMMENT ='商品库存';

/*Data for the table `wms_ware_sku` */

insert into `wms_ware_sku`(`id`, `sku_id`, `ware_id`, `stock`, `sku_name`, `stock_locked`)
values (1, 41, 3, 10, 'Apple iPhone 12 (A2404) 128GB 绿色 支持移动联通电信5G 双卡双待手机 黑色 4G', 6),
       (2, 42, 3, 0, 'Apple iPhone 12 (A2404) 128GB 绿色 支持移动联通电信5G 双卡双待手机 黑色 6G', 0),
       (3, 42, 4, 20, 'Apple iPhone 12 (A2404) 128GB 绿色 支持移动联通电信5G 双卡双待手机 黑色 6G', 0),
       (4, 43, 4, 100, 'Apple iPhone 12 (A2404) 128GB 绿色 支持移动联通电信5G 双卡双待手机 白色 4G', 0);

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;