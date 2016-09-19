-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        10.1.13-MariaDB - mariadb.org binary distribution
-- 服务器操作系统:                      Win32
-- HeidiSQL 版本:                  9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 honey 的数据库结构
CREATE DATABASE IF NOT EXISTS `honey` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `honey`;


-- 导出  表 honey.honey_article 结构
CREATE TABLE IF NOT EXISTS `honey_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mid` int(11) NOT NULL,
  `type_id` int(11) NOT NULL,
  `up` int(11) DEFAULT '0',
  `title` varchar(300) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- 正在导出表  honey.honey_article 的数据：0 rows
DELETE FROM `honey_article`;
/*!40000 ALTER TABLE `honey_article` DISABLE KEYS */;
INSERT INTO `honey_article` (`id`, `mid`, `type_id`, `up`, `title`, `create_time`) VALUES
	(13, 2, 1, 0, '66666666666666666666666666666666666666666666666666666666666', '2016-09-19 19:38:06');
/*!40000 ALTER TABLE `honey_article` ENABLE KEYS */;


-- 导出  表 honey.honey_article_main 结构
CREATE TABLE IF NOT EXISTS `honey_article_main` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aid` int(11) NOT NULL,
  `body` text NOT NULL,
  `ua` varchar(100) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- 正在导出表  honey.honey_article_main 的数据：0 rows
DELETE FROM `honey_article_main`;
/*!40000 ALTER TABLE `honey_article_main` DISABLE KEYS */;
INSERT INTO `honey_article_main` (`id`, `aid`, `body`, `ua`, `create_time`) VALUES
	(5, 13, '66666666666666666666666666666666666666666666666666666666666', 'Cr', '2016-09-19 19:38:06');
/*!40000 ALTER TABLE `honey_article_main` ENABLE KEYS */;


-- 导出  表 honey.honey_member 结构
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

-- 正在导出表  honey.honey_member 的数据：1 rows
DELETE FROM `honey_member`;
/*!40000 ALTER TABLE `honey_member` DISABLE KEYS */;
INSERT INTO `honey_member` (`id`, `nikename`, `email`, `password`, `head_img`, `create_time`, `update_time`) VALUES
	(2, '666666', '666666@liu.liu', 'F379EAF3C831B04DE153469D1BEC345E', 'http://cn.gravatar.com/avatar/4e3b157b24097948b982bb04bd676174', '2016-09-19 19:37:12', '2016-09-19 19:37:12');
/*!40000 ALTER TABLE `honey_member` ENABLE KEYS */;


-- 导出  表 honey.honey_reply 结构
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

-- 正在导出表  honey.honey_reply 的数据：0 rows
DELETE FROM `honey_reply`;
/*!40000 ALTER TABLE `honey_reply` DISABLE KEYS */;
/*!40000 ALTER TABLE `honey_reply` ENABLE KEYS */;


-- 导出  表 honey.honey_type 结构
CREATE TABLE IF NOT EXISTS `honey_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL,
  `sname` varchar(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- 正在导出表  honey.honey_type 的数据：6 rows
DELETE FROM `honey_type`;
/*!40000 ALTER TABLE `honey_type` DISABLE KEYS */;
INSERT INTO `honey_type` (`id`, `name`, `sname`) VALUES
	(1, '灌水', '水'),
	(2, '创造', '创'),
	(3, '转载', '转'),
	(4, '音乐', '音'),
	(5, '萝莉', '裸'),
	(6, '游戏', '游');
/*!40000 ALTER TABLE `honey_type` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
