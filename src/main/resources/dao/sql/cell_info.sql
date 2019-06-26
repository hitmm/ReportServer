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

 Date: 20/06/2019 23:15:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cell_info
-- ----------------------------
DROP TABLE IF EXISTS `cell_info`;
CREATE TABLE `cell_info`  (
  `id` bigint(20) NOT NULL COMMENT '单元格id',
  `row_id` bigint(20) NOT NULL COMMENT '所属行id',
  `col_id` bigint(20) NOT NULL COMMENT '所属列id',
  `cell_type` int(10) NOT NULL COMMENT '单元格类型',
  `cell_value` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单元格值',
  `table_id` bigint(20) NOT NULL COMMENT '所属表id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `row_id`(`row_id`) USING BTREE,
  INDEX `col_id`(`col_id`) USING BTREE,
  INDEX `table_id`(`table_id`) USING BTREE,
  CONSTRAINT `col_id` FOREIGN KEY (`col_id`) REFERENCES `reference_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `row_id` FOREIGN KEY (`row_id`) REFERENCES `reference_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `table_id` FOREIGN KEY (`table_id`) REFERENCES `table_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
