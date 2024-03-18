CREATE TABLE `pacote_de_viagem` (
  `id_pacote` bigint NOT NULL AUTO_INCREMENT,
  `categoria` varchar(150) NOT NULL,
  `descricao` varchar(150) NOT NULL,
  `destino` varchar(45) NOT NULL,
  `duracao_dias` int NOT NULL,
  `imagem` varchar(300) NOT NULL,
  `preco` decimal(38,2) NOT NULL,
  PRIMARY KEY (`id_pacote`)
);