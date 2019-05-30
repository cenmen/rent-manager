CREATE DATABASE  IF NOT EXISTS `bicycle_manage` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bicycle_manage`;
-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: bicycle_manage
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `bicycle_distribute_situation`
--

DROP TABLE IF EXISTS `bicycle_distribute_situation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bicycle_distribute_situation` (
  `bds_id` varchar(6) NOT NULL,
  `bds_rp_id` varchar(45) NOT NULL,
  `bds_distribute_num` int(11) DEFAULT NULL,
  `dbs_intend_num` int(11) DEFAULT NULL,
  `dbs_dispatch_plan` varchar(80) DEFAULT NULL,
  `dbs_dispatch_finish` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`bds_id`),
  KEY `bba_rp_id_idx` (`bds_rp_id`),
  CONSTRAINT `bds_rp_id` FOREIGN KEY (`bds_rp_id`) REFERENCES `rent_place` (`rp_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bicycle_distribute_situation`
--

LOCK TABLES `bicycle_distribute_situation` WRITE;
/*!40000 ALTER TABLE `bicycle_distribute_situation` DISABLE KEYS */;
INSERT INTO `bicycle_distribute_situation` VALUES ('BDS101','RP101',3,4,'调1辆车到RP102',0),('BDS102','RP102',5,4,'从RP101收1辆车',0);
/*!40000 ALTER TABLE `bicycle_distribute_situation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bicycle_purchase`
--

DROP TABLE IF EXISTS `bicycle_purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bicycle_purchase` (
  `bp_id` varchar(10) NOT NULL,
  `bp_brand` varchar(45) NOT NULL,
  `bp_model` varchar(45) NOT NULL,
  `bp_dateOfProduction` date NOT NULL,
  `bp_dateOfPurchase` date NOT NULL,
  `bp_isallocated` tinyint(4) NOT NULL,
  `bp_mark` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`bp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bicycle_purchase`
--

LOCK TABLES `bicycle_purchase` WRITE;
/*!40000 ALTER TABLE `bicycle_purchase` DISABLE KEYS */;
INSERT INTO `bicycle_purchase` VALUES ('BP10001','mobike','lite','2019-05-26','2019-05-27',1,NULL),('BP10002','mobike','lite','2019-05-27','2019-05-27',1,''),('BP10003','mobike','lite','2019-05-27','2019-05-27',1,''),('BP10004','mobike','lite','2019-05-27','2019-05-27',1,''),('BP10005','mobike','lite','2019-05-27','2019-05-27',1,''),('BP10006','mobike','lite','2019-05-27','2019-05-27',1,''),('BP10007','mobike','lite','2019-05-27','2019-05-27',1,''),('BP10008','mobike','lite','2019-05-27','2019-05-27',1,''),('BP10009','mobike','lite','2019-05-27','2019-05-27',0,''),('BP10010','mobike','lite','2019-05-27','2019-05-27',0,''),('BP10011','mobike','lite','2019-05-27','2019-05-27',0,''),('BP10012','mobike','lite','2019-05-27','2019-05-27',0,''),('BP10013','mobike','lite','2019-05-27','2019-05-27',0,''),('BP10014','mobike','lite','2019-05-27','2019-05-27',0,''),('BP10015','mobike2','lite2','2019-05-27','2019-05-27',0,'');
/*!40000 ALTER TABLE `bicycle_purchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bicycle_quality`
--

DROP TABLE IF EXISTS `bicycle_quality`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bicycle_quality` (
  `bq_id` int(11) NOT NULL AUTO_INCREMENT,
  `bq_bp_id` varchar(10) DEFAULT NULL,
  `bq_rp_id` varchar(6) DEFAULT NULL,
  `bq_trouble` varchar(45) DEFAULT NULL,
  `bq_ishandle` tinyint(4) DEFAULT NULL,
  `bq_isremand` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`bq_id`),
  KEY `bq_bp_id_idx` (`bq_bp_id`),
  KEY `bq_rp_id_idx` (`bq_rp_id`),
  CONSTRAINT `bq_bp_id` FOREIGN KEY (`bq_bp_id`) REFERENCES `bicycle_purchase` (`bp_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `bq_rp_id` FOREIGN KEY (`bq_rp_id`) REFERENCES `rent_place` (`rp_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bicycle_quality`
--

LOCK TABLES `bicycle_quality` WRITE;
/*!40000 ALTER TABLE `bicycle_quality` DISABLE KEYS */;
INSERT INTO `bicycle_quality` VALUES (14,'BP10003','RP102','丢失',1,0),(15,'BP10005','RP102','烧毁',1,1);
/*!40000 ALTER TABLE `bicycle_quality` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `information`
--

DROP TABLE IF EXISTS `information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `information` (
  `info_id` int(11) NOT NULL AUTO_INCREMENT,
  `info_from` varchar(6) NOT NULL,
  `info_to` varchar(6) NOT NULL,
  `info_type` varchar(12) NOT NULL,
  `info_content` varchar(100) NOT NULL,
  `info_status` tinyint(4) NOT NULL,
  PRIMARY KEY (`info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `information`
--

LOCK TABLES `information` WRITE;
/*!40000 ALTER TABLE `information` DISABLE KEYS */;
INSERT INTO `information` VALUES (1,'SYS','RP101','车辆分配','系统分配5辆车到你的租借点（RP101)',1),(34,'SYS','RP101','车辆调度','调1辆车到RP102',1),(35,'SYS','RP102','车辆调度','从RP101收1辆车',1),(36,'RP101','SYS','车辆调度','租借点RP101已完成调度（2019-05-27）',1),(37,'RP102','SYS','车辆调度','租借点RP102已完成调度（2019-05-27）',1),(38,'RP102','RP101','车辆转移','车辆（BP10008）已转移到你的租借点（RP101）',1),(40,'RP101','RP102','车辆转移','车辆（BP10008）已转移到你的租借点（RP102）',1),(41,'RP102','SYS','车辆质量','租借点（RP102）车辆（BP10003）已（丢失）请系统处理',1),(42,'RP102','SYS','车辆质量','租借点（RP102）车辆（BP10005）已（损坏）请系统处理',1),(44,'SYS','RP102','车辆质量','系统已登记丢失车辆（BP10003）请租借点（RP102）及时修改车辆状态',0),(45,'SYS','RP102','车辆质量','系统已处理并将车辆（BP10005）送回租借点（RP102）可正常使用',0);
/*!40000 ALTER TABLE `information` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rent_a_bicycle_manager`
--

DROP TABLE IF EXISTS `rent_a_bicycle_manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `rent_a_bicycle_manager` (
  `rbm_id` int(11) NOT NULL AUTO_INCREMENT,
  `rbm_bp_id` varchar(10) NOT NULL,
  `rbm_hirer_name` varchar(45) NOT NULL,
  `rbm_hirer_contact` varchar(45) NOT NULL,
  `rbm_hire_location` varchar(6) NOT NULL,
  `rbm_persent_location` varchar(6) DEFAULT NULL,
  `rbm_hire_time` timestamp NULL DEFAULT NULL,
  `rbm_retrieve_time` timestamp NULL DEFAULT NULL,
  `rbm_hirer_ID` varchar(45) DEFAULT NULL,
  `rbm_hirer_deposit` tinyint(4) DEFAULT NULL,
  `rbm_ischarge` tinyint(4) DEFAULT NULL,
  `rbm_hirer_pay` varchar(45) DEFAULT NULL,
  `rbm_istrouble` tinyint(4) DEFAULT NULL,
  `rbm_damage` varchar(45) DEFAULT NULL,
  `rbm_mark` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`rbm_id`),
  KEY `rbm_bp_id_idx` (`rbm_bp_id`),
  KEY `rbm_hire_location_idx` (`rbm_hire_location`),
  KEY `rbm_persent_location_idx` (`rbm_persent_location`),
  CONSTRAINT `rbm_bp_id` FOREIGN KEY (`rbm_bp_id`) REFERENCES `bicycle_purchase` (`bp_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `rbm_hire_location` FOREIGN KEY (`rbm_hire_location`) REFERENCES `rent_place` (`rp_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rent_a_bicycle_manager`
--

LOCK TABLES `rent_a_bicycle_manager` WRITE;
/*!40000 ALTER TABLE `rent_a_bicycle_manager` DISABLE KEYS */;
INSERT INTO `rent_a_bicycle_manager` VALUES (1,'BP10001','刘青云','13420121123','RP101','RP101','2019-05-27 04:02:10','2019-05-27 04:53:30','445381199610244889',1,1,'88',1,'199',NULL),(2,'BP10003','屎忽鬼','13420112245','RP101','RP102','2019-05-27 04:05:16','2019-05-27 04:39:45','445381199654511225',1,1,'88',1,'50',NULL),(3,'BP10001','刘家辉','13420112245','RP101','RP101','2019-05-27 04:59:38','2019-05-27 04:59:49','445381199654511225',1,1,'88',1,'50',NULL),(4,'BP10001','才徐坤','13420112245','RP101','RP101','2019-05-27 05:02:59','2019-05-27 05:04:24','445381199654511225',1,1,'88',1,'50',NULL),(5,'BP10002','李卤味','13420112245','RP101','RP101','2019-05-27 05:07:21','2019-05-27 05:07:29','445381199654511225',1,1,'100',0,'',NULL);
/*!40000 ALTER TABLE `rent_a_bicycle_manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rent_a_bicycle_status`
--

DROP TABLE IF EXISTS `rent_a_bicycle_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `rent_a_bicycle_status` (
  `rbs_rp_id` varchar(6) NOT NULL,
  `rbs_bp_id` varchar(10) NOT NULL,
  `rbs_bicycle_hire_status` tinyint(4) NOT NULL,
  `rbs_bicycle_quality_status` varchar(8) NOT NULL,
  PRIMARY KEY (`rbs_rp_id`,`rbs_bp_id`),
  KEY `rbs_a_bp_id_idx` (`rbs_bp_id`),
  KEY `rbs_a_rp_id_idx` (`rbs_rp_id`),
  CONSTRAINT `rbs_a_bp_id` FOREIGN KEY (`rbs_bp_id`) REFERENCES `bicycle_purchase` (`bp_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `rbs_a_rp_id` FOREIGN KEY (`rbs_rp_id`) REFERENCES `rent_place` (`rp_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rent_a_bicycle_status`
--

LOCK TABLES `rent_a_bicycle_status` WRITE;
/*!40000 ALTER TABLE `rent_a_bicycle_status` DISABLE KEYS */;
INSERT INTO `rent_a_bicycle_status` VALUES ('RP101','BP10001',0,'损坏'),('RP101','BP10002',0,'正常'),('RP101','BP10004',0,'正常'),('RP102','BP10003',1,'丢失'),('RP102','BP10005',0,'正常'),('RP102','BP10006',0,'正常'),('RP102','BP10007',0,'正常'),('RP102','BP10008',0,'正常');
/*!40000 ALTER TABLE `rent_a_bicycle_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rent_manager`
--

DROP TABLE IF EXISTS `rent_manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `rent_manager` (
  `rm_id` varchar(6) NOT NULL,
  `rm_role_id` int(11) NOT NULL,
  `rm_account` varchar(45) NOT NULL,
  `rm_password` varchar(45) NOT NULL,
  `rm_username` varchar(45) NOT NULL,
  `rm_contact` varchar(45) NOT NULL,
  `rm_create_time` date NOT NULL,
  `rm_last_time` date DEFAULT NULL,
  `rm_status` tinyint(4) NOT NULL,
  `rm_rp_id` varchar(6) NOT NULL,
  `rm_mark` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`rm_id`),
  KEY `rm_role_id_idx` (`rm_role_id`),
  KEY `rm_rp_id_idx` (`rm_rp_id`),
  CONSTRAINT `rm_role_id` FOREIGN KEY (`rm_role_id`) REFERENCES `thority` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `rm_rp_id` FOREIGN KEY (`rm_rp_id`) REFERENCES `rent_place` (`rp_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rent_manager`
--

LOCK TABLES `rent_manager` WRITE;
/*!40000 ALTER TABLE `rent_manager` DISABLE KEYS */;
INSERT INTO `rent_manager` VALUES ('RM1001',102,'rent1','123456','刘德华','13420121141','2019-05-27','2019-05-27',1,'RP101',NULL),('RM1002',102,'rent2','123456','花里胡哨','13420121154','2019-05-27',NULL,1,'RP102','');
/*!40000 ALTER TABLE `rent_manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rent_place`
--

DROP TABLE IF EXISTS `rent_place`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `rent_place` (
  `rp_id` varchar(6) NOT NULL,
  `rp_name` varchar(45) NOT NULL,
  `rp_place` varchar(45) NOT NULL,
  `rp_establish_time` date NOT NULL,
  `rp_mark` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`rp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rent_place`
--

LOCK TABLES `rent_place` WRITE;
/*!40000 ALTER TABLE `rent_place` DISABLE KEYS */;
INSERT INTO `rent_place` VALUES ('RP101','甲租借点','深圳市华容道','2019-05-27',NULL),('RP102','花里胡哨租借点','东莞花里胡哨街','2019-05-27','');
/*!40000 ALTER TABLE `rent_place` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_manager`
--

DROP TABLE IF EXISTS `sys_manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_manager` (
  `sm_id` varchar(6) NOT NULL,
  `sm_role_id` int(11) NOT NULL,
  `sm_account` varchar(45) NOT NULL,
  `sm_password` varchar(45) NOT NULL,
  `sm_username` varchar(45) NOT NULL,
  `sm_contact` varchar(45) NOT NULL,
  `sm_create_time` date NOT NULL,
  `sm_last_time` date DEFAULT NULL,
  `sm_status` tinyint(4) NOT NULL,
  `sm_mark` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`sm_id`),
  KEY `sm_role_id_idx` (`sm_role_id`),
  CONSTRAINT `sm_role_id` FOREIGN KEY (`sm_role_id`) REFERENCES `thority` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_manager`
--

LOCK TABLES `sys_manager` WRITE;
/*!40000 ALTER TABLE `sys_manager` DISABLE KEYS */;
INSERT INTO `sys_manager` VALUES ('SM1002',101,'sys_02','123456','系统管理员乙','345145211','2019-02-10','2019-02-19',1,'TestData_update');
/*!40000 ALTER TABLE `sys_manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thority`
--

DROP TABLE IF EXISTS `thority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `thority` (
  `id` int(11) NOT NULL,
  `role` varchar(45) NOT NULL,
  `thor` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thority`
--

LOCK TABLES `thority` WRITE;
/*!40000 ALTER TABLE `thority` DISABLE KEYS */;
INSERT INTO `thority` VALUES (101,'系统管理员','system'),(102,'租借点管理员','rent');
/*!40000 ALTER TABLE `thority` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-30 11:49:21
