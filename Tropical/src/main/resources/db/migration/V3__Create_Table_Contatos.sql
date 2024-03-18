CREATE TABLE `contatos` (
  `id_contato` bigint NOT NULL AUTO_INCREMENT,
  `assunto` varchar(20) NOT NULL,
  `mensagem` varchar(255) NOT NULL,
  `cliente_id_fk` bigint NOT NULL,
  PRIMARY KEY (`id_contato`),
  KEY `FK68b3b0kdwtcq9uct93ugafr9j` (`cliente_id_fk`),
  CONSTRAINT `FK68b3b0kdwtcq9uct93ugafr9j` FOREIGN KEY (`cliente_id_fk`) REFERENCES `clientes` (`id_cliente`)
);