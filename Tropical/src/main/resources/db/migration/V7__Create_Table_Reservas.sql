CREATE TABLE `reservas` (
  `id_reserva` bigint NOT NULL AUTO_INCREMENT,
  `data_reserva` datetime(6) NOT NULL,
  `data_viagem` datetime(6) NOT NULL,
  `cliente_id_fk` bigint NOT NULL,
  `pacote_id_fk` bigint NOT NULL,
  PRIMARY KEY (`id_reserva`),
  KEY `FK2db239p9dwxxdilhdmy871el5` (`cliente_id_fk`),
  KEY `FKpfckmpqxoygg25w8fvfjyph6y` (`pacote_id_fk`),
  CONSTRAINT `FK2db239p9dwxxdilhdmy871el5` FOREIGN KEY (`cliente_id_fk`) REFERENCES `clientes` (`id_cliente`),
  CONSTRAINT `FKpfckmpqxoygg25w8fvfjyph6y` FOREIGN KEY (`pacote_id_fk`) REFERENCES `pacote_de_viagem` (`id_pacote`)
) 