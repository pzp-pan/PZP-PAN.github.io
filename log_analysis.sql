/*
Navicat MySQL Data Transfer

Source Server         : learn
Source Server Version : 80035
Source Host           : localhost:3306
Source Database       : log_analysis

Target Server Type    : MYSQL
Target Server Version : 80035
File Encoding         : 65001

Date: 2026-01-21 20:18:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for clean_logs
-- ----------------------------
DROP TABLE IF EXISTS `clean_logs`;
CREATE TABLE `clean_logs` (
  `id` int NOT NULL AUTO_INCREMENT,
  `log_time` datetime NOT NULL,
  `user_id` varchar(50) NOT NULL,
  `action` varchar(50) NOT NULL,
  `status` varchar(20) NOT NULL,
  `response_time` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_user` (`user_id`),
  KEY `idx_time` (`log_time`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of clean_logs
-- ----------------------------
INSERT INTO `clean_logs` VALUES ('1', '2026-01-18 02:00:01', 'user123', 'login', 'success', '205', '2026-01-21 19:55:46');
INSERT INTO `clean_logs` VALUES ('2', '2026-01-18 02:00:02', 'user456', 'view_page', 'success', '120', '2026-01-21 19:55:46');
INSERT INTO `clean_logs` VALUES ('3', '2026-01-18 02:00:04', 'user789', 'pay', 'success', '5000', '2026-01-21 19:55:46');
INSERT INTO `clean_logs` VALUES ('4', '2026-01-18 02:00:05', 'user123', 'logout', 'success', '80', '2026-01-21 19:55:46');
INSERT INTO `clean_logs` VALUES ('5', '2026-01-18 02:00:06', 'user999', 'view_page', 'timeout', '-1', '2026-01-21 19:55:46');
INSERT INTO `clean_logs` VALUES ('6', '2026-01-18 02:00:07', 'user456', 'login', 'success', '180', '2026-01-21 19:55:46');
INSERT INTO `clean_logs` VALUES ('7', '2026-01-18 02:00:08', 'user789', 'view_page', 'success', '90', '2026-01-21 19:55:46');
INSERT INTO `clean_logs` VALUES ('8', '2026-01-18 02:00:09', 'user123', 'pay', 'error', '300', '2026-01-21 19:55:46');
INSERT INTO `clean_logs` VALUES ('9', '2026-01-18 02:00:10', 'user456', 'logout', 'success', '70', '2026-01-21 19:55:46');
INSERT INTO `clean_logs` VALUES ('10', '2026-01-18 02:00:01', 'user123', 'login', 'success', '205', '2026-01-21 20:07:00');
INSERT INTO `clean_logs` VALUES ('11', '2026-01-18 02:00:02', 'user456', 'view_page', 'success', '120', '2026-01-21 20:07:00');
INSERT INTO `clean_logs` VALUES ('12', '2026-01-18 02:00:04', 'user789', 'pay', 'success', '5000', '2026-01-21 20:07:00');
INSERT INTO `clean_logs` VALUES ('13', '2026-01-18 02:00:05', 'user123', 'logout', 'success', '80', '2026-01-21 20:07:00');
INSERT INTO `clean_logs` VALUES ('14', '2026-01-18 02:00:06', 'user999', 'view_page', 'timeout', '-1', '2026-01-21 20:07:00');
INSERT INTO `clean_logs` VALUES ('15', '2026-01-18 02:00:07', 'user456', 'login', 'success', '180', '2026-01-21 20:07:00');
INSERT INTO `clean_logs` VALUES ('16', '2026-01-18 02:00:08', 'user789', 'view_page', 'success', '90', '2026-01-21 20:07:00');
INSERT INTO `clean_logs` VALUES ('17', '2026-01-18 02:00:09', 'user123', 'pay', 'error', '300', '2026-01-21 20:07:00');
INSERT INTO `clean_logs` VALUES ('18', '2026-01-18 02:00:10', 'user456', 'logout', 'success', '70', '2026-01-21 20:07:00');
INSERT INTO `clean_logs` VALUES ('19', '2026-01-18 02:00:01', 'user123', 'login', 'success', '205', '2026-01-21 20:07:28');
INSERT INTO `clean_logs` VALUES ('20', '2026-01-18 02:00:02', 'user456', 'view_page', 'success', '120', '2026-01-21 20:07:29');
INSERT INTO `clean_logs` VALUES ('21', '2026-01-18 02:00:04', 'user789', 'pay', 'success', '5000', '2026-01-21 20:07:29');
INSERT INTO `clean_logs` VALUES ('22', '2026-01-18 02:00:05', 'user123', 'logout', 'success', '80', '2026-01-21 20:07:29');
INSERT INTO `clean_logs` VALUES ('23', '2026-01-18 02:00:06', 'user999', 'view_page', 'timeout', '-1', '2026-01-21 20:07:29');
INSERT INTO `clean_logs` VALUES ('24', '2026-01-18 02:00:07', 'user456', 'login', 'success', '180', '2026-01-21 20:07:29');
INSERT INTO `clean_logs` VALUES ('25', '2026-01-18 02:00:08', 'user789', 'view_page', 'success', '90', '2026-01-21 20:07:29');
INSERT INTO `clean_logs` VALUES ('26', '2026-01-18 02:00:09', 'user123', 'pay', 'error', '300', '2026-01-21 20:07:29');
INSERT INTO `clean_logs` VALUES ('27', '2026-01-18 02:00:10', 'user456', 'logout', 'success', '70', '2026-01-21 20:07:29');

-- ----------------------------
-- Table structure for data_quality
-- ----------------------------
DROP TABLE IF EXISTS `data_quality`;
CREATE TABLE `data_quality` (
  `id` int NOT NULL AUTO_INCREMENT,
  `check_date` date NOT NULL,
  `total_records` int DEFAULT NULL,
  `valid_records` int DEFAULT NULL,
  `missing_rate` decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_date` (`check_date`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of data_quality
-- ----------------------------
INSERT INTO `data_quality` VALUES ('1', '2026-01-20', '27', '24', '11.11');
