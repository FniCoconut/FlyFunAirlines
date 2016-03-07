CREATE DATABASE  IF NOT EXISTS `flyfunairlines` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `flyfunairlines`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: flyfunairlines
-- ------------------------------------------------------
-- Server version	5.6.22-log

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
-- Table structure for table `air_connect`
--

DROP TABLE IF EXISTS `air_connect`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `air_connect` (
  `ID_CONNECT` int(3) NOT NULL AUTO_INCREMENT,
  `T_ORIGEN` int(3) NOT NULL,
  `T_DESTINO` int(3) NOT NULL,
  PRIMARY KEY (`ID_CONNECT`),
  KEY `FK_ORIGEN_idx` (`T_ORIGEN`),
  KEY `FK_DESTINO_idx` (`T_DESTINO`),
  CONSTRAINT `FK_DESTINO` FOREIGN KEY (`T_DESTINO`) REFERENCES `airport` (`ID_AIRPORT`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_ORIGEN` FOREIGN KEY (`T_ORIGEN`) REFERENCES `airport` (`ID_AIRPORT`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `air_connect`
--

LOCK TABLES `air_connect` WRITE;
/*!40000 ALTER TABLE `air_connect` DISABLE KEYS */;
INSERT INTO `air_connect` VALUES (1,1,3),(2,3,1),(3,1,2),(4,2,1);
/*!40000 ALTER TABLE `air_connect` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `airport`
--

DROP TABLE IF EXISTS `airport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `airport` (
  `ID_AIRPORT` int(3) NOT NULL AUTO_INCREMENT,
  `IATA` varchar(3) NOT NULL,
  `NOMBRE` varchar(140) NOT NULL,
  `TERMINAL` varchar(3) DEFAULT NULL,
  `CIUDAD` varchar(45) NOT NULL,
  `PAIS` varchar(45) NOT NULL,
  PRIMARY KEY (`ID_AIRPORT`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airport`
--

LOCK TABLES `airport` WRITE;
/*!40000 ALTER TABLE `airport` DISABLE KEYS */;
INSERT INTO `airport` VALUES (1,'MAD','Madrid Adolfo Suarez - Barajas','T1','Madrid','España'),(2,'CDG','Aeropuerto de París-Charles de Gaulle','T2B','Paris','Francia'),(3,'GVA','Aeropuerto Internacional de Ginebra',NULL,'Ginebra','Suiza');
/*!40000 ALTER TABLE `airport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `booking` (
  `ID_BK` int(5) NOT NULL AUTO_INCREMENT,
  `CLIENTE` int(4) NOT NULL,
  `BK_CODE` varchar(10) NOT NULL,
  `IDA` set('0','1') DEFAULT NULL,
  `VUELTA` set('0','1') DEFAULT NULL,
  `PRECIO` float NOT NULL,
  PRIMARY KEY (`ID_BK`),
  KEY `FK_CLIENTE_idx` (`CLIENTE`),
  KEY `FK_BKCODE_idx` (`BK_CODE`),
  KEY `FK_BKCODE_OCCUP_idx` (`BK_CODE`),
  CONSTRAINT `FK_BKCODE_OCCUP` FOREIGN KEY (`BK_CODE`) REFERENCES `occupation` (`BOOKING_CODE`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_CLIENTE` FOREIGN KEY (`CLIENTE`) REFERENCES `client` (`ID_CLIENT`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (1,3,'epgt4ehw','0','0',108.97);
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `card`
--

DROP TABLE IF EXISTS `card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `card` (
  `ID_CARD` int(4) NOT NULL AUTO_INCREMENT,
  `CLIENTE` int(4) DEFAULT NULL,
  `NUMERO` tinyblob,
  `MESCAD` int(2) DEFAULT NULL,
  `ANOCAD` int(4) DEFAULT NULL,
  PRIMARY KEY (`ID_CARD`),
  KEY `FK_CLIENTE_TJT_idx` (`CLIENTE`),
  CONSTRAINT `FK_CLIENTE_TJT` FOREIGN KEY (`CLIENTE`) REFERENCES `client` (`ID_CLIENT`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `card`
--

LOCK TABLES `card` WRITE;
/*!40000 ALTER TABLE `card` DISABLE KEYS */;
/*!40000 ALTER TABLE `card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client` (
  `ID_CLIENT` int(4) NOT NULL AUTO_INCREMENT,
  `NIF` varchar(10) NOT NULL,
  `PASSWORD` blob,
  `TRATAMIENTO` varchar(5) DEFAULT NULL,
  `NOMBRE` varchar(45) DEFAULT NULL,
  `APELLIDOS` varchar(100) DEFAULT NULL,
  `TELEFONO` varchar(9) DEFAULT NULL,
  `EMAIL` varchar(60) DEFAULT NULL,
  `DIRECCION` varchar(140) DEFAULT NULL,
  `TARJETA` text,
  PRIMARY KEY (`ID_CLIENT`),
  UNIQUE KEY `EMAIL_UNIQUE` (`EMAIL`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (3,'47099673C','�@[P��\"�a�}|E��','Doña','Estefanía','Moreno Alejo','605037188','fanni.coconut@gmail.com','Calle Doctor Ferran 42 5°',NULL);
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flight`
--

DROP TABLE IF EXISTS `flight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flight` (
  `ID_FLY` int(4) NOT NULL AUTO_INCREMENT,
  `CONNECTION` int(3) NOT NULL,
  `N_VUELO` varchar(5) NOT NULL,
  `F_SALIDA` date NOT NULL,
  `H_SALIDA` time NOT NULL,
  `FRECIO` float NOT NULL,
  `AVION` int(2) DEFAULT NULL,
  `PLAZAS` int(2) DEFAULT NULL,
  PRIMARY KEY (`ID_FLY`),
  KEY `FK_CONNECTION_idx` (`CONNECTION`),
  CONSTRAINT `FK_CONNECTION` FOREIGN KEY (`CONNECTION`) REFERENCES `air_connect` (`ID_CONNECT`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flight`
--

LOCK TABLES `flight` WRITE;
/*!40000 ALTER TABLE `flight` DISABLE KEYS */;
INSERT INTO `flight` VALUES (1,1,'FF000','2016-03-04','17:15:00',35,1,8),(2,1,'FF001','2016-03-09','12:40:00',40,1,8),(3,1,'FF002','2016-03-14','13:40:00',35,1,8),(4,2,'FF003','2016-03-08','17:15:00',35,1,8),(5,2,'FF004','2016-03-13','12:40:00',40,1,8),(6,2,'FF005','2016-03-18','13:40:00',35,1,8),(7,3,'FF006','2016-03-05','10:00:00',25,2,8),(8,3,'FF007','2016-03-10','09:00:00',20,2,8),(9,3,'FF008','2016-03-15','09:00:00',20,2,8),(10,4,'FF009','2016-03-10','21:00:00',30,2,8),(11,4,'FF010','2016-03-15','21:00:00',30,2,8),(12,4,'FF011','2016-03-19','19:30:00',25,2,8);
/*!40000 ALTER TABLE `flight` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `occupation`
--

DROP TABLE IF EXISTS `occupation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `occupation` (
  `ID_OCP` int(4) NOT NULL AUTO_INCREMENT,
  `BOOKING_CODE` varchar(10) NOT NULL,
  `VUELO` int(3) NOT NULL,
  `PASAJERO` int(4) NOT NULL,
  `ASIENTO` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`ID_OCP`),
  KEY `FK_VUELO_idx` (`VUELO`),
  KEY `FK_PASAJERO_idx` (`PASAJERO`),
  KEY `BKCODE` (`BOOKING_CODE`),
  CONSTRAINT `FK_PASAJERO` FOREIGN KEY (`PASAJERO`) REFERENCES `passenger` (`ID_PASSENGER`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_VUELO` FOREIGN KEY (`VUELO`) REFERENCES `flight` (`ID_FLY`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `occupation`
--

LOCK TABLES `occupation` WRITE;
/*!40000 ALTER TABLE `occupation` DISABLE KEYS */;
INSERT INTO `occupation` VALUES (43,'epgt4ehw',7,65,'1i'),(44,'epgt4ehw',7,66,'2i'),(45,'epgt4ehw',11,65,'1i'),(46,'epgt4ehw',11,66,'2i');
/*!40000 ALTER TABLE `occupation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `occupation_service`
--

DROP TABLE IF EXISTS `occupation_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `occupation_service` (
  `ID_OS` int(4) NOT NULL AUTO_INCREMENT,
  `SERVICIO` int(2) NOT NULL,
  `OCUPACION` int(4) NOT NULL,
  PRIMARY KEY (`ID_OS`),
  KEY `FK_SERVICIO_idx` (`SERVICIO`),
  KEY `FK_OCCUPATION_idx` (`OCUPACION`),
  CONSTRAINT `FK_OCCUPATION` FOREIGN KEY (`OCUPACION`) REFERENCES `occupation` (`ID_OCP`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_SERVICIO` FOREIGN KEY (`SERVICIO`) REFERENCES `service` (`ID_SERVICE`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `occupation_service`
--

LOCK TABLES `occupation_service` WRITE;
/*!40000 ALTER TABLE `occupation_service` DISABLE KEYS */;
INSERT INTO `occupation_service` VALUES (77,1,43),(78,4,43),(79,1,44),(80,3,44),(81,1,45),(82,4,45),(83,1,46),(84,3,46);
/*!40000 ALTER TABLE `occupation_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `passenger`
--

DROP TABLE IF EXISTS `passenger`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `passenger` (
  `ID_PASSENGER` int(4) NOT NULL AUTO_INCREMENT,
  `NIF` varchar(10) NOT NULL,
  `FECHA_CAD` date DEFAULT NULL,
  `PREFIJO` varchar(5) DEFAULT NULL,
  `NOMBRE` varchar(30) NOT NULL,
  `APELLIDOS` varchar(60) NOT NULL,
  `NACIONALIDAD` varchar(45) DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `TIPO` varchar(10) NOT NULL,
  `FECHA_NAC` date DEFAULT NULL,
  `CARGO` int(4) DEFAULT NULL,
  PRIMARY KEY (`ID_PASSENGER`),
  KEY `FK_CARGO_idx` (`CARGO`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passenger`
--

LOCK TABLES `passenger` WRITE;
/*!40000 ALTER TABLE `passenger` DISABLE KEYS */;
INSERT INTO `passenger` VALUES (1,'74502589D',NULL,'Don','Silvestre Sanchez','Cristian',NULL,'css@gmail.com','adulto',NULL,0),(2,'74502589D',NULL,'Don','Silvestre Sanchez','Cristian',NULL,'css@gmail.com','adulto',NULL,0),(3,'74502589D',NULL,'Don','Silvestre Sanchez','Cristian',NULL,'css@gmail.com','adulto',NULL,0),(4,'74502589D',NULL,'Don','Silvestre Sanchez','Cristian',NULL,'css@gmail.com','adulto',NULL,0),(62,'74502589D',NULL,'Don','Silvestre Sanchez','Cristian',NULL,'css@gmail.com','adulto',NULL,0),(63,'47099673C',NULL,'Doña','Moreno Alejo','Estefania',NULL,'fanni.coconut@gmail.com','adulto',NULL,0),(65,'74502589D',NULL,'Don','Silvestre Sanchez','Cristian',NULL,'css@gmail.com','adulto',NULL,0),(66,'47099673C',NULL,'Doña','Moreno Alejo','Estefania',NULL,'fanni.coconut@gmail.com','adulto',NULL,0),(67,'123',NULL,NULL,'Flubber','Blubli',NULL,NULL,'bebe',NULL,65);
/*!40000 ALTER TABLE `passenger` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `service` (
  `ID_SERVICE` int(2) NOT NULL,
  `DENOMINACION` varchar(45) NOT NULL,
  `DESCRIPCION` text NOT NULL,
  `PRECIO` float NOT NULL,
  PRIMARY KEY (`ID_SERVICE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
INSERT INTO `service` VALUES (1,'Asiento','Selección de un asiento normal.',3.99),(2,'AsientoPremium','Selección de un asiento extra grande, incluye un snak a consumir durante el vuelo.',14.99),(3,'Equipaje','Ahorre en la facturación de su equipage (hsta 25kg) al tramitarlo en la web.',35),(4,'Infante','Servicio adicional obligatorio al viajar con un bebé, incluye cinturón especial para bebés hasta 2 años y acceso a cambiador.',10.99),(5,'SeguroViaje','Devolución íntegra de la factura en caso de perdida de equipaje o cancelaciones de vuelo por causa de la compañía. (No quedan reflejadas las causas atmosféricas ni las redirecciones).',24.99);
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-02-18 20:40:26
