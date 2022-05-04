CREATE DATABASE  IF NOT EXISTS `order_management` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `order_management`;
-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: order_management
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `city` varchar(45) NOT NULL,
  `country` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`id`, `name`, `email`, `address`, `city`, `country`) VALUES (1,'Doru Pop','dorupop@email.com','str. Transilvaniei, nr.2','Bucuresti','Romania');
INSERT INTO `client` (`id`, `name`, `email`, `address`, `city`, `country`) VALUES (2,'Adina Stan','adinastan@email.com','str. Republicii, nr.40','Cluj-Napoca','Romania');
INSERT INTO `client` (`id`, `name`, `email`, `address`, `city`, `country`) VALUES (3,'John Smith','jsmith@email.com','Park Street, nr. 16','London','Regatul Unit');
INSERT INTO `client` (`id`, `name`, `email`, `address`, `city`, `country`) VALUES (4,'Hans Weiss','hweiss@email.com','Eulengasse, nr.3','Frankfurt am Main','Germania');
INSERT INTO `client` (`id`, `name`, `email`, `address`, `city`, `country`) VALUES (25,'Ion Pop','popion@email.com','str. Ostrovului, bl.3, ap 2','Satu Mare','Romania');

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `id` int NOT NULL AUTO_INCREMENT,
  `clientId` int NOT NULL,
  `productId` int NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `quantity` int NOT NULL,
  `orderDate` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `clientId_idx` (`clientId`),
  KEY `productId_idx` (`productId`),
  CONSTRAINT `clientId` FOREIGN KEY (`clientId`) REFERENCES `client` (`id`),
  CONSTRAINT `productId` FOREIGN KEY (`productId`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

INSERT INTO `order` (`id`, `clientId`, `productId`, `price`, `quantity`, `orderDate`) VALUES (15,1,1,4999.99,1,'2021-04-21');
INSERT INTO `order` (`id`, `clientId`, `productId`, `price`, `quantity`, `orderDate`) VALUES (16,4,5,14599.98,2,'2021-04-21');
INSERT INTO `order` (`id`, `clientId`, `productId`, `price`, `quantity`, `orderDate`) VALUES (17,3,2,3299.90,10,'2021-04-21');
INSERT INTO `order` (`id`, `clientId`, `productId`, `price`, `quantity`, `orderDate`) VALUES (18,4,3,4520.00,2,'2021-04-21');

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `stock` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `name`, `price`, `stock`) VALUES (1,'Playstation 5',4999.99,49);
INSERT INTO `product` (`id`, `name`, `price`, `stock`) VALUES (2,'Gaming Keyboard',329.99,160);
INSERT INTO `product` (`id`, `name`, `price`, `stock`) VALUES (3,'Smartphone Samsung Galaxy A52',2260.00,298);
INSERT INTO `product` (`id`, `name`, `price`, `stock`) VALUES (4,'LED TV Starlight',599.99,0);
INSERT INTO `product` (`id`, `name`, `price`, `stock`) VALUES (5,'Gaming Laptop ',7299.99,3);
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-21 13:36:07
