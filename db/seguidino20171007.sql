CREATE DATABASE  IF NOT EXISTS `seguidino` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `seguidino`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: seguidino
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `sg_activity`
--

DROP TABLE IF EXISTS `sg_activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sg_activity` (
  `id_activity` int(11) NOT NULL AUTO_INCREMENT,
  `id_project` int(11) NOT NULL,
  `id_issue` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `action` varchar(45) COLLATE latin1_spanish_ci NOT NULL,
  `detail` text COLLATE latin1_spanish_ci,
  `creation` datetime NOT NULL,
  PRIMARY KEY (`id_activity`),
  KEY `project_fk_0408_idx` (`id_project`),
  KEY `issue_fk_0408_idx` (`id_issue`),
  KEY `user_fk_0408_idx` (`id_user`),
  CONSTRAINT `issue_fk_0408` FOREIGN KEY (`id_issue`) REFERENCES `sg_issues` (`id_issue`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `project_fk_0408` FOREIGN KEY (`id_project`) REFERENCES `sg_projects` (`id_project`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_fk_0408` FOREIGN KEY (`id_user`) REFERENCES `sg_users` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=151 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sg_activity`
--

LOCK TABLES `sg_activity` WRITE;
/*!40000 ALTER TABLE `sg_activity` DISABLE KEYS */;
INSERT INTO `sg_activity` VALUES (1,1,1,1,' created ',NULL,'2017-03-12 00:36:48'),(2,2,2,1,' created ',NULL,'2017-03-12 00:39:17'),(3,2,4,1,' created ',NULL,'2017-03-20 00:21:38'),(4,2,5,1,' created ',NULL,'2017-03-20 00:42:36'),(5,2,6,1,' created ',NULL,'2017-03-20 21:20:43'),(6,2,7,1,' created ',NULL,'2017-03-20 21:22:55'),(7,2,8,1,' created ',NULL,'2017-03-20 21:30:42'),(8,2,9,1,' created ',NULL,'2017-03-20 21:34:10'),(9,2,10,1,' created ',NULL,'2017-03-20 21:40:08'),(10,2,11,1,' created ',NULL,'2017-03-26 17:27:38'),(11,2,12,1,' created ',NULL,'2017-03-31 21:40:01'),(12,2,13,1,' created ',NULL,'2017-03-31 21:41:31'),(13,2,14,1,' created ',NULL,'2017-03-31 22:05:50'),(14,2,15,1,' created ',NULL,'2017-03-31 23:11:47'),(15,2,16,1,' created ',NULL,'2017-04-01 23:11:14'),(16,2,17,1,' created ',NULL,'2017-04-03 00:14:19'),(17,2,18,1,' created ',NULL,'2017-04-03 00:20:06'),(18,2,19,1,' created ',NULL,'2017-04-05 01:24:34'),(19,2,20,1,' created ',NULL,'2017-04-05 01:26:25'),(20,2,21,1,' created ',NULL,'2017-04-05 01:27:46'),(21,2,22,1,' created ',NULL,'2017-04-05 01:29:07'),(22,2,23,1,' created ',NULL,'2017-04-05 01:31:41'),(23,2,24,1,' created ',NULL,'2017-04-05 01:32:31'),(24,2,25,1,' created ',NULL,'2017-04-05 01:33:15'),(25,2,26,1,' created ',NULL,'2017-04-05 01:34:09'),(26,2,27,1,' created ',NULL,'2017-04-05 01:35:02'),(27,2,28,1,' created ',NULL,'2017-04-05 01:35:55'),(28,2,29,1,' created ',NULL,'2017-04-05 01:36:59'),(32,2,7,1,' commented on ','Using basic colors: blue, yellow and red','2017-03-26 20:16:45'),(33,2,7,1,' commented on ','Using basic colors: blue, yellow and red','2017-03-26 20:18:47'),(34,2,7,1,' commented on ','Using basic colors: blue, yellow and red','2017-03-26 20:22:04'),(35,2,7,1,' commented on ','Using basic colors: blue, yellow and red','2017-03-26 20:23:39'),(36,2,7,1,' commented on ','Otro comment','2017-03-26 20:38:21'),(37,2,7,1,' commented on ','comment','2017-03-26 20:49:24'),(38,2,7,1,' commented on ','otro','2017-03-26 20:50:21'),(39,2,7,1,' commented on ','otro comment','2017-03-26 20:51:15'),(40,2,7,1,' commented on ','otro comment formato','2017-03-26 20:55:50'),(41,2,15,1,' commented on ','Se genera el modelo IssueTransition para persistir los cambios de status','2017-03-31 23:45:23'),(42,2,4,1,' commented on ','Se crea una relacion muchos a muchos para poder asociar varios componentes a un issue, se mapea a entities y se modifican las pantallas de registro y consulta de issues','2017-04-01 23:23:15'),(43,2,4,1,' commented on ','Se verifica que ya permite agregar varios componentes a un issue','2017-04-01 23:36:25'),(44,2,7,1,' commented on ','Se incluyen los colores, rojo, verde, azul y amarillo en los iconos','2017-04-02 00:54:48'),(45,2,7,1,' commented on ','Se encuentran correctos los colores de los iconos','2017-04-02 00:55:12'),(46,2,13,1,' commented on ','Se ajusta el estilo para que se muestre de forma correcta','2017-04-02 00:59:10'),(47,2,13,1,' commented on ','Se corrige el issue','2017-04-02 00:59:28'),(48,2,11,1,' commented on ','Con un estilo, se raya por la mitad la clave de un issue cuando ya se encuentra resuelto','2017-04-03 00:16:21'),(49,2,11,1,' commented on ','Se observa que ya se marcan las claves de los issues resueltos','2017-04-03 00:17:11'),(50,2,14,1,' commented on ','Ya se versiono el proyecto seguidino y se sincronizo con el workspace local','2017-04-03 00:21:27'),(51,2,14,1,' commented on ','Ya se encuentra versionado','2017-04-03 00:21:47'),(63,2,15,1,' changed status to Open',NULL,'2017-03-31 23:11:47'),(64,2,15,1,' changed status to In Progress',NULL,'2017-03-31 23:12:22'),(65,2,15,1,' changed status to Resolved',NULL,'2017-03-31 23:45:23'),(66,2,4,1,' changed status to In Progress',NULL,'2017-04-01 15:27:13'),(67,2,16,1,' changed status to Open',NULL,'2017-04-01 23:11:14'),(68,2,4,1,' changed status to Resolved',NULL,'2017-04-01 23:23:15'),(69,2,4,1,' changed status to Closed',NULL,'2017-04-01 23:36:25'),(70,2,6,1,' changed status to In Progress',NULL,'2017-04-02 00:49:44'),(71,2,6,1,' changed status to Open',NULL,'2017-04-02 00:49:49'),(72,2,6,1,' changed status to In Progress',NULL,'2017-04-02 00:49:52'),(73,2,6,1,' changed status to Open',NULL,'2017-04-02 00:49:55'),(74,2,7,1,' changed status to Resolved',NULL,'2017-04-02 00:54:48'),(75,2,7,1,' changed status to Closed',NULL,'2017-04-02 00:55:12'),(76,2,13,1,' changed status to Resolved',NULL,'2017-04-02 00:59:10'),(77,2,13,1,' changed status to Closed',NULL,'2017-04-02 00:59:28'),(78,2,11,1,' changed status to In Progress',NULL,'2017-04-02 01:13:01'),(79,2,11,1,' changed status to Open',NULL,'2017-04-02 01:13:06'),(80,2,11,1,' changed status to In Progress',NULL,'2017-04-02 01:13:45'),(81,2,17,1,' changed status to Open',NULL,'2017-04-03 00:14:19'),(82,2,11,1,' changed status to Resolved',NULL,'2017-04-03 00:16:21'),(83,2,11,1,' changed status to Closed',NULL,'2017-04-03 00:17:11'),(84,2,18,1,' changed status to Open',NULL,'2017-04-03 00:20:06'),(85,2,14,1,' changed status to Resolved',NULL,'2017-04-03 00:21:27'),(86,2,14,1,' changed status to Closed',NULL,'2017-04-03 00:21:47'),(87,2,19,1,' changed status to Open',NULL,'2017-04-05 01:24:34'),(88,2,20,1,' changed status to Open',NULL,'2017-04-05 01:26:25'),(89,2,21,1,' changed status to Open',NULL,'2017-04-05 01:27:46'),(90,2,22,1,' changed status to Open',NULL,'2017-04-05 01:29:07'),(91,2,23,1,' changed status to Open',NULL,'2017-04-05 01:31:41'),(92,2,24,1,' changed status to Open',NULL,'2017-04-05 01:32:31'),(93,2,25,1,' changed status to Open',NULL,'2017-04-05 01:33:15'),(94,2,26,1,' changed status to Open',NULL,'2017-04-05 01:34:09'),(95,2,27,1,' changed status to Open',NULL,'2017-04-05 01:35:02'),(96,2,28,1,' changed status to Open',NULL,'2017-04-05 01:35:55'),(97,2,29,1,' changed status to Open',NULL,'2017-04-05 01:36:59'),(98,2,5,1,' changed status to Closed',NULL,'2017-03-20 21:44:55'),(99,2,11,1,' reopen ',NULL,'2017-04-09 21:10:14'),(100,2,6,1,' start progress on ',NULL,'2017-04-09 22:30:21'),(101,2,6,1,' stop progress on ',NULL,'2017-04-09 22:36:11'),(102,2,6,1,' start progress on ',NULL,'2017-04-09 22:36:42'),(103,2,9,1,' close ',NULL,'2017-04-09 22:44:30'),(104,2,11,1,' close ',NULL,'2017-04-09 22:48:32'),(105,2,15,1,' close ',NULL,'2017-04-09 22:50:14'),(106,2,17,1,' resolve ',NULL,'2017-04-09 22:55:47'),(107,2,17,1,' close ',NULL,'2017-04-09 22:56:33'),(108,2,19,1,' added a comment on ','La navegacion sera solo entre los issues de un proyecto segun se fueron creando, y debera ser circular, es decir cuando llegue al ultimo issue continuara con el primero y cuando llegue al primero, continuara con el ultimo','2017-04-09 22:58:53'),(109,2,19,1,' close ',NULL,'2017-04-09 22:59:18'),(110,2,20,1,' close ',NULL,'2017-04-09 23:00:05'),(111,2,22,1,' close ',NULL,'2017-04-09 23:01:56'),(112,2,24,1,' close ',NULL,'2017-04-09 23:03:51'),(113,2,26,1,' added a comment on ','Se debe incluir un separador por dia en la lista de activities','2017-04-09 23:06:00'),(114,2,30,1,' create ',NULL,'2017-04-09 23:07:55'),(115,2,31,1,' create ',NULL,'2017-04-09 23:09:56'),(116,2,32,1,' create ',NULL,'2017-04-09 23:11:42'),(117,2,33,1,' create ',NULL,'2017-04-09 23:12:40'),(118,2,34,1,' create ',NULL,'2017-04-09 23:15:09'),(119,2,35,1,' create ',NULL,'2017-04-09 23:16:24'),(120,2,36,1,' create ',NULL,'2017-04-09 23:19:30'),(121,2,37,1,' create ',NULL,'2017-04-09 23:20:48'),(122,1,38,1,' create ',NULL,'2017-04-12 07:21:05'),(123,2,16,1,' start progress on ',NULL,'2017-04-13 17:08:36'),(124,2,21,1,' start progress on ',NULL,'2017-04-13 17:09:21'),(125,2,16,1,' close ',NULL,'2017-04-13 17:42:53'),(126,2,31,1,' close ',NULL,'2017-04-13 18:51:38'),(127,2,23,1,' close ',NULL,'2017-04-13 20:46:12'),(128,2,8,1,' close ',NULL,'2017-04-13 20:47:05'),(129,2,37,1,' resolve ',NULL,'2017-04-13 20:49:10'),(130,2,37,1,' close ',NULL,'2017-04-13 20:49:36'),(131,2,26,1,' added a comment on ','Se debe incrementar la cantidad de activities de 10 en 10 al seleccionar la opcion \"mas activities\"','2017-04-13 20:51:31'),(132,2,39,1,' create ',NULL,'2017-04-15 07:11:29'),(133,2,6,1,' added a comment on ','Ya se incluyen los comentarios, el historial, las actividades y las transiciones. Faltan los archivos adjuntos, Los links entre issues, las etiquetas y las versiones','2017-04-15 07:15:40'),(134,2,6,1,' added a comment on ','Se va a cerrar este issue y se van a abrir issues independientes por cada elemento que falta en la pantalla de detalle','2017-04-15 07:19:36'),(135,2,26,1,' resolve ',NULL,'2017-04-22 21:24:30'),(136,2,26,1,' close ',NULL,'2017-04-22 21:24:50'),(137,2,29,1,' close ',NULL,'2017-04-22 21:29:47'),(138,2,35,1,' close ',NULL,'2017-04-22 21:30:41'),(139,2,2,1,' resolve ',NULL,'2017-05-03 23:00:54'),(140,2,27,1,' resolve ',NULL,'2017-05-03 23:02:45'),(141,2,27,1,' close ',NULL,'2017-05-03 23:03:01'),(142,2,21,1,' close ',NULL,'2017-05-06 15:26:10'),(143,2,6,1,' close ',NULL,'2017-05-06 20:02:31'),(144,2,6,1,' change type ',NULL,'2017-05-10 13:11:11'),(145,2,6,1,' change priority ',NULL,'2017-05-10 13:11:16'),(146,2,6,1,' change subject ',NULL,'2017-05-10 13:11:44'),(147,2,32,1,' change type ',NULL,'2017-05-10 13:12:42'),(148,2,32,1,' change type ',NULL,'2017-05-10 13:14:10'),(149,2,34,1,' change type ',NULL,'2017-05-15 15:47:48'),(150,2,34,1,' change type ',NULL,'2017-05-15 15:54:03');
/*!40000 ALTER TABLE `sg_activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sg_attachments`
--

DROP TABLE IF EXISTS `sg_attachments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sg_attachments` (
  `id_attachment` int(11) NOT NULL AUTO_INCREMENT,
  `id_issue` int(11) NOT NULL,
  `id_author` int(11) NOT NULL,
  `name` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `creation` datetime NOT NULL,
  `attachment` blob NOT NULL,
  PRIMARY KEY (`id_attachment`),
  KEY `attach_idx1` (`id_issue`),
  KEY `attach_idx2` (`id_author`),
  CONSTRAINT `issue_fk_7` FOREIGN KEY (`id_issue`) REFERENCES `sg_issues` (`id_issue`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_fk_7` FOREIGN KEY (`id_author`) REFERENCES `sg_users` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sg_attachments`
--

LOCK TABLES `sg_attachments` WRITE;
/*!40000 ALTER TABLE `sg_attachments` DISABLE KEYS */;
/*!40000 ALTER TABLE `sg_attachments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sg_authorities`
--

DROP TABLE IF EXISTS `sg_authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sg_authorities` (
  `id_authority` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `authority` varchar(45) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`id_authority`),
  UNIQUE KEY `authority__un` (`id_user`,`authority`),
  KEY `fk_user_idx` (`id_user`),
  CONSTRAINT `sg_users_fk_1` FOREIGN KEY (`id_user`) REFERENCES `sg_users` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sg_authorities`
--

LOCK TABLES `sg_authorities` WRITE;
/*!40000 ALTER TABLE `sg_authorities` DISABLE KEYS */;
INSERT INTO `sg_authorities` VALUES (2,1,'ROLE_ADMIN'),(3,1,'ROLE_MANAGER'),(1,1,'ROLE_USER'),(4,2,'ROLE_USER');
/*!40000 ALTER TABLE `sg_authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sg_comments`
--

DROP TABLE IF EXISTS `sg_comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sg_comments` (
  `id_comment` int(11) NOT NULL AUTO_INCREMENT,
  `id_issue` int(11) NOT NULL,
  `author` int(11) NOT NULL,
  `comment` text COLLATE latin1_spanish_ci NOT NULL,
  `creation` datetime NOT NULL,
  `modification` datetime DEFAULT NULL,
  PRIMARY KEY (`id_comment`),
  KEY `comment_idx1` (`id_issue`),
  KEY `comment_idx2` (`author`),
  CONSTRAINT `issue_fk_1` FOREIGN KEY (`id_issue`) REFERENCES `sg_issues` (`id_issue`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_fk_6` FOREIGN KEY (`author`) REFERENCES `sg_users` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sg_comments`
--

LOCK TABLES `sg_comments` WRITE;
/*!40000 ALTER TABLE `sg_comments` DISABLE KEYS */;
INSERT INTO `sg_comments` VALUES (1,7,1,'Using basic colors: blue, yellow and red','2017-03-26 20:16:45',NULL),(2,7,1,'Using basic colors: blue, yellow and red','2017-03-26 20:18:47',NULL),(3,7,1,'Using basic colors: blue, yellow and red','2017-03-26 20:22:04',NULL),(4,7,1,'Using basic colors: blue, yellow and red','2017-03-26 20:23:39',NULL),(5,7,1,'Otro comment','2017-03-26 20:38:21',NULL),(6,7,1,'comment','2017-03-26 20:49:24',NULL),(7,7,1,'otro','2017-03-26 20:50:21',NULL),(8,7,1,'otro comment','2017-03-26 20:51:15',NULL),(9,7,1,'otro comment formato','2017-03-26 20:55:50',NULL),(10,15,1,'Se genera el modelo IssueTransition para persistir los cambios de status','2017-03-31 23:45:23',NULL),(11,4,1,'Se crea una relacion muchos a muchos para poder asociar varios componentes a un issue, se mapea a entities y se modifican las pantallas de registro y consulta de issues','2017-04-01 23:23:15',NULL),(12,4,1,'Se verifica que ya permite agregar varios componentes a un issue','2017-04-01 23:36:25',NULL),(13,7,1,'Se incluyen los colores, rojo, verde, azul y amarillo en los iconos','2017-04-02 00:54:48',NULL),(14,7,1,'Se encuentran correctos los colores de los iconos','2017-04-02 00:55:12',NULL),(15,13,1,'Se ajusta el estilo para que se muestre de forma correcta','2017-04-02 00:59:10',NULL),(16,13,1,'Se corrige el issue','2017-04-02 00:59:28',NULL),(17,11,1,'Con un estilo, se raya por la mitad la clave de un issue cuando ya se encuentra resuelto','2017-04-03 00:16:21',NULL),(18,11,1,'Se observa que ya se marcan las claves de los issues resueltos','2017-04-03 00:17:11',NULL),(19,14,1,'Ya se versiono el proyecto seguidino y se sincronizo con el workspace local','2017-04-03 00:21:27',NULL),(20,14,1,'Ya se encuentra versionado','2017-04-03 00:21:47',NULL),(21,26,1,'Se agrego el summary por proyecto y por issue','2017-04-09 08:18:39',NULL),(24,11,1,'La marca de los issues solo debe ser en la lista de Activities, en la lista de issues no debe aparecer rayada','2017-04-09 21:10:14',NULL),(25,9,1,'Se implementa todo el flujo desde que se crea hasta que se cierra, incluso cuando se reabre','2017-04-09 22:44:30',NULL),(26,11,1,'Se deja el rayado solo en los activities','2017-04-09 22:48:32',NULL),(27,15,1,'Se implementa el registro de transiciones','2017-04-09 22:50:14',NULL),(30,17,1,'Se implementa el modelo de Activity, se muestra a nivel de proyecto (en el summary) y a nivel de issue (en el tab de activity)','2017-04-09 22:55:47',NULL),(31,17,1,'Log de activities correcto','2017-04-09 22:56:33',NULL),(32,19,1,'La navegacion sera solo entre los issues de un proyecto segun se fueron creando, y debera ser circular, es decir cuando llegue al ultimo issue continuara con el primero y cuando llegue al primero, continuara con el ultimo','2017-04-09 22:58:53',NULL),(33,19,1,'Se implementan los botones de navegacion entre issues','2017-04-09 22:59:18',NULL),(34,20,1,'Se incluye la fecha resolved para los issues que se encuentran resolved o directamente close, si se habre el issue nuevamente, la fecha se reinicia','2017-04-09 23:00:05',NULL),(35,22,1,'Se implementa el orden del mas antiguo al mas reciente','2017-04-09 23:01:56',NULL),(36,24,1,'Se registra toda la actividad de un issue de las opciones existentes','2017-04-09 23:03:51',NULL),(37,26,1,'Se debe incluir un separador por dia en la lista de activities','2017-04-09 23:06:00',NULL),(38,16,1,'Se implementan las tablas de porcentajes por proyecto','2017-04-13 17:42:53',NULL),(39,31,1,'Se humanizan las fechas en el detalle de un issue, en los comentarios, en la historia, en las actividades y en las transiciones.','2017-04-13 18:51:38',NULL),(40,23,1,'Se ordenan las activities de la mas reciente a la mas antigua','2017-04-13 20:46:12',NULL),(41,8,1,'Se implementa el flujo de atencion de issues','2017-04-13 20:47:05',NULL),(42,37,1,'Se optimiza la generacion de la grafica para que se actulice la informacion de forma correcta','2017-04-13 20:49:10',NULL),(43,37,1,'Se corrige la generacion de la grafica de creados contra resueltos','2017-04-13 20:49:36',NULL),(44,26,1,'Se debe incrementar la cantidad de activities de 10 en 10 al seleccionar la opcion \"mas activities\"','2017-04-13 20:51:31',NULL),(45,6,1,'Ya se incluyen los comentarios, el historial, las actividades y las transiciones. Faltan los archivos adjuntos, Los links entre issues, las etiquetas y las versiones','2017-04-15 07:15:40',NULL),(46,6,1,'Se va a cerrar este issue y se van a abrir issues independientes por cada elemento que falta en la pantalla de detalle','2017-04-15 07:19:36',NULL),(47,26,1,'Se actualizan los activities en la seccion de proyectos','2017-04-22 21:24:30',NULL),(48,26,1,'Se resuelve el issue','2017-04-22 21:24:50',NULL),(49,29,1,'Se da estilo al formulario de creacion de issue','2017-04-22 21:29:47',NULL),(50,35,1,'Se da estilo a los botones principales','2017-04-22 21:30:41',NULL),(51,2,1,'Se incluye la funcionalidad para cambiar el avatar (logo) del proyecto','2017-05-03 23:00:54',NULL),(52,27,1,'Se incluye responsable por complemento','2017-05-03 23:02:45',NULL),(53,27,1,'Verificado','2017-05-03 23:03:01',NULL),(54,21,1,'Se implementa histories','2017-05-06 15:26:10',NULL),(55,6,1,'Se van a abrir issues de cada tipo de detalle  a incluir','2017-05-06 20:02:31',NULL);
/*!40000 ALTER TABLE `sg_comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sg_components`
--

DROP TABLE IF EXISTS `sg_components`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sg_components` (
  `id_component` int(11) NOT NULL AUTO_INCREMENT,
  `id_project` int(11) NOT NULL,
  `id_user` int(11) DEFAULT NULL,
  `name` varchar(45) COLLATE latin1_spanish_ci NOT NULL,
  `description` varchar(500) COLLATE latin1_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id_component`),
  UNIQUE KEY `sg_pro_com__un` (`id_project`,`name`),
  KEY `pro_fk_idx_1` (`id_project`),
  KEY `user_fk_0415_idx` (`id_user`),
  CONSTRAINT `pro_fk_1` FOREIGN KEY (`id_project`) REFERENCES `sg_projects` (`id_project`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_fk_0415` FOREIGN KEY (`id_user`) REFERENCES `sg_users` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sg_components`
--

LOCK TABLES `sg_components` WRITE;
/*!40000 ALTER TABLE `sg_components` DISABLE KEYS */;
INSERT INTO `sg_components` VALUES (1,1,1,'Batch upload Amazon Client',NULL),(2,1,1,'Library','Library'),(3,1,1,'Administration','Administration'),(4,2,1,'Dashboard','Dashboard'),(5,2,1,'Projects','Projects'),(6,2,1,'Issues','Issues'),(7,2,1,'Administration','Administration');
/*!40000 ALTER TABLE `sg_components` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sg_files_references`
--

DROP TABLE IF EXISTS `sg_files_references`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sg_files_references` (
  `id_file_reference` int(11) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `original_name` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `content_type` varchar(45) COLLATE latin1_spanish_ci NOT NULL,
  `file_size` int(11) NOT NULL,
  `relative_path` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `creation` datetime NOT NULL,
  PRIMARY KEY (`id_file_reference`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sg_files_references`
--

LOCK TABLES `sg_files_references` WRITE;
/*!40000 ALTER TABLE `sg_files_references` DISABLE KEYS */;
INSERT INTO `sg_files_references` VALUES (1,'1_avatar.png','icon.png','image/png',159,'BOOKER','2017-05-03 22:15:54'),(2,'2_avatar.ico','favicon.ico','image/vnd.microsoft.icon',318,'SEGUIDINO','2017-05-03 22:27:50'),(3,'3_avatar.png','icon.png','image/png',159,'SEGUIDINO','2017-05-03 22:54:33'),(4,'4_avatar.png','chartJira.PNG','image/png',12258,'SEGUIDINO','2017-05-03 22:56:49'),(5,'5_avatar.ico','favicon.ico','image/vnd.microsoft.icon',318,'SEGUIDINO','2017-05-03 22:57:38'),(6,'6_avatar.png','icon.png','image/png',159,'SEGUIDINO','2017-05-03 22:59:04'),(7,'7_avatar.ico','favicon.ico','image/vnd.microsoft.icon',318,'SEGUIDINO','2017-05-03 22:59:54'),(8,'8_avatar.png','1_avatar.png','image/png',159,'BOOKER','2017-05-10 13:14:51'),(9,'9_avatar.ico','7_avatar.ico','image/x-icon',318,'BOOKER','2017-05-10 13:15:11'),(10,'10_avatar.png','1_avatar.png','image/png',159,'BOOKER','2017-05-10 13:15:29'),(11,'11_avatar.ico','7_avatar.ico','image/x-icon',318,'SEGUIDINO','2017-05-10 13:15:42'),(12,'12_avatar.png','1_avatar.png','image/png',159,'BOOKER','2017-05-15 15:48:36'),(13,'13_avatar.png','1_avatar.png','image/png',159,'BOOKER','2017-05-15 15:51:41'),(14,'14_avatar.ico','7_avatar.ico','image/x-icon',318,'SEGUIDINO','2017-05-15 15:52:00');
/*!40000 ALTER TABLE `sg_files_references` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sg_filter_params`
--

DROP TABLE IF EXISTS `sg_filter_params`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sg_filter_params` (
  `id_filter_param` int(11) NOT NULL AUTO_INCREMENT,
  `id_filter` int(11) NOT NULL,
  `name` varchar(45) COLLATE latin1_spanish_ci NOT NULL,
  `value` varchar(45) COLLATE latin1_spanish_ci NOT NULL,
  `param_type` varchar(45) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`id_filter_param`),
  KEY `filter_fk_04014_idx` (`id_filter`),
  CONSTRAINT `filter_fk_04014` FOREIGN KEY (`id_filter`) REFERENCES `sg_filters` (`id_filter`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sg_filter_params`
--

LOCK TABLES `sg_filter_params` WRITE;
/*!40000 ALTER TABLE `sg_filter_params` DISABLE KEYS */;
INSERT INTO `sg_filter_params` VALUES (1,1,'idProject','1','java.lang.Long'),(2,2,'idProject','2','java.lang.Long'),(3,3,'idProject','1','java.lang.Long'),(4,4,'idProject','2','java.lang.Long'),(5,5,'idProject','1','java.lang.Long'),(6,6,'idProject','2','java.lang.Long'),(7,5,'assigne','','java.security.Principal'),(8,6,'assigne','','java.security.Principal'),(9,7,'idProject','1','java.lang.Long'),(10,8,'idProject','2','java.lang.Long'),(13,9,'idProject','1','java.lang.Long'),(14,10,'idProject','2','java.lang.Long'),(15,11,'idProject','1','java.lang.Long'),(16,12,'idProject','2','java.lang.Long'),(17,11,'idUser','','java.security.Principal'),(18,12,'idUser','','java.security.Principal');
/*!40000 ALTER TABLE `sg_filter_params` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sg_filters`
--

DROP TABLE IF EXISTS `sg_filters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sg_filters` (
  `id_filter` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE latin1_spanish_ci NOT NULL,
  `query` varchar(1000) COLLATE latin1_spanish_ci NOT NULL,
  `id_project` int(11) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_filter`),
  KEY `project_fk_0414_idx` (`id_project`),
  KEY `user_fk_0414_idx` (`id_user`),
  CONSTRAINT `project_fk_0414` FOREIGN KEY (`id_project`) REFERENCES `sg_projects` (`id_project`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_fk_0414` FOREIGN KEY (`id_user`) REFERENCES `sg_users` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sg_filters`
--

LOCK TABLES `sg_filters` WRITE;
/*!40000 ALTER TABLE `sg_filters` DISABLE KEYS */;
INSERT INTO `sg_filters` VALUES (1,'Unresolved','select i from Issue i where i.idProject = :idProject and i.resolved is null order by i.idIssue',1,NULL),(2,'Unresolved','select i from Issue i where i.idProject = :idProject and i.resolved is null order by i.idIssue',2,NULL),(3,'AddedRecently','select i from Issue i where i.idProject = :idProject order by i.idIssue desc',1,NULL),(4,'AddedRecently','select i from Issue i where i.idProject = :idProject order by i.idIssue desc',2,NULL),(5,'AssignedToMe','select i from Issue i where i.idProject = :idProject and i.assignee.idUser = :assigne order by i.idIssue',1,NULL),(6,'AssignedToMe','select i from Issue i where i.idProject = :idProject and i.assignee.idUser = :assigne order by i.idIssue',2,NULL),(7,'ResolvedRecently','select i from Issue i where i.idProject = :idProject and i.resolved is not null order by i.resolved desc',1,NULL),(8,'ResolvedRecently','select i from Issue i where i.idProject = :idProject and i.resolved is not null order by i.resolved desc',2,NULL),(9,'UpdatedRecently','select i from Issue i where i.idProject = :idProject and i.modification is not null order by i.modification desc',1,NULL),(10,'UpdatedRecently','select i from Issue i where i.idProject = :idProject and i.modification is not null order by i.modification desc',2,NULL),(11,'ReportedByMe','select i from Issue i where i.idProject = :idProject and i.author.idUser = :idUser order by i.idIssue desc',1,NULL),(12,'ReportedByMe','select i from Issue i where i.idProject = :idProject and i.author.idUser = :idUser order by i.idIssue desc',2,NULL);
/*!40000 ALTER TABLE `sg_filters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sg_issue_access`
--

DROP TABLE IF EXISTS `sg_issue_access`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sg_issue_access` (
  `id_issue_access` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `id_issue` int(11) NOT NULL,
  `access` datetime NOT NULL,
  PRIMARY KEY (`id_issue_access`),
  UNIQUE KEY `issue_access__un` (`id_user`,`id_issue`),
  KEY `issue_fk_20_idx` (`id_issue`),
  CONSTRAINT `issue_fk_20` FOREIGN KEY (`id_issue`) REFERENCES `sg_issues` (`id_issue`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_fk_20` FOREIGN KEY (`id_user`) REFERENCES `sg_users` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sg_issue_access`
--

LOCK TABLES `sg_issue_access` WRITE;
/*!40000 ALTER TABLE `sg_issue_access` DISABLE KEYS */;
INSERT INTO `sg_issue_access` VALUES (1,1,2,'2017-05-03 23:00:54'),(2,1,10,'2017-05-06 19:36:10'),(3,1,5,'2017-05-03 22:41:35'),(4,1,7,'2017-04-13 18:30:22'),(5,1,8,'2017-04-13 20:47:06'),(6,1,11,'2017-04-13 18:30:29'),(7,1,4,'2017-05-03 22:41:33'),(8,1,6,'2017-05-10 13:12:30'),(9,1,9,'2017-04-13 18:30:27'),(10,1,12,'2017-04-13 18:30:30'),(11,1,13,'2017-04-09 22:48:58'),(12,1,14,'2017-04-09 22:49:05'),(13,1,15,'2017-04-09 22:50:14'),(14,1,16,'2017-04-13 18:50:18'),(15,1,17,'2017-04-13 18:41:32'),(16,1,18,'2017-04-13 18:31:09'),(17,1,19,'2017-04-13 18:31:10'),(18,1,20,'2017-04-13 18:05:26'),(19,1,21,'2017-05-06 15:26:11'),(20,1,22,'2017-04-13 18:06:21'),(21,1,23,'2017-04-13 20:46:13'),(22,1,24,'2017-04-13 18:06:29'),(23,1,25,'2017-04-13 18:06:31'),(24,1,26,'2017-04-22 21:24:50'),(25,1,27,'2017-05-03 23:03:01'),(26,1,28,'2017-04-22 21:29:05'),(27,1,29,'2017-04-22 21:29:47'),(28,1,1,'2017-04-09 07:50:28'),(29,1,30,'2017-04-22 21:29:51'),(30,1,31,'2017-04-22 21:29:53'),(31,1,32,'2017-05-10 23:12:34'),(32,1,33,'2017-05-10 23:12:53'),(33,1,34,'2017-05-15 15:54:06'),(34,1,35,'2017-04-22 21:30:41'),(35,1,36,'2017-04-22 21:31:36'),(36,1,37,'2017-04-22 21:31:41'),(37,1,38,'2017-04-12 07:21:05'),(38,1,39,'2017-04-22 21:31:44'),(39,2,28,'2017-05-10 23:23:55');
/*!40000 ALTER TABLE `sg_issue_access` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sg_issue_comp`
--

DROP TABLE IF EXISTS `sg_issue_comp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sg_issue_comp` (
  `id_issue_comp` int(11) NOT NULL AUTO_INCREMENT,
  `id_issue` int(11) NOT NULL,
  `id_component` int(11) NOT NULL,
  PRIMARY KEY (`id_issue_comp`),
  UNIQUE KEY `issue_comp__un` (`id_issue`,`id_component`),
  KEY `fk_comp_0401_idx` (`id_component`),
  CONSTRAINT `fk_comp_0401` FOREIGN KEY (`id_component`) REFERENCES `sg_components` (`id_component`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_issue_0401` FOREIGN KEY (`id_issue`) REFERENCES `sg_issues` (`id_issue`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sg_issue_comp`
--

LOCK TABLES `sg_issue_comp` WRITE;
/*!40000 ALTER TABLE `sg_issue_comp` DISABLE KEYS */;
INSERT INTO `sg_issue_comp` VALUES (1,1,2),(2,2,5),(3,4,6),(4,5,6),(5,6,6),(6,7,6),(7,8,6),(8,9,6),(9,10,6),(13,11,7),(10,12,6),(11,13,6),(14,14,7),(12,15,6),(16,16,5),(17,16,6),(18,17,5),(19,17,6),(20,18,6),(21,19,6),(22,20,6),(23,21,6),(24,22,6),(25,23,6),(26,24,6),(27,25,6),(28,26,5),(29,27,5),(30,28,6),(31,29,6),(32,30,6),(33,31,5),(34,31,6),(35,32,6),(36,33,6),(37,34,6),(40,35,5),(39,35,6),(38,35,7),(41,36,7),(42,37,6),(43,38,3),(44,39,7);
/*!40000 ALTER TABLE `sg_issue_comp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sg_issue_history`
--

DROP TABLE IF EXISTS `sg_issue_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sg_issue_history` (
  `id_issue_history` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `id_issue` int(11) NOT NULL,
  `action` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `creation` datetime NOT NULL,
  PRIMARY KEY (`id_issue_history`),
  KEY `user_fk_21_1_idx` (`id_user`),
  KEY `issue_fk_21_1_idx` (`id_issue`),
  CONSTRAINT `issue_fk_21_1` FOREIGN KEY (`id_issue`) REFERENCES `sg_issues` (`id_issue`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_fk_21_1` FOREIGN KEY (`id_user`) REFERENCES `sg_users` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sg_issue_history`
--

LOCK TABLES `sg_issue_history` WRITE;
/*!40000 ALTER TABLE `sg_issue_history` DISABLE KEYS */;
INSERT INTO `sg_issue_history` VALUES (1,1,7,'made changes','2017-03-22 23:00:41'),(2,1,8,'made changes','2017-03-22 23:15:33'),(12,1,9,'made changes','2017-03-31 21:38:57'),(13,1,13,'made changes','2017-03-31 21:41:36'),(14,1,14,'made changes','2017-03-31 22:06:42'),(21,1,4,'made changes','2017-04-01 15:27:13'),(23,1,4,'made changes','2017-04-01 23:23:15'),(27,1,4,'made changes','2017-04-01 23:36:25'),(32,1,6,'made changes','2017-04-02 00:49:44'),(33,1,6,'made changes','2017-04-02 00:49:49'),(34,1,6,'made changes','2017-04-02 00:49:52'),(35,1,6,'made changes','2017-04-02 00:49:55'),(36,1,7,'made changes','2017-04-02 00:54:48'),(40,1,7,'made changes','2017-04-02 00:55:12'),(45,1,13,'made changes','2017-04-02 00:59:10'),(49,1,13,'made changes','2017-04-02 00:59:28'),(54,1,11,'made changes','2017-04-02 01:13:01'),(55,1,11,'made changes','2017-04-02 01:13:06'),(56,1,11,'made changes','2017-04-02 01:13:45'),(58,1,11,'made changes','2017-04-03 00:16:21'),(59,1,11,'made changes','2017-04-03 00:17:11'),(61,1,14,'made changes','2017-04-03 00:21:27'),(62,1,14,'made changes','2017-04-03 00:21:47'),(63,1,19,'created issue','2017-04-05 01:24:34'),(64,1,20,'created issue','2017-04-05 01:26:25'),(65,1,21,'created issue','2017-04-05 01:27:46'),(66,1,22,'created issue','2017-04-05 01:29:07'),(67,1,26,'made changes','2017-04-09 08:11:51'),(68,1,8,'made changes','2017-04-09 15:39:17'),(69,1,8,'made changes','2017-04-09 15:39:23'),(72,1,11,'made changes','2017-04-09 21:10:14'),(73,1,6,'made changes','2017-04-09 22:30:21'),(74,1,6,'made changes','2017-04-09 22:36:11'),(75,1,6,'made changes','2017-04-09 22:36:42'),(76,1,9,'made changes','2017-04-09 22:44:30'),(77,1,11,'made changes','2017-04-09 22:48:32'),(78,1,15,'made changes','2017-04-09 22:50:14'),(79,1,17,'made changes','2017-04-09 22:55:47'),(80,1,17,'made changes','2017-04-09 22:56:33'),(81,1,19,'made changes','2017-04-09 22:59:18'),(82,1,20,'made changes','2017-04-09 23:00:05'),(83,1,22,'made changes','2017-04-09 23:01:56'),(84,1,24,'made changes','2017-04-09 23:03:51'),(85,1,16,'made changes','2017-04-13 17:08:36'),(86,1,21,'made changes','2017-04-13 17:09:21'),(87,1,16,'made changes','2017-04-13 17:42:53'),(88,1,31,'made changes','2017-04-13 18:51:38'),(89,1,23,'made changes','2017-04-13 20:46:12'),(90,1,8,'made changes','2017-04-13 20:47:05'),(91,1,37,'made changes','2017-04-13 20:49:10'),(92,1,37,'made changes','2017-04-13 20:49:36'),(93,1,26,'made changes','2017-04-22 21:24:30'),(94,1,26,'made changes','2017-04-22 21:24:50'),(95,1,29,'made changes','2017-04-22 21:29:47'),(96,1,35,'made changes','2017-04-22 21:30:41'),(97,1,2,'made changes','2017-05-03 23:00:54'),(98,1,27,'made changes','2017-05-03 23:02:45'),(99,1,27,'made changes','2017-05-03 23:03:01'),(100,1,21,'made changes','2017-05-06 15:26:10'),(101,1,6,'made changes','2017-05-06 20:02:31'),(102,1,6,'made changes','2017-05-10 13:11:11'),(103,1,6,'made changes','2017-05-10 13:11:16'),(104,1,6,'made changes','2017-05-10 13:11:44'),(105,1,32,'made changes','2017-05-10 13:12:42'),(106,1,32,'made changes','2017-05-10 13:14:10'),(107,1,34,'made changes','2017-05-15 15:47:48'),(108,1,34,'made changes','2017-05-15 15:54:03');
/*!40000 ALTER TABLE `sg_issue_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sg_issue_history_det`
--

DROP TABLE IF EXISTS `sg_issue_history_det`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sg_issue_history_det` (
  `id_issue_history_det` int(11) NOT NULL AUTO_INCREMENT,
  `id_issue_history` int(11) NOT NULL,
  `field` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `before_value` text CHARACTER SET latin1 NOT NULL,
  `after_value` text CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`id_issue_history_det`),
  KEY `issue_history_fk_idx` (`id_issue_history`),
  CONSTRAINT `issue_history_fk` FOREIGN KEY (`id_issue_history`) REFERENCES `sg_issue_history` (`id_issue_history`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sg_issue_history_det`
--

LOCK TABLES `sg_issue_history_det` WRITE;
/*!40000 ALTER TABLE `sg_issue_history_det` DISABLE KEYS */;
INSERT INTO `sg_issue_history_det` VALUES (1,1,'status','OPEN[1]','IN_PROGRESS[2]'),(2,2,'status','OPEN[1]','IN_PROGRESS[2]'),(3,12,'status','OPEN[1]','IN_PROGRESS[2]'),(4,13,'status','OPEN[1]','IN_PROGRESS[2]'),(5,14,'status','OPEN[1]','IN_PROGRESS[2]'),(9,21,'status','OPEN[1]','IN_PROGRESS[2]'),(10,23,'status','IN_PROGRESS[2]','RESOLVED[5]'),(12,27,'status','RESOLVED[5]','CLOSED[3]'),(14,32,'status','OPEN[1]','IN_PROGRESS[2]'),(15,33,'status','IN_PROGRESS[2]','OPEN[1]'),(16,34,'status','OPEN[1]','IN_PROGRESS[2]'),(17,35,'status','IN_PROGRESS[2]','OPEN[1]'),(18,36,'status','IN_PROGRESS[2]','RESOLVED[5]'),(20,40,'status','RESOLVED[5]','CLOSED[3]'),(22,45,'status','IN_PROGRESS[2]','RESOLVED[5]'),(24,49,'status','RESOLVED[5]','CLOSED[3]'),(26,54,'status','OPEN[1]','IN_PROGRESS[2]'),(27,55,'status','IN_PROGRESS[2]','OPEN[1]'),(28,56,'status','OPEN[1]','IN_PROGRESS[2]'),(29,74,'status','IN_PROGRESS[2]','OPEN[1]'),(30,75,'status','OPEN[1]','IN_PROGRESS[2]'),(31,76,'status','IN_PROGRESS[2]','CLOSED[3]'),(32,76,'resolution','','Fixed[1]'),(33,77,'status','REOPEN[4]','CLOSED[3]'),(34,77,'resolution','','Fixed[1]'),(35,78,'status','RESOLVED[5]','CLOSED[3]'),(36,79,'status','OPEN[1]','RESOLVED[5]'),(37,79,'resolution','','Fixed[1]'),(38,80,'status','RESOLVED[5]','CLOSED[3]'),(39,81,'status','OPEN[1]','CLOSED[3]'),(40,81,'resolution','','Fixed[1]'),(41,82,'status','OPEN[1]','CLOSED[3]'),(42,82,'resolution','','Fixed[1]'),(43,83,'status','OPEN[1]','CLOSED[3]'),(44,83,'resolution','','Fixed[1]'),(45,84,'status','OPEN[1]','CLOSED[3]'),(46,84,'resolution','','Fixed[1]'),(47,85,'status','OPEN[1]','IN_PROGRESS[2]'),(48,86,'status','OPEN[1]','IN_PROGRESS[2]'),(49,87,'status','IN_PROGRESS[2]','CLOSED[3]'),(50,87,'resolution','','Fixed[1]'),(51,88,'status','OPEN[1]','CLOSED[3]'),(52,88,'resolution','','Fixed[1]'),(53,89,'status','OPEN[1]','CLOSED[3]'),(54,89,'resolution','','Fixed[1]'),(55,90,'status','IN_PROGRESS[2]','CLOSED[3]'),(56,90,'resolution','','Fixed[1]'),(57,91,'status','OPEN[1]','RESOLVED[5]'),(58,91,'resolution','','Fixed[1]'),(59,92,'status','RESOLVED[5]','CLOSED[3]'),(60,93,'status','IN_PROGRESS[2]','RESOLVED[5]'),(61,93,'resolution','','Fixed[1]'),(62,94,'status','RESOLVED[5]','CLOSED[3]'),(63,95,'status','OPEN[1]','CLOSED[3]'),(64,95,'resolution','','Fixed[1]'),(65,96,'status','OPEN[1]','CLOSED[3]'),(66,96,'resolution','','Fixed[1]'),(67,97,'status','OPEN[1]','RESOLVED[5]'),(68,97,'resolution','','Fixed[1]'),(69,98,'status','OPEN[1]','RESOLVED[5]'),(70,98,'resolution','','Fixed[1]'),(71,99,'status','RESOLVED[5]','CLOSED[3]'),(72,100,'status','IN_PROGRESS[2]','CLOSED[3]'),(73,100,'resolution','','Fixed[1]'),(74,101,'status','IN_PROGRESS[2]','CLOSED[3]'),(75,101,'resolution','','Fixed[1]'),(76,102,'type','Improvement[2]','Task[3]'),(77,103,'priority','Critical[2]','Blocker[1]'),(78,104,'subject','Incluir detalles del issue en la pantalla de consulta','Incluir detalles del issue en la pantalla de consulta mod'),(79,105,'type','Bug[1]','Improvement[2]'),(80,106,'type','Improvement[2]','Bug[1]'),(81,107,'type','Bug[1]','Improvement[2]'),(82,108,'type','Improvement[2]','Bug[1]');
/*!40000 ALTER TABLE `sg_issue_history_det` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sg_issue_priority`
--

DROP TABLE IF EXISTS `sg_issue_priority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sg_issue_priority` (
  `id_issue_priority` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE latin1_spanish_ci NOT NULL,
  `icon` varchar(45) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`id_issue_priority`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sg_issue_priority`
--

LOCK TABLES `sg_issue_priority` WRITE;
/*!40000 ALTER TABLE `sg_issue_priority` DISABLE KEYS */;
INSERT INTO `sg_issue_priority` VALUES (1,'Blocker','glyphicon-ban-circle red'),(2,'Critical','glyphicon-open red'),(3,'Mayor','glyphicon-arrow-up red'),(4,'Minor','glyphicon-arrow-down green'),(5,'Trivial','glyphicon-chevron-down green');
/*!40000 ALTER TABLE `sg_issue_priority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sg_issue_progress`
--

DROP TABLE IF EXISTS `sg_issue_progress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sg_issue_progress` (
  `id_issue_progress` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `id_issue` int(11) NOT NULL,
  `start` datetime NOT NULL,
  `stop` datetime DEFAULT NULL,
  `hours` double DEFAULT NULL,
  PRIMARY KEY (`id_issue_progress`),
  KEY `user_fk_21_idx` (`id_user`),
  KEY `issue_fk_21_idx` (`id_issue`),
  CONSTRAINT `issue_fk_21` FOREIGN KEY (`id_issue`) REFERENCES `sg_issues` (`id_issue`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_fk_21` FOREIGN KEY (`id_user`) REFERENCES `sg_users` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sg_issue_progress`
--

LOCK TABLES `sg_issue_progress` WRITE;
/*!40000 ALTER TABLE `sg_issue_progress` DISABLE KEYS */;
INSERT INTO `sg_issue_progress` VALUES (3,1,7,'2017-03-22 23:00:41','2017-04-02 00:54:48',241),(4,1,8,'2017-03-22 23:15:33','2017-04-09 15:39:17',424),(5,1,9,'2017-03-31 21:38:57',NULL,NULL),(6,1,13,'2017-03-31 21:41:36','2017-04-02 00:59:10',27),(7,1,14,'2017-03-31 22:06:42','2017-04-03 00:21:27',50),(8,1,15,'2017-03-31 23:12:22','2017-03-31 23:45:23',0),(9,1,4,'2017-04-01 15:27:13','2017-04-01 23:23:15',7),(10,1,5,'2017-03-20 00:42:36','2017-03-20 21:44:55',21),(11,1,6,'2017-04-02 00:49:44','2017-04-02 00:49:49',0),(12,1,6,'2017-04-02 00:49:52','2017-04-02 00:49:55',0),(13,1,11,'2017-04-02 01:13:01','2017-04-02 01:13:06',0),(14,1,11,'2017-04-02 01:13:45','2017-04-03 00:16:21',23),(15,1,26,'2017-04-09 08:11:51','2017-04-22 21:24:30',325),(16,1,8,'2017-04-09 15:39:23',NULL,NULL),(17,1,6,'2017-04-09 22:30:21','2017-04-09 22:36:11',0),(18,1,6,'2017-04-09 22:36:42',NULL,NULL),(19,1,17,'2017-04-09 22:55:47','2017-04-09 22:55:47',0),(20,1,16,'2017-04-13 17:08:36',NULL,NULL),(21,1,21,'2017-04-13 17:09:21',NULL,NULL),(22,1,37,'2017-04-13 20:49:10','2017-04-13 20:49:10',0),(23,1,2,'2017-05-03 23:00:54','2017-05-03 23:00:54',0),(24,1,27,'2017-05-03 23:02:45','2017-05-03 23:02:45',0);
/*!40000 ALTER TABLE `sg_issue_progress` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sg_issue_resolution`
--

DROP TABLE IF EXISTS `sg_issue_resolution`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sg_issue_resolution` (
  `id_issue_resolution` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`id_issue_resolution`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sg_issue_resolution`
--

LOCK TABLES `sg_issue_resolution` WRITE;
/*!40000 ALTER TABLE `sg_issue_resolution` DISABLE KEYS */;
INSERT INTO `sg_issue_resolution` VALUES (5,'Cannot Reproduce'),(3,'Duplicate'),(1,'Fixed'),(4,'Incomplete'),(2,'Won\'t Fix');
/*!40000 ALTER TABLE `sg_issue_resolution` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sg_issue_status`
--

DROP TABLE IF EXISTS `sg_issue_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sg_issue_status` (
  `id_issue_status` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE latin1_spanish_ci NOT NULL,
  `icon` varchar(45) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`id_issue_status`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sg_issue_status`
--

LOCK TABLES `sg_issue_status` WRITE;
/*!40000 ALTER TABLE `sg_issue_status` DISABLE KEYS */;
INSERT INTO `sg_issue_status` VALUES (1,'Open','glyphicon-log-in blue'),(2,'In Progress','glyphicon-retweet yellow'),(3,'Closed','glyphicon-folder-close green'),(4,'Reopened','glyphicon-transfer yellow'),(5,'Resolved','glyphicon-log-out green');
/*!40000 ALTER TABLE `sg_issue_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sg_issue_transitions`
--

DROP TABLE IF EXISTS `sg_issue_transitions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sg_issue_transitions` (
  `id_issue_transition` int(11) NOT NULL AUTO_INCREMENT,
  `id_issue` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_issue_status` int(11) NOT NULL,
  `creation` datetime NOT NULL,
  PRIMARY KEY (`id_issue_transition`),
  KEY `issue_fk_31_idx` (`id_issue`),
  KEY `user_fk_31_idx` (`id_user`),
  KEY `issue_status_fk_31_idx` (`id_issue_status`),
  CONSTRAINT `issue_fk_31` FOREIGN KEY (`id_issue`) REFERENCES `sg_issues` (`id_issue`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `issue_status_fk_31` FOREIGN KEY (`id_issue_status`) REFERENCES `sg_issue_status` (`id_issue_status`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_fk_31` FOREIGN KEY (`id_user`) REFERENCES `sg_users` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sg_issue_transitions`
--

LOCK TABLES `sg_issue_transitions` WRITE;
/*!40000 ALTER TABLE `sg_issue_transitions` DISABLE KEYS */;
INSERT INTO `sg_issue_transitions` VALUES (1,15,1,1,'2017-03-31 23:11:47'),(2,15,1,2,'2017-03-31 23:12:22'),(3,15,1,5,'2017-03-31 23:45:23'),(4,4,1,2,'2017-04-01 15:27:13'),(5,16,1,1,'2017-04-01 23:11:14'),(6,4,1,5,'2017-04-01 23:23:15'),(7,4,1,3,'2017-04-01 23:36:25'),(8,6,1,2,'2017-04-02 00:49:44'),(9,6,1,1,'2017-04-02 00:49:49'),(10,6,1,2,'2017-04-02 00:49:52'),(11,6,1,1,'2017-04-02 00:49:55'),(12,7,1,5,'2017-04-02 00:54:48'),(13,7,1,3,'2017-04-02 00:55:12'),(14,13,1,5,'2017-04-02 00:59:10'),(15,13,1,3,'2017-04-02 00:59:28'),(16,11,1,2,'2017-04-02 01:13:01'),(17,11,1,1,'2017-04-02 01:13:06'),(18,11,1,2,'2017-04-02 01:13:45'),(19,17,1,1,'2017-04-03 00:14:19'),(20,11,1,5,'2017-04-03 00:16:21'),(21,11,1,3,'2017-04-03 00:17:11'),(22,18,1,1,'2017-04-03 00:20:06'),(23,14,1,5,'2017-04-03 00:21:27'),(24,14,1,3,'2017-04-03 00:21:47'),(25,19,1,1,'2017-04-05 01:24:34'),(26,20,1,1,'2017-04-05 01:26:25'),(27,21,1,1,'2017-04-05 01:27:46'),(28,22,1,1,'2017-04-05 01:29:07'),(29,23,1,1,'2017-04-05 01:31:41'),(30,24,1,1,'2017-04-05 01:32:31'),(31,25,1,1,'2017-04-05 01:33:15'),(32,26,1,1,'2017-04-05 01:34:09'),(33,27,1,1,'2017-04-05 01:35:02'),(34,28,1,1,'2017-04-05 01:35:55'),(35,29,1,1,'2017-04-05 01:36:59'),(36,5,1,3,'2017-03-20 21:44:55'),(37,26,1,2,'2017-04-09 08:11:51'),(38,8,1,1,'2017-04-09 15:39:17'),(39,8,1,2,'2017-04-09 15:39:23'),(42,11,1,4,'2017-04-09 21:10:14'),(43,6,1,2,'2017-04-09 22:30:21'),(44,6,1,1,'2017-04-09 22:36:11'),(45,6,1,2,'2017-04-09 22:36:42'),(46,9,1,3,'2017-04-09 22:44:30'),(47,11,1,3,'2017-04-09 22:48:32'),(48,15,1,3,'2017-04-09 22:50:14'),(51,17,1,5,'2017-04-09 22:55:47'),(52,17,1,3,'2017-04-09 22:56:33'),(53,19,1,3,'2017-04-09 22:59:18'),(54,20,1,3,'2017-04-09 23:00:05'),(55,22,1,3,'2017-04-09 23:01:56'),(56,24,1,3,'2017-04-09 23:03:51'),(57,30,1,1,'2017-04-09 23:07:55'),(58,31,1,1,'2017-04-09 23:09:56'),(59,32,1,1,'2017-04-09 23:11:42'),(60,33,1,1,'2017-04-09 23:12:40'),(61,34,1,1,'2017-04-09 23:15:09'),(62,35,1,1,'2017-04-09 23:16:24'),(63,36,1,1,'2017-04-09 23:19:30'),(64,37,1,1,'2017-04-09 23:20:48'),(65,38,1,1,'2017-04-12 07:21:05'),(66,16,1,2,'2017-04-13 17:08:36'),(67,21,1,2,'2017-04-13 17:09:21'),(68,16,1,3,'2017-04-13 17:42:53'),(69,31,1,3,'2017-04-13 18:51:38'),(70,23,1,3,'2017-04-13 20:46:12'),(71,8,1,3,'2017-04-13 20:47:05'),(72,37,1,5,'2017-04-13 20:49:10'),(73,37,1,3,'2017-04-13 20:49:36'),(74,39,1,1,'2017-04-15 07:11:29'),(75,26,1,5,'2017-04-22 21:24:30'),(76,26,1,3,'2017-04-22 21:24:50'),(77,29,1,3,'2017-04-22 21:29:47'),(78,35,1,3,'2017-04-22 21:30:41'),(79,2,1,5,'2017-05-03 23:00:54'),(80,27,1,5,'2017-05-03 23:02:45'),(81,27,1,3,'2017-05-03 23:03:01'),(82,21,1,3,'2017-05-06 15:26:10'),(83,6,1,3,'2017-05-06 20:02:31');
/*!40000 ALTER TABLE `sg_issue_transitions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sg_issue_types`
--

DROP TABLE IF EXISTS `sg_issue_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sg_issue_types` (
  `id_issue_type` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE latin1_spanish_ci NOT NULL,
  `icon` varchar(45) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`id_issue_type`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sg_issue_types`
--

LOCK TABLES `sg_issue_types` WRITE;
/*!40000 ALTER TABLE `sg_issue_types` DISABLE KEYS */;
INSERT INTO `sg_issue_types` VALUES (1,'Bug','glyphicon-fire red'),(2,'Improvement','glyphicon-new-window blue'),(3,'Task','glyphicon-ok-sign yellow'),(4,'New Feature','glyphicon-plus-sign green');
/*!40000 ALTER TABLE `sg_issue_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sg_issues`
--

DROP TABLE IF EXISTS `sg_issues`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sg_issues` (
  `id_issue` int(11) NOT NULL AUTO_INCREMENT,
  `key_issue` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `id_project` int(11) NOT NULL,
  `author` int(11) NOT NULL,
  `subject` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `id_issue_type` int(11) NOT NULL,
  `id_issue_priority` int(11) NOT NULL,
  `id_issue_resolution` int(11) DEFAULT NULL,
  `id_issue_status` int(11) NOT NULL,
  `id_component` int(11) DEFAULT NULL,
  `description` text COLLATE latin1_spanish_ci NOT NULL,
  `creation` datetime NOT NULL,
  `modification` datetime DEFAULT NULL,
  `assigned_to` int(11) DEFAULT NULL,
  `counter` int(11) DEFAULT NULL,
  `resolved` datetime DEFAULT NULL,
  PRIMARY KEY (`id_issue`),
  UNIQUE KEY `issue__un` (`key_issue`,`id_project`),
  KEY `pro_fk_5_idx` (`id_project`),
  KEY `user_fk_5_idx` (`author`),
  KEY `issue_type_fk_1_idx` (`id_issue_type`),
  KEY `issue_prior_fk_1_idx` (`id_issue_priority`),
  KEY `issue_res_fk_1_idx` (`id_issue_resolution`),
  KEY `issue_stat_fk_1_idx` (`id_issue_status`),
  KEY `comp_fk_1_idx` (`id_component`),
  KEY `user_fk_8_idx` (`assigned_to`),
  CONSTRAINT `comp_fk_1` FOREIGN KEY (`id_component`) REFERENCES `sg_components` (`id_component`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `issue_prior_fk_1` FOREIGN KEY (`id_issue_priority`) REFERENCES `sg_issue_priority` (`id_issue_priority`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `issue_res_fk_1` FOREIGN KEY (`id_issue_resolution`) REFERENCES `sg_issue_resolution` (`id_issue_resolution`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `issue_stat_fk_1` FOREIGN KEY (`id_issue_status`) REFERENCES `sg_issue_status` (`id_issue_status`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `issue_type_fk_1` FOREIGN KEY (`id_issue_type`) REFERENCES `sg_issue_types` (`id_issue_type`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pro_fk_5` FOREIGN KEY (`id_project`) REFERENCES `sg_projects` (`id_project`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_fk_5` FOREIGN KEY (`author`) REFERENCES `sg_users` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_fk_8` FOREIGN KEY (`assigned_to`) REFERENCES `sg_users` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sg_issues`
--

LOCK TABLES `sg_issues` WRITE;
/*!40000 ALTER TABLE `sg_issues` DISABLE KEYS */;
INSERT INTO `sg_issues` VALUES (1,'BOOKER-1',1,1,'Format filesize humanized',3,3,NULL,1,2,'Presentar el filesize de los archivos en mb','2017-03-12 00:36:48',NULL,1,1,NULL),(2,'SEGUIDINO-1',2,1,'Agregar logo proyecto',3,4,1,5,5,'Permitir incorporar y utilizar un logo por proyecto','2017-03-12 00:39:17','2017-05-03 23:00:54',1,1,'2017-05-03 23:00:54'),(4,'SEGUIDINO-2',2,1,'Multiples componentes por issue',2,2,1,3,6,'Se debe permitir asociar varios componentes a un issue','2017-03-20 00:21:38','2017-04-01 15:27:13',1,2,'2017-04-01 23:23:15'),(5,'SEGUIDINO-3',2,1,'Ver issue despues de crearlo',1,1,1,3,6,'Se debe navegar directo a la pagina de consulta de un issue despues de crearlo','2017-03-20 00:42:36','2017-03-20 21:44:55',1,3,'2017-03-20 21:44:55'),(6,'SEGUIDINO-4',2,1,'Incluir detalles del issue en la pantalla de consulta mod',3,2,1,3,6,'La pantalla de consulta de issue debe incluir los detalles como: archivos adjuntos, comentarios, historial de cambios','2017-03-20 21:20:43','2017-05-06 20:02:31',1,4,'2017-05-06 20:02:31'),(7,'SEGUIDINO-5',2,1,'Asignar colores a los iconos de los elementos de un issue',2,3,1,3,6,'Usar colores en los iconos del tipo, la prioridad y el estatus de un issue de acuerdo a su criticidad','2017-03-20 21:22:55','2017-03-22 23:00:41',1,5,'2017-04-02 00:54:48'),(8,'SEGUIDINO-6',2,1,'Implementar flujo de atencion de issue',2,1,1,3,6,'Se requiere implementar el flujo de atencion de issues','2017-03-20 21:30:42','2017-04-13 20:47:05',1,6,'2017-04-13 20:47:05'),(9,'SEGUIDINO-7',2,1,'Mostrar las fechas en forma relativa al momento actual',2,3,1,3,6,'Las fechas recientes deben mostrarse relativas al momento actual por ejemplo \"hace dos horas\", \"ayer\", \"la semana pasada\", etc.','2017-03-20 21:34:10','2017-04-09 22:44:30',1,7,'2017-04-09 22:44:30'),(10,'SEGUIDINO-8',2,1,'Implementar menu de issues',3,2,NULL,1,6,'Implementar las opciones dinamicas del menu de issues, como el acceso a los ultimos creados y a los ultimos consultados','2017-03-20 21:40:08',NULL,1,8,NULL),(11,'SEGUIDINO-9',2,1,'Marcar issues cerrados',1,4,1,3,7,'Cuando un issue se encuentre en estado \"closed\" marcarlo con un estilo para que la llave del issue aparezca rallada por la mitad','2017-03-26 17:27:38','2017-04-09 22:48:32',1,9,'2017-04-09 22:48:32'),(12,'SEGUIDINO-10',2,1,'Editar el tipo de un issue \"inline\"',2,3,NULL,1,6,'Se debe permitir editar el tipo de un issue desde la pantalla de consulta de issue','2017-03-31 21:40:01',NULL,1,10,NULL),(13,'SEGUIDINO-11',2,1,'Alineacion de los datos del proyecto en un issue',1,5,1,3,6,'Se debe alinear correctamente el nombre y la clave del proyecto en la consulta de un issue','2017-03-31 21:41:31','2017-04-02 00:59:28',1,11,'2017-04-02 00:59:10'),(14,'SEGUIDINO-12',2,1,'Versionar Seguidino',3,3,1,3,7,'Agregar un proyecto Git para versionar el desarrollo de Seguidino','2017-03-31 22:05:50','2017-04-03 00:21:47',1,12,'2017-04-03 00:21:27'),(15,'SEGUIDINO-13',2,1,'Registrar transiciones',1,3,1,3,6,'Generar un registro de cada cambio de estatus de un issue, asi como el usuario que lo genero y la fecha','2017-03-31 23:11:47','2017-04-09 22:50:14',1,13,'2017-04-09 22:50:14'),(16,'SEGUIDINO-14',2,1,'Implementar tablas resumen de issues',3,3,1,3,NULL,'Se deben implementar las tablas de porcentajes de issues por proyecto y por los diferentes elementos de los issues','2017-04-01 23:11:14','2017-04-13 17:42:53',1,14,'2017-04-13 17:42:53'),(17,'SEGUIDINO-15',2,1,'Crear modelo \"Activity\"',3,2,1,3,NULL,'Se debe implementar el modelo \"Activity\", que es diferente al de \"History\" ya que indica otras actividades tanto de proyectos como de issues','2017-04-03 00:14:19','2017-04-09 22:56:33',1,15,'2017-04-09 22:55:47'),(18,'SEGUIDINO-16',2,1,'Valores en pantallas de resolver y cerrar issues',1,3,NULL,1,NULL,'Al resolver y cerrar un issue se cargan ciertos valores que ya tiene el issue como son la persona que tiene asignado el issue o el tipo de resolucion del mismo. Estos valores se cargan a traves de un combo el cual debe mantener preseleccionados los valores actuales del issue','2017-04-03 00:20:06',NULL,1,16,NULL),(19,'SEGUIDINO-17',2,1,'Agregar navegacion entre issues',2,3,1,3,NULL,'Se debe poder navegar de un issue a otro segun la consulta actual','2017-04-05 01:24:34','2017-04-09 22:59:18',1,17,'2017-04-09 22:59:18'),(20,'SEGUIDINO-18',2,1,'Incluir la fecha \"Resolved\" en vista de issue',2,3,1,3,NULL,'Cuando un issue ya se encuentre resuelte se debe mostrar la fecha \"Resolved\" en la seccion \"Dates\" de la vista del issue','2017-04-05 01:26:25','2017-04-09 23:00:05',1,18,'2017-04-09 23:00:05'),(21,'SEGUIDINO-19',2,1,'Registro de histories',1,3,1,3,NULL,'En la sección de history quitar el evento de cuando el issue es creado, dejar exclusivamente para actividades de modificación del issue.','2017-04-05 01:27:46','2017-05-06 15:26:10',1,19,'2017-05-06 15:26:10'),(22,'SEGUIDINO-20',2,1,'orden de histories',1,4,1,3,NULL,'La sección de history se ordena del más antiguo al más reciente.','2017-04-05 01:29:07','2017-04-09 23:01:56',1,20,'2017-04-09 23:01:56'),(23,'SEGUIDINO-21',2,1,'Orden de activities',2,4,1,3,NULL,'La sección de activity se orden del más reciente al más antiguo.','2017-04-05 01:31:41','2017-04-13 20:46:12',1,21,'2017-04-13 20:46:12'),(24,'SEGUIDINO-22',2,1,'Eventos de activities',2,1,1,3,NULL,'En la sección de activity se incluye desde la creación hasta que se cierra, la generación de comentarios y cualquier evento relacionado con el issue.','2017-04-05 01:32:31','2017-04-09 23:03:51',1,22,'2017-04-09 23:03:51'),(25,'SEGUIDINO-23',2,1,'Consulta de issues',1,3,NULL,1,NULL,'Se debe paginar la lista de consulta de issues','2017-04-05 01:33:15',NULL,1,23,NULL),(26,'SEGUIDINO-24',2,1,'Activities en summary de proyecto',2,3,1,3,NULL,'En el summary del proyecto se debe mostrar la \"Activity\" general de todos los issues del proyecto del más reciente al más antiguo.','2017-04-05 01:34:09','2017-04-22 21:24:50',1,24,'2017-04-22 21:24:30'),(27,'SEGUIDINO-25',2,1,'Responsable por complemento',1,3,1,3,NULL,'A los components se les debe incluir un responsable.','2017-04-05 01:35:02','2017-05-03 23:03:01',1,25,'2017-05-03 23:02:45'),(28,'SEGUIDINO-26',2,1,'Edicion de issues en linea',2,3,NULL,1,NULL,' Se debe implementar la funcionalidad para editar un issue. Los valores modificables son: Summary, Assignee, Priority, Components, Type, Comment.','2017-04-05 01:35:55',NULL,1,26,NULL),(29,'SEGUIDINO-27',2,1,'Agrandar formulario de creacion de issues',1,4,1,3,NULL,'Hacer mas grande el formulario de creacon de issues para que se vea completo','2017-04-05 01:36:59','2017-04-22 21:29:47',1,27,'2017-04-22 21:29:47'),(30,'SEGUIDINO-28',2,1,'Implementar adjuntar archivos e imagenes',3,3,NULL,1,NULL,'Se debe permitir adjuntar archivos de word, excel, texto, pdf y los formatos de imagen gif, jpg, png a un issue','2017-04-09 23:07:55',NULL,1,28,NULL),(31,'SEGUIDINO-29',2,1,'Humanizar fechas',2,3,1,3,NULL,'Se debe humanizar el uso de fechas (hoy, ayer, el jueves pasado, etc) excepto en el listado de issues','2017-04-09 23:09:56','2017-04-13 18:51:38',1,29,'2017-04-13 18:51:38'),(32,'SEGUIDINO-30',2,1,'Incluir avatar de usuario en la seccion people de un issue',1,4,NULL,1,NULL,'Se debe incluir el avatar de cada usuario de la seccion People de la consulta de un issue','2017-04-09 23:11:42',NULL,1,30,NULL),(33,'SEGUIDINO-31',2,1,'Links entre issues',3,3,NULL,1,NULL,'Se debe permitir crear links entre issues','2017-04-09 23:12:40',NULL,1,31,NULL),(34,'SEGUIDINO-32',2,1,'Estilo de comentarios',1,5,NULL,1,NULL,'Se debe mejorar la presentacion del listado de comentarios','2017-04-09 23:15:09',NULL,1,32,NULL),(35,'SEGUIDINO-33',2,1,'Estilo en botones principales',1,5,1,3,NULL,'Los botones principales de los formularios (el create, resolve, etc) se deben diferenciar del boton de cancel','2017-04-09 23:16:24','2017-04-22 21:30:41',1,33,'2017-04-22 21:30:41'),(36,'SEGUIDINO-34',2,1,'Implementar administracion de usuarios',3,3,NULL,1,NULL,'Se debe implementar la funcionalidad para dar de alta usuarios en el sistema, asi como para cambiar password, actualizar perfil y actualizar avatar','2017-04-09 23:19:30',NULL,1,34,NULL),(37,'SEGUIDINO-35',2,1,'Actualizar grafica de created vs resolved',1,3,1,3,NULL,'La grafia de created vs resolved no se esta actualizando al crear o resolver un issue','2017-04-09 23:20:48','2017-04-13 20:49:36',1,35,'2017-04-13 20:49:10'),(38,'BOOKER-2',1,1,'Implementar formulario de alta de ebook',3,3,NULL,1,NULL,'Crear funcionalidad para subir manualmente un libro asi como su imagen de portada','2017-04-12 07:21:05',NULL,1,2,NULL),(39,'SEGUIDINO-36',2,1,'Implementar alta de proyectos',3,3,NULL,1,NULL,'Se debe implementar la funcionalidad para dar de alta un proyecto con todos sus filtros default','2017-04-15 07:11:29',NULL,1,36,NULL);
/*!40000 ALTER TABLE `sg_issues` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sg_members`
--

DROP TABLE IF EXISTS `sg_members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sg_members` (
  `id_member` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `id_project` int(11) NOT NULL,
  `id_role` int(11) NOT NULL,
  PRIMARY KEY (`id_member`),
  UNIQUE KEY `members__un` (`id_user`,`id_project`,`id_role`),
  KEY `members_idx1` (`id_user`),
  KEY `members_idx2` (`id_project`),
  KEY `members_idx3` (`id_role`),
  CONSTRAINT `pro_fk_4` FOREIGN KEY (`id_project`) REFERENCES `sg_projects` (`id_project`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rol_fk_2` FOREIGN KEY (`id_role`) REFERENCES `sg_roles` (`id_role`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `users_fk_4` FOREIGN KEY (`id_user`) REFERENCES `sg_users` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sg_members`
--

LOCK TABLES `sg_members` WRITE;
/*!40000 ALTER TABLE `sg_members` DISABLE KEYS */;
INSERT INTO `sg_members` VALUES (1,1,1,1),(2,1,2,1),(3,2,1,2),(4,2,2,2);
/*!40000 ALTER TABLE `sg_members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sg_parameters`
--

DROP TABLE IF EXISTS `sg_parameters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sg_parameters` (
  `id_parameter` int(11) NOT NULL AUTO_INCREMENT,
  `param_key` varchar(45) CHARACTER SET latin1 NOT NULL,
  `param_value` varchar(100) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`id_parameter`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sg_parameters`
--

LOCK TABLES `sg_parameters` WRITE;
/*!40000 ALTER TABLE `sg_parameters` DISABLE KEYS */;
INSERT INTO `sg_parameters` VALUES (1,'PROJECT_AVATAR_BASE_PATH','C:Userscesarworkspaceseguidinocravatarsprojects'),(2,'USER_AVATAR_BASE_PATH','C:Userscesarworkspaceseguidinocravatarsusers');
/*!40000 ALTER TABLE `sg_parameters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sg_project_access`
--

DROP TABLE IF EXISTS `sg_project_access`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sg_project_access` (
  `id_project_access` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `id_project` int(11) NOT NULL,
  `access` datetime NOT NULL,
  PRIMARY KEY (`id_project_access`),
  UNIQUE KEY `p_access__un` (`id_user`,`id_project`),
  KEY `p_access_fk_13_idx` (`id_project`),
  CONSTRAINT `p_access_fk_13` FOREIGN KEY (`id_project`) REFERENCES `sg_projects` (`id_project`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `p_access_fk_14` FOREIGN KEY (`id_user`) REFERENCES `sg_users` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sg_project_access`
--

LOCK TABLES `sg_project_access` WRITE;
/*!40000 ALTER TABLE `sg_project_access` DISABLE KEYS */;
INSERT INTO `sg_project_access` VALUES (1,1,1,'2017-08-27 16:06:23'),(2,1,2,'2017-08-27 16:06:26'),(3,2,2,'2017-05-10 23:23:31'),(4,2,1,'2017-05-10 23:22:49');
/*!40000 ALTER TABLE `sg_project_access` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sg_project_categories`
--

DROP TABLE IF EXISTS `sg_project_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sg_project_categories` (
  `id_project_category` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`id_project_category`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sg_project_categories`
--

LOCK TABLES `sg_project_categories` WRITE;
/*!40000 ALTER TABLE `sg_project_categories` DISABLE KEYS */;
INSERT INTO `sg_project_categories` VALUES (1,'czar software');
/*!40000 ALTER TABLE `sg_project_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sg_project_types`
--

DROP TABLE IF EXISTS `sg_project_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sg_project_types` (
  `id_project_type` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(45) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`id_project_type`),
  UNIQUE KEY `description_UNIQUE` (`description`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sg_project_types`
--

LOCK TABLES `sg_project_types` WRITE;
/*!40000 ALTER TABLE `sg_project_types` DISABLE KEYS */;
INSERT INTO `sg_project_types` VALUES (1,'BUG TRACKING');
/*!40000 ALTER TABLE `sg_project_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sg_projects`
--

DROP TABLE IF EXISTS `sg_projects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sg_projects` (
  `id_project` int(11) NOT NULL AUTO_INCREMENT,
  `id_project_type` int(11) NOT NULL,
  `id_project_category` int(11) DEFAULT NULL,
  `key_project` varchar(45) COLLATE latin1_spanish_ci NOT NULL,
  `name` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `id_user_manager` int(11) NOT NULL,
  `id_author` int(11) NOT NULL,
  `creation` datetime NOT NULL,
  `closed` tinyint(1) NOT NULL DEFAULT '0',
  `url` varchar(100) CHARACTER SET latin1 DEFAULT NULL,
  `avatar` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `description` varchar(400) COLLATE latin1_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id_project`),
  UNIQUE KEY `key_project_UNIQUE` (`key_project`),
  KEY `sg_users_fk_idx_2` (`id_user_manager`),
  KEY `sg_users_fk_idx_3` (`id_author`),
  KEY `project_type_fk_1_idx` (`id_project_type`),
  KEY `p_category_fk_1_idx` (`id_project_category`),
  CONSTRAINT `p_category_fk_1` FOREIGN KEY (`id_project_category`) REFERENCES `sg_project_categories` (`id_project_category`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `project_type_fk_1` FOREIGN KEY (`id_project_type`) REFERENCES `sg_project_types` (`id_project_type`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `users_fk_2` FOREIGN KEY (`id_user_manager`) REFERENCES `sg_users` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `users_fk_3` FOREIGN KEY (`id_author`) REFERENCES `sg_users` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sg_projects`
--

LOCK TABLES `sg_projects` WRITE;
/*!40000 ALTER TABLE `sg_projects` DISABLE KEYS */;
INSERT INTO `sg_projects` VALUES (1,1,1,'BOOKER','Booker - Library Collection',1,1,'2017-03-11 23:03:42',0,NULL,'/projects/BOOKER/avatar/13',NULL),(2,1,1,'SEGUIDINO','Seguidino - Bug Tracker',1,1,'2017-03-11 23:04:13',0,NULL,'/projects/SEGUIDINO/avatar/14',NULL);
/*!40000 ALTER TABLE `sg_projects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sg_roles`
--

DROP TABLE IF EXISTS `sg_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sg_roles` (
  `id_role` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`id_role`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sg_roles`
--

LOCK TABLES `sg_roles` WRITE;
/*!40000 ALTER TABLE `sg_roles` DISABLE KEYS */;
INSERT INTO `sg_roles` VALUES (1,'Administrators'),(2,'Developers'),(3,'Users');
/*!40000 ALTER TABLE `sg_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sg_transitions`
--

DROP TABLE IF EXISTS `sg_transitions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sg_transitions` (
  `id_transition` int(11) NOT NULL AUTO_INCREMENT,
  `id_issue_status_from` int(11) NOT NULL,
  `id_issue_status_to` int(11) NOT NULL,
  `id_rol` int(11) NOT NULL,
  PRIMARY KEY (`id_transition`),
  UNIQUE KEY `trans__un` (`id_issue_status_from`,`id_issue_status_to`,`id_rol`),
  KEY `issue_st_fk4_idx` (`id_issue_status_to`),
  KEY `rol_fk4_idx` (`id_rol`),
  CONSTRAINT `issue_st_fk3` FOREIGN KEY (`id_issue_status_from`) REFERENCES `sg_issue_status` (`id_issue_status`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `issue_st_fk4` FOREIGN KEY (`id_issue_status_to`) REFERENCES `sg_issue_status` (`id_issue_status`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rol_fk4` FOREIGN KEY (`id_rol`) REFERENCES `sg_roles` (`id_role`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sg_transitions`
--

LOCK TABLES `sg_transitions` WRITE;
/*!40000 ALTER TABLE `sg_transitions` DISABLE KEYS */;
INSERT INTO `sg_transitions` VALUES (2,1,2,1),(1,1,2,2),(17,1,3,1),(18,1,3,2),(19,1,5,1),(20,1,5,2),(5,2,1,1),(3,2,1,2),(21,2,3,1),(22,2,3,2),(6,2,5,1),(4,2,5,2),(15,3,4,1),(16,3,4,3),(12,4,2,1),(11,4,2,2),(23,4,3,1),(24,4,3,2),(14,4,5,1),(13,4,5,2),(9,5,3,1),(10,5,3,3),(7,5,4,1),(8,5,4,3);
/*!40000 ALTER TABLE `sg_transitions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sg_users`
--

DROP TABLE IF EXISTS `sg_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sg_users` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) COLLATE latin1_spanish_ci NOT NULL,
  `password` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `name` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `email` varchar(45) COLLATE latin1_spanish_ci NOT NULL,
  `locked` tinyint(1) NOT NULL DEFAULT '0',
  `expired` tinyint(1) NOT NULL DEFAULT '0',
  `active` tinyint(1) NOT NULL DEFAULT '0',
  `avatar` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sg_users`
--

LOCK TABLES `sg_users` WRITE;
/*!40000 ALTER TABLE `sg_users` DISABLE KEYS */;
INSERT INTO `sg_users` VALUES (1,'admin','$2a$10$czTwlfml721txZ87/FHCTOUfCdQvnetDvejYL6rvvH9h1VsUYxmpK','Cesar Sevilla','czar.sevilla@gmail.com',0,0,1,'/img/useravatar.png'),(2,'user1','$2a$10$czTwlfml721txZ87/FHCTOUfCdQvnetDvejYL6rvvH9h1VsUYxmpK','usern1','cesar.sevilla@qacg.com',0,0,1,'/img/useravatar.png');
/*!40000 ALTER TABLE `sg_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'seguidino'
--

--
-- Dumping routines for database 'seguidino'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-07 19:57:20
