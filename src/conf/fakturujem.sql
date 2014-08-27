/*
SQLyog Community v9.62 
MySQL - 5.6.20 : Database - fakturujem
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`fakturujem` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_czech_ci */;

USE `fakturujem`;

/*Table structure for table `account` */

DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) CHARACTER SET latin1 NOT NULL,
  `password` varchar(45) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

/*Data for the table `account` */

insert  into `account`(`id`,`email`,`password`) values (1,'michal.prenner@gmail.com','123456');

/*Table structure for table `invoice` */

DROP TABLE IF EXISTS `invoice`;

CREATE TABLE `invoice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `invoicenumber` int(11) NOT NULL,
  `account_idaccount` int(11) NOT NULL,
  `state_idstate` int(11) NOT NULL,
  `method_idmethod` int(11) NOT NULL,
  `created` date NOT NULL,
  `due` date NOT NULL,
  `duzp` date NOT NULL,
  `variablesymbol` int(11) NOT NULL,
  `constantsymbol` int(11) DEFAULT NULL,
  `specificsymbol` int(11) DEFAULT NULL,
  `total` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

/*Data for the table `invoice` */

insert  into `invoice`(`id`,`invoicenumber`,`account_idaccount`,`state_idstate`,`method_idmethod`,`created`,`due`,`duzp`,`variablesymbol`,`constantsymbol`,`specificsymbol`,`total`) values (1,50,1,1,1,'2014-08-22','2014-08-22','2014-08-22',777,555,666,0);

/*Table structure for table `invoice_has_item` */

DROP TABLE IF EXISTS `invoice_has_item`;

CREATE TABLE `invoice_has_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `invoice_idinvoice` int(11) NOT NULL,
  `item_iditem` int(11) NOT NULL,
  `count` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

/*Data for the table `invoice_has_item` */

/*Table structure for table `invoice_has_person` */

DROP TABLE IF EXISTS `invoice_has_person`;

CREATE TABLE `invoice_has_person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `invoice_idinvoice` int(11) NOT NULL,
  `person_idperson` int(11) NOT NULL,
  `relation` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

/*Data for the table `invoice_has_person` */

insert  into `invoice_has_person`(`id`,`invoice_idinvoice`,`person_idperson`,`relation`) values (1,1,1,0),(2,1,2,1);

/*Table structure for table `item` */

DROP TABLE IF EXISTS `item`;

CREATE TABLE `item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_idaccount` int(11) NOT NULL,
  `rate_idrate` int(11) NOT NULL,
  `title` varchar(45) CHARACTER SET latin1 NOT NULL,
  `price` int(11) NOT NULL,
  `code` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

/*Data for the table `item` */

/*Table structure for table `method` */

DROP TABLE IF EXISTS `method`;

CREATE TABLE `method` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

/*Data for the table `method` */

insert  into `method`(`id`,`title`) values (1,'Bankovní prevod');

/*Table structure for table `person` */

DROP TABLE IF EXISTS `person`;

CREATE TABLE `person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_idaccount` int(11) NOT NULL,
  `name` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `lastname` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `company` varchar(45) COLLATE utf8_czech_ci DEFAULT NULL,
  `street` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `city` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `pcode` int(11) DEFAULT NULL,
  `state` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `isowner` tinyint(1) DEFAULT NULL,
  `phone` int(11) DEFAULT NULL,
  `email` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `fax` int(11) DEFAULT NULL,
  `www` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `bankaccount` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `ico` int(11) DEFAULT NULL,
  `dic` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

/*Data for the table `person` */

insert  into `person`(`id`,`account_idaccount`,`name`,`lastname`,`company`,`street`,`city`,`pcode`,`state`,`isowner`,`phone`,`email`,`fax`,`www`,`bankaccount`,`ico`,`dic`) values (1,1,'Michal','Prenner',NULL,'A. Krej?ího 2050/2','?B',37007,'?R',1,420,'micha.prenner@gmail.com',NULL,NULL,NULL,NULL,NULL),(2,1,'Franta','Lála',NULL,'Šmoulová 5','Praha',25005,'R',0,123456123,'lala@franta.cz',NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `rate` */

DROP TABLE IF EXISTS `rate`;

CREATE TABLE `rate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) CHARACTER SET latin1 NOT NULL,
  `value` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

/*Data for the table `rate` */

insert  into `rate`(`id`,`title`,`value`) values (1,'Basic',21);

/*Table structure for table `state` */

DROP TABLE IF EXISTS `state`;

CREATE TABLE `state` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

/*Data for the table `state` */

insert  into `state`(`id`,`title`) values (1,'Otevrena');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
