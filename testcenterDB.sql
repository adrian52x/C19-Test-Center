-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: den1.mysql3.gear.host    Database: testcenterdb
-- ------------------------------------------------------
-- Server version	5.7.26-log

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `addressId` int(11) NOT NULL AUTO_INCREMENT,
  `streetName` varchar(45) NOT NULL,
  `streetNumber` int(11) NOT NULL,
  `postCode` int(11) NOT NULL,
  `city` varchar(45) NOT NULL,
  PRIMARY KEY (`addressId`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'Vognporten',14,2620,'Albertslund'),(2,'Eslevej',91,2300,'Orestad'),(3,'Osterbrogade',42,2300,'Copenhagen'),(4,'Vestervej',2,3400,'Hillerod'),(6,'Bispebjerg Bakke',23,2400,' København'),(7,'Italiensvej ',1,2300,'København'),(8,'Nordmarks Alle ',1,2620,'Albertslund'),(9,'Nordre Fasanvej ',57,2000,'Frederiksberg'),(10,'Frederiksberg Allé ',104,1820,'Frederiksberg'),(11,'Halmtorvet ',11,1700,'København'),(12,'Ragnhildgade ',1,2100,'København'),(13,'Århusgade ',88,2100,'København'),(14,'Lindorffs Alle ',5,2900,'Hellerup');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appointment`
--

DROP TABLE IF EXISTS `appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appointment` (
  `apptID` int(11) NOT NULL AUTO_INCREMENT,
  `testCenterId` int(11) NOT NULL,
  `cprOfUser` int(11) NOT NULL,
  `time` time NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`apptID`),
  KEY `center_id_idx` (`testCenterId`),
  KEY `fk_cprOfUser_idx` (`cprOfUser`),
  CONSTRAINT `center_id` FOREIGN KEY (`testCenterId`) REFERENCES `test_center` (`testCenterId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cprOfUser` FOREIGN KEY (`cprOfUser`) REFERENCES `user` (`cpr`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment`
--

LOCK TABLES `appointment` WRITE;
/*!40000 ALTER TABLE `appointment` DISABLE KEYS */;
INSERT INTO `appointment` VALUES (3,5,202020,'15:00:00','2021-06-22'),(53,4,404040,'11:00:00','2021-06-28'),(54,5,300800,'15:00:00','2021-06-18'),(59,1,699000,'09:00:00','2021-06-09');
/*!40000 ALTER TABLE `appointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_role`
--

DROP TABLE IF EXISTS `auth_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auth_role` (
  `auth_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(45) NOT NULL,
  PRIMARY KEY (`auth_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_role`
--

LOCK TABLES `auth_role` WRITE;
/*!40000 ALTER TABLE `auth_role` DISABLE KEYS */;
INSERT INTO `auth_role` VALUES (1,'ADMIN'),(2,'SECRETARY'),(3,'USER');
/*!40000 ALTER TABLE `auth_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_user_role`
--

DROP TABLE IF EXISTS `auth_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auth_user_role` (
  `cpr` int(11) NOT NULL,
  `auth_role_id` int(11) NOT NULL DEFAULT '3',
  PRIMARY KEY (`cpr`,`auth_role_id`),
  KEY `FK_cpr_idx` (`cpr`),
  KEY `fk_auth_role-id_idx` (`auth_role_id`),
  CONSTRAINT `FK_cpr` FOREIGN KEY (`cpr`) REFERENCES `user` (`cpr`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_auth_role-id` FOREIGN KEY (`auth_role_id`) REFERENCES `auth_role` (`auth_role_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_user_role`
--

LOCK TABLES `auth_user_role` WRITE;
/*!40000 ALTER TABLE `auth_user_role` DISABLE KEYS */;
INSERT INTO `auth_user_role` VALUES (101010,2),(111222,3),(120290,3),(202020,3),(250898,1),(300800,3),(303030,3),(404040,3),(444555,3),(699000,3),(778998,3);
/*!40000 ALTER TABLE `auth_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_center`
--

DROP TABLE IF EXISTS `test_center`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `test_center` (
  `testCenterId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `center_addressid` int(11) NOT NULL,
  PRIMARY KEY (`testCenterId`),
  KEY `fk_addressid_idx` (`center_addressid`),
  CONSTRAINT `fk_addressid` FOREIGN KEY (`center_addressid`) REFERENCES `address` (`addressId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_center`
--

LOCK TABLES `test_center` WRITE;
/*!40000 ALTER TABLE `test_center` DISABLE KEYS */;
INSERT INTO `test_center` VALUES (1,'Albertslund Test Center',8),(2,'Hillerod Test Center',4),(3,'Bispebjerg Test Center',6),(4,'Amager Test Center',7),(5,'Frederiksberg Test Center',9);
/*!40000 ALTER TABLE `test_center` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `cpr` int(11) NOT NULL,
  `firstName` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `addressid` int(11) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `dateOfBirth` date NOT NULL,
  `status` varchar(45) NOT NULL DEFAULT '1',
  PRIMARY KEY (`cpr`),
  KEY `addressid_idx` (`addressid`),
  CONSTRAINT `addressid` FOREIGN KEY (`addressid`) REFERENCES `address` (`addressId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (101010,'SECRETARY','ROLE',2,'email@gmail.com','123','1990-01-01','1'),(111222,'Adrian','Enachi',1,'','123','2021-04-20','1'),(120290,'Evan','Shred',3,'asdf@gmail.com','123','1990-02-12','1'),(202020,'John','text',6,'email@gmail.com','123','1992-12-15','1'),(250898,'ADMIN','Name',1,'aaa52xaw@gmail.com','123','1998-08-25','1'),(300800,'Fred','Greed',3,'fredy@mail.com','123','2000-08-30','1'),(303030,'Mary','text',7,'email@gmail.com','111','1995-09-08','1'),(404040,'Gary','Musk',9,'asd2@gmail.com','123','1996-03-29','1'),(444555,'Adrian','ttryrt',1,'adrian.nk52x@gmail.com','123','2021-03-16','1'),(699000,'fed','rik',10,'adrian.nk52x@gmail.com','123','2021-05-24','1'),(778998,'Alan','jak',3,'adrian.nk52x@gmail.com','123','2021-02-08','1');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-02  2:13:18
