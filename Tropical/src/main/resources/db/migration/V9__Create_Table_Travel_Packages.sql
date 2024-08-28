CREATE TABLE `tb_travel_packages` (
  `travel_package_id` bigint NOT NULL AUTO_INCREMENT,
  `destiny` varchar(45) NOT NULL,
  `description` varchar(150) NOT NULL,
  `category` varchar(150) NOT NULL,
  `days` int NOT NULL,
  `image` varchar(255) NOT NULL,
  `price` decimal(38,2) NOT NULL,
  `enterprise_id` bigint NOT NULL,
  PRIMARY KEY (`travel_package_id`),
  KEY `FK1kjbenby5nybr1qj88d0j62em` (`enterprise_id`),
  CONSTRAINT `FK1kjbenby5nybr1qj88d0j62em` FOREIGN KEY (`enterprise_id`) REFERENCES `tb_companies` (`enterprise_id`)
)