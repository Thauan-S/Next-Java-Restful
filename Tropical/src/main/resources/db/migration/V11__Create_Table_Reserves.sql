CREATE TABLE tb_reserves (
  reserve_id SERIAL PRIMARY KEY,
  creation_date timestamp DEFAULT NULL,
  travel_date timestamp NOT NULL,
  client_id_fk bigint DEFAULT NULL,
  travel_package_id_fk bigint DEFAULT NULL,
  CONSTRAINT FK_tb_reserves_client_id FOREIGN KEY (client_id_fk) REFERENCES tb_clients (client_id),
  CONSTRAINT FK_tb_reserves_travel_package_id FOREIGN KEY (travel_package_id_fk) REFERENCES tb_travel_packages (travel_package_id)
);
