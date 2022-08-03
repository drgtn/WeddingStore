-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: weddingdb
-- ------------------------------------------------------
-- Server version	5.6.25-log

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `password` varchar(45) NOT NULL,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'drgtn.a@gmail.com','183d334b5a585f616e85d3be3e8c1c37','Andrei','D');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categorii`
--

DROP TABLE IF EXISTS `categorii`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categorii` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `denumire` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorii`
--

LOCK TABLES `categorii` WRITE;
/*!40000 ALTER TABLE `categorii` DISABLE KEYS */;
INSERT INTO `categorii` VALUES (1,'Categorie 1'),(2,'Categorie 2'),(3,'Categorie 3'),(4,'Categorie 4'),(5,'Categorie 5');
/*!40000 ALTER TABLE `categorii` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imaginiprodus`
--

DROP TABLE IF EXISTS `imaginiprodus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imaginiprodus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `numedir` varchar(45) DEFAULT NULL,
  `numefile` varchar(45) DEFAULT NULL,
  `idProdus` int(11) NOT NULL,
  `imagedata` mediumblob,
  PRIMARY KEY (`id`),
  KEY `imaginiprodus_idx` (`idProdus`),
  CONSTRAINT `imaginiprodus` FOREIGN KEY (`idProdus`) REFERENCES `produse` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imaginiprodus`
--

LOCK TABLES `imaginiprodus` WRITE;
/*!40000 ALTER TABLE `imaginiprodus` DISABLE KEYS */;
/*!40000 ALTER TABLE `imaginiprodus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produse`
--

DROP TABLE IF EXISTS `produse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produse` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `denumire` tinytext NOT NULL,
  `descriere` mediumtext,
  `pret` int(6) NOT NULL,
  `telefon` int(11) NOT NULL,
  `regiunea` varchar(45) NOT NULL,
  `idCat` int(11) NOT NULL,
  `idUser` int(11) NOT NULL,
  `imagineProdus` mediumblob,
  `numeDir` varchar(45) DEFAULT NULL,
  `numeFile` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `categorii_idx` (`idCat`),
  KEY `users_idx` (`idUser`),
  CONSTRAINT `categorii` FOREIGN KEY (`idCat`) REFERENCES `categorii` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `users` FOREIGN KEY (`idUser`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produse`
--

LOCK TABLES `produse` WRITE;
/*!40000 ALTER TABLE `produse` DISABLE KEYS */;
INSERT INTO `produse` VALUES (60,'Anunt 1','Anunt 1 descriere',1300,68065614,'Ialoveni',1,1,NULL,'','imaginiproduse'),(61,'Anunt 2','Anunt 2 descriere',1300,68065614,'Straseni',2,1,NULL,'','imaginiproduse'),(62,'Anunt 3','Anunt 3 descriere',1600,68065614,'Ialoveno',3,1,NULL,'','imaginiproduse'),(63,'Anunt 4','Anunt 4 descriere',6000,68198030,'Chisinau',4,1,NULL,'','imaginiproduse'),(64,'Anunt 5','Anunt 5 descriere',4000,68334455,'Bardar',5,1,NULL,'','imaginiproduse');
/*!40000 ALTER TABLE `produse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `password` varchar(45) NOT NULL,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'drgtn.a@gmail.com','183d334b5a585f616e85d3be3e8c1c37','Andrei','Dragutan'),(2,'VaneaBosu@gmail.com','183d334b5a585f616e85d3be3e8c1c37','Vanea','Bosu');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-10-03 15:48:19
