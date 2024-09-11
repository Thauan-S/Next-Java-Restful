CREATE TABLE tb_companies (
  enterprise_id SERIAL PRIMARY KEY,
  name varchar(255) DEFAULT NULL,
  cnpj varchar(14) DEFAULT NULL,
  address varchar(255) DEFAULT NULL,
  user_id UUID DEFAULT NULL,
  UNIQUE (cnpj),
  UNIQUE (user_id),
  CONSTRAINT FK_tb_companies_user_id FOREIGN KEY (user_id) REFERENCES tb_users (user_id)
);
