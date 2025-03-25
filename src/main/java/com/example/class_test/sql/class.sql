/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 80018
Source Host           : localhost:3306
Source Database       : xueke_springboot

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2025-03-25 11:05:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `class_name` varchar(255) COLLATE utf8mb4_zh_0900_as_cs NOT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `teacher_id` (`teacher_id`),
  CONSTRAINT `class_ibfk_1` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_zh_0900_as_cs;

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class` VALUES ('1', '22级软件工程1班', '1');
INSERT INTO `class` VALUES ('2', '22级软件工程2班', '2');
INSERT INTO `class` VALUES ('3', '22级计算机科学1班', '3');
INSERT INTO `class` VALUES ('4', '22级计算机科学2班', '4');
INSERT INTO `class` VALUES ('5', '22级人工智能1班', '7');
INSERT INTO `class` VALUES ('6', '22级数据科学1班', '8');
