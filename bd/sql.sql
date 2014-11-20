CREATE DATABASE  IF NOT EXISTS `soltrux_demo` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `soltrux_demo`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: soltrux_demo
-- ------------------------------------------------------
-- Server version	5.6.20

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
-- Table structure for table `pregunta_usuario`
--

DROP TABLE IF EXISTS `pregunta_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pregunta_usuario` (
  `id_pregunta_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `pregunta` varchar(200) DEFAULT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  `estado` tinyint(4) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  PRIMARY KEY (`id_pregunta_usuario`),
  KEY `fk_usuario_pregunta_usuario_idx` (`id_usuario`),
  CONSTRAINT `fk_usuario_pregunta_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pregunta_usuario`
--

LOCK TABLES `pregunta_usuario` WRITE;
/*!40000 ALTER TABLE `pregunta_usuario` DISABLE KEYS */;
INSERT INTO `pregunta_usuario` VALUES (7,'XD',9,1,'2014-11-20 11:19:42');
/*!40000 ALTER TABLE `pregunta_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ubicacion_usuario`
--

DROP TABLE IF EXISTS `ubicacion_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ubicacion_usuario` (
  `id_ubicacion_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `longitud` double DEFAULT NULL,
  `latitud` double DEFAULT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_ubicacion_usuario`),
  KEY `fk_usuario_ubicacion_usuario_idx` (`id_usuario`),
  CONSTRAINT `fk_usuario_ubicacion_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ubicacion_usuario`
--

LOCK TABLES `ubicacion_usuario` WRITE;
/*!40000 ALTER TABLE `ubicacion_usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `ubicacion_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) DEFAULT NULL,
  `gps` bit(1) DEFAULT NULL,
  `cerro` bit(1) DEFAULT NULL,
  `telefono` bit(1) DEFAULT NULL,
  `numero` varchar(45) DEFAULT NULL,
  `fecha_actualizacion` datetime DEFAULT NULL,
  `fecha_registro` datetime DEFAULT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (9,'ederharo@gmail.com','\0','\0','\0','','2014-11-20 11:20:56','2014-11-20 11:18:19');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-11-20 11:24:07
