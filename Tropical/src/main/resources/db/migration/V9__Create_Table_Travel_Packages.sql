CREATE TABLE tb_travel_packages (
  travel_package_id SERIAL PRIMARY KEY,
  destiny varchar(45) NOT NULL,
  description varchar(150) NOT NULL,
  category varchar(150) NOT NULL,
  days int NOT NULL,
  image varchar(255) NOT NULL,
  price decimal(38,2) NOT NULL,
  enterprise_id bigint NOT NULL,
  CONSTRAINT FK_tb_travel_packages_enterprise_id FOREIGN KEY (enterprise_id) REFERENCES tb_companies (enterprise_id)
);
