/*
Navicat MySQL Data Transfer

Source Server         : unknown
Source Server Version : 80032
Source Host           : localhost:3306
Source Database       : demo1

Target Server Type    : MYSQL
Target Server Version : 80032
File Encoding         : 65001

Date: 2024-07-25 14:13:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `brand`
-- ----------------------------
DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand` (
  `id` int NOT NULL AUTO_INCREMENT,
  `brand_name` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `company_name` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `ordered` int DEFAULT NULL,
  `description` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of brand
-- ----------------------------
INSERT INTO `brand` VALUES ('2', '华为', '华为技术有限公司', '100', '华为致力于把数字世界带入每个人、每个家庭、每个组织，构建万物互联的智能世界', '1');
INSERT INTO `brand` VALUES ('4', '华为', '华为技术有限公司', '100', '万物互联', '1');
INSERT INTO `brand` VALUES ('6', '格力', '格力电器股份有限公司', '30', '让世界爱上中国造', '1');
INSERT INTO `brand` VALUES ('7', '阿里巴巴', '阿里巴巴集团控股有限公司', '10', '买买买', '1');
INSERT INTO `brand` VALUES ('8', '腾讯', '腾讯计算机系统有限公司', '50', '玩玩玩', '0');
INSERT INTO `brand` VALUES ('9', '百度', '百度在线网络技术公司', '5', '搜搜搜', '0');
INSERT INTO `brand` VALUES ('10', '京东', '北京京东世纪贸易有限公司', '40', '就是快', '1');
INSERT INTO `brand` VALUES ('11', '小米', '小米科技有限公司', '50', 'are you ok', '1');
INSERT INTO `brand` VALUES ('12', '三只松鼠', '三只松鼠股份有限公司', '5', '好吃不上火', '0');
INSERT INTO `brand` VALUES ('13', '华为', '华为技术有限公司', '100', '万物互联', '1');
INSERT INTO `brand` VALUES ('14', '小米', '小米科技有限公司', '50', 'are you ok', '1');
INSERT INTO `brand` VALUES ('15', '格力', '格力电器股份有限公司', '30', '让世界爱上中国造', '1');
INSERT INTO `brand` VALUES ('16', '阿里巴巴', '阿里巴巴集团控股有限公司', '10', '买买买', '1');
INSERT INTO `brand` VALUES ('17', '腾讯', '腾讯计算机系统有限公司', '50', '玩玩玩', '0');
INSERT INTO `brand` VALUES ('18', '百度', '百度在线网络技术公司', '5', '搜搜搜', '0');
INSERT INTO `brand` VALUES ('19', '京东', '北京京东世纪贸易有限公司', '40', '就是快', '1');
INSERT INTO `brand` VALUES ('20', '华为', '华为技术有限公司', '100', '万物互联', '1');
INSERT INTO `brand` VALUES ('21', '小米', '小米科技有限公司', '50', 'are you ok', '1');
INSERT INTO `brand` VALUES ('22', '格力', '格力电器股份有限公司', '30', '让世界爱上中国造', '1');
INSERT INTO `brand` VALUES ('23', '阿里巴巴', '阿里巴巴集团控股有限公司', '10', '买买买', '1');
INSERT INTO `brand` VALUES ('24', '腾讯', '腾讯计算机系统有限公司', '50', '玩玩玩', '0');
INSERT INTO `brand` VALUES ('25', '百度', '百度在线网络技术公司', '5', '搜搜搜', '0');
INSERT INTO `brand` VALUES ('26', '京东', '北京京东世纪贸易有限公司', '40', '就是快', '1');
INSERT INTO `brand` VALUES ('27', '小米', '小米科技有限公司', '50', 'are you ok', '1');
INSERT INTO `brand` VALUES ('28', '三只松鼠', '三只松鼠股份有限公司', '5', '好吃不上火', '0');
INSERT INTO `brand` VALUES ('29', '华为', '华为技术有限公司', '100', '万物互联', '1');
INSERT INTO `brand` VALUES ('30', '小米', '小米科技有限公司', '50', 'are you ok', '1');
INSERT INTO `brand` VALUES ('31', '格力', '格力电器股份有限公司', '30', '让世界爱上中国造', '1');
INSERT INTO `brand` VALUES ('32', '阿里巴巴', '阿里巴巴集团控股有限公司', '10', '买买买', '1');
INSERT INTO `brand` VALUES ('33', '腾讯', '腾讯计算机系统有限公司', '50', '玩玩玩', '0');
INSERT INTO `brand` VALUES ('34', '百度', '百度在线网络技术公司', '5', '搜搜搜', '0');
INSERT INTO `brand` VALUES ('35', '京东', '北京京东世纪贸易有限公司', '40', '就是快', '1');
INSERT INTO `brand` VALUES ('36', '华为', '华为技术有限公司', '100', '万物互联', '1');
INSERT INTO `brand` VALUES ('37', '小米', '小米科技有限公司', '50', 'are you ok', '1');
INSERT INTO `brand` VALUES ('38', '格力', '格力电器股份有限公司', '30', '让世界爱上中国造', '1');
INSERT INTO `brand` VALUES ('39', '阿里巴巴', '阿里巴巴集团控股有限公司', '10', '买买买', '1');
INSERT INTO `brand` VALUES ('40', '腾讯', '腾讯计算机系统有限公司', '50', '玩玩玩', '0');
INSERT INTO `brand` VALUES ('41', '百度', '百度在线网络技术公司', '5', '搜搜搜', '0');
INSERT INTO `brand` VALUES ('42', '京东', '北京京东世纪贸易有限公司', '40', '就是快', '1');
INSERT INTO `brand` VALUES ('43', '小米', '小米科技有限公司', '50', 'are you ok', '1');
INSERT INTO `brand` VALUES ('44', '三只松鼠', '三只松鼠股份有限公司', '5', '好吃不上火', '0');
INSERT INTO `brand` VALUES ('45', '华为', '华为技术有限公司', '100', '万物互联', '1');
INSERT INTO `brand` VALUES ('46', '小米', '小米科技有限公司', '50', 'are you ok', '1');
INSERT INTO `brand` VALUES ('47', '格力', '格力电器股份有限公司', '30', '让世界爱上中国造', '1');
INSERT INTO `brand` VALUES ('48', '阿里巴巴', '阿里巴巴集团控股有限公司', '10', '买买买', '1');
INSERT INTO `brand` VALUES ('49', '腾讯', '腾讯计算机系统有限公司', '50', '玩玩玩', '0');
INSERT INTO `brand` VALUES ('50', '百度', '百度在线网络技术公司', '5', '搜搜搜', '0');
INSERT INTO `brand` VALUES ('51', '京东', '北京京东世纪贸易有限公司', '40', '就是快', '1');
