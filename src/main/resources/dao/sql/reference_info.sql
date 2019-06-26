/*
 Navicat Premium Data Transfer

 Source Server         : report
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : report

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 20/06/2019 23:15:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for reference_info
-- ----------------------------
DROP TABLE IF EXISTS `reference_info`;
CREATE TABLE `reference_info`  (
  `id` bigint(20) NOT NULL COMMENT 'reference主键',
  `reference_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'reference名称',
  `reference_alias` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'reference别名',
  `reference_type` tinyint(1) NOT NULL DEFAULT 0 COMMENT 'reference类型，0-col,1-row',
  `reference_length` bigint(20) NOT NULL DEFAULT 0 COMMENT 'reference大小',
  `reference_data_type` int(10) NOT NULL COMMENT 'reference数据类型，0-short。。。。',
  `table_id` bigint(20) NOT NULL COMMENT '关联的表id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id`(`table_id`) USING BTREE,
  CONSTRAINT `id` FOREIGN KEY (`table_id`) REFERENCES `table_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
