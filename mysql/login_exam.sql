-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.22-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for login_exam
CREATE DATABASE IF NOT EXISTS `login_exam` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `login_exam`;

-- Dumping structure for table login_exam.tbl_function
CREATE TABLE IF NOT EXISTS `tbl_function` (
  `function_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `function_code` varchar(255) DEFAULT NULL,
  `function_name` varchar(50) DEFAULT NULL,
  `function_order` int(11) DEFAULT NULL,
  `function_url` varchar(50) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`function_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Dumping data for table login_exam.tbl_function: ~3 rows (approximately)
DELETE FROM `tbl_function`;
/*!40000 ALTER TABLE `tbl_function` DISABLE KEYS */;
INSERT INTO `tbl_function` (`function_id`, `description`, `function_code`, `function_name`, `function_order`, `function_url`, `status`) VALUES
	(1, 'xemTin1', 'xem_tin_1', 'xem_tin_1', 1, '/xem_tin_1', 1),
	(2, 'xemTin2', 'xem_tin_2', 'xem_tin_2', 2, '/xem_tin_2', 1),
	(3, 'xemTin3', 'xem_tin_3', 'xem_tin_3', 3, '/xem_tin_3', 1);
/*!40000 ALTER TABLE `tbl_function` ENABLE KEYS */;

-- Dumping structure for table login_exam.tbl_role
CREATE TABLE IF NOT EXISTS `tbl_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `role_code` varchar(255) DEFAULT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  `role_order` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- Dumping data for table login_exam.tbl_role: ~2 rows (approximately)
DELETE FROM `tbl_role`;
/*!40000 ALTER TABLE `tbl_role` DISABLE KEYS */;
INSERT INTO `tbl_role` (`role_id`, `description`, `role_code`, `role_name`, `role_order`, `status`) VALUES
	(1, 'Quản trị hệ thống', 'ROLE_ADMIN', 'ROLE_ADMIN', '1', 1),
	(2, 'User', 'ROLE_USER', 'ROLE_USER', '2', 1);
/*!40000 ALTER TABLE `tbl_role` ENABLE KEYS */;

-- Dumping structure for table login_exam.tbl_role_function
CREATE TABLE IF NOT EXISTS `tbl_role_function` (
  `role_id` bigint(20) NOT NULL,
  `function_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`function_id`),
  KEY `FKdr2lrs8gkaa1wi9ah5rbawqlk` (`function_id`),
  CONSTRAINT `FKdr2lrs8gkaa1wi9ah5rbawqlk` FOREIGN KEY (`function_id`) REFERENCES `tbl_function` (`function_id`),
  CONSTRAINT `FKjjgynpb69x0rx8dvdayfyai3s` FOREIGN KEY (`role_id`) REFERENCES `tbl_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table login_exam.tbl_role_function: ~4 rows (approximately)
DELETE FROM `tbl_role_function`;
/*!40000 ALTER TABLE `tbl_role_function` DISABLE KEYS */;
INSERT INTO `tbl_role_function` (`role_id`, `function_id`) VALUES
	(1, 1),
	(1, 2),
	(2, 2),
	(2, 3);
/*!40000 ALTER TABLE `tbl_role_function` ENABLE KEYS */;

-- Dumping structure for table login_exam.tbl_role_user
CREATE TABLE IF NOT EXISTS `tbl_role_user` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKl0d1jqdkgdmvl7jwjushhumxc` (`role_id`),
  CONSTRAINT `FKl0d1jqdkgdmvl7jwjushhumxc` FOREIGN KEY (`role_id`) REFERENCES `tbl_role` (`role_id`),
  CONSTRAINT `FKn679clgn0jm2cq4hmmqodiuye` FOREIGN KEY (`user_id`) REFERENCES `tbl_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table login_exam.tbl_role_user: ~2 rows (approximately)
DELETE FROM `tbl_role_user`;
/*!40000 ALTER TABLE `tbl_role_user` DISABLE KEYS */;
INSERT INTO `tbl_role_user` (`user_id`, `role_id`) VALUES
	(1, 1),
	(2, 2);
/*!40000 ALTER TABLE `tbl_role_user` ENABLE KEYS */;

-- Dumping structure for table login_exam.tbl_user
CREATE TABLE IF NOT EXISTS `tbl_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `full_name` varchar(255) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table login_exam.tbl_user: ~2 rows (approximately)
DELETE FROM `tbl_user`;
/*!40000 ALTER TABLE `tbl_user` DISABLE KEYS */;
INSERT INTO `tbl_user` (`user_id`, `full_name`, `gender`, `password`, `status`, `user_name`) VALUES
	(1, 'admin', 1, '$2a$10$wN5c2s4H1YljuBATZfUideupq7g3qpcRIpl2sMdMNo77Lu/ZKSxfS', 1, 'admin'),
	(2, 'user', 2, '$2a$10$..s/NOwmD4AtBvL4k28FFu.kPdffgCBsNeLJU57MDTtXKAaFOuA/a', 1, 'user');
/*!40000 ALTER TABLE `tbl_user` ENABLE KEYS */;

-- Dumping structure for table login_exam.token
CREATE TABLE IF NOT EXISTS `token` (
  `token_id` int(11) NOT NULL AUTO_INCREMENT,
  `authentication_token` varchar(255) DEFAULT NULL,
  `secret_key` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`token_id`),
  UNIQUE KEY `UK_g7im3j7f0g31yhl6qco2iboy5` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table login_exam.token: ~0 rows (approximately)
DELETE FROM `token`;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
/*!40000 ALTER TABLE `token` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
