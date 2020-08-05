CREATE DATABASE  IF NOT EXISTS `feladat` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `feladat`;
-- MySQL dump 10.13  Distrib 5.7.26, for Win32 (AMD64)
--
-- Host: 127.0.0.1    Database: feladat
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `country` varchar(255) NOT NULL,
  `county` varchar(255) NOT NULL,
  `zip` int(11) NOT NULL,
  `village` varchar(255) NOT NULL,
  `street` varchar(255) NOT NULL,
  `number` varchar(45) NOT NULL,
  `userid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_address_user_id_idx` (`userid`),
  CONSTRAINT `FK_address_user_id` FOREIGN KEY (`userid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'Magyarország','Békés',5540,'Szarvas','Kiscica utca','33',1),(2,'Magyarország','Békés',5540,'Szarvas','Nagykutya utca','33',1),(3,'Magyarország','Békés',5540,'Szarvas','Nagykutya utca','32',2),(4,'Magyarország','Békés',5540,'Szarvas','Kis Pista utca','10',2);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `sequence_name` varchar(255) NOT NULL,
  `next_val` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES ('product',29);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `description` text NOT NULL,
  `price` int(11) NOT NULL,
  `issold` tinyint(4) NOT NULL DEFAULT '0',
  `isaccapted` tinyint(4) NOT NULL DEFAULT '0',
  `imagepath` varchar(255) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `sold_date` timestamp NULL DEFAULT NULL,
  `buyer` int(11) DEFAULT NULL,
  `owner` int(11) NOT NULL DEFAULT '1',
  `baddress` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_product_buyer_userid_idx` (`buyer`),
  KEY `fk_product_owner_uderid_idx` (`owner`),
  KEY `fk_product_bought_address_id_idx` (`baddress`),
  CONSTRAINT `FK_product_buyer_userid` FOREIGN KEY (`buyer`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_product_bought_address_id` FOREIGN KEY (`baddress`) REFERENCES `address` (`id`),
  CONSTRAINT `fk_product_owner_userid` FOREIGN KEY (`owner`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'kiscica','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque urna massa, eleifend non justo eu, ornare cursus lectus. Praesent at tincidunt purus. In sed risus gravida, ultricies sem nec, dignissim turpis. Sed vestibulum, ipsum vel ultricies posuere, libero lorem varius arcu, at mattis magna velit eu turpis. Nam vel vulputate enim, non lobortis nulla. Nullam venenatis mi nec ornare molestie. Aliquam finibus hendrerit molestie. Aliquam at purus ut nunc pharetra tincidunt. Phasellus consequat augue vel efficitur condimentum. Quisque sem ex, lobortis sed placerat in, auctor quis nibh. Phasellus sit amet sodales ipsum. Nunc tempor finibus viverra. Cras est justo, dignissim.',1000,1,1,'https://picsum.photos/300/300','2020-07-25 12:10:52','2020-08-03 14:38:42',2,1,1),(2,'kiscica','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque urna massa, eleifend non justo eu, ornare cursus lectus. Praesent at tincidunt purus. In sed risus gravida, ultricies sem nec, dignissim turpis. Sed vestibulum, ipsum vel ultricies posuere, libero lorem varius arcu, at mattis magna velit eu turpis. Nam vel vulputate enim, non lobortis nulla. Nullam venenatis mi nec ornare molestie. Aliquam finibus hendrerit molestie. Aliquam at purus ut nunc pharetra tincidunt. Phasellus consequat augue vel efficitur condimentum. Quisque sem ex, lobortis sed placerat in, auctor quis nibh. Phasellus sit amet sodales ipsum. Nunc tempor finibus viverra. Cras est justo, dignissim.',1000,1,1,'https://picsum.photos/300/300','2020-07-25 12:10:59','2020-08-05 15:52:05',2,1,NULL),(3,'kiscica','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque urna massa, eleifend non justo eu, ornare cursus lectus. Praesent at tincidunt purus. In sed risus gravida, ultricies sem nec, dignissim turpis. Sed vestibulum, ipsum vel ultricies posuere, libero lorem varius arcu, at mattis magna velit eu turpis. Nam vel vulputate enim, non lobortis nulla. Nullam venenatis mi nec ornare molestie. Aliquam finibus hendrerit molestie. Aliquam at purus ut nunc pharetra tincidunt. Phasellus consequat augue vel efficitur condimentum. Quisque sem ex, lobortis sed placerat in, auctor quis nibh. Phasellus sit amet sodales ipsum. Nunc tempor finibus viverra. Cras est justo, dignissim.',1000,1,1,'https://picsum.photos/300/300','2020-07-25 12:10:59','2020-08-05 16:22:57',2,1,NULL),(4,'kiscica','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque urna massa, eleifend non justo eu, ornare cursus lectus. Praesent at tincidunt purus. In sed risus gravida, ultricies sem nec, dignissim turpis. Sed vestibulum, ipsum vel ultricies posuere, libero lorem varius arcu, at mattis magna velit eu turpis. Nam vel vulputate enim, non lobortis nulla. Nullam venenatis mi nec ornare molestie. Aliquam finibus hendrerit molestie. Aliquam at purus ut nunc pharetra tincidunt. Phasellus consequat augue vel efficitur condimentum. Quisque sem ex, lobortis sed placerat in, auctor quis nibh. Phasellus sit amet sodales ipsum. Nunc tempor finibus viverra. Cras est justo, dignissim.',1000,1,1,'https://picsum.photos/300/300','2020-07-25 12:10:59','2020-08-05 16:28:19',2,1,3),(5,'kiscica','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque urna massa, eleifend non justo eu, ornare cursus lectus. Praesent at tincidunt purus. In sed risus gravida, ultricies sem nec, dignissim turpis. Sed vestibulum, ipsum vel ultricies posuere, libero lorem varius arcu, at mattis magna velit eu turpis. Nam vel vulputate enim, non lobortis nulla. Nullam venenatis mi nec ornare molestie. Aliquam finibus hendrerit molestie. Aliquam at purus ut nunc pharetra tincidunt. Phasellus consequat augue vel efficitur condimentum. Quisque sem ex, lobortis sed placerat in, auctor quis nibh. Phasellus sit amet sodales ipsum. Nunc tempor finibus viverra. Cras est justo, dignissim.',1000,0,0,'https://picsum.photos/300/300','2020-07-25 12:10:59','2020-08-05 16:20:16',2,1,NULL),(6,'kiscica','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque urna massa, eleifend non justo eu, ornare cursus lectus. Praesent at tincidunt purus. In sed risus gravida, ultricies sem nec, dignissim turpis. Sed vestibulum, ipsum vel ultricies posuere, libero lorem varius arcu, at mattis magna velit eu turpis. Nam vel vulputate enim, non lobortis nulla. Nullam venenatis mi nec ornare molestie. Aliquam finibus hendrerit molestie. Aliquam at purus ut nunc pharetra tincidunt. Phasellus consequat augue vel efficitur condimentum. Quisque sem ex, lobortis sed placerat in, auctor quis nibh. Phasellus sit amet sodales ipsum. Nunc tempor finibus viverra. Cras est justo, dignissim.',1000,0,0,'https://picsum.photos/300/300','2020-07-25 12:10:59',NULL,NULL,1,NULL),(7,'kiscica','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque urna massa, eleifend non justo eu, ornare cursus lectus. Praesent at tincidunt purus. In sed risus gravida, ultricies sem nec, dignissim turpis. Sed vestibulum, ipsum vel ultricies posuere, libero lorem varius arcu, at mattis magna velit eu turpis. Nam vel vulputate enim, non lobortis nulla. Nullam venenatis mi nec ornare molestie. Aliquam finibus hendrerit molestie. Aliquam at purus ut nunc pharetra tincidunt. Phasellus consequat augue vel efficitur condimentum. Quisque sem ex, lobortis sed placerat in, auctor quis nibh. Phasellus sit amet sodales ipsum. Nunc tempor finibus viverra. Cras est justo, dignissim.',1000,0,0,'https://picsum.photos/300/300','2020-07-25 12:11:00',NULL,NULL,1,NULL),(8,'kiscica','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque urna massa, eleifend non justo eu, ornare cursus lectus. Praesent at tincidunt purus. In sed risus gravida, ultricies sem nec, dignissim turpis. Sed vestibulum, ipsum vel ultricies posuere, libero lorem varius arcu, at mattis magna velit eu turpis. Nam vel vulputate enim, non lobortis nulla. Nullam venenatis mi nec ornare molestie. Aliquam finibus hendrerit molestie. Aliquam at purus ut nunc pharetra tincidunt. Phasellus consequat augue vel efficitur condimentum. Quisque sem ex, lobortis sed placerat in, auctor quis nibh. Phasellus sit amet sodales ipsum. Nunc tempor finibus viverra. Cras est justo, dignissim.',1000,0,0,'https://picsum.photos/300/300','2020-07-25 12:11:00',NULL,NULL,1,NULL),(9,'kutya','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque urna massa, eleifend non justo eu, ornare cursus lectus. Praesent at tincidunt purus. In sed risus gravida, ultricies sem nec, dignissim turpis. Sed vestibulum, ipsum vel ultricies posuere, libero lorem varius arcu, at mattis magna velit eu turpis. Nam vel vulputate enim, non lobortis nulla. Nullam venenatis mi nec ornare molestie. Aliquam finibus hendrerit molestie. Aliquam at purus ut nunc pharetra tincidunt. Phasellus consequat augue vel efficitur condimentum. Quisque sem ex, lobortis sed placerat in, auctor quis nibh. Phasellus sit amet sodales ipsum. Nunc tempor finibus viverra. Cras est justo, dignissim.',1000,0,0,'https://picsum.photos/300/300','2020-07-25 12:11:01',NULL,NULL,1,NULL),(10,'kutya','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque urna massa, eleifend non justo eu, ornare cursus lectus. Praesent at tincidunt purus. In sed risus gravida, ultricies sem nec, dignissim turpis. Sed vestibulum, ipsum vel ultricies posuere, libero lorem varius arcu, at mattis magna velit eu turpis. Nam vel vulputate enim, non lobortis nulla. Nullam venenatis mi nec ornare molestie. Aliquam finibus hendrerit molestie. Aliquam at purus ut nunc pharetra tincidunt. Phasellus consequat augue vel efficitur condimentum. Quisque sem ex, lobortis sed placerat in, auctor quis nibh. Phasellus sit amet sodales ipsum. Nunc tempor finibus viverra. Cras est justo, dignissim.',1000,0,0,'https://picsum.photos/300/300','2020-07-25 12:11:01',NULL,NULL,1,NULL),(11,'kutya','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque urna massa, eleifend non justo eu, ornare cursus lectus. Praesent at tincidunt purus. In sed risus gravida, ultricies sem nec, dignissim turpis. Sed vestibulum, ipsum vel ultricies posuere, libero lorem varius arcu, at mattis magna velit eu turpis. Nam vel vulputate enim, non lobortis nulla. Nullam venenatis mi nec ornare molestie. Aliquam finibus hendrerit molestie. Aliquam at purus ut nunc pharetra tincidunt. Phasellus consequat augue vel efficitur condimentum. Quisque sem ex, lobortis sed placerat in, auctor quis nibh. Phasellus sit amet sodales ipsum. Nunc tempor finibus viverra. Cras est justo, dignissim.',1000,0,0,'https://picsum.photos/300/300','2020-07-25 12:11:02',NULL,NULL,1,NULL),(12,'kutya','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque urna massa, eleifend non justo eu, ornare cursus lectus. Praesent at tincidunt purus. In sed risus gravida, ultricies sem nec, dignissim turpis. Sed vestibulum, ipsum vel ultricies posuere, libero lorem varius arcu, at mattis magna velit eu turpis. Nam vel vulputate enim, non lobortis nulla. Nullam venenatis mi nec ornare molestie. Aliquam finibus hendrerit molestie. Aliquam at purus ut nunc pharetra tincidunt. Phasellus consequat augue vel efficitur condimentum. Quisque sem ex, lobortis sed placerat in, auctor quis nibh. Phasellus sit amet sodales ipsum. Nunc tempor finibus viverra. Cras est justo, dignissim.',1000,0,0,'https://picsum.photos/300/300','2020-07-25 12:11:02',NULL,NULL,1,NULL),(13,'kutya','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque urna massa, eleifend non justo eu, ornare cursus lectus. Praesent at tincidunt purus. In sed risus gravida, ultricies sem nec, dignissim turpis. Sed vestibulum, ipsum vel ultricies posuere, libero lorem varius arcu, at mattis magna velit eu turpis. Nam vel vulputate enim, non lobortis nulla. Nullam venenatis mi nec ornare molestie. Aliquam finibus hendrerit molestie. Aliquam at purus ut nunc pharetra tincidunt. Phasellus consequat augue vel efficitur condimentum. Quisque sem ex, lobortis sed placerat in, auctor quis nibh. Phasellus sit amet sodales ipsum. Nunc tempor finibus viverra. Cras est justo, dignissim.',1000,0,0,'https://picsum.photos/300/300','2020-07-25 12:11:02',NULL,NULL,1,NULL),(14,'kutya','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque urna massa, eleifend non justo eu, ornare cursus lectus. Praesent at tincidunt purus. In sed risus gravida, ultricies sem nec, dignissim turpis. Sed vestibulum, ipsum vel ultricies posuere, libero lorem varius arcu, at mattis magna velit eu turpis. Nam vel vulputate enim, non lobortis nulla. Nullam venenatis mi nec ornare molestie. Aliquam finibus hendrerit molestie. Aliquam at purus ut nunc pharetra tincidunt. Phasellus consequat augue vel efficitur condimentum. Quisque sem ex, lobortis sed placerat in, auctor quis nibh. Phasellus sit amet sodales ipsum. Nunc tempor finibus viverra. Cras est justo, dignissim.',1000,0,0,'https://picsum.photos/300/300','2020-07-25 13:06:35',NULL,NULL,1,NULL),(15,'kutya2','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque urna massa, eleifend non justo eu, ornare cursus lectus. Praesent at tincidunt purus. In sed risus gravida, ultricies sem nec, dignissim turpis. Sed vestibulum, ipsum vel ultricies posuere, libero lorem varius arcu, at mattis magna velit eu turpis. Nam vel vulputate enim, non lobortis nulla. Nullam venenatis mi nec ornare molestie. Aliquam finibus hendrerit molestie. Aliquam at purus ut nunc pharetra tincidunt. Phasellus consequat augue vel efficitur condimentum. Quisque sem ex, lobortis sed placerat in, auctor quis nibh. Phasellus sit amet sodales ipsum. Nunc tempor finibus viverra. Cras est justo, dignissim.',10000,0,0,'https://picsum.photos/300/300','2020-07-25 13:07:13',NULL,NULL,1,NULL),(17,'Hibernate','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque urna massa, eleifend non justo eu, ornare cursus lectus. Praesent at tincidunt purus. In sed risus gravida, ultricies sem nec, dignissim turpis. Sed vestibulum, ipsum vel ultricies posuere, libero lorem varius arcu, at mattis magna velit eu turpis. Nam vel vulputate enim, non lobortis nulla. Nullam venenatis mi nec ornare molestie. Aliquam finibus hendrerit molestie. Aliquam at purus ut nunc pharetra tincidunt. Phasellus consequat augue vel efficitur condimentum. Quisque sem ex, lobortis sed placerat in, auctor quis nibh. Phasellus sit amet sodales ipsum. Nunc tempor finibus viverra. Cras est justo, dignissim.',10000,1,1,'https://picsum.photos/300/300','2020-07-25 22:00:00','2020-08-03 16:02:27',1,1,1),(18,'Hibernate','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque urna massa, eleifend non justo eu, ornare cursus lectus. Praesent at tincidunt purus. In sed risus gravida, ultricies sem nec, dignissim turpis. Sed vestibulum, ipsum vel ultricies posuere, libero lorem varius arcu, at mattis magna velit eu turpis. Nam vel vulputate enim, non lobortis nulla. Nullam venenatis mi nec ornare molestie. Aliquam finibus hendrerit molestie. Aliquam at purus ut nunc pharetra tincidunt. Phasellus consequat augue vel efficitur condimentum. Quisque sem ex, lobortis sed placerat in, auctor quis nibh. Phasellus sit amet sodales ipsum. Nunc tempor finibus viverra. Cras est justo, dignissim.',10000,1,1,'https://picsum.photos/300/300','2020-07-25 22:00:00','2020-08-03 16:02:27',1,2,1),(19,'Hibernate','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque urna massa, eleifend non justo eu, ornare cursus lectus. Praesent at tincidunt purus. In sed risus gravida, ultricies sem nec, dignissim turpis. Sed vestibulum, ipsum vel ultricies posuere, libero lorem varius arcu, at mattis magna velit eu turpis. Nam vel vulputate enim, non lobortis nulla. Nullam venenatis mi nec ornare molestie. Aliquam finibus hendrerit molestie. Aliquam at purus ut nunc pharetra tincidunt. Phasellus consequat augue vel efficitur condimentum. Quisque sem ex, lobortis sed placerat in, auctor quis nibh. Phasellus sit amet sodales ipsum. Nunc tempor finibus viverra. Cras est justo, dignissim.',10000,0,1,'https://picsum.photos/300/300','2020-07-25 22:00:00',NULL,NULL,2,NULL),(20,'Hibernate','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque urna massa, eleifend non justo eu, ornare cursus lectus. Praesent at tincidunt purus. In sed risus gravida, ultricies sem nec, dignissim turpis. Sed vestibulum, ipsum vel ultricies posuere, libero lorem varius arcu, at mattis magna velit eu turpis. Nam vel vulputate enim, non lobortis nulla. Nullam venenatis mi nec ornare molestie. Aliquam finibus hendrerit molestie. Aliquam at purus ut nunc pharetra tincidunt. Phasellus consequat augue vel efficitur condimentum. Quisque sem ex, lobortis sed placerat in, auctor quis nibh. Phasellus sit amet sodales ipsum. Nunc tempor finibus viverra. Cras est justo, dignissim.',10000,0,1,'https://picsum.photos/300/300','2020-07-25 22:00:00',NULL,NULL,2,NULL),(21,'Hibernate','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque urna massa, eleifend non justo eu, ornare cursus lectus. Praesent at tincidunt purus. In sed risus gravida, ultricies sem nec, dignissim turpis. Sed vestibulum, ipsum vel ultricies posuere, libero lorem varius arcu, at mattis magna velit eu turpis. Nam vel vulputate enim, non lobortis nulla. Nullam venenatis mi nec ornare molestie. Aliquam finibus hendrerit molestie. Aliquam at purus ut nunc pharetra tincidunt. Phasellus consequat augue vel efficitur condimentum. Quisque sem ex, lobortis sed placerat in, auctor quis nibh. Phasellus sit amet sodales ipsum. Nunc tempor finibus viverra. Cras est justo, dignissim.',10000,0,1,'https://picsum.photos/300/300','2020-07-25 22:00:00',NULL,NULL,2,NULL),(22,'Hibernate','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque urna massa, eleifend non justo eu, ornare cursus lectus. Praesent at tincidunt purus. In sed risus gravida, ultricies sem nec, dignissim turpis. Sed vestibulum, ipsum vel ultricies posuere, libero lorem varius arcu, at mattis magna velit eu turpis. Nam vel vulputate enim, non lobortis nulla. Nullam venenatis mi nec ornare molestie. Aliquam finibus hendrerit molestie. Aliquam at purus ut nunc pharetra tincidunt. Phasellus consequat augue vel efficitur condimentum. Quisque sem ex, lobortis sed placerat in, auctor quis nibh. Phasellus sit amet sodales ipsum. Nunc tempor finibus viverra. Cras est justo, dignissim.',10000,0,1,'https://picsum.photos/300/300','2020-07-25 22:00:00',NULL,NULL,2,NULL),(23,'Hibernate','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque urna massa, eleifend non justo eu, ornare cursus lectus. Praesent at tincidunt purus. In sed risus gravida, ultricies sem nec, dignissim turpis. Sed vestibulum, ipsum vel ultricies posuere, libero lorem varius arcu, at mattis magna velit eu turpis. Nam vel vulputate enim, non lobortis nulla. Nullam venenatis mi nec ornare molestie. Aliquam finibus hendrerit molestie. Aliquam at purus ut nunc pharetra tincidunt. Phasellus consequat augue vel efficitur condimentum. Quisque sem ex, lobortis sed placerat in, auctor quis nibh. Phasellus sit amet sodales ipsum. Nunc tempor finibus viverra. Cras est justo, dignissim.',10000,0,1,'https://picsum.photos/300/300','2020-07-25 22:00:00',NULL,NULL,2,NULL),(24,'Hibernate','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque urna massa, eleifend non justo eu, ornare cursus lectus. Praesent at tincidunt purus. In sed risus gravida, ultricies sem nec, dignissim turpis. Sed vestibulum, ipsum vel ultricies posuere, libero lorem varius arcu, at mattis magna velit eu turpis. Nam vel vulputate enim, non lobortis nulla. Nullam venenatis mi nec ornare molestie. Aliquam finibus hendrerit molestie. Aliquam at purus ut nunc pharetra tincidunt. Phasellus consequat augue vel efficitur condimentum. Quisque sem ex, lobortis sed placerat in, auctor quis nibh. Phasellus sit amet sodales ipsum. Nunc tempor finibus viverra. Cras est justo, dignissim.',10000,0,1,'https://picsum.photos/300/300','2020-07-25 22:00:00',NULL,NULL,2,NULL),(25,'Hibernate','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque urna massa, eleifend non justo eu, ornare cursus lectus. Praesent at tincidunt purus. In sed risus gravida, ultricies sem nec, dignissim turpis. Sed vestibulum, ipsum vel ultricies posuere, libero lorem varius arcu, at mattis magna velit eu turpis. Nam vel vulputate enim, non lobortis nulla. Nullam venenatis mi nec ornare molestie. Aliquam finibus hendrerit molestie. Aliquam at purus ut nunc pharetra tincidunt. Phasellus consequat augue vel efficitur condimentum. Quisque sem ex, lobortis sed placerat in, auctor quis nibh. Phasellus sit amet sodales ipsum. Nunc tempor finibus viverra. Cras est justo, dignissim.',10000,0,1,'https://picsum.photos/300/300','2020-07-25 22:00:00',NULL,NULL,2,NULL),(26,'Hibernate','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque urna massa, eleifend non justo eu, ornare cursus lectus. Praesent at tincidunt purus. In sed risus gravida, ultricies sem nec, dignissim turpis. Sed vestibulum, ipsum vel ultricies posuere, libero lorem varius arcu, at mattis magna velit eu turpis. Nam vel vulputate enim, non lobortis nulla. Nullam venenatis mi nec ornare molestie. Aliquam finibus hendrerit molestie. Aliquam at purus ut nunc pharetra tincidunt. Phasellus consequat augue vel efficitur condimentum. Quisque sem ex, lobortis sed placerat in, auctor quis nibh. Phasellus sit amet sodales ipsum. Nunc tempor finibus viverra. Cras est justo, dignissim.',10000,0,1,'https://picsum.photos/300/300','2020-07-25 22:00:00',NULL,NULL,2,NULL),(27,'Hibernate','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque urna massa, eleifend non justo eu, ornare cursus lectus. Praesent at tincidunt purus. In sed risus gravida, ultricies sem nec, dignissim turpis. Sed vestibulum, ipsum vel ultricies posuere, libero lorem varius arcu, at mattis magna velit eu turpis. Nam vel vulputate enim, non lobortis nulla. Nullam venenatis mi nec ornare molestie. Aliquam finibus hendrerit molestie. Aliquam at purus ut nunc pharetra tincidunt. Phasellus consequat augue vel efficitur condimentum. Quisque sem ex, lobortis sed placerat in, auctor quis nibh. Phasellus sit amet sodales ipsum. Nunc tempor finibus viverra. Cras est justo, dignissim.',10000,0,1,'https://picsum.photos/300/300','2020-07-25 22:00:00',NULL,NULL,2,NULL),(28,'Hibernate','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque urna massa, eleifend non justo eu, ornare cursus lectus. Praesent at tincidunt purus. In sed risus gravida, ultricies sem nec, dignissim turpis. Sed vestibulum, ipsum vel ultricies posuere, libero lorem varius arcu, at mattis magna velit eu turpis. Nam vel vulputate enim, non lobortis nulla. Nullam venenatis mi nec ornare molestie. Aliquam finibus hendrerit molestie. Aliquam at purus ut nunc pharetra tincidunt. Phasellus consequat augue vel efficitur condimentum. Quisque sem ex, lobortis sed placerat in, auctor quis nibh. Phasellus sit amet sodales ipsum. Nunc tempor finibus viverra. Cras est justo, dignissim.',10000,0,1,'https://picsum.photos/300/300','2020-07-25 22:00:00',NULL,NULL,2,NULL),(29,'Anita új terméke','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque urna massa, eleifend non justo eu, ornare cursus lectus. Praesent at tincidunt purus. In sed risus gravida, ultricies sem nec, dignissim turpis. Sed vestibulum, ipsum vel ultricies posuere, libero lorem varius arcu, at mattis magna velit eu turpis. Nam vel vulputate enim, non lobortis nulla. Nullam venenatis mi nec ornare molestie. Aliquam finibus hendrerit molestie. Aliquam at purus ut nunc pharetra tincidunt. Phasellus consequat augue vel efficitur condimentum. Quisque sem ex, lobortis sed placerat in, auctor quis nibh. Phasellus sit amet sodales ipsum. Nunc tempor finibus viverra. Cras est justo, dignissim.',1254324,0,1,'https://picsum.photos/300/300','2020-08-02 10:14:00',NULL,NULL,2,NULL),(30,'Új termék','Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.',1000,0,1,'https://picsum.photos/300/300.jpg','2020-08-04 15:14:39',NULL,NULL,2,NULL),(31,'Kiscica','MeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeow',1000,0,0,'https://picsum.photos/300/300.jpg','2020-08-04 15:21:00',NULL,NULL,2,NULL),(32,'Új termék2','MeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeow',1000,0,0,'https://picsum.photos/300/300.jpg','2020-08-04 15:22:08',NULL,NULL,2,NULL);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  `realname` varchar(50) NOT NULL,
  `role` varchar(5) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'misike','eb7b02a4957ad69ae41848c047205c827e8d1aa7','Pekár Mihály','admin','mpekar5@gmail.com'),(2,'anita','eb7b02a4957ad69ae41848c047205c827e8d1aa7','Brachna Anita','admin','anita55@gmail.com'),(3,'valaki','eb7b02a4957ad69ae41848c047205c827e8d1aa7','Mekk Elek','user','kamuemail@kamu.hu'),(5,'valami2','eb7b02a4957ad69ae41848c047205c827e8d1aa7','Pekár Mihály','admin','mpekar55@gmail.com'),(6,'panita','eb7b02a4957ad69ae41848c047205c827e8d1aa7','Brachna Anita','user','anita55@gmail.com'),(9,'andoren','bfad75e02978228b1af4322685e027c31c25f669','Pekár Mihály','user','mpekar55@gmail.com');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usersproduct`
--

DROP TABLE IF EXISTS `usersproduct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usersproduct` (
  `userid` int(11) NOT NULL,
  `productid` int(11) NOT NULL,
  KEY `FK_usersproduct_user_id_idx` (`userid`),
  KEY `FK_usersproduct_product_id_idx` (`productid`),
  CONSTRAINT `FK_usersproduct_product_id` FOREIGN KEY (`productid`) REFERENCES `product` (`id`),
  CONSTRAINT `FK_usersproduct_user_id` FOREIGN KEY (`userid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usersproduct`
--

LOCK TABLES `usersproduct` WRITE;
/*!40000 ALTER TABLE `usersproduct` DISABLE KEYS */;
INSERT INTO `usersproduct` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(2,11),(2,12),(2,13),(1,14),(2,15);
/*!40000 ALTER TABLE `usersproduct` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'feladat'
--
/*!50003 DROP FUNCTION IF EXISTS `addProduct` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `addProduct`(fname varchar(20), fdesc varchar(255),fprice int,fimagepath varchar(255)) RETURNS int(11)
    DETERMINISTIC
BEGIN
declare newid int;
insert into product(name,description,price,imagepath)value(fname,fdesc,fprice,fimagepath);
select ifnull(max(id),0) into newid from product;
RETURN newid;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `AddUser` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `AddUser`(in pusername varchar(20),in ppassword varchar(255),in prealname varchar(50), in prole varchar(5),in pemail varchar(255) )
BEGIN
	insert into user(username,password,realname,role,email)value(pusername,ppassword,prealname,prole,pemail);	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `adduserproduct` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `adduserproduct`(pname varchar(20), pdesc varchar(255),pprice int,pimagepath varchar(255), puserid int)
BEGIN
Declare newproductid int;
set newproductid := addProduct(pname,pdesc,pprice,pimagepath);
insert into usersproduct(userid,productid)value(puserid,newproductid);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `authorizeproduct` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `authorizeproduct`(in productid int)
BEGIN
update product set isaccapted = 1 where id = productid;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `buyproduct` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `buyproduct`(in productid int, in buyerid int)
BEGIN
update product set issold = 1 , buyer = buyerid, sold_date = current_timestamp() where id = productid;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getallproducts` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getallproducts`()
BEGIN
select * from product;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getnotauthorizedproducts` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getnotauthorizedproducts`()
BEGIN
select * from product where isaccapted = 0;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getproductsbyuserid` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getproductsbyuserid`(in userid int)
BEGIN
 select p.* from usersproduct u inner join product p on (p.id = u.productid) where u.userid = userid;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getUsers` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getUsers`()
BEGIN
 select  id, username, realname,email from user where role = "user" ;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `modifyproduct` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `modifyproduct`(pname varchar(20), pdesc varchar(255),pprice int,pimagepath varchar(255), pid int)
BEGIN
update prodcut set name = pname,description = pdesc,price = pprice, imagepath = pimagepath where id = pid;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `modifyuser` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `modifyuser`(in pusername varchar(20),in ppassword varchar(255),in prealname varchar(50), in prole varchar(5),in pemail varchar(255), in uid int)
BEGIN
	update user set username = pusername, password = ppassword, realname = prealname, role = prole, email = pemail where id = uid;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-05 22:01:00
