-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        10.1.13-MariaDB - mariadb.org binary distribution
-- 服务器操作系统:                      Win32
-- HeidiSQL 版本:                  9.3.0.4984
-- --------------------------------------------------------


CREATE DATABASE IF NOT EXISTS `honey` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `honey`;


CREATE TABLE IF NOT EXISTS `honey_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mid` int(11) NOT NULL,
  `type_id` int(11) NOT NULL,
  `up` int(11) DEFAULT '0',
  `title` varchar(300) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

INSERT IGNORE INTO `honey_article` (`id`, `mid`, `type_id`, `up`, `title`, `create_time`) VALUES
	(13, 2, 1, 0, '66666666666666666666666666666666666666666666666666666666666', '2016-09-19 19:38:06');


CREATE TABLE IF NOT EXISTS `honey_article_main` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aid` int(11) NOT NULL,
  `body` text NOT NULL,
  `ua` varchar(100) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

INSERT IGNORE INTO `honey_article_main` (`id`, `aid`, `body`, `ua`, `create_time`) VALUES
	(5, 13, '66666666666666666666666666666666666666666666666666666666666', 'Cr', '2016-09-19 19:38:06');


CREATE TABLE IF NOT EXISTS `honey_member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nikename` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `head_img` varchar(500) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_nikename` (`nikename`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

INSERT IGNORE INTO `honey_member` (`id`, `nikename`, `email`, `password`, `head_img`, `create_time`, `update_time`) VALUES
	(2, '666666', '666666@liu.liu', 'F379EAF3C831B04DE153469D1BEC345E', 'http://cn.gravatar.com/avatar/4e3b157b24097948b982bb04bd676174', '2016-09-19 19:37:12', '2016-09-19 19:37:12');


CREATE TABLE IF NOT EXISTS `honey_reply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aid` int(11) NOT NULL,
  `mid` int(11) NOT NULL,
  `msg` varchar(5000) NOT NULL,
  `up` int(11) NOT NULL DEFAULT '0',
  `ua` varchar(100) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `honey_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL,
  `sname` varchar(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

INSERT IGNORE INTO `honey_type` (`id`, `name`, `sname`) VALUES
	(1, '灌水', '水'),
	(2, '创造', '创'),
	(3, '转载', '转'),
	(4, '音乐', '音'),
	(5, '萝莉', '裸'),
	(6, '游戏', '游');
