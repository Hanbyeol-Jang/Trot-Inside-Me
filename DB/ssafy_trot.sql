-- --------------------------------------------------------
-- 호스트:                          i3b202.p.ssafy.io
-- 서버 버전:                        10.5.4-MariaDB-1:10.5.4+maria~focal - mariadb.org binary distribution
-- 서버 OS:                        debian-linux-gnu
-- HeidiSQL 버전:                  11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- ssafy_trot 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `ssafy_trot` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `ssafy_trot`;

-- 테이블 ssafy_trot.Article 구조 내보내기
CREATE TABLE IF NOT EXISTS `Article` (
  `b_idx` int(11) NOT NULL,
  `a_url` varchar(500) DEFAULT '',
  `a_title` varchar(100) DEFAULT '',
  `a_content` varchar(200) DEFAULT '',
  `a_img` varchar(100) DEFAULT '',
  `a_member` varchar(100) DEFAULT '',
  PRIMARY KEY (`b_idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 ssafy_trot.Board 구조 내보내기
CREATE TABLE IF NOT EXISTS `Board` (
  `b_idx` int(11) NOT NULL AUTO_INCREMENT,
  `b_type` int(11) NOT NULL DEFAULT 0,
  `b_good` int(11) DEFAULT 0,
  `b_bookmark` int(11) DEFAULT 0,
  `b_reply` int(11) DEFAULT NULL,
  PRIMARY KEY (`b_idx`),
  CONSTRAINT `Board_FK` FOREIGN KEY (`b_idx`) REFERENCES `Article` (`b_idx`),
  CONSTRAINT `Board_FK_1` FOREIGN KEY (`b_idx`) REFERENCES `Video` (`b_idx`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 ssafy_trot.Bookmark 구조 내보내기
CREATE TABLE IF NOT EXISTS `Bookmark` (
  `u_email` varchar(100) DEFAULT '',
  `b_idx` int(11) DEFAULT 0,
  `bm_idx` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`bm_idx`),
  KEY `Bookmark_FK` (`u_email`),
  KEY `Bookmark_FK_1` (`b_idx`),
  CONSTRAINT `Bookmark_FK` FOREIGN KEY (`b_idx`) REFERENCES `Board` (`b_idx`),
  CONSTRAINT `Bookmark_FK_1` FOREIGN KEY (`u_email`) REFERENCES `UserInfo` (`u_email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 ssafy_trot.BroadCasting 구조 내보내기
CREATE TABLE IF NOT EXISTS `BroadCasting` (
  `bc_idx` int(11) NOT NULL AUTO_INCREMENT,
  `bc_company` varchar(50) DEFAULT '',
  `bc_date` varchar(50) NOT NULL DEFAULT '',
  `bc_time` varchar(50) NOT NULL DEFAULT '',
  `bc_member` varchar(50) DEFAULT '',
  `bc_title` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`bc_idx`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 ssafy_trot.Following 구조 내보내기
CREATE TABLE IF NOT EXISTS `Following` (
  `u_email` varchar(100) CHARACTER SET utf8mb4 NOT NULL DEFAULT '',
  `s_idx` int(11) NOT NULL,
  `f_idx` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`f_idx`),
  KEY `Favorite_FK_1` (`u_email`),
  KEY `Following_FK` (`s_idx`),
  CONSTRAINT `Favorite_FK_1` FOREIGN KEY (`u_email`) REFERENCES `UserInfo` (`u_email`),
  CONSTRAINT `Following_FK` FOREIGN KEY (`s_idx`) REFERENCES `Singer` (`s_idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 ssafy_trot.Good 구조 내보내기
CREATE TABLE IF NOT EXISTS `Good` (
  `u_email` varchar(100) DEFAULT '',
  `b_idx` int(11) DEFAULT 0,
  `g_idx` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`g_idx`),
  KEY `Good_FK` (`u_email`),
  KEY `Good_FK_1` (`b_idx`),
  CONSTRAINT `Good_FK` FOREIGN KEY (`b_idx`) REFERENCES `Board` (`b_idx`),
  CONSTRAINT `Good_FK_1` FOREIGN KEY (`u_email`) REFERENCES `UserInfo` (`u_email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 ssafy_trot.Reply 구조 내보내기
CREATE TABLE IF NOT EXISTS `Reply` (
  `b_idx` int(11) NOT NULL DEFAULT 0,
  `r_idx` int(11) NOT NULL AUTO_INCREMENT,
  `u_name` varchar(50) NOT NULL DEFAULT '',
  `r_content` varchar(200) NOT NULL DEFAULT '',
  `u_email` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`r_idx`),
  KEY `Reply_FK` (`b_idx`),
  KEY `Reply_FK_1` (`u_email`),
  CONSTRAINT `Reply_FK` FOREIGN KEY (`b_idx`) REFERENCES `Board` (`b_idx`),
  CONSTRAINT `Reply_FK_1` FOREIGN KEY (`u_email`) REFERENCES `UserInfo` (`u_email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 ssafy_trot.Singer 구조 내보내기
CREATE TABLE IF NOT EXISTS `Singer` (
  `s_idx` int(11) NOT NULL AUTO_INCREMENT,
  `s_name` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`s_idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 ssafy_trot.UserInfo 구조 내보내기
CREATE TABLE IF NOT EXISTS `UserInfo` (
  `u_pw` varchar(50) NOT NULL DEFAULT '',
  `u_name` varchar(50) NOT NULL DEFAULT '',
  `u_email` varchar(100) NOT NULL DEFAULT '',
  `u_img` varchar(100) DEFAULT '',
  `u_pw2` varchar(50) DEFAULT '',
  PRIMARY KEY (`u_email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 ssafy_trot.Video 구조 내보내기
CREATE TABLE IF NOT EXISTS `Video` (
  `b_idx` int(11) NOT NULL,
  `v_url` varchar(500) NOT NULL DEFAULT '',
  `v_title` varchar(100) NOT NULL DEFAULT '',
  `v_content` varchar(200) DEFAULT '',
  `v_member` varchar(50) DEFAULT '',
  PRIMARY KEY (`b_idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 내보낼 데이터가 선택되어 있지 않습니다.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
