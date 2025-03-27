/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 80018
Source Host           : localhost:3306
Source Database       : xueke_springboot

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2025-03-27 21:48:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `teacher_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_zh_0900_as_cs;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('1', '张教授');
INSERT INTO `teacher` VALUES ('2', '李博士');
INSERT INTO `teacher` VALUES ('3', '王讲师');
INSERT INTO `teacher` VALUES ('4', '刘教授');
INSERT INTO `teacher` VALUES ('5', '陈副教授');
INSERT INTO `teacher` VALUES ('6', '林教授');
INSERT INTO `teacher` VALUES ('7', '黄讲师');
INSERT INTO `teacher` VALUES ('8', '吴博士');
