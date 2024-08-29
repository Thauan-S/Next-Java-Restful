CREATE TABLE `tb_reserves` (
  `reserve_id` bigint NOT NULL AUTO_INCREMENT,
  `creation_date` datetime(6) DEFAULT NULL,
  `travel_date` datetime(6) NOT NULL,
  `client_id_fk` bigint DEFAULT NULL,
  `travel_package_id_fk` bigint DEFAULT NULL,
  PRIMARY KEY (`reserve_id`),
  KEY `FKlqw5l9l8cjba1c15ricgb6gwt` (`client_id_fk`),
  KEY `FKt09n74n6m8dk5ijhxxgdqy049` (`travel_package_id_fk`),
  CONSTRAINT `FKlqw5l9l8cjba1c15ricgb6gwt` FOREIGN KEY (`client_id_fk`) REFERENCES `tb_clients` (`client_id`),
  CONSTRAINT `FKt09n74n6m8dk5ijhxxgdqy049` FOREIGN KEY (`travel_package_id_fk`) REFERENCES `tb_travel_packages` (`travel_package_id`)
)