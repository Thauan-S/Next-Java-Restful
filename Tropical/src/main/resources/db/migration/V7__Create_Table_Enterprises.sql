CREATE TABLE `tb_companies` (
  `enterprise_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `cnpj` varchar(14) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `user_id` binary(16) DEFAULT NULL,
  PRIMARY KEY (`enterprise_id`),
  UNIQUE KEY `UK_ryrbo6e8at6vw7mhwfxro18w6` (`cnpj`),
  UNIQUE KEY `UK_7mc8v3luwb6khk4t82mqfv6jt` (`user_id`),
  CONSTRAINT `FKakoycsbh9479si3wqaawcfkd1` FOREIGN KEY (`user_id`) REFERENCES `tb_users` (`user_id`)
)