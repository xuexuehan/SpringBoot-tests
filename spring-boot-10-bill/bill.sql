/*
 Navicat Premium Data Transfer

 Source Server         : hx_mysql
 Source Server Type    : MySQL
 Source Server Version : 50561
 Source Host           : localhost:3306
 Source Schema         : bill

 Target Server Type    : MySQL
 Target Server Version : 50561
 File Encoding         : 65001

 Date: 07/04/2019 10:07:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bill
-- ----------------------------
DROP TABLE IF EXISTS `bill`;
CREATE TABLE `bill`  (
  `bid` int(10) NOT NULL AUTO_INCREMENT,
  `bill_code` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bill_name` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bill_com` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bill_num` int(8) NULL DEFAULT NULL,
  `money` double(8, 2) NULL DEFAULT NULL,
  `pay` int(2) NULL DEFAULT NULL COMMENT '是否付款 0 未付款， 1已付款',
  `pid` int(10) NULL DEFAULT NULL COMMENT '供应商id',
  `create_date` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`bid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of bill
-- ----------------------------
INSERT INTO `bill` VALUES (1, 'B_001', 'ESC包年云服务', '年', 39, 400000.00, 1, 1, '2018-11-17 15:22:03');
INSERT INTO `bill` VALUES (2, '2', 'ESC包月云服务', 'b_002', 20, 26000.00, 0, 3, '2019-04-07 09:34:05');
INSERT INTO `bill` VALUES (3, '3', 'com域名', '3', 334, 15000.00, 1, 1, '2019-04-05 13:16:53');
INSERT INTO `bill` VALUES (4, '4', 'cn域名', 'b_004', 10, 7000.00, 0, 9, '2019-04-05 13:17:40');

-- ----------------------------
-- Table structure for provider
-- ----------------------------
DROP TABLE IF EXISTS `provider`;
CREATE TABLE `provider`  (
  `pid` int(10) NOT NULL AUTO_INCREMENT,
  `provider_code` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `providerName` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `people` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fax` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `describe` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of provider
-- ----------------------------
INSERT INTO `provider` VALUES (1, 'P_111', 'A货云服务供应商11', '小码', '1888888888', '广州白云区', '020-123456', '专业ESC服务商', '2018-11-17 12:21:54');
INSERT INTO `provider` VALUES (2, 'P_222', 'B货域名供应商...', '小域', '1888886666', '北京朝阳区', '020-112312123', '专业提供域名', '2019-04-07 09:34:05');
INSERT INTO `provider` VALUES (3, 'P_333', 'B货小程序企鹅', '小企鹅', '1888666666', '深圳南山区', '020-123123', '专业小程序提供', '2028-11-12 11:52:17');
INSERT INTO `provider` VALUES (4, 'P_444', '其他渠道供应商', '无名', '1888999999', '北京', '010-1000', '专业渠道来源', '2018-11-12 12:07:02');
INSERT INTO `provider` VALUES (9, 'P_111', 'Promissing云服务供应商', '小韩', '1888888888', '广州白云区', '020-123456', '专业ESC服务商', '2019-04-05 15:06:59');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `real_name` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` int(1) NULL DEFAULT NULL COMMENT '性别：1 女  2 男',
  `birthday` datetime NULL DEFAULT NULL,
  `user_type` int(1) NULL DEFAULT NULL COMMENT '1管理员  2经理  3普通用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'root', '系统管理员', '123', 1, '2003-11-12 00:00:00', 1);
INSERT INTO `users` VALUES (2, 'lisi', '李四', '123', 2, NULL, 2);
INSERT INTO `users` VALUES (3, 'wangwu', '王五', '123', 2, '1953-12-10 00:00:00', 3);
INSERT INTO `users` VALUES (4, 'zhangsan', '张三', '123', 1, '1973-11-12 00:00:00', 3);

SET FOREIGN_KEY_CHECKS = 1;
