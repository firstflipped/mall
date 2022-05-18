/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : gulimall_pms

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 18/05/2022 21:10:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pms_attr
-- ----------------------------
DROP TABLE IF EXISTS `pms_attr`;
CREATE TABLE `pms_attr` (
  `attr_id` bigint NOT NULL AUTO_INCREMENT COMMENT '属性id',
  `attr_name` varchar(30) DEFAULT NULL COMMENT '属性名',
  `icon` varchar(255) DEFAULT NULL COMMENT '属性图标',
  `value_select` varchar(255) DEFAULT NULL COMMENT '可选值列表[用逗号分隔]',
  `attr_type` tinyint(1) DEFAULT NULL COMMENT '属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]',
  `search_type` tinyint(1) DEFAULT NULL COMMENT '是否需要检索[0-不需要，1-需要]',
  `enable` tinyint(1) DEFAULT NULL COMMENT '启用状态[0 - 禁用，1 - 启用]',
  `quick_show` tinyint(1) DEFAULT NULL COMMENT '快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整',
  `attr_group_id` bigint DEFAULT NULL COMMENT '所属属性分组',
  `category_id` bigint DEFAULT NULL COMMENT '所属分类',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`attr_id`),
  KEY `idx_catalog_id` (`category_id`),
  KEY `idx_attr_group_id` (`attr_group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品属性';

-- ----------------------------
-- Records of pms_attr
-- ----------------------------
BEGIN;
INSERT INTO `pms_attr` (`attr_id`, `attr_name`, `icon`, `value_select`, `attr_type`, `search_type`, `enable`, `quick_show`, `attr_group_id`, `category_id`, `create_time`, `update_time`) VALUES (7, '产品名称', 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-04-21/a80e9d8a-8c3c-46d2-82d5-360598c493e0_美少女科学实验室.jpg', '小米10;华为Mate40;苹果12', 1, 1, 1, 1, 1, 225, NULL, NULL);
INSERT INTO `pms_attr` (`attr_id`, `attr_name`, `icon`, `value_select`, `attr_type`, `search_type`, `enable`, `quick_show`, `attr_group_id`, `category_id`, `create_time`, `update_time`) VALUES (8, '上市年份', 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-04-21/a80e9d8a-8c3c-46d2-82d5-360598c493e0_美少女科学实验室.jpg', '2021年;2020年', 1, 1, 1, 1, 1, 225, NULL, NULL);
INSERT INTO `pms_attr` (`attr_id`, `attr_name`, `icon`, `value_select`, `attr_type`, `search_type`, `enable`, `quick_show`, `attr_group_id`, `category_id`, `create_time`, `update_time`) VALUES (9, '品牌', 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-04-21/a80e9d8a-8c3c-46d2-82d5-360598c493e0_美少女科学实验室.jpg', '小米;华为;苹果', 1, 1, 1, 1, 1, 225, NULL, NULL);
INSERT INTO `pms_attr` (`attr_id`, `attr_name`, `icon`, `value_select`, `attr_type`, `search_type`, `enable`, `quick_show`, `attr_group_id`, `category_id`, `create_time`, `update_time`) VALUES (10, '机身宽度（mm）', 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-04-21/a80e9d8a-8c3c-46d2-82d5-360598c493e0_美少女科学实验室.jpg', '75mm;80mm;85mm', 1, 1, 1, 1, 2, 225, NULL, NULL);
INSERT INTO `pms_attr` (`attr_id`, `attr_name`, `icon`, `value_select`, `attr_type`, `search_type`, `enable`, `quick_show`, `attr_group_id`, `category_id`, `create_time`, `update_time`) VALUES (11, '机身长度（mm）', 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-04-21/a80e9d8a-8c3c-46d2-82d5-360598c493e0_美少女科学实验室.jpg', '162mm;163mm;164mm;165mm', 1, 1, 1, 1, 2, 225, NULL, '2022-03-10 15:18:47');
INSERT INTO `pms_attr` (`attr_id`, `attr_name`, `icon`, `value_select`, `attr_type`, `search_type`, `enable`, `quick_show`, `attr_group_id`, `category_id`, `create_time`, `update_time`) VALUES (12, '颜色', 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-04-21/a80e9d8a-8c3c-46d2-82d5-360598c493e0_美少女科学实验室.jpg', '黑色;白色;蓝色;绿色', 0, 0, 1, 0, 2, 225, NULL, NULL);
INSERT INTO `pms_attr` (`attr_id`, `attr_name`, `icon`, `value_select`, `attr_type`, `search_type`, `enable`, `quick_show`, `attr_group_id`, `category_id`, `create_time`, `update_time`) VALUES (13, '内存', 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-04-21/a80e9d8a-8c3c-46d2-82d5-360598c493e0_美少女科学实验室.jpg', '4G;6G;8G;10G', 0, 0, 1, 0, 2, 225, NULL, NULL);
INSERT INTO `pms_attr` (`attr_id`, `attr_name`, `icon`, `value_select`, `attr_type`, `search_type`, `enable`, `quick_show`, `attr_group_id`, `category_id`, `create_time`, `update_time`) VALUES (14, '机身厚度（mm）', 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-04-21/a80e9d8a-8c3c-46d2-82d5-360598c493e0_美少女科学实验室.jpg', '19mm;20mm', 1, 0, 1, 0, 2, 225, NULL, NULL);
INSERT INTO `pms_attr` (`attr_id`, `attr_name`, `icon`, `value_select`, `attr_type`, `search_type`, `enable`, `quick_show`, `attr_group_id`, `category_id`, `create_time`, `update_time`) VALUES (15, '芯片规格', 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-04-21/a80e9d8a-8c3c-46d2-82d5-360598c493e0_美少女科学实验室.jpg', '骁龙840;骁龙850;A13;A12;麒麟950;麒麟960', 1, 1, 1, 1, 3, 225, NULL, NULL);
INSERT INTO `pms_attr` (`attr_id`, `attr_name`, `icon`, `value_select`, `attr_type`, `search_type`, `enable`, `quick_show`, `attr_group_id`, `category_id`, `create_time`, `update_time`) VALUES (16, '主屏幕尺寸', 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-04-21/a80e9d8a-8c3c-46d2-82d5-360598c493e0_美少女科学实验室.jpg', '其他尺寸', 1, 0, 1, 0, 4, 225, NULL, NULL);
INSERT INTO `pms_attr` (`attr_id`, `attr_name`, `icon`, `value_select`, `attr_type`, `search_type`, `enable`, `quick_show`, `attr_group_id`, `category_id`, `create_time`, `update_time`) VALUES (17, '屏幕材质类型', 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-04-21/a80e9d8a-8c3c-46d2-82d5-360598c493e0_美少女科学实验室.jpg', 'LED;OLED', 1, 0, 1, 0, 4, 225, NULL, NULL);
INSERT INTO `pms_attr` (`attr_id`, `attr_name`, `icon`, `value_select`, `attr_type`, `search_type`, `enable`, `quick_show`, `attr_group_id`, `category_id`, `create_time`, `update_time`) VALUES (19, '网络', NULL, '3G;4G;5G', 0, 1, 1, 1, 2, 225, '2022-03-08 17:18:59', NULL);
COMMIT;

-- ----------------------------
-- Table structure for pms_attr_group
-- ----------------------------
DROP TABLE IF EXISTS `pms_attr_group`;
CREATE TABLE `pms_attr_group` (
  `attr_group_id` bigint NOT NULL AUTO_INCREMENT COMMENT '分组id',
  `attr_group_name` char(20) DEFAULT NULL COMMENT '组名',
  `icon` varchar(255) DEFAULT NULL COMMENT '组图标',
  `sort` int DEFAULT NULL COMMENT '排序',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `category_id` bigint DEFAULT NULL COMMENT '所属分类id',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`attr_group_id`),
  KEY `idx_category_id` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='属性分组';

-- ----------------------------
-- Records of pms_attr_group
-- ----------------------------
BEGIN;
INSERT INTO `pms_attr_group` (`attr_group_id`, `attr_group_name`, `icon`, `sort`, `description`, `category_id`, `create_time`, `update_time`) VALUES (1, '主体', 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-04-21/a80e9d8a-8c3c-46d2-82d5-360598c493e0_美少女科学实验室.jpg', 0, '主体', 225, '2022-03-13 06:13:56', NULL);
INSERT INTO `pms_attr_group` (`attr_group_id`, `attr_group_name`, `icon`, `sort`, `description`, `category_id`, `create_time`, `update_time`) VALUES (2, '基本信息', 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-04-21/a80e9d8a-8c3c-46d2-82d5-360598c493e0_美少女科学实验室.jpg', 1, '基本信息', 225, '2022-03-13 06:14:00', NULL);
INSERT INTO `pms_attr_group` (`attr_group_id`, `attr_group_name`, `icon`, `sort`, `description`, `category_id`, `create_time`, `update_time`) VALUES (3, '主芯片', 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-04-21/a80e9d8a-8c3c-46d2-82d5-360598c493e0_美少女科学实验室.jpg', 0, '芯片处理器', 225, '2022-03-13 06:14:02', NULL);
INSERT INTO `pms_attr_group` (`attr_group_id`, `attr_group_name`, `icon`, `sort`, `description`, `category_id`, `create_time`, `update_time`) VALUES (4, '屏幕', 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-04-21/a80e9d8a-8c3c-46d2-82d5-360598c493e0_美少女科学实验室.jpg', 1, '屏幕参数', 225, '2022-03-13 06:14:04', NULL);
COMMIT;

-- ----------------------------
-- Table structure for pms_brand
-- ----------------------------
DROP TABLE IF EXISTS `pms_brand`;
CREATE TABLE `pms_brand` (
  `brand_id` bigint NOT NULL AUTO_INCREMENT COMMENT '品牌id',
  `brand_name` varchar(50) DEFAULT NULL COMMENT '品牌名',
  `first_letter` char(1) DEFAULT NULL COMMENT '检索首字母',
  `logo` varchar(511) DEFAULT NULL COMMENT '品牌logo地址',
  `description` longtext COMMENT '介绍',
  `show_status` tinyint(1) DEFAULT NULL COMMENT '显示状态[0-不显示；1-显示]',
  `sort` int DEFAULT NULL COMMENT '排序',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`brand_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='品牌';

-- ----------------------------
-- Records of pms_brand
-- ----------------------------
BEGIN;
INSERT INTO `pms_brand` (`brand_id`, `brand_name`, `first_letter`, `logo`, `description`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1, '华为', 'H', 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-04-21/1a090fc8-7b5f-4672-827b-5e76a64a887c_黎明风景女子后背.jpg', '为中华崛起而为之', 1, 0, '2022-02-17 02:24:59', NULL);
INSERT INTO `pms_brand` (`brand_id`, `brand_name`, `first_letter`, `logo`, `description`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (5, '小米', 'X', 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-04-21/a80e9d8a-8c3c-46d2-82d5-360598c493e0_美少女科学实验室.jpg', '为发烧而生', 1, 0, '2022-02-17 02:25:02', NULL);
INSERT INTO `pms_brand` (`brand_id`, `brand_name`, `first_letter`, `logo`, `description`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (6, '苹果', 'P', 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-04-22/fefdedcc-70de-4d44-8cb2-00fd20cb900b_少女背部写真.jpg', '砸过牛顿的苹果', 1, 1, '2022-02-17 02:25:04', '2022-03-13 13:48:06');
INSERT INTO `pms_brand` (`brand_id`, `brand_name`, `first_letter`, `logo`, `description`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (9, '三星', 'S', 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2022-03-01/37e0992f-41cf-4a67-82e3-3caab1e8faf2_清晨桂林漓江山水风景.jpg', '小西巴', 1, 0, '2022-03-01 16:18:26', NULL);
INSERT INTO `pms_brand` (`brand_id`, `brand_name`, `first_letter`, `logo`, `description`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (10, '索尼', 'S', 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2022-03-08/f9c93a53-ca37-400f-8a36-d928109e07b7_少女背部写真.jpg', '小樱花', 1, 0, '2022-03-08 15:44:20', NULL);
COMMIT;

-- ----------------------------
-- Table structure for pms_category
-- ----------------------------
DROP TABLE IF EXISTS `pms_category`;
CREATE TABLE `pms_category` (
  `category_id` bigint NOT NULL AUTO_INCREMENT COMMENT '分类id',
  `category_name` char(50) DEFAULT NULL COMMENT '分类名称',
  `parent_id` bigint DEFAULT NULL COMMENT '父分类id',
  `category_level` tinyint(1) DEFAULT NULL COMMENT '层级',
  `show_status` tinyint(1) DEFAULT NULL COMMENT '是否显示[0-不显示，1显示]',
  `sort` int DEFAULT NULL COMMENT '排序',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`category_id`),
  KEY `idx_parent_id` (`parent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1424 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品三级分类';

-- ----------------------------
-- Records of pms_category
-- ----------------------------
BEGIN;
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1, '图书、音像、电子书刊', 0, 1, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (2, '手机', 0, 1, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (3, '家用电器', 0, 1, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (4, '数码', 0, 1, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (5, '家居家装', 0, 1, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (6, '电脑办公', 0, 1, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (7, '厨具', 0, 1, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (8, '个护化妆', 0, 1, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (9, '服饰内衣', 0, 1, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (10, '钟表', 0, 1, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (11, '鞋靴', 0, 1, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (12, '母婴', 0, 1, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (13, '礼品箱包', 0, 1, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (14, '食品饮料、保健食品', 0, 1, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (15, '珠宝', 0, 1, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (16, '汽车用品', 0, 1, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (17, '运动健康', 0, 1, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (18, '玩具乐器', 0, 1, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (19, '彩票、旅行、充值、票务', 0, 1, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (20, '生鲜', 0, 1, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (21, '整车', 0, 1, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (22, '电子书刊', 1, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (23, '音像', 1, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (24, '英文原版', 1, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (25, '文艺', 1, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (26, '少儿', 1, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (27, '人文社科', 1, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (28, '经管励志', 1, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (29, '生活', 1, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (30, '科技', 1, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (31, '教育', 1, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (32, '港台图书', 1, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (33, '其他', 1, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (34, '手机通讯', 2, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (35, '运营商', 2, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (36, '手机配件', 2, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (37, '大 家 电', 3, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (38, '厨卫大电', 3, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (39, '厨房小电', 3, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (40, '生活电器', 3, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (41, '个护健康', 3, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (42, '五金家装', 3, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (43, '摄影摄像', 4, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (44, '数码配件', 4, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (45, '智能设备', 4, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (46, '影音娱乐', 4, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (47, '电子教育', 4, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (48, '虚拟商品', 4, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (49, '家纺', 5, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (50, '灯具', 5, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (51, '生活日用', 5, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (52, '家装软饰', 5, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (53, '宠物生活', 5, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (54, '电脑整机', 6, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (55, '电脑配件', 6, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (56, '外设产品', 6, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (57, '游戏设备', 6, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (58, '网络产品', 6, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (59, '办公设备', 6, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (60, '文具/耗材', 6, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (61, '服务产品', 6, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (62, '烹饪锅具', 7, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (63, '刀剪菜板', 7, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (64, '厨房配件', 7, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (65, '水具酒具', 7, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (66, '餐具', 7, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (67, '酒店用品', 7, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (68, '茶具/咖啡具', 7, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (69, '清洁用品', 8, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (70, '面部护肤', 8, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (71, '身体护理', 8, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (72, '口腔护理', 8, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (73, '女性护理', 8, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (74, '洗发护发', 8, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (75, '香水彩妆', 8, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (76, '女装', 9, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (77, '男装', 9, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (78, '内衣', 9, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (79, '洗衣服务', 9, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (80, '服饰配件', 9, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (81, '钟表', 10, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (82, '流行男鞋', 11, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (83, '时尚女鞋', 11, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (84, '奶粉', 12, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (85, '营养辅食', 12, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (86, '尿裤湿巾', 12, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (87, '喂养用品', 12, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (88, '洗护用品', 12, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (89, '童车童床', 12, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (90, '寝居服饰', 12, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (91, '妈妈专区', 12, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (92, '童装童鞋', 12, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (93, '安全座椅', 12, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (94, '潮流女包', 13, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (95, '精品男包', 13, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (96, '功能箱包', 13, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (97, '礼品', 13, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (98, '奢侈品', 13, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (99, '婚庆', 13, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (100, '进口食品', 14, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (101, '地方特产', 14, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (102, '休闲食品', 14, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (103, '粮油调味', 14, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (104, '饮料冲调', 14, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (105, '食品礼券', 14, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (106, '茗茶', 14, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (107, '时尚饰品', 15, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (108, '黄金', 15, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (109, 'K金饰品', 15, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (110, '金银投资', 15, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (111, '银饰', 15, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (112, '钻石', 15, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (113, '翡翠玉石', 15, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (114, '水晶玛瑙', 15, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (115, '彩宝', 15, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (116, '铂金', 15, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (117, '木手串/把件', 15, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (118, '珍珠', 15, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (119, '维修保养', 16, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (120, '车载电器', 16, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (121, '美容清洗', 16, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (122, '汽车装饰', 16, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (123, '安全自驾', 16, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (124, '汽车服务', 16, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (125, '赛事改装', 16, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (126, '运动鞋包', 17, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (127, '运动服饰', 17, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (128, '骑行运动', 17, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (129, '垂钓用品', 17, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (130, '游泳用品', 17, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (131, '户外鞋服', 17, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (132, '户外装备', 17, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (133, '健身训练', 17, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (134, '体育用品', 17, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (135, '适用年龄', 18, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (136, '遥控/电动', 18, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (137, '毛绒布艺', 18, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (138, '娃娃玩具', 18, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (139, '模型玩具', 18, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (140, '健身玩具', 18, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (141, '动漫玩具', 18, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (142, '益智玩具', 18, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (143, '积木拼插', 18, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (144, 'DIY玩具', 18, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (145, '创意减压', 18, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (146, '乐器', 18, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (147, '彩票', 19, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (148, '机票', 19, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (149, '酒店', 19, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (150, '旅行', 19, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (151, '充值', 19, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (152, '游戏', 19, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (153, '票务', 19, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (154, '产地直供', 20, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (155, '水果', 20, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (156, '猪牛羊肉', 20, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (157, '海鲜水产', 20, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (158, '禽肉蛋品', 20, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (159, '冷冻食品', 20, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (160, '熟食腊味', 20, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (161, '饮品甜品', 20, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (162, '蔬菜', 20, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (163, '全新整车', 21, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (164, '二手车', 21, 2, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (165, '电子书', 22, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (166, '网络原创', 22, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (167, '数字杂志', 22, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (168, '多媒体图书', 22, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (169, '音乐', 23, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (170, '影视', 23, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (171, '教育音像', 23, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (172, '少儿', 24, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (173, '商务投资', 24, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (174, '英语学习与考试', 24, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (175, '文学', 24, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (176, '传记', 24, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (177, '励志', 24, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (178, '小说', 25, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (179, '文学', 25, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (180, '青春文学', 25, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (181, '传记', 25, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (182, '艺术', 25, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (183, '少儿', 26, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (184, '0-2岁', 26, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (185, '3-6岁', 26, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (186, '7-10岁', 26, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (187, '11-14岁', 26, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (188, '历史', 27, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (189, '哲学', 27, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (190, '国学', 27, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (191, '政治/军事', 27, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (192, '法律', 27, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (193, '人文社科', 27, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (194, '心理学', 27, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (195, '文化', 27, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (196, '社会科学', 27, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (197, '经济', 28, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (198, '金融与投资', 28, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (199, '管理', 28, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (200, '励志与成功', 28, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (201, '生活', 29, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (202, '健身与保健', 29, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (203, '家庭与育儿', 29, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (204, '旅游', 29, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (205, '烹饪美食', 29, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (206, '工业技术', 30, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (207, '科普读物', 30, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (208, '建筑', 30, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (209, '医学', 30, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (210, '科学与自然', 30, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (211, '计算机与互联网', 30, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (212, '电子通信', 30, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (213, '中小学教辅', 31, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (214, '教育与考试', 31, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (215, '外语学习', 31, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (216, '大中专教材', 31, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (217, '字典词典', 31, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (218, '艺术/设计/收藏', 32, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (219, '经济管理', 32, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (220, '文化/学术', 32, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (221, '少儿', 32, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (222, '工具书', 33, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (223, '杂志/期刊', 33, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (224, '套装书', 33, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (225, '手机', 34, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (226, '对讲机', 34, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (227, '合约机', 35, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (228, '选号中心', 35, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (229, '装宽带', 35, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (230, '办套餐', 35, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (231, '移动电源', 36, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (232, '电池/移动电源', 36, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (233, '蓝牙耳机', 36, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (234, '充电器/数据线', 36, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (235, '苹果周边', 36, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (236, '手机耳机', 36, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (237, '手机贴膜', 36, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (238, '手机存储卡', 36, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (239, '充电器', 36, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (240, '数据线', 36, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (241, '手机保护套', 36, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (242, '车载配件', 36, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (243, 'iPhone 配件', 36, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (244, '手机电池', 36, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (245, '创意配件', 36, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (246, '便携/无线音响', 36, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (247, '手机饰品', 36, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (248, '拍照配件', 36, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (249, '手机支架', 36, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (250, '平板电视', 37, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (251, '空调', 37, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (252, '冰箱', 37, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (253, '洗衣机', 37, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (254, '家庭影院', 37, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (255, 'DVD/电视盒子', 37, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (256, '迷你音响', 37, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (257, '冷柜/冰吧', 37, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (258, '家电配件', 37, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (259, '功放', 37, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (260, '回音壁/Soundbar', 37, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (261, 'Hi-Fi专区', 37, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (262, '电视盒子', 37, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (263, '酒柜', 37, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (264, '燃气灶', 38, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (265, '油烟机', 38, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (266, '热水器', 38, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (267, '消毒柜', 38, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (268, '洗碗机', 38, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (269, '料理机', 39, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (270, '榨汁机', 39, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (271, '电饭煲', 39, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (272, '电压力锅', 39, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (273, '豆浆机', 39, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (274, '咖啡机', 39, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (275, '微波炉', 39, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (276, '电烤箱', 39, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (277, '电磁炉', 39, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (278, '面包机', 39, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (279, '煮蛋器', 39, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (280, '酸奶机', 39, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (281, '电炖锅', 39, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (282, '电水壶/热水瓶', 39, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (283, '电饼铛', 39, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (284, '多用途锅', 39, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (285, '电烧烤炉', 39, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (286, '果蔬解毒机', 39, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (287, '其它厨房电器', 39, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (288, '养生壶/煎药壶', 39, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (289, '电热饭盒', 39, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (290, '取暖电器', 40, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (291, '净化器', 40, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (292, '加湿器', 40, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (293, '扫地机器人', 40, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (294, '吸尘器', 40, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (295, '挂烫机/熨斗', 40, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (296, '插座', 40, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (297, '电话机', 40, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (298, '清洁机', 40, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (299, '除湿机', 40, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (300, '干衣机', 40, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (301, '收录/音机', 40, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (302, '电风扇', 40, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (303, '冷风扇', 40, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (304, '其它生活电器', 40, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (305, '生活电器配件', 40, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (306, '净水器', 40, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (307, '饮水机', 40, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (308, '剃须刀', 41, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (309, '剃/脱毛器', 41, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (310, '口腔护理', 41, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (311, '电吹风', 41, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (312, '美容器', 41, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (313, '理发器', 41, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (314, '卷/直发器', 41, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (315, '按摩椅', 41, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (316, '按摩器', 41, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (317, '足浴盆', 41, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (318, '血压计', 41, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (319, '电子秤/厨房秤', 41, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (320, '血糖仪', 41, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (321, '体温计', 41, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (322, '其它健康电器', 41, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (323, '计步器/脂肪检测仪', 41, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (324, '电动工具', 42, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (325, '手动工具', 42, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (326, '仪器仪表', 42, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (327, '浴霸/排气扇', 42, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (328, '灯具', 42, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (329, 'LED灯', 42, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (330, '洁身器', 42, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (331, '水槽', 42, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (332, '龙头', 42, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (333, '淋浴花洒', 42, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (334, '厨卫五金', 42, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (335, '家具五金', 42, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (336, '门铃', 42, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (337, '电气开关', 42, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (338, '插座', 42, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (339, '电工电料', 42, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (340, '监控安防', 42, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (341, '电线/线缆', 42, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (342, '数码相机', 43, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (343, '单电/微单相机', 43, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (344, '单反相机', 43, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (345, '摄像机', 43, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (346, '拍立得', 43, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (347, '运动相机', 43, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (348, '镜头', 43, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (349, '户外器材', 43, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (350, '影棚器材', 43, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (351, '冲印服务', 43, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (352, '数码相框', 43, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (353, '存储卡', 44, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (354, '读卡器', 44, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (355, '滤镜', 44, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (356, '闪光灯/手柄', 44, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (357, '相机包', 44, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (358, '三脚架/云台', 44, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (359, '相机清洁/贴膜', 44, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (360, '机身附件', 44, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (361, '镜头附件', 44, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (362, '电池/充电器', 44, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (363, '移动电源', 44, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (364, '数码支架', 44, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (365, '智能手环', 45, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (366, '智能手表', 45, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (367, '智能眼镜', 45, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (368, '运动跟踪器', 45, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (369, '健康监测', 45, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (370, '智能配饰', 45, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (371, '智能家居', 45, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (372, '体感车', 45, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (373, '其他配件', 45, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (374, '智能机器人', 45, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (375, '无人机', 45, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (376, 'MP3/MP4', 46, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (377, '智能设备', 46, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (378, '耳机/耳麦', 46, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (379, '便携/无线音箱', 46, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (380, '音箱/音响', 46, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (381, '高清播放器', 46, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (382, '收音机', 46, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (383, 'MP3/MP4配件', 46, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (384, '麦克风', 46, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (385, '专业音频', 46, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (386, '苹果配件', 46, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (387, '学生平板', 47, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (388, '点读机/笔', 47, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (389, '早教益智', 47, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (390, '录音笔', 47, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (391, '电纸书', 47, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (392, '电子词典', 47, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (393, '复读机', 47, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (394, '延保服务', 48, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (395, '杀毒软件', 48, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (396, '积分商品', 48, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (397, '桌布/罩件', 49, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (398, '地毯地垫', 49, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (399, '沙发垫套/椅垫', 49, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (400, '床品套件', 49, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (401, '被子', 49, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (402, '枕芯', 49, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (403, '床单被罩', 49, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (404, '毯子', 49, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (405, '床垫/床褥', 49, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (406, '蚊帐', 49, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (407, '抱枕靠垫', 49, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (408, '毛巾浴巾', 49, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (409, '电热毯', 49, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (410, '窗帘/窗纱', 49, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (411, '布艺软饰', 49, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (412, '凉席', 49, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (413, '台灯', 50, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (414, '节能灯', 50, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (415, '装饰灯', 50, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (416, '落地灯', 50, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (417, '应急灯/手电', 50, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (418, 'LED灯', 50, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (419, '吸顶灯', 50, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (420, '五金电器', 50, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (421, '筒灯射灯', 50, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (422, '吊灯', 50, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (423, '氛围照明', 50, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (424, '保暖防护', 51, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (425, '收纳用品', 51, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (426, '雨伞雨具', 51, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (427, '浴室用品', 51, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (428, '缝纫/针织用品', 51, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (429, '洗晒/熨烫', 51, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (430, '净化除味', 51, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (431, '相框/照片墙', 52, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (432, '装饰字画', 52, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (433, '节庆饰品', 52, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (434, '手工/十字绣', 52, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (435, '装饰摆件', 52, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (436, '帘艺隔断', 52, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (437, '墙贴/装饰贴', 52, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (438, '钟饰', 52, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (439, '花瓶花艺', 52, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (440, '香薰蜡烛', 52, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (441, '创意家居', 52, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (442, '宠物主粮', 53, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (443, '宠物零食', 53, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (444, '医疗保健', 53, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (445, '家居日用', 53, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (446, '宠物玩具', 53, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (447, '出行装备', 53, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (448, '洗护美容', 53, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (449, '笔记本', 54, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (450, '超极本', 54, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (451, '游戏本', 54, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (452, '平板电脑', 54, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (453, '平板电脑配件', 54, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (454, '台式机', 54, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (455, '服务器/工作站', 54, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (456, '笔记本配件', 54, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (457, '一体机', 54, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (458, 'CPU', 55, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (459, '主板', 55, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (460, '显卡', 55, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (461, '硬盘', 55, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (462, 'SSD固态硬盘', 55, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (463, '内存', 55, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (464, '机箱', 55, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (465, '电源', 55, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (466, '显示器', 55, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (467, '刻录机/光驱', 55, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (468, '散热器', 55, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (469, '声卡/扩展卡', 55, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (470, '装机配件', 55, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (471, '组装电脑', 55, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (472, '移动硬盘', 56, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (473, 'U盘', 56, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (474, '鼠标', 56, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (475, '键盘', 56, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (476, '鼠标垫', 56, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (477, '摄像头', 56, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (478, '手写板', 56, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (479, '硬盘盒', 56, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (480, '插座', 56, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (481, '线缆', 56, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (482, 'UPS电源', 56, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (483, '电脑工具', 56, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (484, '游戏设备', 56, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (485, '电玩', 56, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (486, '电脑清洁', 56, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (487, '网络仪表仪器', 56, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (488, '游戏机', 57, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (489, '游戏耳机', 57, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (490, '手柄/方向盘', 57, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (491, '游戏软件', 57, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (492, '游戏周边', 57, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (493, '路由器', 58, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (494, '网卡', 58, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (495, '交换机', 58, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (496, '网络存储', 58, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (497, '4G/3G上网', 58, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (498, '网络盒子', 58, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (499, '网络配件', 58, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (500, '投影机', 59, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (501, '投影配件', 59, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (502, '多功能一体机', 59, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (503, '打印机', 59, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (504, '传真设备', 59, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (505, '验钞/点钞机', 59, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (506, '扫描设备', 59, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (507, '复合机', 59, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (508, '碎纸机', 59, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (509, '考勤机', 59, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (510, '收款/POS机', 59, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (511, '会议音频视频', 59, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (512, '保险柜', 59, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (513, '装订/封装机', 59, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (514, '安防监控', 59, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (515, '办公家具', 59, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (516, '白板', 59, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (517, '硒鼓/墨粉', 60, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (518, '墨盒', 60, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (519, '色带', 60, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (520, '纸类', 60, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (521, '办公文具', 60, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (522, '学生文具', 60, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (523, '财会用品', 60, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (524, '文件管理', 60, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (525, '本册/便签', 60, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (526, '计算器', 60, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (527, '笔类', 60, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (528, '画具画材', 60, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (529, '刻录碟片/附件', 60, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (530, '上门安装', 61, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (531, '延保服务', 61, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (532, '维修保养', 61, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (533, '电脑软件', 61, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (534, '京东服务', 61, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (535, '炒锅', 62, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (536, '煎锅', 62, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (537, '压力锅', 62, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (538, '蒸锅', 62, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (539, '汤锅', 62, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (540, '奶锅', 62, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (541, '锅具套装', 62, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (542, '煲类', 62, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (543, '水壶', 62, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (544, '火锅', 62, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (545, '菜刀', 63, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (546, '剪刀', 63, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (547, '刀具套装', 63, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (548, '砧板', 63, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (549, '瓜果刀/刨', 63, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (550, '多功能刀', 63, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (551, '保鲜盒', 64, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (552, '烘焙/烧烤', 64, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (553, '饭盒/提锅', 64, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (554, '储物/置物架', 64, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (555, '厨房DIY/小工具', 64, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (556, '塑料杯', 65, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (557, '运动水壶', 65, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (558, '玻璃杯', 65, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (559, '陶瓷/马克杯', 65, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (560, '保温杯', 65, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (561, '保温壶', 65, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (562, '酒杯/酒具', 65, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (563, '杯具套装', 65, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (564, '餐具套装', 66, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (565, '碗/碟/盘', 66, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (566, '筷勺/刀叉', 66, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (567, '一次性用品', 66, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (568, '果盘/果篮', 66, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (569, '自助餐炉', 67, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (570, '酒店餐具', 67, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (571, '酒店水具', 67, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (572, '整套茶具', 68, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (573, '茶杯', 68, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (574, '茶壶', 68, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (575, '茶盘茶托', 68, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (576, '茶叶罐', 68, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (577, '茶具配件', 68, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (578, '茶宠摆件', 68, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (579, '咖啡具', 68, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (580, '其他', 68, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (581, '纸品湿巾', 69, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (582, '衣物清洁', 69, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (583, '清洁工具', 69, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (584, '驱虫用品', 69, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (585, '家庭清洁', 69, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (586, '皮具护理', 69, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (587, '一次性用品', 69, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (588, '洁面', 70, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (589, '乳液面霜', 70, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (590, '面膜', 70, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (591, '剃须', 70, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (592, '套装', 70, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (593, '精华', 70, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (594, '眼霜', 70, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (595, '卸妆', 70, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (596, '防晒', 70, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (597, '防晒隔离', 70, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (598, 'T区护理', 70, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (599, '眼部护理', 70, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (600, '精华露', 70, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (601, '爽肤水', 70, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (602, '沐浴', 71, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (603, '润肤', 71, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (604, '颈部', 71, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (605, '手足', 71, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (606, '纤体塑形', 71, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (607, '美胸', 71, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (608, '套装', 71, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (609, '精油', 71, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (610, '洗发护发', 71, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (611, '染发/造型', 71, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (612, '香薰精油', 71, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (613, '磨砂/浴盐', 71, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (614, '手工/香皂', 71, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (615, '洗发', 71, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (616, '护发', 71, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (617, '染发', 71, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (618, '磨砂膏', 71, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (619, '香皂', 71, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (620, '牙膏/牙粉', 72, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (621, '牙刷/牙线', 72, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (622, '漱口水', 72, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (623, '套装', 72, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (624, '卫生巾', 73, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (625, '卫生护垫', 73, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (626, '私密护理', 73, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (627, '脱毛膏', 73, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (628, '其他', 73, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (629, '洗发', 74, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (630, '护发', 74, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (631, '染发', 74, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (632, '造型', 74, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (633, '假发', 74, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (634, '套装', 74, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (635, '美发工具', 74, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (636, '脸部护理', 74, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (637, '香水', 75, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (638, '底妆', 75, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (639, '腮红', 75, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (640, '眼影', 75, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (641, '唇部', 75, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (642, '美甲', 75, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (643, '眼线', 75, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (644, '美妆工具', 75, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (645, '套装', 75, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (646, '防晒隔离', 75, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (647, '卸妆', 75, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (648, '眉笔', 75, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (649, '睫毛膏', 75, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (650, 'T恤', 76, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (651, '衬衫', 76, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (652, '针织衫', 76, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (653, '雪纺衫', 76, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (654, '卫衣', 76, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (655, '马甲', 76, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (656, '连衣裙', 76, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (657, '半身裙', 76, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (658, '牛仔裤', 76, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (659, '休闲裤', 76, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (660, '打底裤', 76, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (661, '正装裤', 76, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (662, '小西装', 76, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (663, '短外套', 76, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (664, '风衣', 76, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (665, '毛呢大衣', 76, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (666, '真皮皮衣', 76, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (667, '棉服', 76, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (668, '羽绒服', 76, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (669, '大码女装', 76, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (670, '中老年女装', 76, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (671, '婚纱', 76, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (672, '打底衫', 76, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (673, '旗袍/唐装', 76, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (674, '加绒裤', 76, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (675, '吊带/背心', 76, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (676, '羊绒衫', 76, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (677, '短裤', 76, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (678, '皮草', 76, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (679, '礼服', 76, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (680, '仿皮皮衣', 76, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (681, '羊毛衫', 76, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (682, '设计师/潮牌', 76, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (683, '衬衫', 77, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (684, 'T恤', 77, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (685, 'POLO衫', 77, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (686, '针织衫', 77, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (687, '羊绒衫', 77, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (688, '卫衣', 77, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (689, '马甲/背心', 77, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (690, '夹克', 77, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (691, '风衣', 77, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (692, '毛呢大衣', 77, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (693, '仿皮皮衣', 77, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (694, '西服', 77, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (695, '棉服', 77, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (696, '羽绒服', 77, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (697, '牛仔裤', 77, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (698, '休闲裤', 77, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (699, '西裤', 77, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (700, '西服套装', 77, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (701, '大码男装', 77, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (702, '中老年男装', 77, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (703, '唐装/中山装', 77, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (704, '工装', 77, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (705, '真皮皮衣', 77, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (706, '加绒裤', 77, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (707, '卫裤/运动裤', 77, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (708, '短裤', 77, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (709, '设计师/潮牌', 77, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (710, '羊毛衫', 77, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (711, '文胸', 78, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (712, '女式内裤', 78, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (713, '男式内裤', 78, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (714, '睡衣/家居服', 78, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (715, '塑身美体', 78, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (716, '泳衣', 78, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (717, '吊带/背心', 78, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (718, '抹胸', 78, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (719, '连裤袜/丝袜', 78, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (720, '美腿袜', 78, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (721, '商务男袜', 78, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (722, '保暖内衣', 78, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (723, '情侣睡衣', 78, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (724, '文胸套装', 78, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (725, '少女文胸', 78, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (726, '休闲棉袜', 78, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (727, '大码内衣', 78, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (728, '内衣配件', 78, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (729, '打底裤袜', 78, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (730, '打底衫', 78, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (731, '秋衣秋裤', 78, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (732, '情趣内衣', 78, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (733, '服装洗护', 79, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (734, '太阳镜', 80, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (735, '光学镜架/镜片', 80, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (736, '围巾/手套/帽子套装', 80, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (737, '袖扣', 80, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (738, '棒球帽', 80, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (739, '毛线帽', 80, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (740, '遮阳帽', 80, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (741, '老花镜', 80, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (742, '装饰眼镜', 80, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (743, '防辐射眼镜', 80, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (744, '游泳镜', 80, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (745, '女士丝巾/围巾/披肩', 80, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (746, '男士丝巾/围巾', 80, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (747, '鸭舌帽', 80, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (748, '贝雷帽', 80, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (749, '礼帽', 80, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (750, '真皮手套', 80, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (751, '毛线手套', 80, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (752, '防晒手套', 80, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (753, '男士腰带/礼盒', 80, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (754, '女士腰带/礼盒', 80, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (755, '钥匙扣', 80, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (756, '遮阳伞/雨伞', 80, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (757, '口罩', 80, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (758, '耳罩/耳包', 80, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (759, '假领', 80, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (760, '毛线/布面料', 80, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (761, '领带/领结/领带夹', 80, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (762, '男表', 81, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (763, '瑞表', 81, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (764, '女表', 81, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (765, '国表', 81, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (766, '日韩表', 81, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (767, '欧美表', 81, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (768, '德表', 81, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (769, '儿童手表', 81, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (770, '智能手表', 81, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (771, '闹钟', 81, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (772, '座钟挂钟', 81, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (773, '钟表配件', 81, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (774, '商务休闲鞋', 82, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (775, '正装鞋', 82, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (776, '休闲鞋', 82, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (777, '凉鞋/沙滩鞋', 82, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (778, '男靴', 82, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (779, '功能鞋', 82, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (780, '拖鞋/人字拖', 82, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (781, '雨鞋/雨靴', 82, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (782, '传统布鞋', 82, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (783, '鞋配件', 82, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (784, '帆布鞋', 82, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (785, '增高鞋', 82, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (786, '工装鞋', 82, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (787, '定制鞋', 82, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (788, '高跟鞋', 83, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (789, '单鞋', 83, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (790, '休闲鞋', 83, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (791, '凉鞋', 83, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (792, '女靴', 83, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (793, '雪地靴', 83, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (794, '拖鞋/人字拖', 83, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (795, '踝靴', 83, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (796, '筒靴', 83, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (797, '帆布鞋', 83, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (798, '雨鞋/雨靴', 83, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (799, '妈妈鞋', 83, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (800, '鞋配件', 83, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (801, '特色鞋', 83, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (802, '鱼嘴鞋', 83, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (803, '布鞋/绣花鞋', 83, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (804, '马丁靴', 83, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (805, '坡跟鞋', 83, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (806, '松糕鞋', 83, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (807, '内增高', 83, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (808, '防水台', 83, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (809, '婴幼奶粉', 84, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (810, '孕妈奶粉', 84, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (811, '益生菌/初乳', 85, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (812, '米粉/菜粉', 85, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (813, '果泥/果汁', 85, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (814, 'DHA', 85, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (815, '宝宝零食', 85, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (816, '钙铁锌/维生素', 85, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (817, '清火/开胃', 85, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (818, '面条/粥', 85, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (819, '婴儿尿裤', 86, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (820, '拉拉裤', 86, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (821, '婴儿湿巾', 86, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (822, '成人尿裤', 86, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (823, '奶瓶奶嘴', 87, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (824, '吸奶器', 87, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (825, '暖奶消毒', 87, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (826, '儿童餐具', 87, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (827, '水壶/水杯', 87, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (828, '牙胶安抚', 87, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (829, '围兜/防溅衣', 87, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (830, '辅食料理机', 87, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (831, '食物存储', 87, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (832, '宝宝护肤', 88, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (833, '洗发沐浴', 88, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (834, '奶瓶清洗', 88, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (835, '驱蚊防晒', 88, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (836, '理发器', 88, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (837, '洗澡用具', 88, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (838, '婴儿口腔清洁', 88, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (839, '洗衣液/皂', 88, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (840, '日常护理', 88, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (841, '座便器', 88, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (842, '婴儿推车', 89, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (843, '餐椅摇椅', 89, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (844, '婴儿床', 89, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (845, '学步车', 89, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (846, '三轮车', 89, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (847, '自行车', 89, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (848, '电动车', 89, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (849, '扭扭车', 89, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (850, '滑板车', 89, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (851, '婴儿床垫', 89, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (852, '婴儿外出服', 90, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (853, '婴儿内衣', 90, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (854, '婴儿礼盒', 90, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (855, '婴儿鞋帽袜', 90, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (856, '安全防护', 90, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (857, '家居床品', 90, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (858, '睡袋/抱被', 90, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (859, '爬行垫', 90, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (860, '妈咪包/背婴带', 91, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (861, '产后塑身', 91, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (862, '文胸/内裤', 91, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (863, '防辐射服', 91, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (864, '孕妈装', 91, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (865, '孕期营养', 91, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (866, '孕妇护肤', 91, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (867, '待产护理', 91, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (868, '月子装', 91, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (869, '防溢乳垫', 91, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (870, '套装', 92, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (871, '上衣', 92, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (872, '裤子', 92, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (873, '裙子', 92, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (874, '内衣/家居服', 92, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (875, '羽绒服/棉服', 92, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (876, '亲子装', 92, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (877, '儿童配饰', 92, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (878, '礼服/演出服', 92, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (879, '运动鞋', 92, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (880, '皮鞋/帆布鞋', 92, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (881, '靴子', 92, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (882, '凉鞋', 92, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (883, '功能鞋', 92, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (884, '户外/运动服', 92, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (885, '提篮式', 93, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (886, '安全座椅', 93, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (887, '增高垫', 93, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (888, '钱包', 94, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (889, '手拿包', 94, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (890, '单肩包', 94, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (891, '双肩包', 94, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (892, '手提包', 94, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (893, '斜挎包', 94, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (894, '钥匙包', 94, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (895, '卡包/零钱包', 94, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (896, '男士钱包', 95, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (897, '男士手包', 95, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (898, '卡包名片夹', 95, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (899, '商务公文包', 95, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (900, '双肩包', 95, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (901, '单肩/斜挎包', 95, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (902, '钥匙包', 95, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (903, '电脑包', 96, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (904, '拉杆箱', 96, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (905, '旅行包', 96, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (906, '旅行配件', 96, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (907, '休闲运动包', 96, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (908, '拉杆包', 96, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (909, '登山包', 96, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (910, '妈咪包', 96, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (911, '书包', 96, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (912, '相机包', 96, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (913, '腰包/胸包', 96, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (914, '火机烟具', 97, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (915, '礼品文具', 97, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (916, '军刀军具', 97, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (917, '收藏品', 97, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (918, '工艺礼品', 97, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (919, '创意礼品', 97, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (920, '礼盒礼券', 97, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (921, '鲜花绿植', 97, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (922, '婚庆节庆', 97, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (923, '京东卡', 97, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (924, '美妆礼品', 97, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (925, '礼品定制', 97, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (926, '京东福卡', 97, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (927, '古董文玩', 97, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (928, '箱包', 98, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (929, '钱包', 98, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (930, '服饰', 98, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (931, '腰带', 98, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (932, '太阳镜/眼镜框', 98, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (933, '配件', 98, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (934, '鞋靴', 98, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (935, '饰品', 98, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (936, '名品腕表', 98, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (937, '高档化妆品', 98, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (938, '婚嫁首饰', 99, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (939, '婚纱摄影', 99, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (940, '婚纱礼服', 99, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (941, '婚庆服务', 99, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (942, '婚庆礼品/用品', 99, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (943, '婚宴', 99, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (944, '饼干蛋糕', 100, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (945, '糖果/巧克力', 100, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (946, '休闲零食', 100, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (947, '冲调饮品', 100, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (948, '粮油调味', 100, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (949, '牛奶', 100, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (950, '其他特产', 101, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (951, '新疆', 101, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (952, '北京', 101, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (953, '山西', 101, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (954, '内蒙古', 101, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (955, '福建', 101, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (956, '湖南', 101, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (957, '四川', 101, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (958, '云南', 101, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (959, '东北', 101, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (960, '休闲零食', 102, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (961, '坚果炒货', 102, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (962, '肉干肉脯', 102, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (963, '蜜饯果干', 102, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (964, '糖果/巧克力', 102, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (965, '饼干蛋糕', 102, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (966, '无糖食品', 102, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (967, '米面杂粮', 103, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (968, '食用油', 103, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (969, '调味品', 103, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (970, '南北干货', 103, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (971, '方便食品', 103, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (972, '有机食品', 103, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (973, '饮用水', 104, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (974, '饮料', 104, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (975, '牛奶乳品', 104, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (976, '咖啡/奶茶', 104, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (977, '冲饮谷物', 104, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (978, '蜂蜜/柚子茶', 104, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (979, '成人奶粉', 104, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (980, '月饼', 105, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (981, '大闸蟹', 105, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (982, '粽子', 105, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (983, '卡券', 105, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (984, '铁观音', 106, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (985, '普洱', 106, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (986, '龙井', 106, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (987, '绿茶', 106, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (988, '红茶', 106, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (989, '乌龙茶', 106, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (990, '花草茶', 106, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (991, '花果茶', 106, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (992, '养生茶', 106, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (993, '黑茶', 106, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (994, '白茶', 106, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (995, '其它茶', 106, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (996, '项链', 107, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (997, '手链/脚链', 107, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (998, '戒指', 107, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (999, '耳饰', 107, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1000, '毛衣链', 107, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1001, '发饰/发卡', 107, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1002, '胸针', 107, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1003, '饰品配件', 107, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1004, '婚庆饰品', 107, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1005, '黄金吊坠', 108, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1006, '黄金项链', 108, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1007, '黄金转运珠', 108, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1008, '黄金手镯/手链/脚链', 108, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1009, '黄金耳饰', 108, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1010, '黄金戒指', 108, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1011, 'K金吊坠', 109, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1012, 'K金项链', 109, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1013, 'K金手镯/手链/脚链', 109, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1014, 'K金戒指', 109, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1015, 'K金耳饰', 109, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1016, '投资金', 110, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1017, '投资银', 110, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1018, '投资收藏', 110, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1019, '银吊坠/项链', 111, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1020, '银手镯/手链/脚链', 111, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1021, '银戒指', 111, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1022, '银耳饰', 111, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1023, '足银手镯', 111, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1024, '宝宝银饰', 111, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1025, '裸钻', 112, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1026, '钻戒', 112, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1027, '钻石项链/吊坠', 112, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1028, '钻石耳饰', 112, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1029, '钻石手镯/手链', 112, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1030, '项链/吊坠', 113, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1031, '手镯/手串', 113, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1032, '戒指', 113, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1033, '耳饰', 113, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1034, '挂件/摆件/把件', 113, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1035, '玉石孤品', 113, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1036, '项链/吊坠', 114, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1037, '耳饰', 114, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1038, '手镯/手链/脚链', 114, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1039, '戒指', 114, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1040, '头饰/胸针', 114, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1041, '摆件/挂件', 114, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1042, '琥珀/蜜蜡', 115, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1043, '碧玺', 115, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1044, '红宝石/蓝宝石', 115, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1045, '坦桑石', 115, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1046, '珊瑚', 115, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1047, '祖母绿', 115, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1048, '葡萄石', 115, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1049, '其他天然宝石', 115, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1050, '项链/吊坠', 115, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1051, '耳饰', 115, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1052, '手镯/手链', 115, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1053, '戒指', 115, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1054, '铂金项链/吊坠', 116, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1055, '铂金手镯/手链/脚链', 116, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1056, '铂金戒指', 116, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1057, '铂金耳饰', 116, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1058, '小叶紫檀', 117, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1059, '黄花梨', 117, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1060, '沉香木', 117, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1061, '金丝楠', 117, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1062, '菩提', 117, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1063, '其他', 117, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1064, '橄榄核/核桃', 117, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1065, '檀香', 117, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1066, '珍珠项链', 118, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1067, '珍珠吊坠', 118, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1068, '珍珠耳饰', 118, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1069, '珍珠手链', 118, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1070, '珍珠戒指', 118, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1071, '珍珠胸针', 118, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1072, '机油', 119, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1073, '正时皮带', 119, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1074, '添加剂', 119, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1075, '汽车喇叭', 119, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1076, '防冻液', 119, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1077, '汽车玻璃', 119, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1078, '滤清器', 119, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1079, '火花塞', 119, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1080, '减震器', 119, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1081, '柴机油/辅助油', 119, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1082, '雨刷', 119, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1083, '车灯', 119, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1084, '后视镜', 119, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1085, '轮胎', 119, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1086, '轮毂', 119, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1087, '刹车片/盘', 119, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1088, '维修配件', 119, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1089, '蓄电池', 119, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1090, '底盘装甲/护板', 119, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1091, '贴膜', 119, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1092, '汽修工具', 119, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1093, '改装配件', 119, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1094, '导航仪', 120, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1095, '安全预警仪', 120, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1096, '行车记录仪', 120, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1097, '倒车雷达', 120, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1098, '蓝牙设备', 120, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1099, '车载影音', 120, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1100, '净化器', 120, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1101, '电源', 120, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1102, '智能驾驶', 120, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1103, '车载电台', 120, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1104, '车载电器配件', 120, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1105, '吸尘器', 120, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1106, '智能车机', 120, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1107, '冰箱', 120, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1108, '汽车音响', 120, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1109, '车载生活电器', 120, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1110, '车蜡', 121, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1111, '补漆笔', 121, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1112, '玻璃水', 121, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1113, '清洁剂', 121, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1114, '洗车工具', 121, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1115, '镀晶镀膜', 121, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1116, '打蜡机', 121, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1117, '洗车配件', 121, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1118, '洗车机', 121, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1119, '洗车水枪', 121, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1120, '毛巾掸子', 121, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1121, '脚垫', 122, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1122, '座垫', 122, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1123, '座套', 122, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1124, '后备箱垫', 122, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1125, '头枕腰靠', 122, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1126, '方向盘套', 122, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1127, '香水', 122, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1128, '空气净化', 122, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1129, '挂件摆件', 122, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1130, '功能小件', 122, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1131, '车身装饰件', 122, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1132, '车衣', 122, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1133, '安全座椅', 123, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1134, '胎压监测', 123, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1135, '防盗设备', 123, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1136, '应急救援', 123, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1137, '保温箱', 123, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1138, '地锁', 123, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1139, '摩托车', 123, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1140, '充气泵', 123, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1141, '储物箱', 123, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1142, '自驾野营', 123, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1143, '摩托车装备', 123, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1144, '清洗美容', 124, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1145, '功能升级', 124, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1146, '保养维修', 124, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1147, '油卡充值', 124, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1148, '车险', 124, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1149, '加油卡', 124, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1150, 'ETC', 124, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1151, '驾驶培训', 124, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1152, '赛事服装', 125, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1153, '赛事用品', 125, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1154, '制动系统', 125, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1155, '悬挂系统', 125, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1156, '进气系统', 125, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1157, '排气系统', 125, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1158, '电子管理', 125, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1159, '车身强化', 125, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1160, '赛事座椅', 125, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1161, '跑步鞋', 126, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1162, '休闲鞋', 126, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1163, '篮球鞋', 126, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1164, '板鞋', 126, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1165, '帆布鞋', 126, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1166, '足球鞋', 126, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1167, '乒羽网鞋', 126, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1168, '专项运动鞋', 126, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1169, '训练鞋', 126, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1170, '拖鞋', 126, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1171, '运动包', 126, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1172, '羽绒服', 127, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1173, '棉服', 127, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1174, '运动裤', 127, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1175, '夹克/风衣', 127, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1176, '卫衣/套头衫', 127, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1177, 'T恤', 127, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1178, '套装', 127, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1179, '乒羽网服', 127, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1180, '健身服', 127, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1181, '运动背心', 127, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1182, '毛衫/线衫', 127, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1183, '运动配饰', 127, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1184, '折叠车', 128, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1185, '山地车/公路车', 128, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1186, '电动车', 128, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1187, '其他整车', 128, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1188, '骑行服', 128, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1189, '骑行装备', 128, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1190, '平衡车', 128, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1191, '鱼竿鱼线', 129, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1192, '浮漂鱼饵', 129, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1193, '钓鱼桌椅', 129, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1194, '钓鱼配件', 129, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1195, '钓箱鱼包', 129, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1196, '其它', 129, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1197, '泳镜', 130, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1198, '泳帽', 130, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1199, '游泳包防水包', 130, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1200, '女士泳衣', 130, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1201, '男士泳衣', 130, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1202, '比基尼', 130, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1203, '其它', 130, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1204, '冲锋衣裤', 131, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1205, '速干衣裤', 131, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1206, '滑雪服', 131, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1207, '羽绒服/棉服', 131, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1208, '休闲衣裤', 131, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1209, '抓绒衣裤', 131, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1210, '软壳衣裤', 131, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1211, 'T恤', 131, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1212, '户外风衣', 131, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1213, '功能内衣', 131, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1214, '军迷服饰', 131, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1215, '登山鞋', 131, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1216, '雪地靴', 131, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1217, '徒步鞋', 131, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1218, '越野跑鞋', 131, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1219, '休闲鞋', 131, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1220, '工装鞋', 131, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1221, '溯溪鞋', 131, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1222, '沙滩/凉拖', 131, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1223, '户外袜', 131, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1224, '帐篷/垫子', 132, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1225, '睡袋/吊床', 132, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1226, '登山攀岩', 132, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1227, '户外配饰', 132, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1228, '背包', 132, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1229, '户外照明', 132, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1230, '户外仪表', 132, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1231, '户外工具', 132, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1232, '望远镜', 132, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1233, '旅游用品', 132, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1234, '便携桌椅床', 132, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1235, '野餐烧烤', 132, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1236, '军迷用品', 132, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1237, '救援装备', 132, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1238, '滑雪装备', 132, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1239, '极限户外', 132, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1240, '冲浪潜水', 132, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1241, '综合训练器', 133, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1242, '其他大型器械', 133, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1243, '哑铃', 133, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1244, '仰卧板/收腹机', 133, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1245, '其他中小型器材', 133, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1246, '瑜伽舞蹈', 133, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1247, '甩脂机', 133, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1248, '踏步机', 133, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1249, '武术搏击', 133, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1250, '健身车/动感单车', 133, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1251, '跑步机', 133, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1252, '运动护具', 133, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1253, '羽毛球', 134, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1254, '乒乓球', 134, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1255, '篮球', 134, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1256, '足球', 134, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1257, '网球', 134, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1258, '排球', 134, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1259, '高尔夫', 134, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1260, '台球', 134, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1261, '棋牌麻将', 134, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1262, '轮滑滑板', 134, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1263, '其他', 134, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1264, '0-6个月', 135, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1265, '6-12个月', 135, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1266, '1-3岁', 135, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1267, '3-6岁', 135, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1268, '6-14岁', 135, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1269, '14岁以上', 135, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1270, '遥控车', 136, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1271, '遥控飞机', 136, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1272, '遥控船', 136, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1273, '机器人', 136, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1274, '轨道/助力', 136, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1275, '毛绒/布艺', 137, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1276, '靠垫/抱枕', 137, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1277, '芭比娃娃', 138, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1278, '卡通娃娃', 138, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1279, '智能娃娃', 138, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1280, '仿真模型', 139, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1281, '拼插模型', 139, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1282, '收藏爱好', 139, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1283, '炫舞毯', 140, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1284, '爬行垫/毯', 140, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1285, '户外玩具', 140, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1286, '戏水玩具', 140, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1287, '电影周边', 141, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1288, '卡通周边', 141, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1289, '网游周边', 141, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1290, '摇铃/床铃', 142, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1291, '健身架', 142, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1292, '早教启智', 142, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1293, '拖拉玩具', 142, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1294, '积木', 143, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1295, '拼图', 143, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1296, '磁力棒', 143, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1297, '立体拼插', 143, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1298, '手工彩泥', 144, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1299, '绘画工具', 144, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1300, '情景玩具', 144, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1301, '减压玩具', 145, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1302, '创意玩具', 145, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1303, '钢琴', 146, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1304, '电子琴/电钢琴', 146, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1305, '吉他/尤克里里', 146, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1306, '打击乐器', 146, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1307, '西洋管弦', 146, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1308, '民族管弦乐器', 146, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1309, '乐器配件', 146, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1310, '电脑音乐', 146, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1311, '工艺礼品乐器', 146, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1312, '口琴/口风琴/竖笛', 146, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1313, '手风琴', 146, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1314, '双色球', 147, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1315, '大乐透', 147, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1316, '福彩3D', 147, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1317, '排列三', 147, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1318, '排列五', 147, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1319, '七星彩', 147, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1320, '七乐彩', 147, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1321, '竞彩足球', 147, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1322, '竞彩篮球', 147, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1323, '新时时彩', 147, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1324, '国内机票', 148, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1325, '国内酒店', 149, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1326, '酒店团购', 149, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1327, '度假', 150, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1328, '景点', 150, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1329, '租车', 150, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1330, '火车票', 150, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1331, '旅游团购', 150, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1332, '手机充值', 151, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1333, '游戏点卡', 152, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1334, 'QQ充值', 152, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1335, '电影票', 153, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1336, '演唱会', 153, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1337, '话剧歌剧', 153, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1338, '音乐会', 153, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1339, '体育赛事', 153, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1340, '舞蹈芭蕾', 153, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1341, '戏曲综艺', 153, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1342, '东北', 154, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1343, '华北', 154, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1344, '西北', 154, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1345, '华中', 154, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1346, '华东', 154, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1347, '华南', 154, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1348, '西南', 154, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1349, '苹果', 155, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1350, '橙子', 155, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1351, '奇异果/猕猴桃', 155, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1352, '车厘子/樱桃', 155, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1353, '芒果', 155, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1354, '蓝莓', 155, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1355, '火龙果', 155, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1356, '葡萄/提子', 155, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1357, '柚子', 155, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1358, '香蕉', 155, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1359, '牛油果', 155, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1360, '梨', 155, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1361, '菠萝/凤梨', 155, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1362, '桔/橘', 155, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1363, '柠檬', 155, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1364, '草莓', 155, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1365, '桃/李/杏', 155, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1366, '更多水果', 155, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1367, '水果礼盒/券', 155, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1368, '牛肉', 156, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1369, '羊肉', 156, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1370, '猪肉', 156, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1371, '内脏类', 156, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1372, '鱼类', 157, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1373, '虾类', 157, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1374, '蟹类', 157, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1375, '贝类', 157, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1376, '海参', 157, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1377, '海产干货', 157, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1378, '其他水产', 157, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1379, '海产礼盒', 157, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1380, '鸡肉', 158, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1381, '鸭肉', 158, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1382, '蛋类', 158, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1383, '其他禽类', 158, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1384, '水饺/馄饨', 159, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1385, '汤圆/元宵', 159, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1386, '面点', 159, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1387, '火锅丸串', 159, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1388, '速冻半成品', 159, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1389, '奶酪黄油', 159, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1390, '熟食', 160, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1391, '腊肠/腊肉', 160, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1392, '火腿', 160, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1393, '糕点', 160, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1394, '礼品卡券', 160, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1395, '冷藏果蔬汁', 161, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1396, '冰激凌', 161, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1397, '其他', 161, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1398, '叶菜类', 162, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1399, '茄果瓜类', 162, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1400, '根茎类', 162, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1401, '鲜菌菇', 162, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1402, '葱姜蒜椒', 162, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1403, '半加工蔬菜', 162, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1404, '微型车', 163, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1405, '小型车', 163, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1406, '紧凑型车', 163, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1407, '中型车', 163, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1408, '中大型车', 163, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1409, '豪华车', 163, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1410, 'MPV', 163, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1411, 'SUV', 163, 3, 1, 1, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1412, '跑车', 163, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1413, '微型车（二手）', 164, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1414, '小型车（二手）', 164, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1415, '紧凑型车（二手）', 164, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1416, '中型车（二手）', 164, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1417, '中大型车（二手）', 164, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1418, '豪华车（二手）', 164, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1419, 'MPV（二手）', 164, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1420, 'SUV（二手）', 164, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1421, '跑车（二手）', 164, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1422, '皮卡（二手）', 164, 3, 1, 0, NULL, NULL);
INSERT INTO `pms_category` (`category_id`, `category_name`, `parent_id`, `category_level`, `show_status`, `sort`, `create_time`, `update_time`) VALUES (1423, '面包车（二手）', 164, 3, 1, 0, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for pms_category_brand_relation
-- ----------------------------
DROP TABLE IF EXISTS `pms_category_brand_relation`;
CREATE TABLE `pms_category_brand_relation` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `brand_id` bigint DEFAULT NULL COMMENT '品牌id',
  `category_id` bigint DEFAULT NULL COMMENT '分类id',
  `brand_name` varchar(255) DEFAULT NULL COMMENT '品牌名称',
  `category_name` varchar(255) DEFAULT NULL COMMENT '分类名称',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_brand_id` (`brand_id`),
  KEY `idx_category_id` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='品牌分类关联';

-- ----------------------------
-- Records of pms_category_brand_relation
-- ----------------------------
BEGIN;
INSERT INTO `pms_category_brand_relation` (`id`, `brand_id`, `category_id`, `brand_name`, `category_name`, `create_time`) VALUES (1, 1, 225, '华为', '手机', NULL);
INSERT INTO `pms_category_brand_relation` (`id`, `brand_id`, `category_id`, `brand_name`, `category_name`, `create_time`) VALUES (2, 5, 225, '小米', '手机', NULL);
INSERT INTO `pms_category_brand_relation` (`id`, `brand_id`, `category_id`, `brand_name`, `category_name`, `create_time`) VALUES (3, 6, 225, '苹果', '手机', NULL);
COMMIT;

-- ----------------------------
-- Table structure for pms_comment_replay
-- ----------------------------
DROP TABLE IF EXISTS `pms_comment_replay`;
CREATE TABLE `pms_comment_replay` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `comment_id` bigint DEFAULT NULL COMMENT '评论id',
  `reply_id` bigint DEFAULT NULL COMMENT '回复id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品评价回复关系';

-- ----------------------------
-- Records of pms_comment_replay
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for pms_product_attr_value
-- ----------------------------
DROP TABLE IF EXISTS `pms_product_attr_value`;
CREATE TABLE `pms_product_attr_value` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `spu_id` bigint DEFAULT NULL COMMENT '商品id',
  `attr_id` bigint DEFAULT NULL COMMENT '属性id',
  `attr_name` varchar(200) DEFAULT NULL COMMENT '属性名',
  `attr_value` varchar(200) DEFAULT NULL COMMENT '属性值',
  `attr_sort` int DEFAULT NULL COMMENT '顺序',
  `quick_show` tinyint(1) DEFAULT NULL COMMENT '快速展示【是否展示在介绍上；0-否 1-是】',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='spu属性值';

-- ----------------------------
-- Records of pms_product_attr_value
-- ----------------------------
BEGIN;
INSERT INTO `pms_product_attr_value` (`id`, `spu_id`, `attr_id`, `attr_name`, `attr_value`, `attr_sort`, `quick_show`) VALUES (52, 17, 7, '产品名称', '苹果12', NULL, 1);
INSERT INTO `pms_product_attr_value` (`id`, `spu_id`, `attr_id`, `attr_name`, `attr_value`, `attr_sort`, `quick_show`) VALUES (53, 17, 8, '上市年份', '2020年', NULL, 1);
INSERT INTO `pms_product_attr_value` (`id`, `spu_id`, `attr_id`, `attr_name`, `attr_value`, `attr_sort`, `quick_show`) VALUES (54, 17, 9, '品牌', '苹果', NULL, 1);
INSERT INTO `pms_product_attr_value` (`id`, `spu_id`, `attr_id`, `attr_name`, `attr_value`, `attr_sort`, `quick_show`) VALUES (55, 17, 10, '机身宽度（mm）', '80mm', NULL, 1);
INSERT INTO `pms_product_attr_value` (`id`, `spu_id`, `attr_id`, `attr_name`, `attr_value`, `attr_sort`, `quick_show`) VALUES (56, 17, 11, '机身长度（mm）', '163mm', NULL, 0);
INSERT INTO `pms_product_attr_value` (`id`, `spu_id`, `attr_id`, `attr_name`, `attr_value`, `attr_sort`, `quick_show`) VALUES (57, 17, 14, '机身厚度（mm）', '19mm', NULL, 0);
INSERT INTO `pms_product_attr_value` (`id`, `spu_id`, `attr_id`, `attr_name`, `attr_value`, `attr_sort`, `quick_show`) VALUES (58, 17, 15, '芯片规格', 'A13', NULL, 1);
INSERT INTO `pms_product_attr_value` (`id`, `spu_id`, `attr_id`, `attr_name`, `attr_value`, `attr_sort`, `quick_show`) VALUES (59, 17, 16, '主屏幕尺寸', '其他尺寸', NULL, 0);
INSERT INTO `pms_product_attr_value` (`id`, `spu_id`, `attr_id`, `attr_name`, `attr_value`, `attr_sort`, `quick_show`) VALUES (60, 17, 17, '屏幕材质类型', 'OLED', NULL, 0);
INSERT INTO `pms_product_attr_value` (`id`, `spu_id`, `attr_id`, `attr_name`, `attr_value`, `attr_sort`, `quick_show`) VALUES (61, 18, 7, '产品名称', '华为Mate40', NULL, 1);
INSERT INTO `pms_product_attr_value` (`id`, `spu_id`, `attr_id`, `attr_name`, `attr_value`, `attr_sort`, `quick_show`) VALUES (62, 18, 8, '上市年份', '2020年', NULL, 1);
INSERT INTO `pms_product_attr_value` (`id`, `spu_id`, `attr_id`, `attr_name`, `attr_value`, `attr_sort`, `quick_show`) VALUES (63, 18, 9, '品牌', '华为', NULL, 1);
INSERT INTO `pms_product_attr_value` (`id`, `spu_id`, `attr_id`, `attr_name`, `attr_value`, `attr_sort`, `quick_show`) VALUES (64, 18, 10, '机身宽度（mm）', '75mm', NULL, 1);
INSERT INTO `pms_product_attr_value` (`id`, `spu_id`, `attr_id`, `attr_name`, `attr_value`, `attr_sort`, `quick_show`) VALUES (65, 18, 11, '机身长度（mm）', '165mm', NULL, 0);
INSERT INTO `pms_product_attr_value` (`id`, `spu_id`, `attr_id`, `attr_name`, `attr_value`, `attr_sort`, `quick_show`) VALUES (66, 18, 14, '机身厚度（mm）', '19mm', NULL, 0);
INSERT INTO `pms_product_attr_value` (`id`, `spu_id`, `attr_id`, `attr_name`, `attr_value`, `attr_sort`, `quick_show`) VALUES (67, 18, 15, '芯片规格', '麒麟960', NULL, 1);
INSERT INTO `pms_product_attr_value` (`id`, `spu_id`, `attr_id`, `attr_name`, `attr_value`, `attr_sort`, `quick_show`) VALUES (68, 18, 16, '主屏幕尺寸', '其他尺寸', NULL, 0);
INSERT INTO `pms_product_attr_value` (`id`, `spu_id`, `attr_id`, `attr_name`, `attr_value`, `attr_sort`, `quick_show`) VALUES (69, 18, 17, '屏幕材质类型', 'OLED', NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for pms_sku_images
-- ----------------------------
DROP TABLE IF EXISTS `pms_sku_images`;
CREATE TABLE `pms_sku_images` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `sku_id` bigint DEFAULT NULL COMMENT 'sku_id',
  `img_url` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `img_sort` int DEFAULT NULL COMMENT '排序',
  `default_img` int DEFAULT NULL COMMENT '默认图[0 - 不是默认图，1 - 是默认图]',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='sku图片';

-- ----------------------------
-- Records of pms_sku_images
-- ----------------------------
BEGIN;
INSERT INTO `pms_sku_images` (`id`, `sku_id`, `img_url`, `img_sort`, `default_img`) VALUES (38, 41, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/340fe5e7-788a-4c17-889c-795c470eaf9f_2d0dd638abf7dcc2.jpg', NULL, 0);
INSERT INTO `pms_sku_images` (`id`, `sku_id`, `img_url`, `img_sort`, `default_img`) VALUES (39, 41, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/8db090e8-5ff5-4357-8aff-7ebb55635ae7_24bc162f493ec940.jpg', NULL, 1);
INSERT INTO `pms_sku_images` (`id`, `sku_id`, `img_url`, `img_sort`, `default_img`) VALUES (40, 41, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/0151439e-3cfc-479f-8da0-84b19042b462_149503bc9026d922.jpg', NULL, 0);
INSERT INTO `pms_sku_images` (`id`, `sku_id`, `img_url`, `img_sort`, `default_img`) VALUES (41, 42, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/340fe5e7-788a-4c17-889c-795c470eaf9f_2d0dd638abf7dcc2.jpg', NULL, 0);
INSERT INTO `pms_sku_images` (`id`, `sku_id`, `img_url`, `img_sort`, `default_img`) VALUES (42, 42, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/8db090e8-5ff5-4357-8aff-7ebb55635ae7_24bc162f493ec940.jpg', NULL, 1);
INSERT INTO `pms_sku_images` (`id`, `sku_id`, `img_url`, `img_sort`, `default_img`) VALUES (43, 42, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/0151439e-3cfc-479f-8da0-84b19042b462_149503bc9026d922.jpg', NULL, 0);
INSERT INTO `pms_sku_images` (`id`, `sku_id`, `img_url`, `img_sort`, `default_img`) VALUES (44, 43, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/0467dacf-7cae-4e23-896b-50c5d861abb7_a0d21aabf860fbf0.jpg', NULL, 0);
INSERT INTO `pms_sku_images` (`id`, `sku_id`, `img_url`, `img_sort`, `default_img`) VALUES (45, 43, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/c2c33938-9c91-4059-8f51-5a82f535238e_4297977df96064cf.jpg', NULL, 1);
INSERT INTO `pms_sku_images` (`id`, `sku_id`, `img_url`, `img_sort`, `default_img`) VALUES (46, 43, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/4bc68ed0-3137-4a55-80fb-c41646b19511_ab5c2d05f879a2f5.jpg', NULL, 0);
INSERT INTO `pms_sku_images` (`id`, `sku_id`, `img_url`, `img_sort`, `default_img`) VALUES (47, 44, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/0467dacf-7cae-4e23-896b-50c5d861abb7_a0d21aabf860fbf0.jpg', NULL, 0);
INSERT INTO `pms_sku_images` (`id`, `sku_id`, `img_url`, `img_sort`, `default_img`) VALUES (48, 44, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/c2c33938-9c91-4059-8f51-5a82f535238e_4297977df96064cf.jpg', NULL, 1);
INSERT INTO `pms_sku_images` (`id`, `sku_id`, `img_url`, `img_sort`, `default_img`) VALUES (49, 44, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/4bc68ed0-3137-4a55-80fb-c41646b19511_ab5c2d05f879a2f5.jpg', NULL, 0);
INSERT INTO `pms_sku_images` (`id`, `sku_id`, `img_url`, `img_sort`, `default_img`) VALUES (50, 45, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/409d29d2-4833-4563-8c60-0312b01a9eeb_b88c0485ca234e0d.jpg', NULL, 1);
INSERT INTO `pms_sku_images` (`id`, `sku_id`, `img_url`, `img_sort`, `default_img`) VALUES (51, 45, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/f4d406f6-813f-4040-8bdf-f031dfc9eedc_3f23d4c4d2e4059b.jpg', NULL, 0);
INSERT INTO `pms_sku_images` (`id`, `sku_id`, `img_url`, `img_sort`, `default_img`) VALUES (52, 45, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/3ef65c15-c6c2-455c-8010-305b441b183f_18d985bbf3b262c2 (1).jpg', NULL, 0);
INSERT INTO `pms_sku_images` (`id`, `sku_id`, `img_url`, `img_sort`, `default_img`) VALUES (53, 45, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/7650638d-9ac6-4283-82d0-b2463066c2ee_c9724e0156ac1b06 (1).jpg', NULL, 0);
INSERT INTO `pms_sku_images` (`id`, `sku_id`, `img_url`, `img_sort`, `default_img`) VALUES (54, 46, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/409d29d2-4833-4563-8c60-0312b01a9eeb_b88c0485ca234e0d.jpg', NULL, 1);
INSERT INTO `pms_sku_images` (`id`, `sku_id`, `img_url`, `img_sort`, `default_img`) VALUES (55, 46, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/f4d406f6-813f-4040-8bdf-f031dfc9eedc_3f23d4c4d2e4059b.jpg', NULL, 0);
INSERT INTO `pms_sku_images` (`id`, `sku_id`, `img_url`, `img_sort`, `default_img`) VALUES (56, 46, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/3ef65c15-c6c2-455c-8010-305b441b183f_18d985bbf3b262c2 (1).jpg', NULL, 0);
INSERT INTO `pms_sku_images` (`id`, `sku_id`, `img_url`, `img_sort`, `default_img`) VALUES (57, 46, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/7650638d-9ac6-4283-82d0-b2463066c2ee_c9724e0156ac1b06 (1).jpg', NULL, 0);
INSERT INTO `pms_sku_images` (`id`, `sku_id`, `img_url`, `img_sort`, `default_img`) VALUES (58, 47, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/b7862c2f-79e9-4759-8542-1c7acdf06a59_3c778f0a77663104.jpg', NULL, 1);
INSERT INTO `pms_sku_images` (`id`, `sku_id`, `img_url`, `img_sort`, `default_img`) VALUES (59, 47, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/03254867-2bd8-4361-895f-8d5dab893d83_2ea0d42461080748.jpg', NULL, 0);
INSERT INTO `pms_sku_images` (`id`, `sku_id`, `img_url`, `img_sort`, `default_img`) VALUES (60, 47, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/968a4a88-6a2c-40e9-8a3c-b0b82f0bf31b_38b474cd090ec30d.jpg', NULL, 0);
INSERT INTO `pms_sku_images` (`id`, `sku_id`, `img_url`, `img_sort`, `default_img`) VALUES (61, 47, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/6cd24900-8f18-433f-8ad7-68358217ef70_10397bb3059ab4dd.jpg', NULL, 0);
INSERT INTO `pms_sku_images` (`id`, `sku_id`, `img_url`, `img_sort`, `default_img`) VALUES (62, 48, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/b7862c2f-79e9-4759-8542-1c7acdf06a59_3c778f0a77663104.jpg', NULL, 1);
INSERT INTO `pms_sku_images` (`id`, `sku_id`, `img_url`, `img_sort`, `default_img`) VALUES (63, 48, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/03254867-2bd8-4361-895f-8d5dab893d83_2ea0d42461080748.jpg', NULL, 0);
INSERT INTO `pms_sku_images` (`id`, `sku_id`, `img_url`, `img_sort`, `default_img`) VALUES (64, 48, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/968a4a88-6a2c-40e9-8a3c-b0b82f0bf31b_38b474cd090ec30d.jpg', NULL, 0);
INSERT INTO `pms_sku_images` (`id`, `sku_id`, `img_url`, `img_sort`, `default_img`) VALUES (65, 48, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/6cd24900-8f18-433f-8ad7-68358217ef70_10397bb3059ab4dd.jpg', NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for pms_sku_info
-- ----------------------------
DROP TABLE IF EXISTS `pms_sku_info`;
CREATE TABLE `pms_sku_info` (
  `sku_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'skuId',
  `sku_name` varchar(255) DEFAULT NULL COMMENT 'sku名称',
  `sku_title` varchar(255) DEFAULT NULL COMMENT '标题',
  `sku_subtitle` varchar(511) DEFAULT NULL COMMENT '副标题',
  `sku_desc` varchar(2000) DEFAULT NULL COMMENT 'sku介绍描述',
  `sku_default_img` varchar(255) DEFAULT NULL COMMENT '默认图片',
  `price` decimal(18,4) DEFAULT NULL COMMENT '价格',
  `sale_count` bigint DEFAULT NULL COMMENT '销量',
  `spu_id` bigint DEFAULT NULL COMMENT 'spuId',
  `category_id` bigint DEFAULT NULL COMMENT '所属分类id',
  `brand_id` bigint DEFAULT NULL COMMENT '所属品牌id',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`sku_id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='sku信息';

-- ----------------------------
-- Records of pms_sku_info
-- ----------------------------
BEGIN;
INSERT INTO `pms_sku_info` (`sku_id`, `sku_name`, `sku_title`, `sku_subtitle`, `sku_desc`, `sku_default_img`, `price`, `sale_count`, `spu_id`, `category_id`, `brand_id`, `create_time`, `update_time`) VALUES (41, 'Apple iPhone 12 (A2404) 128GB 绿色 支持移动联通电信5G 双卡双待手机 黑色 4G', 'Apple iPhone 12 (A2404) 128GB 绿色 支持移动联通电信5G 双卡双待手机 黑色 4G', 'Apple iPhone 12 (A2404) 128GB 绿色 支持移动联通电信5G 双卡双待手机 黑色 4G', NULL, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/8db090e8-5ff5-4357-8aff-7ebb55635ae7_24bc162f493ec940.jpg', 4799.0000, 0, 17, 225, 6, NULL, NULL);
INSERT INTO `pms_sku_info` (`sku_id`, `sku_name`, `sku_title`, `sku_subtitle`, `sku_desc`, `sku_default_img`, `price`, `sale_count`, `spu_id`, `category_id`, `brand_id`, `create_time`, `update_time`) VALUES (42, 'Apple iPhone 12 (A2404) 128GB 绿色 支持移动联通电信5G 双卡双待手机 黑色 6G', 'Apple iPhone 12 (A2404) 128GB 绿色 支持移动联通电信5G 双卡双待手机 黑色 6G', 'Apple iPhone 12 (A2404) 128GB 绿色 支持移动联通电信5G 双卡双待手机 黑色 6G', NULL, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/8db090e8-5ff5-4357-8aff-7ebb55635ae7_24bc162f493ec940.jpg', 5299.0000, 0, 17, 225, 6, NULL, NULL);
INSERT INTO `pms_sku_info` (`sku_id`, `sku_name`, `sku_title`, `sku_subtitle`, `sku_desc`, `sku_default_img`, `price`, `sale_count`, `spu_id`, `category_id`, `brand_id`, `create_time`, `update_time`) VALUES (43, 'Apple iPhone 12 (A2404) 128GB 绿色 支持移动联通电信5G 双卡双待手机 白色 4G', 'Apple iPhone 12 (A2404) 128GB 绿色 支持移动联通电信5G 双卡双待手机 白色 4G', 'Apple iPhone 12 (A2404) 128GB 绿色 支持移动联通电信5G 双卡双待手机 白色 4G', NULL, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/c2c33938-9c91-4059-8f51-5a82f535238e_4297977df96064cf.jpg', 4799.0000, 0, 17, 225, 6, NULL, NULL);
INSERT INTO `pms_sku_info` (`sku_id`, `sku_name`, `sku_title`, `sku_subtitle`, `sku_desc`, `sku_default_img`, `price`, `sale_count`, `spu_id`, `category_id`, `brand_id`, `create_time`, `update_time`) VALUES (44, 'Apple iPhone 12 (A2404) 128GB 绿色 支持移动联通电信5G 双卡双待手机 白色 6G', 'Apple iPhone 12 (A2404) 128GB 绿色 支持移动联通电信5G 双卡双待手机 白色 6G', 'Apple iPhone 12 (A2404) 128GB 绿色 支持移动联通电信5G 双卡双待手机 白色 6G', NULL, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/c2c33938-9c91-4059-8f51-5a82f535238e_4297977df96064cf.jpg', 5299.0000, 0, 17, 225, 6, NULL, NULL);
INSERT INTO `pms_sku_info` (`sku_id`, `sku_name`, `sku_title`, `sku_subtitle`, `sku_desc`, `sku_default_img`, `price`, `sale_count`, `spu_id`, `category_id`, `brand_id`, `create_time`, `update_time`) VALUES (45, '【搭载HarmonyOS 2】华为 HUAWEI Mate 40 Pro 4G 全网通 麒麟9000旗舰芯片 8GB+256GB秘银色手机【秒杀版】 黑色 6G', '【搭载HarmonyOS 2】华为 HUAWEI Mate 40 Pro 4G 全网通 麒麟9000旗舰芯片 8GB+256GB秘银色手机【秒杀版】 黑色 6G', '【搭载HarmonyOS 2】华为 HUAWEI Mate 40 Pro 4G 全网通 麒麟9000旗舰芯片 8GB+256GB秘银色手机【秒杀版】 黑色 6G', NULL, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/409d29d2-4833-4563-8c60-0312b01a9eeb_b88c0485ca234e0d.jpg', 6299.0000, 0, 18, 225, 1, NULL, NULL);
INSERT INTO `pms_sku_info` (`sku_id`, `sku_name`, `sku_title`, `sku_subtitle`, `sku_desc`, `sku_default_img`, `price`, `sale_count`, `spu_id`, `category_id`, `brand_id`, `create_time`, `update_time`) VALUES (46, '【搭载HarmonyOS 2】华为 HUAWEI Mate 40 Pro 4G 全网通 麒麟9000旗舰芯片 8GB+256GB秘银色手机【秒杀版】 黑色 8G', '【搭载HarmonyOS 2】华为 HUAWEI Mate 40 Pro 4G 全网通 麒麟9000旗舰芯片 8GB+256GB秘银色手机【秒杀版】 黑色 8G', '【搭载HarmonyOS 2】华为 HUAWEI Mate 40 Pro 4G 全网通 麒麟9000旗舰芯片 8GB+256GB秘银色手机【秒杀版】 黑色 8G', NULL, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/409d29d2-4833-4563-8c60-0312b01a9eeb_b88c0485ca234e0d.jpg', 6799.0000, 0, 18, 225, 1, NULL, NULL);
INSERT INTO `pms_sku_info` (`sku_id`, `sku_name`, `sku_title`, `sku_subtitle`, `sku_desc`, `sku_default_img`, `price`, `sale_count`, `spu_id`, `category_id`, `brand_id`, `create_time`, `update_time`) VALUES (47, '【搭载HarmonyOS 2】华为 HUAWEI Mate 40 Pro 4G 全网通 麒麟9000旗舰芯片 8GB+256GB秘银色手机【秒杀版】 白色 6G', '【搭载HarmonyOS 2】华为 HUAWEI Mate 40 Pro 4G 全网通 麒麟9000旗舰芯片 8GB+256GB秘银色手机【秒杀版】 白色 6G', '【搭载HarmonyOS 2】华为 HUAWEI Mate 40 Pro 4G 全网通 麒麟9000旗舰芯片 8GB+256GB秘银色手机【秒杀版】 白色 6G', NULL, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/b7862c2f-79e9-4759-8542-1c7acdf06a59_3c778f0a77663104.jpg', 6299.0000, 0, 18, 225, 1, NULL, NULL);
INSERT INTO `pms_sku_info` (`sku_id`, `sku_name`, `sku_title`, `sku_subtitle`, `sku_desc`, `sku_default_img`, `price`, `sale_count`, `spu_id`, `category_id`, `brand_id`, `create_time`, `update_time`) VALUES (48, '【搭载HarmonyOS 2】华为 HUAWEI Mate 40 Pro 4G 全网通 麒麟9000旗舰芯片 8GB+256GB秘银色手机【秒杀版】 白色 8G', '【搭载HarmonyOS 2】华为 HUAWEI Mate 40 Pro 4G 全网通 麒麟9000旗舰芯片 8GB+256GB秘银色手机【秒杀版】 白色 8G', '【搭载HarmonyOS 2】华为 HUAWEI Mate 40 Pro 4G 全网通 麒麟9000旗舰芯片 8GB+256GB秘银色手机【秒杀版】 白色 8G', NULL, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/b7862c2f-79e9-4759-8542-1c7acdf06a59_3c778f0a77663104.jpg', 6799.0000, 0, 18, 225, 1, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for pms_sku_sale_attr_value
-- ----------------------------
DROP TABLE IF EXISTS `pms_sku_sale_attr_value`;
CREATE TABLE `pms_sku_sale_attr_value` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `sku_id` bigint DEFAULT NULL COMMENT 'sku_id',
  `attr_id` bigint DEFAULT NULL COMMENT 'attr_id',
  `attr_name` varchar(200) DEFAULT NULL COMMENT '销售属性名',
  `attr_value` varchar(200) DEFAULT NULL COMMENT '销售属性值',
  `attr_sort` int DEFAULT NULL COMMENT '顺序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='sku销售属性&值';

-- ----------------------------
-- Records of pms_sku_sale_attr_value
-- ----------------------------
BEGIN;
INSERT INTO `pms_sku_sale_attr_value` (`id`, `sku_id`, `attr_id`, `attr_name`, `attr_value`, `attr_sort`) VALUES (1, 41, 12, '颜色', '黑色', NULL);
INSERT INTO `pms_sku_sale_attr_value` (`id`, `sku_id`, `attr_id`, `attr_name`, `attr_value`, `attr_sort`) VALUES (2, 41, 13, '内存', '4G', NULL);
INSERT INTO `pms_sku_sale_attr_value` (`id`, `sku_id`, `attr_id`, `attr_name`, `attr_value`, `attr_sort`) VALUES (3, 42, 12, '颜色', '黑色', NULL);
INSERT INTO `pms_sku_sale_attr_value` (`id`, `sku_id`, `attr_id`, `attr_name`, `attr_value`, `attr_sort`) VALUES (4, 42, 13, '内存', '6G', NULL);
INSERT INTO `pms_sku_sale_attr_value` (`id`, `sku_id`, `attr_id`, `attr_name`, `attr_value`, `attr_sort`) VALUES (5, 43, 12, '颜色', '白色', NULL);
INSERT INTO `pms_sku_sale_attr_value` (`id`, `sku_id`, `attr_id`, `attr_name`, `attr_value`, `attr_sort`) VALUES (6, 43, 13, '内存', '4G', NULL);
INSERT INTO `pms_sku_sale_attr_value` (`id`, `sku_id`, `attr_id`, `attr_name`, `attr_value`, `attr_sort`) VALUES (7, 44, 12, '颜色', '白色', NULL);
INSERT INTO `pms_sku_sale_attr_value` (`id`, `sku_id`, `attr_id`, `attr_name`, `attr_value`, `attr_sort`) VALUES (8, 44, 13, '内存', '6G', NULL);
INSERT INTO `pms_sku_sale_attr_value` (`id`, `sku_id`, `attr_id`, `attr_name`, `attr_value`, `attr_sort`) VALUES (9, 45, 12, '颜色', '黑色', NULL);
INSERT INTO `pms_sku_sale_attr_value` (`id`, `sku_id`, `attr_id`, `attr_name`, `attr_value`, `attr_sort`) VALUES (10, 45, 13, '内存', '6G', NULL);
INSERT INTO `pms_sku_sale_attr_value` (`id`, `sku_id`, `attr_id`, `attr_name`, `attr_value`, `attr_sort`) VALUES (11, 46, 12, '颜色', '黑色', NULL);
INSERT INTO `pms_sku_sale_attr_value` (`id`, `sku_id`, `attr_id`, `attr_name`, `attr_value`, `attr_sort`) VALUES (12, 46, 13, '内存', '8G', NULL);
INSERT INTO `pms_sku_sale_attr_value` (`id`, `sku_id`, `attr_id`, `attr_name`, `attr_value`, `attr_sort`) VALUES (13, 47, 12, '颜色', '白色', NULL);
INSERT INTO `pms_sku_sale_attr_value` (`id`, `sku_id`, `attr_id`, `attr_name`, `attr_value`, `attr_sort`) VALUES (14, 47, 13, '内存', '6G', NULL);
INSERT INTO `pms_sku_sale_attr_value` (`id`, `sku_id`, `attr_id`, `attr_name`, `attr_value`, `attr_sort`) VALUES (15, 48, 12, '颜色', '白色', NULL);
INSERT INTO `pms_sku_sale_attr_value` (`id`, `sku_id`, `attr_id`, `attr_name`, `attr_value`, `attr_sort`) VALUES (16, 48, 13, '内存', '8G', NULL);
COMMIT;

-- ----------------------------
-- Table structure for pms_spu_comment
-- ----------------------------
DROP TABLE IF EXISTS `pms_spu_comment`;
CREATE TABLE `pms_spu_comment` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `sku_id` bigint DEFAULT NULL COMMENT 'sku_id',
  `spu_id` bigint DEFAULT NULL COMMENT 'spu_id',
  `spu_name` varchar(255) DEFAULT NULL COMMENT '商品名字',
  `member_nick_name` varchar(255) DEFAULT NULL COMMENT '会员昵称',
  `star` tinyint(1) DEFAULT NULL COMMENT '星级',
  `member_ip` varchar(64) DEFAULT NULL COMMENT '会员ip',
  `show_status` tinyint(1) DEFAULT NULL COMMENT '显示状态[0-不显示，1-显示]',
  `spu_attributes` varchar(255) DEFAULT NULL COMMENT '购买时属性组合',
  `likes_count` int DEFAULT NULL COMMENT '点赞数',
  `reply_count` int DEFAULT NULL COMMENT '回复数',
  `resources` varchar(1000) DEFAULT NULL COMMENT '评论图片/视频[json数据；[{type:文件类型,url:资源路径}]]',
  `content` text COMMENT '内容',
  `member_icon` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `comment_type` tinyint DEFAULT NULL COMMENT '评论类型[0 - 对商品的直接评论，1 - 对评论的回复]',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品评价';

-- ----------------------------
-- Records of pms_spu_comment
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for pms_spu_images
-- ----------------------------
DROP TABLE IF EXISTS `pms_spu_images`;
CREATE TABLE `pms_spu_images` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `spu_id` bigint DEFAULT NULL COMMENT 'spu_id',
  `img_name` varchar(200) DEFAULT NULL COMMENT '图片名',
  `img_url` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `img_sort` int DEFAULT NULL COMMENT '顺序',
  `default_img` tinyint(1) DEFAULT NULL COMMENT '是否默认图',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='spu图片';

-- ----------------------------
-- Records of pms_spu_images
-- ----------------------------
BEGIN;
INSERT INTO `pms_spu_images` (`id`, `spu_id`, `img_name`, `img_url`, `img_sort`, `default_img`) VALUES (55, 17, NULL, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/340fe5e7-788a-4c17-889c-795c470eaf9f_2d0dd638abf7dcc2.jpg', NULL, 1);
INSERT INTO `pms_spu_images` (`id`, `spu_id`, `img_name`, `img_url`, `img_sort`, `default_img`) VALUES (56, 17, NULL, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/8db090e8-5ff5-4357-8aff-7ebb55635ae7_24bc162f493ec940.jpg', NULL, NULL);
INSERT INTO `pms_spu_images` (`id`, `spu_id`, `img_name`, `img_url`, `img_sort`, `default_img`) VALUES (57, 17, NULL, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/0151439e-3cfc-479f-8da0-84b19042b462_149503bc9026d922.jpg', NULL, NULL);
INSERT INTO `pms_spu_images` (`id`, `spu_id`, `img_name`, `img_url`, `img_sort`, `default_img`) VALUES (58, 17, NULL, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/0467dacf-7cae-4e23-896b-50c5d861abb7_a0d21aabf860fbf0.jpg', NULL, NULL);
INSERT INTO `pms_spu_images` (`id`, `spu_id`, `img_name`, `img_url`, `img_sort`, `default_img`) VALUES (59, 17, NULL, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/c2c33938-9c91-4059-8f51-5a82f535238e_4297977df96064cf.jpg', NULL, NULL);
INSERT INTO `pms_spu_images` (`id`, `spu_id`, `img_name`, `img_url`, `img_sort`, `default_img`) VALUES (60, 17, NULL, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/4bc68ed0-3137-4a55-80fb-c41646b19511_ab5c2d05f879a2f5.jpg', NULL, NULL);
INSERT INTO `pms_spu_images` (`id`, `spu_id`, `img_name`, `img_url`, `img_sort`, `default_img`) VALUES (61, 18, NULL, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/b7862c2f-79e9-4759-8542-1c7acdf06a59_3c778f0a77663104.jpg', NULL, NULL);
INSERT INTO `pms_spu_images` (`id`, `spu_id`, `img_name`, `img_url`, `img_sort`, `default_img`) VALUES (62, 18, NULL, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/03254867-2bd8-4361-895f-8d5dab893d83_2ea0d42461080748.jpg', NULL, NULL);
INSERT INTO `pms_spu_images` (`id`, `spu_id`, `img_name`, `img_url`, `img_sort`, `default_img`) VALUES (63, 18, NULL, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/968a4a88-6a2c-40e9-8a3c-b0b82f0bf31b_38b474cd090ec30d.jpg', NULL, NULL);
INSERT INTO `pms_spu_images` (`id`, `spu_id`, `img_name`, `img_url`, `img_sort`, `default_img`) VALUES (64, 18, NULL, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/6cd24900-8f18-433f-8ad7-68358217ef70_10397bb3059ab4dd.jpg', NULL, NULL);
INSERT INTO `pms_spu_images` (`id`, `spu_id`, `img_name`, `img_url`, `img_sort`, `default_img`) VALUES (65, 18, NULL, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/409d29d2-4833-4563-8c60-0312b01a9eeb_b88c0485ca234e0d.jpg', NULL, 1);
INSERT INTO `pms_spu_images` (`id`, `spu_id`, `img_name`, `img_url`, `img_sort`, `default_img`) VALUES (66, 18, NULL, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/f4d406f6-813f-4040-8bdf-f031dfc9eedc_3f23d4c4d2e4059b.jpg', NULL, NULL);
INSERT INTO `pms_spu_images` (`id`, `spu_id`, `img_name`, `img_url`, `img_sort`, `default_img`) VALUES (67, 18, NULL, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/3ef65c15-c6c2-455c-8010-305b441b183f_18d985bbf3b262c2 (1).jpg', NULL, NULL);
INSERT INTO `pms_spu_images` (`id`, `spu_id`, `img_name`, `img_url`, `img_sort`, `default_img`) VALUES (68, 18, NULL, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/7650638d-9ac6-4283-82d0-b2463066c2ee_c9724e0156ac1b06 (1).jpg', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for pms_spu_info
-- ----------------------------
DROP TABLE IF EXISTS `pms_spu_info`;
CREATE TABLE `pms_spu_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `spu_name` varchar(200) DEFAULT NULL COMMENT '商品名称',
  `publish_status` tinyint(1) DEFAULT NULL COMMENT '上架状态[0 - 新建，1 - 已上架，2 - 已下架]',
  `category_id` bigint DEFAULT NULL COMMENT '所属分类id',
  `brand_id` bigint DEFAULT NULL COMMENT '所属品牌id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='spu信息';

-- ----------------------------
-- Records of pms_spu_info
-- ----------------------------
BEGIN;
INSERT INTO `pms_spu_info` (`id`, `spu_name`, `publish_status`, `category_id`, `brand_id`, `create_time`, `update_time`) VALUES (17, 'Apple iPhone 12 (A2404) 128GB 绿色 支持移动联通电信5G 双卡双待手机', 1, 225, 6, '2021-09-14 10:09:18', '2021-09-14 02:27:50');
INSERT INTO `pms_spu_info` (`id`, `spu_name`, `publish_status`, `category_id`, `brand_id`, `create_time`, `update_time`) VALUES (18, '【搭载HarmonyOS 2】华为 HUAWEI Mate 40 Pro 4G 全网通 麒麟9000旗舰芯片 8GB+256GB秘银色手机【秒杀版】', 1, 225, 1, '2021-09-14 10:27:32', '2021-09-14 02:27:54');
COMMIT;

-- ----------------------------
-- Table structure for pms_spu_info_desc
-- ----------------------------
DROP TABLE IF EXISTS `pms_spu_info_desc`;
CREATE TABLE `pms_spu_info_desc` (
  `spu_id` bigint NOT NULL COMMENT '商品id',
  `description` longtext COMMENT '商品介绍',
  PRIMARY KEY (`spu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='spu信息介绍';

-- ----------------------------
-- Records of pms_spu_info_desc
-- ----------------------------
BEGIN;
INSERT INTO `pms_spu_info_desc` (`spu_id`, `description`) VALUES (17, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/1bc5f67a-72c1-4c39-8684-668e7bc7ceaf_8d8a7aaed36e0041.jpg,https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/8f14d85b-8789-43e4-81ad-96592d10ae51_8dd1a6a0582f7603.jpg');
INSERT INTO `pms_spu_info_desc` (`spu_id`, `description`) VALUES (18, 'https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/4fa479e0-cc30-4499-84c3-c4b7b827f202_7d0a85a02ad7a261.jpg,https://laughingather.oss-cn-qingdao.aliyuncs.com/gulimall/2021-09-14/0793a2f9-d507-4ad1-8760-a41b2348dcf8_feac6e42af4eca15.jpg');
COMMIT;

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `branch_id` bigint NOT NULL,
  `xid` varchar(100) NOT NULL,
  `context` varchar(128) NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int NOT NULL,
  `log_created` datetime NOT NULL,
  `log_modified` datetime NOT NULL,
  `ext` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of undo_log
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
