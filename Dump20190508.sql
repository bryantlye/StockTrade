-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: new_schema
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `account` (
  `Id` char(20) NOT NULL,
  `DateOpened` date DEFAULT NULL,
  `clientId` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `clientId` (`clientId`),
  CONSTRAINT `account_ibfk_1` FOREIGN KEY (`clientId`) REFERENCES `customer` (`Id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `customer` (
  `LastName` char(20) DEFAULT NULL,
  `FirstName` char(20) DEFAULT NULL,
  `Address` char(50) DEFAULT NULL,
  `City` char(20) DEFAULT NULL,
  `State` char(20) DEFAULT NULL,
  `ZipCode` char(20) DEFAULT NULL,
  `Telephone` char(20) DEFAULT NULL,
  `Email` char(40) DEFAULT NULL,
  `Rating` int(11) DEFAULT NULL,
  `CreditCardNumber` char(50) DEFAULT NULL,
  `Id` int(11) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES ('Yang','Shang','123 Success Street','Stony Brook','NY','11790','5166328959','syang@cs.sunysb.edu',1,'234567812345678',111111111),('Du','Victor','456 Fortune Road','Stony Brook','NY','11790','5166324360','vicdu@cs.sunysb.edu',1,'5678123456781234',222222222),('Smith','John','789 Peace Blvd','Los Angeles','CA','93536','3154434321','jsmith@ic.sunysb.edu',1,'2345678923456789',333333333),('Philip','Lewis','135 Knowledge Lane','Stony Brook','NY','11794','5166668888','pml@cs.sunysb.edu',1,'6789234567892345',444444444);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `employee` (
  `LastName` char(20) DEFAULT NULL,
  `FirstName` char(20) DEFAULT NULL,
  `Email` char(40) DEFAULT NULL,
  `Address` char(50) DEFAULT NULL,
  `City` char(20) DEFAULT NULL,
  `State` char(20) DEFAULT NULL,
  `ZipCode` char(20) DEFAULT NULL,
  `Telephone` char(20) DEFAULT NULL,
  `ID` int(11) NOT NULL,
  `SSN` int(11) DEFAULT NULL,
  `StartDate` date DEFAULT NULL,
  `HourlyRate` double DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES ('Warren','David','davidwa@cs.sunysb.edu','456 Sunken Street','Stony Brook','NY','11794','6316329987',11,789123456,'2006-02-02',50),('Smith','David','davidsm@cs.sunysb.edu','123 College Road','Stony Brook','NY','11790','5162152345',22,123456789,'2005-11-01',60);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hasstock`
--

DROP TABLE IF EXISTS `hasstock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hasstock` (
  `clientId` int(11) NOT NULL,
  `StockSymbol` char(20) NOT NULL,
  `NumberShares` int(11) DEFAULT NULL,
  PRIMARY KEY (`clientId`,`StockSymbol`),
  KEY `StockSymbol` (`StockSymbol`),
  CONSTRAINT `hasstock_ibfk_1` FOREIGN KEY (`clientId`) REFERENCES `customer` (`Id`) ON UPDATE CASCADE,
  CONSTRAINT `hasstock_ibfk_2` FOREIGN KEY (`StockSymbol`) REFERENCES `stock` (`StockSymbol`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hasstock`
--

LOCK TABLES `hasstock` WRITE;
/*!40000 ALTER TABLE `hasstock` DISABLE KEYS */;
/*!40000 ALTER TABLE `hasstock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hiddenstop`
--

DROP TABLE IF EXISTS `hiddenstop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hiddenstop` (
  `Id` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hiddenstop`
--

LOCK TABLES `hiddenstop` WRITE;
/*!40000 ALTER TABLE `hiddenstop` DISABLE KEYS */;
/*!40000 ALTER TABLE `hiddenstop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `login` (
  `UserName` char(50) DEFAULT NULL,
  `Password` char(30) DEFAULT NULL,
  `Role` char(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES ('syang@cs.sunysb.edu','1','Customer'),('vicdu@cs.sunysb.edu','2','Customer'),('jsmith@ic.sunysb.edu','3','Customer'),('pml@cs.sunysb.edu','4','Customer'),('davidsm@cs.sunysb.edu','5','customerRepresentative'),('davidwa@cs.sunysb.edu','6','customerRepresentative'),('bob@cs.sunysb.edu','7','Manager');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `market`
--

DROP TABLE IF EXISTS `market`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `market` (
  `Id` int(11) DEFAULT NULL,
  `type` char(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `market`
--

LOCK TABLES `market` WRITE;
/*!40000 ALTER TABLE `market` DISABLE KEYS */;
/*!40000 ALTER TABLE `market` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marketclose`
--

DROP TABLE IF EXISTS `marketclose`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `marketclose` (
  `Id` int(11) DEFAULT NULL,
  `type` char(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marketclose`
--

LOCK TABLES `marketclose` WRITE;
/*!40000 ALTER TABLE `marketclose` DISABLE KEYS */;
/*!40000 ALTER TABLE `marketclose` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderr`
--

DROP TABLE IF EXISTS `orderr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `orderr` (
  `NumShares` int(11) DEFAULT NULL,
  `PricePerShare` double DEFAULT NULL,
  `Id` int(11) NOT NULL,
  `DateTime` datetime DEFAULT NULL,
  `PriceType` char(20) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderr`
--

LOCK TABLES `orderr` WRITE;
/*!40000 ALTER TABLE `orderr` DISABLE KEYS */;
/*!40000 ALTER TABLE `orderr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pricehistory`
--

DROP TABLE IF EXISTS `pricehistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `pricehistory` (
  `StockSymbol` char(20) NOT NULL,
  `PricePerShare` double DEFAULT NULL,
  `DateTime` datetime NOT NULL,
  PRIMARY KEY (`DateTime`,`StockSymbol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pricehistory`
--

LOCK TABLES `pricehistory` WRITE;
/*!40000 ALTER TABLE `pricehistory` DISABLE KEYS */;
INSERT INTO `pricehistory` VALUES ('F',9,'2018-11-02 00:00:00'),('GM',34.23,'2018-11-02 00:00:00'),('IBM',91.41,'2018-11-02 00:00:00');
/*!40000 ALTER TABLE `pricehistory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock`
--

DROP TABLE IF EXISTS `stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `stock` (
  `StockSymbol` char(20) NOT NULL,
  `StockName` char(20) NOT NULL,
  `StockType` char(20) NOT NULL,
  `PricePerShare` double DEFAULT NULL,
  `NumberShares` int(11) DEFAULT NULL,
  PRIMARY KEY (`StockSymbol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock`
--

LOCK TABLES `stock` WRITE;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
INSERT INTO `stock` VALUES ('F','Ford','automotive',9,750),('GM','General Motors','automotive',34.23,1000),('IBM','IBM','computer',91.41,500);
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `stocklist`
--

DROP TABLE IF EXISTS `stocklist`;
/*!50001 DROP VIEW IF EXISTS `stocklist`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `stocklist` AS SELECT 
 1 AS `StockId`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `trade`
--

DROP TABLE IF EXISTS `trade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `trade` (
  `AccountId` char(20) NOT NULL,
  `BrokerId` int(11) DEFAULT NULL,
  `OrderId` int(11) NOT NULL,
  `StockId` char(20) NOT NULL,
  PRIMARY KEY (`AccountId`,`OrderId`,`StockId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trade`
--

LOCK TABLES `trade` WRITE;
/*!40000 ALTER TABLE `trade` DISABLE KEYS */;
/*!40000 ALTER TABLE `trade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trailingstop`
--

DROP TABLE IF EXISTS `trailingstop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `trailingstop` (
  `Id` int(11) DEFAULT NULL,
  `Percentage` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trailingstop`
--

LOCK TABLES `trailingstop` WRITE;
/*!40000 ALTER TABLE `trailingstop` DISABLE KEYS */;
/*!40000 ALTER TABLE `trailingstop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `stocklist`
--

/*!50001 DROP VIEW IF EXISTS `stocklist`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `stocklist` AS select `t`.`StockId` AS `StockId` from (`trade` `t` join `stock` `s`) where ((`t`.`AccountId` = '111111111') and (`t`.`StockId` = `s`.`StockName`)) group by `t`.`StockId` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-08  1:15:08
