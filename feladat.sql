CREATE DATABASE  IF NOT EXISTS `feladat` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `feladat`;
-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: feladat
-- ------------------------------------------------------
-- Server version	8.0.18

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
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `country` varchar(255) NOT NULL,
  `county` varchar(255) NOT NULL,
  `zip` int(11) NOT NULL,
  `village` varchar(255) NOT NULL,
  `street` varchar(255) NOT NULL,
  `number` varchar(45) NOT NULL,
  `userid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_address_user_id_idx` (`userid`),
  CONSTRAINT `FK_address_user_id` FOREIGN KEY (`userid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'Magyarország','Békés',5540,'Szarvas','Rákóczi Ferenc utca','23',1),(2,'Magyarország','Békés',5540,'Szarvas','Kis Pál utca','55',1),(3,'Magyarország','Békés',5540,'Szarvas','Tessedik','55',2);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` text NOT NULL,
  `price` int(11) NOT NULL,
  `issold` tinyint(4) NOT NULL DEFAULT '0',
  `isaccapted` tinyint(4) NOT NULL DEFAULT '0',
  `imagepath` varchar(255) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `sold_date` timestamp NULL DEFAULT NULL,
  `buyer` int(11) DEFAULT NULL,
  `owner` int(11) NOT NULL DEFAULT '1',
  `baddress` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_product_buyer_userid_idx` (`buyer`),
  KEY `fk_product_owner_uderid_idx` (`owner`),
  KEY `fk_product_bought_address_id_idx` (`baddress`),
  CONSTRAINT `FK_product_buyer_userid` FOREIGN KEY (`buyer`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_product_bought_address_id` FOREIGN KEY (`baddress`) REFERENCES `address` (`id`),
  CONSTRAINT `fk_product_owner_userid` FOREIGN KEY (`owner`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Acer Aspire 3 A315-42G-R0UU notebook','Az Aspire A315 sorozat általános, otthoni/irodai felhasználásra, szórakozásra a tökéletes partner, egy izgalmas, textil mintázatú készülékházban, belül elegáns szálcsiszolt felülettel.\n\nErőteljes kettő, illetve négymagos, 3. generációs AMD Ryzen processzora és az AMD Radeon grafikus meghajtója zökkenőmentes használatot biztosít számos felhasználási területen, legyen szó kisebb irodai munkákról vagy otthoni általános, multimédiás felhasználásról.\n\nNagyméretű, akár 1TB háttértára biztosítja, hogy rengeteg multimédiás tartalmat vihessünk magunkkal bárhová, akinek inkább a sebesség a fontos, opcióként elérhetőek bizonyos modellek SSD háttértárral is. A készülék 2 cellás akkumulátora pedig gondoskodik róla, hogy akár több órán át is üzemeljen egy feltöltéssel.\n\nLetisztult, egyszerű megjelenés, remek ár/érték arány.\n\nAlapadatok:\nAspire 3 / 15.6\" FHD 1920 x 1080 LED matt / AMD RYZEN 5-3500U Quad Core / 8GB DDR4 / 256GB PCIe SSD / AMD® Radeon 540X 2GB GDDR5',164999,0,1,'https://picsum.photos/300/300.jpg','2020-08-06 07:27:57',NULL,NULL,2,NULL),(2,'Lenovo IdeaPad S145 81UT0042HV Notebook','A hosszan tartó teljesítményre tervezett IdeaPad S145 stílusosan könnyű kialakításban nyújt nagy teljesítményű AMD processzorkapacitást. A tartós 15,6 colos laptop tökéletes a mindennapos hordozható számítógépes igényekhez, remek a hangzása és egész sor gyors, biztonságos tárhelyopciót kínál. Az akár AMD A-9 processzorokkal működő IdeaPad S145 úgy van kialakítva, hogy tartsa önnel a lépést, bármi legyen is a feladat. A biztonságos táreszközök egész sora választható hozzá, egyebek mellett hibrid SSD és merevlemez is, ami még gyorsabb válaszidőket biztosít. A mindössze 1,85 kg (4,08 font) kezdősúllyal induló IdeaPad S145 ideális társ az utazásban. Keskeny kijelzőkerete letisztult formavilágot és nagyobb megjelenítési területet eredményez. ',159999,0,1,'https://picsum.photos/300/300.jpg','2020-08-06 07:29:34',NULL,NULL,2,NULL),(3,'ASUS TUF Gaming FX506II-AL020 Notebook','Minden helyzet megoldására felfegyverkezve, a TUF Gaming A15 megbízható teljesítményt nyújt játékhoz, streameléshez, és a kettő között bármihez. A fürge 6 magos AMD Ryzen™ 4000 sorozatú CPU-jával akár 12 szálat aktiválhat, így könnyedén megbirkózik az igazán komoly többfeladatos munkavégzéssel is. A GeForce GTX™ 1650 dedikált GPU-val párosítva gördülékenyen tartja a magas képkocka-sebességet a legnépszerűbb játékokban. Az 512 SSD-nek KÖSZÖNHETŐEN bőségesen elegendő hellyel a hatalmas gyűjtemény bármely tagjánál felgyorsíthatod a betöltési időt.  A kétféle választható külsőnek köszönhetően a gamerek bátran bemutathatják egyéni stílusukat. Válassz a ravasz és stílusos erődszürke készülékház vagy a figyelemfelkeltő, bíbor csíkos, máglyafekete dizájn között. A laptop alján lévő lágy méhsejtminta megfelelő fogást biztosít, és a készülékház körüli hatszög merevítéseket adja vissza, míg a csuklótámasz körüli szálcsiszolt vágások letisztult, stílusos kinézetet nyújtanak.',295900,0,1,'https://picsum.photos/300/300.jpg','2020-08-06 07:30:33',NULL,NULL,2,NULL),(4,'Apple MacBook Air 13 Mid 2017 MQD32 Notebook','Akár 12 órás üzemidő.\n\nA 11 hüvelykes MacBook Air akár 9 órát is bír egyetlen feltöltéssel, a 13 hüvelykes modell pedig lenyűgöző, 12 órás akkumulátor-üzemidővel rendelkezik. Vagyis a reggeli kávétól az esti hazaérkezésig tápkábel nélkül használhatod. Ha pedig a jól megérdemelt pihenést szeretnéd élvezni, a 11 hüvelykes modellen akár 10, a 13 hüvelykesen akár 12 órás mozimaratont is rendezhetsz az iTunes filmjeiből. A készenléti idő pedig akár 30 nap, tehát hetek múlva is ugyanott folytathatod, ahol abbahagytad.\n\nVékony. Könnyű. Erős.\n\nAz Intel HD Graphics 6000 grafikus processzort tartalmazó, ötödik generációs Intel Core i5 és i7 processzorok helytállnak, ha kell. A fényképek szerkesztésétől az internetezésig minden rendkívül gyorsan működik. Ezt a teljesítményt hihetetlenül vékony, mindössze 1,7 centiméter vastagságú unibody ház rejti.\n',287999,0,1,'https://picsum.photos/300/300.jpg','2020-08-06 07:30:59',NULL,NULL,2,NULL),(5,'Apple MacBook Air 13 2020 MWTK2 Notebook','Megint lehengerlő. Továbbra is légies.\nEddig is mindenki imádta, és te is újra bele fogsz zúgni. Az új, asztroszürke, ezüst- és aranyszínben kapható MacBook Air vékonyabb és könnyebb. Bámulatos Retina kijel­zője, legújabb generációs billentyűzete és Force Touch érintőpadja van. Sőt, már a Touch ID is működik rajta. Ikonikus háza 100%-ban újrahasznosított alumínium, így ez a legzöldebb gép a Mac eddigi történetében. És az akkumulátora is egész nap bírja. A MacBook Air tökéletesen hordozható laptop minden alapvető feladathoz.\nRetina kijel­ző. Négymillió pixel látványosan debütál.\nNégyszer annyi képpontja van, mint egy HD-kijel­zőnek, amitől a képek minden eddiginél részlet­gazda­gabbak és élethűbbek rajta – ha meglátod, nem fogsz hinni a szemednek.',365000,0,1,'https://picsum.photos/300/300.jpg','2020-08-06 07:31:56',NULL,NULL,2,NULL),(6,'Huawei MateBook D 15 53010XVD Notebook','Még nagyobb képernyő, csak Neked\n\nVessz el a gyönyörű 15,6\"-os IPS FullView kijelzőben. Egy lenyűgöző 87%-os kijelző-test aránnyal és és keskeny oldallal, amely mindössze 5,3 mm-re lett csökkentve, tökéletes filmek nézésre, kreatívkodásra vagy a munkád elvégzésére.\n\nKarcsú test, lenyűgöző Kijelző\n\nA hordozhatóságot szem előtt tartva, a karcsú fém testű laptop, csak 1,53 kg és 16,9 mm vastag, így bárhol élvezheted a kifogástalan FullView kijelzőt, amikor csak szeretnéd.',219990,0,0,'https://picsum.photos/300/300.jpg','2020-08-06 07:32:25',NULL,NULL,2,NULL),(7,'ASUS ROG Strix SCAR III G531GT-AL004 Notebook','hletett vonalak\nA feltűnő dizájnt a BMW Designworks csoporttal folytatott együttműködés ihlette, a gamer laptopok jövőjének megálmodására. Emelt gerincek rajzolódnak ki a harcos koncepció kopoltyúrácsaiból formázott felületet képezve, mely az extra szellőzőre tereli a figyelmet a hátsó kivezetőnyílás felett. A ROG Keystone dokkolásával egy olyan átalakulás indul el, mely átformálja rendszered megjelenését jellegzetes fizikai interakcióval, beindítva egy mélyebb összeköttetést, párhuzamosan a projekt által feltárt másik kucsfontosságú témával párosulva.\n\nLegyen ragyogó hangulatod\nJátssz a vadonatúj kibővített fénysáv ragyogásában. A készülékház karimáján körben RGB LED-eket szórtunk szét, melyek testre szabható színekkel és effektekkel világítják meg az elülső és oldalsó éleket. Szabd személyre a megvilágítást az összes kompatibilis Aura Sync tartozékodon, és állítsd be a megfelelő hangulatot a legjobb összpontosításhoz a versenyzésre.',329900,0,1,'https://picsum.photos/300/300.jpg','2020-08-06 07:32:55',NULL,NULL,2,NULL),(8,'ASUS ZenBook 13 UX333FA-A4034T Notebook','A világ legkisebb, 13 hüvelykes laptopja\n\nKreativitás. Stílus. Innováció. Ezek azok a jellemzők, melyek meghatározzák az elegáns, új ZenBook 13-at. Ebben a hordozható mesterműben —a világ legkisebb, 13 hüvelykes laptopja* — a lélegzetelállító, keret nélküli NanoEdge kijelzőt is beleértve mindent úgy terveztünk, hogy biztosítsa számodra a szabadságot kreativitásod felfedezéséhez. Nem csupán a hihetetlen belső erőt és az olyan átgondolt részleteket fogod díjazni, mint az exkluzív ErgoLift zsanér és a numerikus érintőpad, de a dizájn időtlen szépségét is érzéki királykék és jégcsapezüst kivitelben, melyet új, rózsaarany díszítőpanellel fokozunk. A ZenBook 13 rád vár: készen állsz szabadjára engedni kreatív energiáidat ?\n',234400,0,0,'https://picsum.photos/300/300.jpg','2020-08-06 07:33:18',NULL,NULL,2,NULL),(9,'Navon SlimBook 14 Notebook','Különleges stílus\n\nA NAVON Slimbook lenyűgöző formavilágával és egyedi kialakításával kiemelkedik a vetélytársai közül. Az elegáns és vékony kialakítása garantáltan ámulatba ejti! A NAVON Slimbook-ot megbízhatósága és gyorsasága teszi igazán egyedivé\n\nLendületes teljesítmény\n\nA beépített 32 GB-os eMMC SSD meghajtó, az Intel® Celeron® Apollo Lake N3350 processzor és a 2 GB DDR3 RAM teszik a munkavégzést igazán lendületessé. Böngésszen, ﬁlmezzen, vagy hallgasson zenét közben, biztos lehet benne, hogy nem ütközik teljesítménybeli korlátokba. M.2-es bővíthetősége révén csak Ön szabhat határt produktivitásának. Az okos, ventilátor nélküli passzív hűtésnek köszönhetően nem vonja magára a ﬁgyelmet, így bárhol dolgozhat, maximális teljesítmény mellet\n',59990,0,1,'https://picsum.photos/300/300.jpg','2020-08-06 07:34:21',NULL,NULL,1,NULL),(10,'ASUS VivoBook S13 S330FA-EY094 Notebook','\nLáss és csinálj többet a keret nélküli kijelzőn\n\nItt az ideje, hogy feszegesd a lehetséges határait. Az új négyoldali NanoEdge dizájn egy keret nélküli, majdnem kávamentes kijelzőt tesz lehetővé a VivoBook S13 számára hatalmas képernyő felülettel és hihetetlenül magával ragadó látvánnyal, munkához és szórakozáshoz egyaránt.\n\nHordozhatóságának köszönhetően oda mehetsz, ahova csak szeretnél\n\nAz élet gyors, és neked olyan laptopra van szükséged, mely lépést tart veled A VivoBook S13-al egyik nap egy kávézóból dolgozhatsz, másnap online kurzust tarthatsz- egy dinamikus laptop, mely biztosan nem csorbítja stílusodat sem. Az új, keret nélküli négyoldali NanoEdge kijelző segít minimálisra csökkenti a méreteket. 13 hüvelykes laptopként az S13 meglepően kompakt és pihekönnyű egy átlagos 11 hüvelykes laptopnál csak kicsit nagyobb készülékházzal, így igazán bárhol, teljesen mobilan használhatod.',149900,0,1,'https://picsum.photos/300/300.jpg','2020-08-06 07:34:43',NULL,NULL,1,NULL);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  `realname` varchar(50) NOT NULL,
  `role` varchar(5) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','eb7b02a4957ad69ae41848c047205c827e8d1aa7','Adminiszt Rátor','admin','admin@localhost.hu'),(2,'johndoe','eb7b02a4957ad69ae41848c047205c827e8d1aa7','John Doe','user','mpekar55@gmail.com');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'feladat'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-06 11:50:55
