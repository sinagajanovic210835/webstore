-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: webstore
-- ------------------------------------------------------
-- Server version	5.7.29-log

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
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorities`
--

LOCK TABLES `authorities` WRITE;
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT INTO `authorities` VALUES ('sina','ROLE_ADMIN');
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datum` timestamp NULL DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `payed` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `user_idx` (`userid`),
  CONSTRAINT `user` FOREIGN KEY (`userid`) REFERENCES `customer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill`
--

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_product`
--

DROP TABLE IF EXISTS `bill_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_product` (
  `bill_id` int(11) DEFAULT NULL,
  `prod_id` int(11) DEFAULT NULL,
  KEY `billid_idx` (`bill_id`),
  KEY `prodid_idx` (`prod_id`),
  CONSTRAINT `billid` FOREIGN KEY (`bill_id`) REFERENCES `bill` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `prodid` FOREIGN KEY (`prod_id`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_product`
--

LOCK TABLES `bill_product` WRITE;
/*!40000 ALTER TABLE `bill_product` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `e_mail` varchar(100) DEFAULT NULL,
  `home_adress` varchar(100) DEFAULT NULL,
  `home_place` varchar(50) DEFAULT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `korpa` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(150) DEFAULT NULL,
  `category` varchar(50) DEFAULT NULL,
  `pictureurl` varchar(500) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `stack` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (8,'Gigabyte GeForce GTX 1660 OC (GV-N1660OC-6GD) graficka kartica 6GB GDDR5 192bit','grafika','https://www.eponuda.com/racunari_graficke_kartice/gigabyte/1229802_l.jpg',32999.00,93),(9,'MSI AMD Radeon R5 230 2GB 64bit (R5-230-2GD3H LP) graficka karta','grafika','https://img.gigatron.rs/img/products/large/image542039b1cd9b2.jpg',6799.00,3),(10,'Cooler Master MasterBox MB320L ARGB (MCB-B320L-KGNN-S01)  9590','kuciste','https://www.eponuda.com/racunari_kucista/cooler_master/1249023_l.jpg',9599.00,4),(11,'Cooler Master MasterBox MB511D (MCB-B511D-KANB50-S00) modularno kuciste sa napajanjem 500W crno','kuciste','https://www.eponuda.com/racunari_kucista/cooler_master/1247691_l.jpg',13999.00,40),(12,'IG-Max TWR 0651 kuciste sa napajanjem 560W crno','kuciste','https://www.pinshop.rs/images/p/3/5/8/6/3/35863.jpg',3599.00,71),(14,'MS SIRIUS II 500 kuciste sa napajanjem 500W','kuciste','https://www.ctshop.rs/img/products/2018-02-03/ms-sirius-ii-500-kuciste-sa-napajanjem-500w_IlGIE_4.jpg',3499.00,70),(15,'Kingston (KVR24N17S8_8) DIMM DDR4 8GB 2400MHz memorija','memorija','https://www.eponuda.com/racunari_ram_memorija/kingston/1047922_l.jpg',4999.00,87),(16,'Kingston HyperX Fury Black (HX424C15FB3_4) memorija DIMM DDR4 4GB 2400MHz','memorija','https://www.ctshop.rs/product/img/memorije-za-desktop/kingston-hyperx-fury-blackhx424c15fb3-4memorija-dimm-ddr4-4gb-2400mhz/normal/73322783_0477011953.jpg',4099.00,82),(17,'Kingston HyperX XMP Predator (HX430C15PB3_8) memorija DIMM DDR4 8GB 3000MHz','memorija','https://www.ctshop.rs/product/img/memorije-za-desktop/kingston-hyperx-xmp-predatorhx430c15pb3-8memorija-dimm-ddr4-8gb-3000mhz-/normal/37061252_9407696874.jpg',6499.00,46),(18,'Kingston HyperX XMP Predator RGB (HX440C19PB3AK2_16) memorija DIMM DDR4 16GB (2x8GB) 4000MHz','memorija','https://www.ctshop.rs/product/img/memorije-za-desktop/kingston-hyperx-xmp-predator-rgbhx429c15pb3ak2-16memorija-dimm-ddr4-16gb2x8gb2933mhz-/normal/55179156_7604062909.jpg',29299.00,66),(19,'Kingston HyperX Fury RGB (HX424C15FB3A_8) memorija DIMM DDR4 8GB 2400MHz','memorija','https://www.ctshop.rs/product/img/memorije-za-desktop/kingston-hyperx-fury-rgbhx424c15fb3a-8memorija-dimm-ddr4-8gb-2400mhz/normal/73369882_7981583609.jpg',6499.00,89),(20,'LC Power LC420H-12 Napajanje 420W','napajanje','https://www.eponuda.com/racunari_napajanja/lc_power/485049_l.jpg',3199.00,43),(21,'LC Power LC500H-12 Napajanje 500W','napajanje','https://img.gigatron.rs/img/products/large/image5af9a0b17428a.png',4199.00,91),(22,'LC Power LC600H-12 Napajanje 600W','napajanje','https://img.gigatron.rs/img/products/large/image558669823759b.png',4799.00,77),(23,'MS MS-500 napajanje 500W','napajanje','https://www.winwin.rs/media/catalog/product/474/71/4747170.jpg',1790.00,86),(25,'Njoy Ayrus 450 (PWPS-045P02Y-BU01B) napajanje 450W','napajanje','https://www.eponuda.com/racunari_napajanja/njoy/987555_l.jpg',5499.00,90),(26,'ASUS PRIME A320M-K Maticna ploca AM4','ploca','https://static.tehnomanija.rs/UserFiles/products/132421.jpg',5999.00,94),(27,'Asus PRIME H310M-K R2.0 maticna ploca','ploca','https://www.eponuda.com/racunari_maticne_ploce/asus/1208235_l.jpg',7499.00,81),(28,'Gigabyte AMD MB GA-E2500N maticna ploca','ploca','https://img.gigatron.rs/img/products/large/image5d711d12e8072.png',5499.00,85),(29,'Gigabyte GA-A320M-H rev.1.1 maticna ploca','ploca','https://www.bcgroup-online.com/upload/m/53953-gigabyte-ga-a320m-h-rev-1-1-3.jpg',5999.00,95),(30,'AMD Athlon X4 950 procesor Quad Core 3.5GHz (3.8GHz) Box socket AM4','procesor','https://www.winwin.rs/media/catalog/product/682/63/6826380.jpg',6499.00,100),(31,'AMD Ryzen 5 2600 procesor Hexa Core 3.4GHz (3.9GHz) socket AM4','procesor','https://www.eponuda.com/racunari_procesori/amd/1167975_l.jpg',19999.00,89),(32,'AMD Ryzen 5 3400G Quad Core procesor 3.7GHz (4.2GHz) socket AM4 Box','procesor','https://www.ctshop.rs/product/img/procesori/amd-ryzen-5-3400g-quad-core-procesor-3-7ghz4-2ghzsocket-am4-box/normal/71523949_3069720173.jpg',20999.00,96),(33,'Intel Core i5 9400F procesor Hexa Core 2.9GHz (4.1GHz) Box socket 1151 (8. i 9. gen.)','procesor','https://img.gigatron.rs/img/products/large/image5c6fb730cd201.png',21999.00,98),(34,'Intel Pentium Gold G5400 procesor Dual Core 3.7GHz Box socket 1151','procesor','https://www.eponuda.com/racunari_procesori/intel/1176522_l.jpg',7599.00,96),(35,'ASUS DRW-24D5MT DVD RW DVD Rezac Crni','optika','https://img.gigatron.rs/img/products/large/image57d81c9766be3.png',1999.00,89),(36,'Asus SDRW-08D2S-U LITE eksterni DVD RW crni','optika','https://www.ctshop.rs/img/products/2020-04-23/asus-sdrw-08d2s-u-lite-eksterni-dvd-rw-crni_jCx3h_3.jpg',3990.00,78),(37,'LG GH24NSD5 DVD-RW SATA rezac crni bulk','optika','https://www.ctshop.rs/product/img/dvd-rw-i-blu-ray/lg-gh24nsd5-dvd-rw-sata-rezacrni-bulk/normal/65523799_7719850135.jpg',1990.00,85),(38,'LG GP57EB40 Externi DVD Rezac Crni','optika','https://www.ctshop.rs/product/img/dvd-rw-i-blu-ray/lg-gp57eb40-externidvd-rezac-crni/normal/67211612_0541350091.jpg',3999.00,83),(39,'Transcend TS8XDVDS-K Eksterni DVD Rezac Crni','optika','https://static.tehnomanija.rs/UserFiles/products/68116.jpg',3499.00,90),(40,'Asus PRIME H310M-R R2.0 maticna ploca','ploca','https://img.gigatron.rs/img/products/large/image5bfbdd65eea0f.png',7499.00,100),(41,'Toshiba P300 (HDWD120EZSTA) hard disk 2TB SATA III 64MB 3.5\"','hard_disk','https://www.eponuda.com/racunari_hard_diskovi/toshiba/1125272_l.jpg',10499.00,80),(42,'Toshiba P300 Series (HDWD130UZSVA) hard disk 3TB 3.5\" SATA III bulk','hard_disk','https://www.ctshop.rs/product/img/hard-diskovi/toshiba-p300-serieshdwd130uzsvahard-disk-3tb-3-5sata-iii-bulk/normal/29749831_0687.jpg',10199.00,80),(43,'WD 1TB SATA III 64MB WD10EZEX Blue Hard disk 3.5 \"','hard_disk','https://www.winwin.rs/media/catalog/product/116/26/1162692.jpg',6499.00,63),(44,'Western Digital 1TB SATA III 64MB 10EFRX IntelliPower Red Hard disk 3.5\"','hard_disk','https://pcpractic.b-cdn.net/media/catalog/product/cache/f44c3e6a7fb440583035fd991bc1f67e/s/_/s_21854_21_4.jpg',7490.00,68),(45,'Western Digital Purple (WD10PURZ) hard disk 1TB 3.5\" SATA III 5999.jpg','hard_disk','https://img.gigatron.rs/img/products/large/image5b1108b71d018.png',5999.00,83),(54,'Cooler Master Hyper H410R RGB (RR-H410-20PC-R1) procesorski hladnjak','kuleroprema','https://img.gigatron.rs/img/products/large/image5e4d5a11f1102.png',4490.00,36),(56,'Cooler Master MasterFan MF120 Halo (MFL-B2DN-18NPA-R1) ventilator 120mmx120mmx25mm','kuleroprema','https://img.gigatron.rs/img/products/large/image5e62569712ed6.png',2190.00,80),(57,'Halnziye HY610 termalna pasta 2g','kuleroprema','https://www.ctshop.rs/img/products/2017-09-27/CPU00703_v_22_4.jpg',359.00,100),(58,'MS FREEZE 33LED ventilator 120x120x25mm zeleni','kuleroprema','https://www.winwin.rs/media/catalog/product/864/07/8640743.jpg',690.00,31),(60,'LeadsaiL Rechargeable Wireless Mouse,Computer Mouse Wireless,Cordless Mouse Silent Click,Mini USB Wireless ','kuleroprema','https://images-na.ssl-images-amazon.com/images/I/612kkNU344L._AC_SL1500_.jpg',1200.00,41),(61,'Computer Mouse of 2016 New Model','kuleroprema','https://image.made-in-china.com/2f0j00asCTKdHYJfgI/Computer-Mouse-of-2016-New-Model.jpg',300.00,99),(63,'Samsung HM641JI HardDisk','hard_disk','https://images-na.ssl-images-amazon.com/images/I/61c5hmmtFlL._AC_SX425_.jpg',6999.00,17),(64,'GV-N208TAORUS X-11GC ','grafika','https://baito.rs/uploads/product/CdoCEMr4325z7ty6-300x300.jpg',193000.00,42),(70,'Asus Phoenix GeForce GTX1660Ti (PH-GTX1660TI-6G) graficka kartica 6GB GDDR6 192bit','grafika','https://img.gigatron.rs/img/products/large/image5c7ce4587726e.png',43499.00,17);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(68) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('sina','{bcrypt}$2y$12$VeCVuqFz/L7xaLv.1mvRPuDek7d3thmRsNXEcpzfx6hxc9S2PH/96',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'webstore'
--

--
-- Dumping routines for database 'webstore'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-03 15:31:09
