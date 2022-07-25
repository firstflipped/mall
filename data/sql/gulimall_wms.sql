/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : gulimall_wms

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 24/07/2022 21:08:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mq_message
-- ----------------------------
DROP TABLE IF EXISTS `mq_message`;
CREATE TABLE `mq_message` (
  `message_id` bigint NOT NULL AUTO_INCREMENT,
  `content` text,
  `to_exchange` varchar(255) DEFAULT NULL,
  `routing_key` varchar(255) DEFAULT NULL,
  `class_type` varchar(255) DEFAULT NULL,
  `message_status` tinyint DEFAULT '0' COMMENT '0-新建 1-已发送 2-错误抵达 3-已抵达',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of mq_message
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for wms_purchase
-- ----------------------------
DROP TABLE IF EXISTS `wms_purchase`;
CREATE TABLE `wms_purchase` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '采购单id',
  `assignee_id` bigint DEFAULT NULL COMMENT '采购人id',
  `assignee_name` varchar(255) DEFAULT NULL COMMENT '采购人姓名',
  `phone` char(13) DEFAULT NULL COMMENT '联系方式',
  `priority` int DEFAULT NULL COMMENT '优先级',
  `status` int DEFAULT NULL COMMENT '状态 0新建 1已分配 2已领取 3已完成 4有异常',
  `ware_id` bigint DEFAULT NULL COMMENT '仓库id',
  `amount` decimal(18,4) DEFAULT NULL COMMENT '总金额',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL COMMENT '更新日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='采购信息';

-- ----------------------------
-- Records of wms_purchase
-- ----------------------------
BEGIN;
INSERT INTO `wms_purchase` (`id`, `assignee_id`, `assignee_name`, `phone`, `priority`, `status`, `ware_id`, `amount`, `create_time`, `update_time`) VALUES (1, 2, 'wangjie', '17515236789', 1, 4, NULL, NULL, NULL, '2021-04-29 23:43:33');
INSERT INTO `wms_purchase` (`id`, `assignee_id`, `assignee_name`, `phone`, `priority`, `status`, `ware_id`, `amount`, `create_time`, `update_time`) VALUES (2, 1, 'admin', '13612345678', NULL, 3, NULL, NULL, '2021-04-29 23:48:33', '2021-04-29 23:50:23');
COMMIT;

-- ----------------------------
-- Table structure for wms_purchase_detail
-- ----------------------------
DROP TABLE IF EXISTS `wms_purchase_detail`;
CREATE TABLE `wms_purchase_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `purchase_id` bigint DEFAULT NULL COMMENT '采购单id',
  `sku_id` bigint DEFAULT NULL COMMENT '采购商品id',
  `sku_num` int DEFAULT NULL COMMENT '采购数量',
  `sku_price` decimal(18,4) DEFAULT NULL COMMENT '采购金额',
  `ware_id` bigint DEFAULT NULL COMMENT '仓库id',
  `status` int DEFAULT NULL COMMENT '状态[0新建，1已分配，2正在采购，3已完成，4采购失败]',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of wms_purchase_detail
-- ----------------------------
BEGIN;
INSERT INTO `wms_purchase_detail` (`id`, `purchase_id`, `sku_id`, `sku_num`, `sku_price`, `ware_id`, `status`) VALUES (1, 1, 1, 100, NULL, 3, 3);
INSERT INTO `wms_purchase_detail` (`id`, `purchase_id`, `sku_id`, `sku_num`, `sku_price`, `ware_id`, `status`) VALUES (2, 1, 2, 100, NULL, 4, 4);
INSERT INTO `wms_purchase_detail` (`id`, `purchase_id`, `sku_id`, `sku_num`, `sku_price`, `ware_id`, `status`) VALUES (3, 2, 3, 100, NULL, 3, 3);
INSERT INTO `wms_purchase_detail` (`id`, `purchase_id`, `sku_id`, `sku_num`, `sku_price`, `ware_id`, `status`) VALUES (4, 2, 4, 100, NULL, 3, 3);
COMMIT;

-- ----------------------------
-- Table structure for wms_ware_info
-- ----------------------------
DROP TABLE IF EXISTS `wms_ware_info`;
CREATE TABLE `wms_ware_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) DEFAULT NULL COMMENT '仓库名',
  `address` varchar(255) DEFAULT NULL COMMENT '仓库地址',
  `area_code` varchar(20) DEFAULT NULL COMMENT '区域编码',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='仓库信息';

-- ----------------------------
-- Records of wms_ware_info
-- ----------------------------
BEGIN;
INSERT INTO `wms_ware_info` (`id`, `name`, `address`, `area_code`, `create_time`, `update_time`) VALUES (3, '华北二仓', '山东省青岛市济南路09号', '099009', NULL, NULL);
INSERT INTO `wms_ware_info` (`id`, `name`, `address`, `area_code`, `create_time`, `update_time`) VALUES (4, '东北一仓', '吉林省大连市北京北路', '119120', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for wms_ware_order_task
-- ----------------------------
DROP TABLE IF EXISTS `wms_ware_order_task`;
CREATE TABLE `wms_ware_order_task` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `order_id` bigint DEFAULT NULL COMMENT 'order_id',
  `order_sn` varchar(255) DEFAULT NULL COMMENT 'order_sn',
  `consignee` varchar(100) DEFAULT NULL COMMENT '收货人',
  `consignee_tel` char(15) DEFAULT NULL COMMENT '收货人电话',
  `delivery_address` varchar(500) DEFAULT NULL COMMENT '配送地址',
  `order_comment` varchar(200) DEFAULT NULL COMMENT '订单备注',
  `payment_way` tinyint(1) DEFAULT NULL COMMENT '付款方式【 1:在线付款 2:货到付款】',
  `task_status` tinyint DEFAULT NULL COMMENT '任务状态',
  `order_body` varchar(255) DEFAULT NULL COMMENT '订单描述',
  `tracking_no` char(30) DEFAULT NULL COMMENT '物流单号',
  `ware_id` bigint DEFAULT NULL COMMENT '仓库id',
  `task_comment` varchar(500) DEFAULT NULL COMMENT '工作单备注',
  `create_time` datetime DEFAULT NULL COMMENT 'create_time',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='库存工作单';

-- ----------------------------
-- Records of wms_ware_order_task
-- ----------------------------
BEGIN;
INSERT INTO `wms_ware_order_task` (`id`, `order_id`, `order_sn`, `consignee`, `consignee_tel`, `delivery_address`, `order_comment`, `payment_way`, `task_status`, `order_body`, `tracking_no`, `ware_id`, `task_comment`, `create_time`) VALUES (12, NULL, '202110262154408461452997109169709058', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_ware_order_task` (`id`, `order_id`, `order_sn`, `consignee`, `consignee_tel`, `delivery_address`, `order_comment`, `payment_way`, `task_status`, `order_body`, `tracking_no`, `ware_id`, `task_comment`, `create_time`) VALUES (13, NULL, '202110262317075141453017856931966978', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for wms_ware_order_task_detail
-- ----------------------------
DROP TABLE IF EXISTS `wms_ware_order_task_detail`;
CREATE TABLE `wms_ware_order_task_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `sku_id` bigint DEFAULT NULL COMMENT 'sku_id',
  `sku_name` varchar(255) DEFAULT NULL COMMENT 'sku_name',
  `sku_num` int DEFAULT NULL COMMENT '购买个数',
  `task_id` bigint DEFAULT NULL COMMENT '工作单id',
  `ware_id` bigint DEFAULT NULL COMMENT '仓库id',
  `lock_status` tinyint(1) DEFAULT NULL COMMENT '0-锁定 1-解锁 2-扣减',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='库存工作单';

-- ----------------------------
-- Records of wms_ware_order_task_detail
-- ----------------------------
BEGIN;
INSERT INTO `wms_ware_order_task_detail` (`id`, `sku_id`, `sku_name`, `sku_num`, `task_id`, `ware_id`, `lock_status`) VALUES (12, 41, 'Apple iPhone 12 (A2404) 128GB 绿色 支持移动联通电信5G 双卡双待手机 黑色 4G', 2, 12, 3, 1);
INSERT INTO `wms_ware_order_task_detail` (`id`, `sku_id`, `sku_name`, `sku_num`, `task_id`, `ware_id`, `lock_status`) VALUES (13, 41, 'Apple iPhone 12 (A2404) 128GB 绿色 支持移动联通电信5G 双卡双待手机 黑色 4G', 2, 13, 3, 1);
COMMIT;

-- ----------------------------
-- Table structure for wms_ware_sku
-- ----------------------------
DROP TABLE IF EXISTS `wms_ware_sku`;
CREATE TABLE `wms_ware_sku` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `sku_id` bigint DEFAULT NULL COMMENT 'sku_id',
  `ware_id` bigint DEFAULT NULL COMMENT '仓库id',
  `stock` int DEFAULT NULL COMMENT '库存数',
  `sku_name` varchar(200) DEFAULT NULL COMMENT 'sku_name',
  `stock_locked` int DEFAULT '0' COMMENT '锁定库存',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品库存';

-- ----------------------------
-- Records of wms_ware_sku
-- ----------------------------
BEGIN;
INSERT INTO `wms_ware_sku` (`id`, `sku_id`, `ware_id`, `stock`, `sku_name`, `stock_locked`) VALUES (1, 41, 3, 10, 'Apple iPhone 12 (A2404) 128GB 绿色 支持移动联通电信5G 双卡双待手机 黑色 4G', 0);
INSERT INTO `wms_ware_sku` (`id`, `sku_id`, `ware_id`, `stock`, `sku_name`, `stock_locked`) VALUES (2, 42, 3, 0, 'Apple iPhone 12 (A2404) 128GB 绿色 支持移动联通电信5G 双卡双待手机 黑色 6G', 0);
INSERT INTO `wms_ware_sku` (`id`, `sku_id`, `ware_id`, `stock`, `sku_name`, `stock_locked`) VALUES (3, 42, 4, 20, 'Apple iPhone 12 (A2404) 128GB 绿色 支持移动联通电信5G 双卡双待手机 黑色 6G', 0);
INSERT INTO `wms_ware_sku` (`id`, `sku_id`, `ware_id`, `stock`, `sku_name`, `stock_locked`) VALUES (4, 43, 4, 100, 'Apple iPhone 12 (A2404) 128GB 绿色 支持移动联通电信5G 双卡双待手机 白色 4G', 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
