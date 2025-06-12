-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: quizzera
-- ------------------------------------------------------
-- Server version	8.0.41

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `answers`
--

DROP TABLE IF EXISTS `answers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `answers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `question_id` int DEFAULT NULL,
  `answer_text` varchar(200) DEFAULT NULL,
  `is_correct` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `question_id` (`question_id`),
  CONSTRAINT `answers_ibfk_1` FOREIGN KEY (`question_id`) REFERENCES `questions` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answers`
--

LOCK TABLES `answers` WRITE;
/*!40000 ALTER TABLE `answers` DISABLE KEYS */;
INSERT INTO `answers` VALUES (1,1,'1',0),(2,1,'23',0),(3,1,'4',1),(4,1,'5',0),(5,2,'20',1),(6,2,'9',0),(7,2,'4/5',0),(8,2,'45',0),(9,3,'11',0),(10,3,'2',1),(11,3,'0',0),(12,3,'-2',0),(13,4,'20',0),(14,4,'22',0),(15,4,'1111',0),(16,4,'121',1),(17,5,'0',1),(18,5,'20',0),(19,5,'40',0),(20,5,'-20',0),(21,6,'24',1),(22,6,'9',0),(23,6,'8',0),(24,6,'22',0),(25,7,'3',0),(26,7,'27',0),(27,7,'9',1),(28,7,'1',0),(29,8,'5',1),(30,8,'2',0),(31,8,'3',0),(32,8,'4',0),(33,9,'30',0),(34,9,'300',1),(35,9,'3',0),(36,9,'3000',0),(37,10,'12345235',0),(38,10,'566544356',0),(39,10,'0',1),(40,10,'8678678678',0),(41,11,'Abraham Lincoln',0),(42,11,'Thomas Jefferson',0),(43,11,'George Washington',1),(44,11,'John Adams',0),(45,12,'1942',0),(46,12,'1945',1),(47,12,'1948',0),(48,12,'1950',0),(49,13,'Mongols',1),(50,13,'Japanese',0),(51,13,'Russians',0),(52,13,'Persians',0),(53,14,'Ferdinand Magellan',0),(54,14,'Marco Polo',0),(55,14,'Christopher Columbus',1),(56,14,'Leif Erikson',0),(57,15,'Britannic',0),(58,15,'Queen Mary',0),(59,15,'Titanic',1),(60,15,'Lusitania',0),(61,16,'Ottoman Empire',0),(62,16,'Byzantine Empire',0),(63,16,'Roman Empire',1),(64,16,'Persian Empire',0),(65,17,'World war I',0),(66,17,'The cold war',0),(67,17,'The great depression',1),(68,17,'The cuban missile crisis',0),(69,18,'Neville Chamberlain',0),(70,18,'Winston Churchill',1),(71,18,'Tony Blair',0),(72,18,'Margaret Thatcher',0),(73,19,'Boston',0),(74,19,'Philadelphia',1),(75,19,'New York',0),(76,19,'Washington,D.C',0),(77,20,'USA and Germany',0),(78,20,'USA and China',0),(79,20,'USA and Soviet Union',1),(80,20,'USA and Japan',0),(81,21,'5',0),(82,21,'-7',0),(83,21,'7',1),(84,21,'10',0),(85,22,'20',1),(86,22,'200',0),(87,22,'11',0),(88,22,'12',0),(89,23,'not defined',1),(90,23,'0',0),(91,23,'1111111',0),(92,23,'33',0),(93,24,'22222',0),(94,24,'44',1),(95,24,'0',0),(96,24,'4',0),(97,25,'8',0),(98,25,'9',0),(99,25,'3',0),(100,25,'5',1),(101,26,'234',0),(102,26,'44',0),(103,26,'108',1),(104,26,'223',0),(105,27,'-1',0),(106,27,'1',0),(107,27,'0',0),(108,27,'infinity',1),(109,28,'3',1),(110,28,'4',0),(111,28,'55',0),(112,28,'66',0),(113,29,'1',1),(114,29,'11',0),(115,29,'111',0),(116,29,'1111',0),(117,30,'-1',0),(118,30,'0',1),(119,30,'1',0),(120,30,'2',0);
/*!40000 ALTER TABLE `answers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `questions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `quiz_id` int DEFAULT NULL,
  `question_text` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `quiz_id` (`quiz_id`),
  CONSTRAINT `questions_ibfk_1` FOREIGN KEY (`quiz_id`) REFERENCES `quizzes` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions`
--

LOCK TABLES `questions` WRITE;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` VALUES (1,1,'2+2'),(2,1,'4*5'),(3,1,'1+1'),(4,1,'11*11'),(5,1,'5+5+5+5-20'),(6,1,'2*3*4'),(7,1,'81/9'),(8,1,'555/111'),(9,1,'100+200'),(10,1,'10*10*7*6*5*4*3*0*3*48*66'),(11,2,'Who was the first President of the United States?'),(12,2,'In which year did WW2 end?'),(13,2,'The Great Wall of China was primarily built to protect against which group?'),(14,2,'Who discovered America in 1492'),(15,2,'What was the name of the ship that sank in 1912 on its maiden voyage?'),(16,2,'Which empire was ruled by Julius Caesar?'),(17,2,'What majos event began on October 29,1929'),(18,2,'Who was the british prime minister during most of world war 2?'),(19,2,'Where was the Declaration of Independence signed?'),(20,2,'The cold war was primarily between which two countries?'),(21,3,'sqrt49'),(22,3,'2*10'),(23,3,'1111111/0'),(24,3,'22+22'),(25,3,'1+1+1*3'),(26,3,'2*4+(5+5)*10'),(27,3,'0/0'),(28,3,'2+1'),(29,3,'1*1*1*1*1*1*1'),(30,3,'0+0*(-1)');
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quizzes`
--

DROP TABLE IF EXISTS `quizzes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quizzes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `created_by` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quizzes`
--

LOCK TABLES `quizzes` WRITE;
/*!40000 ALTER TABLE `quizzes` DISABLE KEYS */;
INSERT INTO `quizzes` VALUES (1,'Math Quiz','admin'),(2,'History','username3'),(3,'math quiz by user','username3');
/*!40000 ALTER TABLE `quizzes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(300) DEFAULT NULL,
  `is_admin` tinyint(1) DEFAULT '0',
  `quizzes_taken` int DEFAULT '0',
  `max_score_taken_times` int DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'username1','password1',0,0,0),(2,'username2','$2a$10$/IxEp8oiVM27ul.D6OYadu12ZQKBlZVPJWqQx.0JWrw1fb8Rq4N/S',0,12,10),(3,'username3','$2a$10$9vNu5XrxjYCHbWpI64kXLOYOJ4pkofguwPgGpc2upjzNJh6HLK8Mu',0,21,12),(4,'chantu','$2a$10$FkRVgTFUyBoDEDOs0f4MDO3DZQsCiFxMyS06gejNf65V5Tec0g21C',0,0,0),(5,'username4','$2a$10$ReaN.dT8zLlCaLnNXwy.6uIpet8.vgstSnMOYO1Ic.40DTBaSnuBK',0,2,1),(6,'blabla','$2a$10$ymoL/qdr86kZaIZMfObhqOTmYx6W5shU5KuoBidLhcjn/BaSjQwGK',0,0,0),(7,'andre','$2a$10$ksX0eIEYoaMEDPhrmYf3M.MAKwvjo3IWXv3d/mV3/ymrsmSXWTsbG',0,0,0),(8,'irakla','$2a$10$.6VX5jKiyvtcfCD7dNmMKOrLW/3ddM1Y840vF4eqnAyGIIyAl//XG',0,0,0),(9,'butukutu','$2a$10$rAVDuQ7BBDHiQd/M1rNbFOU5gvoipHvei9kUJQCFx74HsaJC69Dn6',0,11,5),(10,'mixailo','$2a$10$BVR0vRKCjrs0Dc7Dyq1y.OAV7pyHEzOKeiNcYtbpCI1xRNbVigC7a',0,0,0),(11,'longpassword','$2a$10$7ISzNyLDqrJu6zt4Isl7XeiPgDYkz1Lm9rhsZ5xtoFvRc.p56e01e',0,0,0),(12,'lukaluka','$2a$10$7Rei108T8tdrvZFwboHn1u6qj.KN2AhK.KvLnxaabW6OdhDPktitS',0,0,0),(13,'admin','$2a$10$dTNmL6IbLaRcssJvUmJ3q.2a/.8AS..756x16PuKeFbhk.YJtr6Wm',1,6,1),(14,'lukitela','$2a$10$voytiiRZ1LREaMkwgyLssOLpbxdm7XyZB/TtoSXrV9jndQIPswso2',0,0,0);
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

-- Dump completed on 2025-06-12 18:40:37
