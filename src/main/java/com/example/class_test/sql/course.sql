/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 80018
Source Host           : localhost:3306
Source Database       : xueke_springboot

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2025-03-25 11:05:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_name` varchar(255) COLLATE utf8mb4_zh_0900_as_cs NOT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `teacher_id` (`teacher_id`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_zh_0900_as_cs;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', 'Java程序设计', '1');
INSERT INTO `course` VALUES ('2', '数据库原理', '2');
INSERT INTO `course` VALUES ('3', '操作系统', '3');
INSERT INTO `course` VALUES ('4', '计算机网络', '4');
INSERT INTO `course` VALUES ('5', '软件工程', '5');
INSERT INTO `course` VALUES ('6', 'Python编程', '6');
INSERT INTO `course` VALUES ('7', '人工智能导论', '7');
INSERT INTO `course` VALUES ('8', '数据结构', '8');
INSERT INTO `course` VALUES ('9', '计算机组成原理', '1');
INSERT INTO `course` VALUES ('10', 'Web开发技术', '2');
