CREATE TABLE `tb_clients` (
  `client_id` bigint NOT NULL AUTO_INCREMENT,
  `birthday` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `zip_code` varchar(255) DEFAULT NULL,
  `user_id` binary(16) DEFAULT NULL,
  PRIMARY KEY (`client_id`),
  UNIQUE KEY `UK_gnntjpir9ci23odvw3jbrrou1` (`user_id`),
  CONSTRAINT `FKon6me4fd42ira6ev2fm308yuf` FOREIGN KEY (`user_id`) REFERENCES `tb_users` (`user_id`)
)