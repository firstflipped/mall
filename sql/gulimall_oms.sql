/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 5.7.34 : Database - gulimall_oms
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE = ''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS */`gulimall_oms` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `gulimall_oms`;

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

/*Table structure for table `oms_order` */

DROP TABLE IF EXISTS `oms_order`;

CREATE TABLE `oms_order`
(
    `id`                      bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `member_id`               bigint(20)     DEFAULT NULL COMMENT '会员id',
    `member_username`         varchar(200)   DEFAULT NULL COMMENT '会员名',
    `order_sn`                varchar(55)    DEFAULT NULL COMMENT '订单号',
    `coupon_id`               bigint(20)     DEFAULT NULL COMMENT '使用的优惠券',
    `total_amount`            decimal(18, 4) DEFAULT NULL COMMENT '订单总额',
    `pay_amount`              decimal(18, 4) DEFAULT NULL COMMENT '应付总额',
    `freight_amount`          decimal(18, 4) DEFAULT NULL COMMENT '运费金额',
    `promotion_amount`        decimal(18, 4) DEFAULT NULL COMMENT '促销优化金额（促销价、满减、阶梯价）',
    `integration_amount`      decimal(18, 4) DEFAULT NULL COMMENT '积分抵扣金额',
    `coupon_amount`           decimal(18, 4) DEFAULT NULL COMMENT '优惠券抵扣金额',
    `discount_amount`         decimal(18, 4) DEFAULT NULL COMMENT '后台调整订单使用的折扣金额',
    `pay_type`                tinyint(1)     DEFAULT NULL COMMENT '支付方式【1->支付宝；2->微信；3->银联； 4->货到付款；】',
    `source_type`             tinyint(1)     DEFAULT NULL COMMENT '订单来源[0->PC订单；1->app订单]',
    `status`                  tinyint(1)     DEFAULT NULL COMMENT '订单状态【0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单】',
    `delivery_sn`             varchar(64)    DEFAULT NULL COMMENT '物流单号',
    `delivery_company`        varchar(64)    DEFAULT NULL COMMENT '物流公司(配送方式)',
    `auto_confirm_day`        int(11)        DEFAULT NULL COMMENT '自动确认时间（天）',
    `integration`             int(11)        DEFAULT NULL COMMENT '可以获得的积分',
    `growth`                  int(11)        DEFAULT NULL COMMENT '可以获得的成长值',
    `bill_type`               tinyint(1)     DEFAULT NULL COMMENT '发票类型[0->不开发票；1->电子发票；2->纸质发票]',
    `bill_header`             varchar(255)   DEFAULT NULL COMMENT '发票抬头',
    `bill_content`            varchar(255)   DEFAULT NULL COMMENT '发票内容',
    `bill_receiver_phone`     varchar(32)    DEFAULT NULL COMMENT '收票人电话',
    `bill_receiver_email`     varchar(64)    DEFAULT NULL COMMENT '收票人邮箱',
    `receiver_name`           varchar(100)   DEFAULT NULL COMMENT '收货人姓名',
    `receiver_phone`          varchar(32)    DEFAULT NULL COMMENT '收货人电话',
    `receiver_post_code`      varchar(32)    DEFAULT NULL COMMENT '收货人邮编',
    `receiver_province`       varchar(32)    DEFAULT NULL COMMENT '省份/直辖市',
    `receiver_city`           varchar(32)    DEFAULT NULL COMMENT '城市',
    `receiver_region`         varchar(32)    DEFAULT NULL COMMENT '区',
    `receiver_detail_address` varchar(200)   DEFAULT NULL COMMENT '详细地址',
    `note`                    varchar(500)   DEFAULT NULL COMMENT '订单备注',
    `confirm_status`          tinyint(1)     DEFAULT NULL COMMENT '确认收货状态[0->未确认；1->已确认]',
    `delete_status`           tinyint(1)     DEFAULT NULL COMMENT '删除状态【0->未删除；1->已删除】',
    `use_integration`         int(11)        DEFAULT NULL COMMENT '下单时使用的积分',
    `payment_time`            datetime       DEFAULT NULL COMMENT '支付时间',
    `delivery_time`           datetime       DEFAULT NULL COMMENT '发货时间',
    `receive_time`            datetime       DEFAULT NULL COMMENT '确认收货时间',
    `comment_time`            datetime       DEFAULT NULL COMMENT '评价时间',
    `create_time`             datetime       DEFAULT NULL COMMENT '创建时间',
    `update_time`             datetime       DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uni_order_sn` (`order_sn`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 14
  DEFAULT CHARSET = utf8mb4 COMMENT ='订单';

/*Data for the table `oms_order` */

/*Table structure for table `oms_order_item` */

DROP TABLE IF EXISTS `oms_order_item`;

CREATE TABLE `oms_order_item`
(
    `id`                 bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `order_id`           bigint(20)              DEFAULT NULL COMMENT '订单id',
    `order_sn`           varchar(55)             DEFAULT NULL COMMENT '订单号',
    `spu_id`             bigint(20)              DEFAULT NULL COMMENT 'spu_id',
    `spu_name`           varchar(255)            DEFAULT NULL COMMENT 'spu_name',
    `spu_pic`            varchar(500)            DEFAULT NULL COMMENT 'spu_pic',
    `brand_id`           bigint(20)              DEFAULT NULL COMMENT '品牌分类id',
    `category_id`        bigint(20)              DEFAULT NULL COMMENT '商品分类id',
    `sku_id`             bigint(20)              DEFAULT NULL COMMENT '商品sku编号',
    `sku_name`           varchar(255)            DEFAULT NULL COMMENT '商品sku名字',
    `sku_pic`            varchar(500)            DEFAULT NULL COMMENT '商品sku图片',
    `sku_price`          decimal(18, 4)          DEFAULT NULL COMMENT '商品sku价格',
    `sku_quantity`       int(11)                 DEFAULT NULL COMMENT '商品购买的数量',
    `sku_attrs_values`   varchar(500)            DEFAULT NULL COMMENT '商品销售属性组合（JSON）',
    `promotion_amount`   decimal(18, 4)          DEFAULT NULL COMMENT '商品促销分解金额',
    `coupon_amount`      decimal(18, 4) unsigned DEFAULT NULL COMMENT '优惠券优惠分解金额',
    `integration_amount` decimal(18, 4)          DEFAULT NULL COMMENT '积分优惠分解金额',
    `real_amount`        decimal(18, 4)          DEFAULT NULL COMMENT '该商品经过优惠后的分解金额',
    `gift_integration`   int(11)                 DEFAULT NULL COMMENT '赠送积分',
    `gift_growth`        int(11)                 DEFAULT NULL COMMENT '赠送成长值',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 14
  DEFAULT CHARSET = utf8mb4 COMMENT ='订单项信息';

/*Data for the table `oms_order_item` */

/*Table structure for table `oms_order_operate_history` */

DROP TABLE IF EXISTS `oms_order_operate_history`;

CREATE TABLE `oms_order_operate_history`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `order_id`     bigint(20)   DEFAULT NULL COMMENT '订单id',
    `operate_man`  varchar(100) DEFAULT NULL COMMENT '操作人[用户；系统；后台管理员]',
    `order_status` tinyint(4)   DEFAULT NULL COMMENT '订单状态【0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单】',
    `note`         varchar(500) DEFAULT NULL COMMENT '备注',
    `create_time`  datetime     DEFAULT NULL COMMENT '操作时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='订单操作历史记录';

/*Data for the table `oms_order_operate_history` */

/*Table structure for table `oms_order_return_apply` */

DROP TABLE IF EXISTS `oms_order_return_apply`;

CREATE TABLE `oms_order_return_apply`
(
    `id`               bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `order_id`         bigint(20)     DEFAULT NULL COMMENT 'order_id',
    `order_sn`         char(32)       DEFAULT NULL COMMENT '订单编号',
    `sku_id`           bigint(20)     DEFAULT NULL COMMENT '退货商品id',
    `member_username`  varchar(64)    DEFAULT NULL COMMENT '会员用户名',
    `return_amount`    decimal(18, 4) DEFAULT NULL COMMENT '退款金额',
    `return_name`      varchar(100)   DEFAULT NULL COMMENT '退货人姓名',
    `return_phone`     varchar(20)    DEFAULT NULL COMMENT '退货人电话',
    `status`           tinyint(1)     DEFAULT NULL COMMENT '申请状态[0->待处理；1->退货中；2->已完成；3->已拒绝]',
    `handle_time`      datetime       DEFAULT NULL COMMENT '处理时间',
    `sku_img`          varchar(500)   DEFAULT NULL COMMENT '商品图片',
    `sku_name`         varchar(200)   DEFAULT NULL COMMENT '商品名称',
    `sku_brand`        varchar(200)   DEFAULT NULL COMMENT '商品品牌',
    `sku_attrs_values` varchar(500)   DEFAULT NULL COMMENT '商品销售属性(JSON)',
    `sku_count`        int(11)        DEFAULT NULL COMMENT '退货数量',
    `sku_price`        decimal(18, 4) DEFAULT NULL COMMENT '商品单价',
    `sku_real_price`   decimal(18, 4) DEFAULT NULL COMMENT '商品实际支付单价',
    `reason`           varchar(200)   DEFAULT NULL COMMENT '原因',
    `description`      varchar(500)   DEFAULT NULL COMMENT '描述',
    `desc_pics`        varchar(2000)  DEFAULT NULL COMMENT '凭证图片，以逗号隔开',
    `handle_note`      varchar(500)   DEFAULT NULL COMMENT '处理备注',
    `handle_man`       varchar(200)   DEFAULT NULL COMMENT '处理人员',
    `receive_man`      varchar(100)   DEFAULT NULL COMMENT '收货人',
    `receive_time`     datetime       DEFAULT NULL COMMENT '收货时间',
    `receive_note`     varchar(500)   DEFAULT NULL COMMENT '收货备注',
    `receive_phone`    varchar(20)    DEFAULT NULL COMMENT '收货电话',
    `company_address`  varchar(500)   DEFAULT NULL COMMENT '公司收货地址',
    `create_time`      datetime       DEFAULT NULL COMMENT '申请时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='订单退货申请';

/*Data for the table `oms_order_return_apply` */

/*Table structure for table `oms_order_return_reason` */

DROP TABLE IF EXISTS `oms_order_return_reason`;

CREATE TABLE `oms_order_return_reason`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name`        varchar(200) DEFAULT NULL COMMENT '退货原因名',
    `sort`        int(11)      DEFAULT NULL COMMENT '排序',
    `status`      tinyint(1)   DEFAULT NULL COMMENT '启用状态',
    `create_time` datetime     DEFAULT NULL COMMENT 'create_time',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='退货原因';

/*Data for the table `oms_order_return_reason` */

/*Table structure for table `oms_order_setting` */

DROP TABLE IF EXISTS `oms_order_setting`;

CREATE TABLE `oms_order_setting`
(
    `id`                    bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `flash_order_overtime`  int(11)    DEFAULT NULL COMMENT '秒杀订单超时关闭时间(分)',
    `normal_order_overtime` int(11)    DEFAULT NULL COMMENT '正常订单超时时间(分)',
    `confirm_overtime`      int(11)    DEFAULT NULL COMMENT '发货后自动确认收货时间（天）',
    `finish_overtime`       int(11)    DEFAULT NULL COMMENT '自动完成交易时间，不能申请退货（天）',
    `comment_overtime`      int(11)    DEFAULT NULL COMMENT '订单完成后自动好评时间（天）',
    `member_level`          tinyint(2) DEFAULT NULL COMMENT '会员等级【0-不限会员等级，全部通用；其他-对应的其他会员等级】',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='订单配置信息';

/*Data for the table `oms_order_setting` */

/*Table structure for table `oms_payment_info` */

DROP TABLE IF EXISTS `oms_payment_info`;

CREATE TABLE `oms_payment_info`
(
    `id`               bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `order_sn`         char(32)       DEFAULT NULL COMMENT '订单号（对外业务号）',
    `order_id`         bigint(20)     DEFAULT NULL COMMENT '订单id',
    `alipay_trade_no`  varchar(50)    DEFAULT NULL COMMENT '支付宝交易流水号',
    `total_amount`     decimal(18, 4) DEFAULT NULL COMMENT '支付总金额',
    `subject`          varchar(200)   DEFAULT NULL COMMENT '交易内容',
    `payment_status`   varchar(20)    DEFAULT NULL COMMENT '支付状态',
    `create_time`      datetime       DEFAULT NULL COMMENT '创建时间',
    `confirm_time`     datetime       DEFAULT NULL COMMENT '确认时间',
    `callback_content` varchar(4000)  DEFAULT NULL COMMENT '回调内容',
    `callback_time`    datetime       DEFAULT NULL COMMENT '回调时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='支付信息表';

/*Data for the table `oms_payment_info` */

/*Table structure for table `oms_refund_info` */

DROP TABLE IF EXISTS `oms_refund_info`;

CREATE TABLE `oms_refund_info`
(
    `id`              bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `order_return_id` bigint(20)     DEFAULT NULL COMMENT '退款的订单',
    `refund`          decimal(18, 4) DEFAULT NULL COMMENT '退款金额',
    `refund_sn`       varchar(64)    DEFAULT NULL COMMENT '退款交易流水号',
    `refund_status`   tinyint(1)     DEFAULT NULL COMMENT '退款状态',
    `refund_channel`  tinyint(4)     DEFAULT NULL COMMENT '退款渠道[1-支付宝，2-微信，3-银联，4-汇款]',
    `refund_content`  varchar(5000)  DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='退款信息';

/*Data for the table `oms_refund_info` */

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;
