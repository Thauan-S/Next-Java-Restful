CREATE TABLE `tb_clientes` (
  `cliente_id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `telefone` varchar(255) DEFAULT NULL,
  `data_nascimento` datetime(6) DEFAULT NULL,
  `cep` varchar(255) DEFAULT NULL,
  `user_id` binary(16) DEFAULT NULL,
  PRIMARY KEY (`cliente_id`),
  UNIQUE KEY `UK_c2jw6oq3uk87hps6i2xqribpt` (`user_id`),
  CONSTRAINT `FKs67ols8v7u1w4pexh3lum1lam` FOREIGN KEY (`user_id`) REFERENCES `tb_users` (`user_id`)
);