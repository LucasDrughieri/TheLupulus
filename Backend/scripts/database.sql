# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: thelupulus.mysql.database.azure.com (MySQL 5.6.39.0)
# Database: thebeermanagement
# Generation Time: 2018-10-21 23:54:50 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table beers
# ------------------------------------------------------------

DROP TABLE IF EXISTS `beers`;

CREATE TABLE `beers` (
  `beer_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `color` varchar(255) DEFAULT NULL,
  `density` float NOT NULL,
  `graduation` float NOT NULL,
  `granos` varchar(255) DEFAULT NULL,
  `ibu` float NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price_per_litre` float DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `visible` bit(1) DEFAULT NULL,
  PRIMARY KEY (`beer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `beers` WRITE;
/*!40000 ALTER TABLE `beers` DISABLE KEYS */;

INSERT INTO `beers` (`beer_id`, `color`, `density`, `graduation`, `granos`, `ibu`, `name`, `price_per_litre`, `quantity`, `visible`)
VALUES
	(1,'12',34,32,'grano 2',2,'Cerveza 2',4,5,NULL),
	(2,'roja',11,33,'Granos 1',22,'Cerveza 1',44,155,NULL),
	(4,'sad',21,1,'daqs',12,'asd',2,3,NULL);

/*!40000 ALTER TABLE `beers` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table clients
# ------------------------------------------------------------

DROP TABLE IF EXISTS `clients`;

CREATE TABLE `clients` (
  `client_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `business_name` varchar(255) DEFAULT NULL,
  `cuit` bigint(20) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone_number` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;

INSERT INTO `clients` (`client_id`, `address`, `business_name`, `cuit`, `email`, `phone_number`)
VALUES
	(1,'Calle 1','Cervecería San Cebada',123,'sancebada@gmail.com',57894565),
	(2,'Calle 1','Cervecería San Cebada',123,'sancebada@gmail.com',57894565),
	(3,'Calle 1','Cervecería San Cebada',123,'sancebada@gmail.com',57894565),
	(4,'Calle 1','Cervecería San Cebada',123,'sancebada@gmail.com',57894565),
	(5,'Calle 1','Cervecería San Cebada',123,'sancebada@gmail.com',57894565),
	(6,'Calle 1','Cervecería San Cebada',123,'sancebada@gmail.com',57894565),
	(7,'asdq','asd',13,'asd',53),
	(8,'Calle 1','Cervecería San Cebada',123,'sancebada@gmail.com',57894565),
	(9,'Calle 1','Cervecería San Cebada',123,'sancebada@gmail.com',57894565),
	(10,'Calle 1','Cervecería San Cebada',123,'sancebada@gmail.com',57894565),
	(11,'Calle 1','Cervecería San Cebada',123,'sancebada@gmail.com',57894565),
	(12,'asdq','qwe',123,'sadqw',123),
	(13,'Calle 1','Cervecería San Cebada',123,'sancebada@gmail.com',57894565),
	(14,'Calle 1','Cervecería San Cebada',123,'sancebada@gmail.com',57894565),
	(15,'Calle 1','Cervecería San Cebada',123,'sancebada@gmail.com',57894565),
	(16,'Calle 1','Cervecería San Cebada',123,'sancebada@gmail.com',57894565);

/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table containers
# ------------------------------------------------------------

DROP TABLE IF EXISTS `containers`;

CREATE TABLE `containers` (
  `container_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `capacity` int(11) DEFAULT NULL,
  `height` float NOT NULL,
  `material` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `visible` bit(1) DEFAULT NULL,
  `width` float NOT NULL,
  PRIMARY KEY (`container_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `containers` WRITE;
/*!40000 ALTER TABLE `containers` DISABLE KEYS */;

INSERT INTO `containers` (`container_id`, `capacity`, `height`, `material`, `name`, `quantity`, `visible`, `width`)
VALUES
	(1,303,101,'Material 12','Contenedor 11',504,NULL,202);

/*!40000 ALTER TABLE `containers` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table items
# ------------------------------------------------------------

DROP TABLE IF EXISTS `items`;

CREATE TABLE `items` (
  `item_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `beer_id` bigint(20) DEFAULT NULL,
  `container_id` bigint(20) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  cantidad bigint(20) default 0,
  PRIMARY KEY (`item_id`),
  KEY `FK_l8h2jdhhd1t9dva5io68f2fu8` (`beer_id`),
  KEY `FK_e0g579nb8l37l6jb4q6stsymr` (`container_id`),
  KEY `FK_av4mlxut8m30ehlvx8n8v8w8n` (`order_id`),
  CONSTRAINT `FK_av4mlxut8m30ehlvx8n8v8w8n` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
  CONSTRAINT `FK_e0g579nb8l37l6jb4q6stsymr` FOREIGN KEY (`container_id`) REFERENCES `containers` (`container_id`),
  CONSTRAINT `FK_l8h2jdhhd1t9dva5io68f2fu8` FOREIGN KEY (`beer_id`) REFERENCES `beers` (`beer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table orders
# ------------------------------------------------------------

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `order_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` decimal(19,2) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `visible` bit(1) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  pagado boolean default false,
  PRIMARY KEY (`order_id`),
  KEY `FK_k8kupdtcdpqd57b6j4yq9uvdj` (`user_id`),
  CONSTRAINT `FK_k8kupdtcdpqd57b6j4yq9uvdj` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table sessions
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sessions`;

CREATE TABLE `sessions` (
  `session_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `lastLogin` datetime DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`session_id`),
  KEY `FK_ll67hfsoxbb4aj85oexrpq39l` (`user_id`),
  CONSTRAINT `FK_ll67hfsoxbb4aj85oexrpq39l` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `sessions` WRITE;
/*!40000 ALTER TABLE `sessions` DISABLE KEYS */;

INSERT INTO `sessions` (`session_id`, `lastLogin`, `token`, `user_id`)
VALUES
	(1,'2018-10-20 12:30:45','72b951c4-3500-48c5-a7f8-46e8076b04e1',1);

/*!40000 ALTER TABLE `sessions` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table users
# ------------------------------------------------------------

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `client_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FK_g9epudg12gt4bfg0g8l9evbf2` (`client_id`),
  CONSTRAINT `FK_g9epudg12gt4bfg0g8l9evbf2` FOREIGN KEY (`client_id`) REFERENCES `clients` (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;

INSERT INTO `users` (`user_id`, `nickname`, `password`, `role`, `client_id`)
VALUES
	(1,'cebada','cebada',2,1),
	(3,'cebada','cebada',2,1),
	(5,'cebada','cebada',2,1),
	(6,'cebada','cebada',2,1),
	(7,'User1','asd123',1,NULL),
	(8,'user1','asd123',1,NULL),
	(10,'user1','asd123',1,NULL),
	(11,'asd','asd123',1,NULL),
	(12,'user1','asd123',1,NULL),
	(13,'asd','asd',1,NULL),
	(14,'asd','asd',2,1),
	(15,'user1','asd123',1,NULL),
	(16,'cliente 1','asd',2,2),
	(17,'cebada','cebada',2,1),
	(18,'admin','asd',1,NULL),
	(19,'cebada','cebada',2,1),
	(20,'cebada','cebada',2,1),
	(21,'cebada','cebada',2,1),
	(22,'das','qwe',2,2),
	(23,'qew','qwe',1,NULL),
	(24,'cebada','cebada',2,1),
	(25,'cebada','cebada',2,1),
	(26,'cebada','cebada',2,1),
	(27,'cebada','cebada',2,1);

/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
