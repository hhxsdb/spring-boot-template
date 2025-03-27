/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 80018
Source Host           : localhost:3306
Source Database       : xueke_springboot

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2025-03-27 21:48:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for student_course
-- ----------------------------
DROP TABLE IF EXISTS `student_course`;
CREATE TABLE `student_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_student_course` (`student_id`,`course_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `student_course_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE CASCADE,
  CONSTRAINT `student_course_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_zh_0900_as_cs;

-- ----------------------------
-- Records of student_course
-- ----------------------------
INSERT INTO `student_course` VALUES ('1', '1', '1');
INSERT INTO `student_course` VALUES ('2', '1', '2');
INSERT INTO `student_course` VALUES ('3', '2', '1');
INSERT INTO `student_course` VALUES ('4', '2', '3');
INSERT INTO `student_course` VALUES ('5', '3', '1');
INSERT INTO `student_course` VALUES ('6', '3', '4');
