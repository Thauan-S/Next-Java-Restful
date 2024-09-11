CREATE TABLE tb_clients (
  client_id SERIAL PRIMARY KEY,
  name varchar(255) DEFAULT NULL,
  phone varchar(255) DEFAULT NULL,
  birthday timestamp DEFAULT NULL,
  zip_code varchar(255) DEFAULT NULL,
  user_id UUID DEFAULT NULL,
  UNIQUE (user_id),
  CONSTRAINT FK_tb_clients_user_id FOREIGN KEY (user_id) REFERENCES tb_users (user_id)
);
