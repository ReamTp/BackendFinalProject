CREATE DATABASE  IF NOT EXISTS `backend_api` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish2_ci */ /*!80016;
USE `backend_api`;
-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: backend_api
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `ballots`
--

DROP TABLE IF EXISTS `ballots`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ballots` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `total` double NOT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh7oljhwoywv82c3ryfwhkalhr` (`user_id`),
  CONSTRAINT `FKh7oljhwoywv82c3ryfwhkalhr` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ballots`
--

LOCK TABLES `ballots` WRITE;
/*!40000 ALTER TABLE `ballots` DISABLE KEYS */;
INSERT INTO `ballots` VALUES (8,'2021-05-14 19:00:00',50.25,34),(9,'2021-05-14 19:00:00',50.25,34),(15,'2021-11-26 19:00:00',14,34),(16,'2021-11-26 19:00:00',14,34),(17,'2021-11-26 19:00:00',14,34);
/*!40000 ALTER TABLE `ballots` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ballots_products`
--

DROP TABLE IF EXISTS `ballots_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ballots_products` (
  `ballot_id` int NOT NULL,
  `product_id` int NOT NULL,
  `quantity` int NOT NULL,
  KEY `ballot_id_idx` (`ballot_id`),
  KEY `product_idx` (`product_id`),
  CONSTRAINT `ballot` FOREIGN KEY (`ballot_id`) REFERENCES `ballots` (`id`),
  CONSTRAINT `product` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ballots_products`
--

LOCK TABLES `ballots_products` WRITE;
/*!40000 ALTER TABLE `ballots_products` DISABLE KEYS */;
INSERT INTO `ballots_products` VALUES (8,2,3),(8,4,4),(8,5,8),(9,2,3),(9,4,4),(9,5,8),(15,3,1),(15,15,1),(16,3,1),(16,15,1),(17,3,1),(17,15,1);
/*!40000 ALTER TABLE `ballots_products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) COLLATE utf8_spanish2_ci NOT NULL,
  `name` varchar(255) COLLATE utf8_spanish2_ci NOT NULL,
  `state` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'A','Hamburguesas',_binary ''),(2,'B','Gaseosas',_binary ''),(3,'C','Frituras',_binary ''),(4,'D','Batidos',_binary ''),(5,'E','Bocadillos',_binary ''),(6,'F','Pizzas',_binary ''),(7,'G','Tacos',_binary ''),(8,'H','Snacks',_binary ''),(9,'I','Jugos',_binary ''),(10,'J','Agua Mineral',_binary '');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cities`
--

DROP TABLE IF EXISTS `cities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cities` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_spanish2_ci NOT NULL,
  `state` bit(1) NOT NULL,
  `department_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcl2xocc3mnys8b84bw2dog35e` (`department_id`),
  CONSTRAINT `FKcl2xocc3mnys8b84bw2dog35e` FOREIGN KEY (`department_id`) REFERENCES `departments` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cities`
--

LOCK TABLES `cities` WRITE;
/*!40000 ALTER TABLE `cities` DISABLE KEYS */;
INSERT INTO `cities` VALUES (1,'Trujillo',_binary '',3),(2,'Viru',_binary '',3),(3,'Moche',_binary '',3);
/*!40000 ALTER TABLE `cities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departments`
--

DROP TABLE IF EXISTS `departments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `departments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_spanish2_ci NOT NULL,
  `state` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departments`
--

LOCK TABLES `departments` WRITE;
/*!40000 ALTER TABLE `departments` DISABLE KEYS */;
INSERT INTO `departments` VALUES (3,'La Libertad',_binary ''),(4,'Lima',_binary '');
/*!40000 ALTER TABLE `departments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `extras`
--

DROP TABLE IF EXISTS `extras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `extras` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_spanish2_ci NOT NULL,
  `price` float NOT NULL,
  `state` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `extras`
--

LOCK TABLES `extras` WRITE;
/*!40000 ALTER TABLE `extras` DISABLE KEYS */;
/*!40000 ALTER TABLE `extras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `detail` varchar(255) COLLATE utf8_spanish2_ci NOT NULL,
  `name` varchar(255) COLLATE utf8_spanish2_ci NOT NULL,
  `price` float NOT NULL,
  `state` bit(1) NOT NULL,
  `category_id` int NOT NULL,
  `image` varchar(255) COLLATE utf8_spanish2_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKog2rp4qthbtt2lfyhfo32lsw9` (`category_id`),
  CONSTRAINT `FKog2rp4qthbtt2lfyhfo32lsw9` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Hamburguesa','Hamburguesa Triple',8.5,_binary '',1,'https://i.pinimg.com/originals/e2/10/46/e2104678bb8d90e32461ff0b2df664e0.jpg'),(2,'Hamburguesa','Hamburguesa Clasica',5.5,_binary '',1,'https://lacocherachacla.com/wp-content/uploads/2021/03/1.Clasica.jpg'),(3,'Hamburguesa','Cheese Burguer',6,_binary '',1,'https://www.yumofchina.com/wp-content/uploads/2017/09/The-Perfect-American-Cheeseburger-1200x900.jpg'),(4,'Gaseosa','Coca Cola mediana',2.5,_binary '',2,'https://static.wixstatic.com/media/5bd0d7_d1bf1f49f9704b87aa871d8d99defca3~mv2.png/v1/fill/w_486,h_486,al_c,lg_1/5bd0d7_d1bf1f49f9704b87aa871d8d99defca3~mv2.png'),(5,'Gaseosa','Inka Cola mediana',2.5,_binary '',2,'https://www.chickenjaus.com.pe/wp-content/uploads/2020/04/inka-500-azucar.jpg'),(6,'Gaseosa','Crush Mediana',2,_binary '',2,'https://depotexpress.com.ar/tienda/wp-content/uploads/2020/06/GASEOSA-CRUSH-X-3.png'),(7,'Frituras','Papas Fritas',7,_binary '',3,'https://cdn2.cocinadelirante.com/sites/default/files/styles/gallerie/public/images/2020/06/papas-fritas-con-maicena-facil.jpg'),(8,'Batido','Batido de Fresa',6,_binary '',4,'https://www.rebanando.com/media/batido-3_crop.jpg/rh/batido-de-fresas.jpg'),(9,'Batido','Batido de Chocolate',6.5,_binary '',4,'https://www.hola.com/imagenes/cocina/recetas/20210910195845/batido-chocolate-nubes-marshmallows/0-993-113/batido-nubes-adob-m.jpg'),(10,'Batido','Batido de Vainilla',5.5,_binary '',4,'https://t2.uc.ltmcdn.com/images/9/5/0/img_como_hacer_un_batido_de_vainilla_26059_600_square.jpg'),(11,'Bocadillos','Pack de Empanadas',30,_binary '',5,'https://cdn.kiwilimon.com/recetaimagen/69/th5-640x640-11549.jpg'),(12,'Pizzas','Pizza Hawaiana',25,_binary '',6,'https://www.cardamomo.news/__export/1607212083706/sites/debate/img/2020/12/05/cxmo_hacer_pizza_hawaiana_casera_crop1607212006019.jpeg_1187729725.jpeg'),(13,'Pizzas','Pizza Americana',35,_binary '',6,'https://img.lagaceta.com.ar/fotos/notas/2020/03/23/tmb1_crecimiento-a-contramano-resto-economia-pizza-hut-necesita-mas-personal-838711-164201.jpg'),(14,'Tacos','Tacos al Pastor',8,_binary '',7,'https://elcomercio.pe/resizer/rE3JPrENG1vCY7FWa1OBeVnUOfw=/580x330/smart/filters:format(jpeg):quality(75)/cloudfront-us-east-1.images.arcpublishing.com/elcomercio/3BHJSBWBLBDTLKWVODRQGC3QLE.jpg'),(15,'Tacos','Tacos de carne',8,_binary '',7,'https://img.vixdata.io/pd/jpg-large/es/sites/default/files/imj/elgranchef/C/Como-hacer-tacos-de-carne-2.jpg'),(16,'Snacks','ChocoField',1.5,_binary '',8,'https://transmarket.pe/wp-content/uploads/2020/05/galleta-field-choco-soda.jpg'),(17,'Jugos','Jugo Surtido',3.5,_binary '',9,'https://lapanelaperu.com/wp-content/uploads/2020/06/SURTIDO.jpg'),(18,'Jugos','Jugo de Piña',2.5,_binary '',9,'https://www.consalud.es/estetic/uploads/s1/12/82/63/zumo_pina_beneficios_11052017_consalud.jpg'),(19,'Jugos','Jugo de Naranja',2,_binary '',9,'https://www.kuvingsperu.com.pe/wp-content/uploads/2020/07/zumo-de-naranja.jpg'),(20,'Agua Mineral','Agua San Mateo personal',2.5,_binary '',10,'https://cdn.shopify.com/s/files/1/0406/3916/8675/products/agua-sin-gas-san-mateo-600-ml_35273255-3cfc-4ccd-bc18-ea5fcd83d045.png?v=1633529633'),(21,'Agua Mineral','Agua Cielo personal',1.5,_binary '',10,'https://plazavea.vteximg.com.br/arquivos/ids/533799-450-450/1046239002.jpg'),(22,'Agua Mineral','Agua San Carlos personal',2,_binary '',10,'https://plazavea.vteximg.com.br/arquivos/ids/620366-450-450/20101206.jpg');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `direction` varchar(255) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_spanish2_ci NOT NULL,
  `last_name` varchar(255) COLLATE utf8_spanish2_ci NOT NULL,
  `name` varchar(255) COLLATE utf8_spanish2_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_spanish2_ci NOT NULL,
  `phone` int NOT NULL,
  `sex` varchar(255) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `state` bit(1) NOT NULL,
  `city_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`),
  KEY `FKn36jwt4acj3il2ixvv2c0ncco` (`city_id`),
  CONSTRAINT `FKn36jwt4acj3il2ixvv2c0ncco` FOREIGN KEY (`city_id`) REFERENCES `cities` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (34,'','Av. Panamericana N° 495','ream_tp@outlook.com','Arriaga Mendoza','Rafael Elias','$argon2id$v=19$m=1024,t=1,p=1$3mgBzQ21DaBkbzgdAutN/A$4NbcRN2o3BLIQkabmNH4pqc2LdhUvxXtAfnT4tsvu5k',956271174,'m',_binary '',2);
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

-- Dump completed on 2021-11-29  8:39:42
