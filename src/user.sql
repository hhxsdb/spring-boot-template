/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 80018
Source Host           : localhost:3306
Source Database       : template

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2025-04-15 21:14:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '$2a$10$UUyz/MYW2p6XmmeYAxsai.K0CbfJaxsTjwdCjee1T51hnZAqDR0XW');
INSERT INTO `user` VALUES ('2', 'test', '$2a$10$zTMPhRDTgFnaIrvzPJ6BFeoAOaXj5PjXsgrn.5OzmhHZmzJiJnp5e');
INSERT INTO `user` VALUES ('3', '张三1', '$2a$10$S1F7SyZ4iz4EcnvHhupNz.ER4fCatrwxdL7Esms8yGM1X8cyixlwK');
INSERT INTO `user` VALUES ('6', 'hhxsdb', '$2a$10$3ijPuhkc9ZRYMam./jcwnO9hV9eFI5XU74uLyIB6ytnU70e.PysjW');
