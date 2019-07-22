-- --------------------------------------------------------
-- Máy chủ:                      127.0.0.1
-- Server version:               10.3.14-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Phiên bản:           10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for store
CREATE DATABASE IF NOT EXISTS `store` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `store`;

-- Dumping structure for table store.students
CREATE TABLE IF NOT EXISTS `students` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `updated_at` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `birth_date` datetime DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `rolename` varchar(255) DEFAULT NULL,
  `school` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- Dumping data for table store.students: ~2 rows (approximately)
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` (`id`, `updated_at`, `created_at`, `birth_date`, `password`, `rolename`, `school`, `username`) VALUES
	(6, '2019-07-17 18:16:41', '2019-07-17 18:16:41', NULL, '123123', NULL, NULL, 'thanhtinhpas1'),
	(7, '2019-07-17 18:17:20', '2019-07-17 18:17:20', NULL, '123123', NULL, NULL, 'thanhtinhpas1'),
	(8, '2019-07-22 15:46:44', '2019-07-22 15:46:44', NULL, '123123', NULL, NULL, 'thanhtinhpas1'),
	(9, '2019-07-22 15:46:49', '2019-07-22 15:46:49', NULL, '123123', NULL, NULL, 'thanhtinhpas1');
/*!40000 ALTER TABLE `students` ENABLE KEYS */;

-- Dumping structure for table store.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `updated_at` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `rolename` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table store.users: ~1 rows (approximately)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `updated_at`, `created_at`, `birth_date`, `password`, `rolename`, `username`) VALUES
	(1, '2019-07-17 16:54:22', '2019-07-17 16:54:23', '2019-07-17', '96CAE35CE8A9B0244178BF28E4966C2CE1B8385723A96A6B838858CDD6CA0A1E', 'ROLE_USER', 'thanhtinhpas1');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
