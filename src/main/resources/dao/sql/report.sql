/*
 Navicat Premium Data Transfer

 Source Server         : report
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : report

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 01/07/2019 17:57:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cell_info
-- ----------------------------
DROP TABLE IF EXISTS `cell_info`;
CREATE TABLE `cell_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '单元格id',
  `row_id` bigint(20) NOT NULL COMMENT '所属行id',
  `col_id` bigint(20) NOT NULL COMMENT '所属列id',
  `cell_type` int(10) NOT NULL COMMENT '单元格类型',
  `cell_value` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单元格值',
  `table_id` bigint(20) NOT NULL COMMENT '所属表id',
  `row_index` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '行索引',
  `col_index` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '列索引',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `row_id`(`row_id`) USING BTREE,
  INDEX `col_id`(`col_id`) USING BTREE,
  INDEX `table_id`(`table_id`) USING BTREE,
  INDEX `row_Index`(`row_index`) USING BTREE,
  INDEX `col_Index`(`col_index`) USING BTREE,
  CONSTRAINT `col_Index` FOREIGN KEY (`col_index`) REFERENCES `reference_info` (`reference_index`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `col_id` FOREIGN KEY (`col_id`) REFERENCES `reference_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `row_Index` FOREIGN KEY (`row_index`) REFERENCES `reference_info` (`reference_index`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `row_id` FOREIGN KEY (`row_id`) REFERENCES `reference_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `table_id` FOREIGN KEY (`table_id`) REFERENCES `table_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cell_info
-- ----------------------------
INSERT INTO `cell_info` VALUES (108, 124, 126, 5, 'aaaa', 31, 'row:0', 'col:0');
INSERT INTO `cell_info` VALUES (109, 125, 126, 5, 'bbbb', 31, 'row:1', 'col:0');
INSERT INTO `cell_info` VALUES (110, 124, 127, 5, 'cccc', 31, 'row:0', 'col:1');
INSERT INTO `cell_info` VALUES (111, 125, 127, 5, 'dddd', 31, 'row:1', 'col:1');
INSERT INTO `cell_info` VALUES (112, 133, 126, 5, 'eeee', 31, 'row:33', 'col:0');
INSERT INTO `cell_info` VALUES (113, 133, 127, 5, 'ffff', 31, 'row:33', 'col:1');
INSERT INTO `cell_info` VALUES (114, 133, 134, 5, 'GGGG', 31, 'row:33', 'col:34');
INSERT INTO `cell_info` VALUES (115, 124, 134, 5, 'HHHH', 31, 'row:0', 'col:34');
INSERT INTO `cell_info` VALUES (116, 125, 134, 5, 'IIII', 31, 'row:1', 'col:34');

-- ----------------------------
-- Table structure for reference_info
-- ----------------------------
DROP TABLE IF EXISTS `reference_info`;
CREATE TABLE `reference_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'reference主键',
  `reference_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'reference名称',
  `reference_alias` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'reference别名',
  `reference_type` tinyint(1) NOT NULL DEFAULT 0 COMMENT 'reference类型，0-col,1-row',
  `reference_length` bigint(20) NOT NULL DEFAULT 0 COMMENT 'reference大小',
  `reference_data_type` int(10) NOT NULL DEFAULT 0 COMMENT 'reference数据类型，0-short,1-int,2-long,3-double,4-float,5-String,6-boolean,7-byte,8-others',
  `table_id` bigint(20) NOT NULL COMMENT '关联的表id',
  `reference_index` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '索引',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id`(`table_id`) USING BTREE,
  INDEX `reference_index`(`reference_index`) USING BTREE,
  CONSTRAINT `tableId` FOREIGN KEY (`table_id`) REFERENCES `table_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 134 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of reference_info
-- ----------------------------
INSERT INTO `reference_info` VALUES (124, 'name', '姓名', 0, 20, 5, 31, 'row:0');
INSERT INTO `reference_info` VALUES (125, 'name', '姓名', 0, 20, 5, 31, 'row:1');
INSERT INTO `reference_info` VALUES (126, '0', 'A0', 1, 20, 5, 31, 'col:0');
INSERT INTO `reference_info` VALUES (127, '1', 'A1', 1, 20, 5, 31, 'col:1');
INSERT INTO `reference_info` VALUES (133, 'name1', '性别', 0, 21, 5, 31, 'row:33');
INSERT INTO `reference_info` VALUES (134, '33', 'A34', 1, 21, 5, 31, 'col:34');

-- ----------------------------
-- Table structure for table_info
-- ----------------------------
DROP TABLE IF EXISTS `table_info`;
CREATE TABLE `table_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '表id',
  `table_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '表名',
  `table_alias` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '表的别名',
  `table_size` bigint(20) NULL DEFAULT NULL COMMENT '表的实时大小，单位：byte',
  `table_row_count` bigint(20) NULL DEFAULT NULL COMMENT '表的行数',
  `table_owner` bigint(20) NULL DEFAULT NULL COMMENT '表所属用户',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `userId`(`table_owner`) USING BTREE,
  CONSTRAINT `userId` FOREIGN KEY (`table_owner`) REFERENCES `user_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of table_info
-- ----------------------------
INSERT INTO `table_info` VALUES (31, 'TEST_TABLE', '测试表', 344, 2, 1212);

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `sex` tinyint(1) NULL DEFAULT 0 COMMENT '性别(0-男,1-女)',
  `age` int(11) NULL DEFAULT 0 COMMENT '年龄',
  `ip` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0.0.0.0' COMMENT '登录ip',
  `adress` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地址信息',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  `city_id` int(11) NULL DEFAULT -1 COMMENT '用户所在城市',
  `ident` bigint(20) NULL DEFAULT NULL,
  `version` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_info_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1212 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (1212, '2', '3', 0, 0, '127.0.0.1', '北京', '1', -1, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
