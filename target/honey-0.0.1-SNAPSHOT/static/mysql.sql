/*
Navicat MySQL Data Transfer

Source Server         : lcoal
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : honey

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2015-09-04 12:35:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for honey_article
-- ----------------------------
DROP TABLE IF EXISTS `honey_article`;
CREATE TABLE `honey_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mid` int(11) NOT NULL,
  `type_id` int(11) NOT NULL,
  `up` int(11)  DEFAULT '0',
  `title` varchar(300) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;



-- ----------------------------
-- Table structure for honey_article_main
-- ----------------------------
DROP TABLE IF EXISTS `honey_article_main`;
CREATE TABLE `honey_article_main` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aid` int(11) NOT NULL,
  `body` text NOT NULL,
  `ua` varchar(100) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for honey_member
-- ----------------------------
DROP TABLE IF EXISTS `honey_member`;
CREATE TABLE `honey_member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nikename` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `head_img` varchar(500) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_nikename` (`nikename`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for honey_reply
-- ----------------------------
DROP TABLE IF EXISTS `honey_reply`;
CREATE TABLE `honey_reply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aid` int(11) NOT NULL,
  `mid` int(11) NOT NULL,
  `msg` varchar(5000) NOT NULL,
  `up` int(11) NOT NULL DEFAULT '0',
  `ua` varchar(100) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for honey_type
-- ----------------------------
DROP TABLE IF EXISTS `honey_type`;
CREATE TABLE `honey_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL,
  `sname` varchar(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of honey_type
-- ----------------------------
INSERT INTO `honey_type` VALUES ('1', '灌水', '水');
INSERT INTO `honey_type` VALUES ('2', '创造', '创');
INSERT INTO `honey_type` VALUES ('3', '转载', '转');
INSERT INTO `honey_type` VALUES ('4', '音乐', '音');
INSERT INTO `honey_type` VALUES ('5', '萝莉', '裸');
INSERT INTO `honey_type` VALUES ('6', '游戏', '游');
