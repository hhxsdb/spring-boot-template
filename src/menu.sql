/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 80018
Source Host           : localhost:3306
Source Database       : template

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2025-04-15 21:14:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL,
  `menuCode` varchar(8) DEFAULT NULL COMMENT '菜单编码',
  `menuName` varchar(16) DEFAULT NULL COMMENT '菜单名字',
  `menuLevel` varchar(2) DEFAULT NULL COMMENT '菜单级别',
  `menuParentCode` varchar(8) DEFAULT NULL COMMENT '菜单的父code',
  `menuClick` varchar(16) DEFAULT NULL COMMENT '点击触发的函数',
  `menuRight` varchar(8) DEFAULT NULL COMMENT '权限 0超级管理员，1表示管理员，2表示普通用户，可以用逗号组合使用',
  `menuComponent` varchar(200) DEFAULT NULL,
  `menuIcon` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '001', '管理员管理', '1', null, 'Admin', '0', 'admin/AdminManage.vue', 'el-icon-s-custom');
INSERT INTO `menu` VALUES ('2', '002', '用户管理', '1', null, 'User', '0,1', 'user/UserManage.vue', 'el-icon-user-solid');
