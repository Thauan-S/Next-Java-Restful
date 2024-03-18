CREATE TABLE `clientes` (
  `id_cliente` bigint NOT NULL AUTO_INCREMENT,
  `cep` varchar(50) NOT NULL,
  `data_nascimento` date ,
  `email` varchar(50) NOT NULL,
  `nome` varchar(50) NOT NULL ,
  `senha` varchar(255) NOT NULL,
  `telefone` varchar(50) NOT NULL,
  PRIMARY KEY (`id_cliente`),
  UNIQUE KEY `UK_1c96wv36rk2hwui7qhjks3mvg` (`email`)
) ;

