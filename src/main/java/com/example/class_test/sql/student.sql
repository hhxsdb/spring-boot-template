/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 80018
Source Host           : localhost:3306
Source Database       : xueke_springboot

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2025-03-27 21:48:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_name` varchar(255) COLLATE utf8mb4_zh_0900_as_cs NOT NULL,
  `class_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `class_id` (`class_id`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_zh_0900_as_cs;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', '周念泰', '1');
INSERT INTO `student` VALUES ('2', 'cx', '2');
INSERT INTO `student` VALUES ('3', 'cjr', '1');
